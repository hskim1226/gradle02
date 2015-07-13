package com.apexsoft.ysprj.sysadmin.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.exception.YSBizException;
import com.apexsoft.framework.exception.YSBizNoticeException;
import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.ysprj.applicants.application.domain.Application;
import com.apexsoft.ysprj.applicants.common.service.BirtService;
import com.apexsoft.ysprj.applicants.common.service.PDFService;
import com.apexsoft.ysprj.applicants.common.util.FileUtil;
import com.apexsoft.ysprj.applicants.common.util.StringUtil;
import com.apexsoft.ysprj.sysadmin.domain.BackUpApplDoc;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by hanmomhanda on 15. 4. 16.
 */
@Service
public class SysAdminServiceImpl implements  SysAdminService {

    private final static String NAME_SPACE = "com.apexsoft.ysprj.sysadmin.sqlmap.";

    @Autowired
    private BirtService birtService;

    @Autowired
    private PDFService pdfService;

    @Autowired
    private CommonDAO commonDAO;

    @Value("#{app['s3.bucketName']}")
    private String s3BucketName;

    @Value("#{app['s3.midPath']}")
    private String s3MidPath;

    @Value("#{app['file.backupDir']}")
    private String backupDir;

    private static final Logger logger = LoggerFactory.getLogger(SysAdminServiceImpl.class);

    /**
     * Batch로 최종 PDF 파일 다건을 생성한다.
     * 다건 처리를 위해 예외 발생 시 던지지 않고
     * map에 담아 반환
     *
     * @param application
     * @return
     */
    public ExecutionContext processReGenMergeUpload(Application application) {
        ExecutionContext ecResult = new ExecutionContext();
        Map<String, Object> map = new HashMap<String, Object>();

        String admsTypeCode = application.getAdmsTypeCode();
        int applNo = application.getApplNo();
        String lang = "C".equals(admsTypeCode) || "D".equals(admsTypeCode) ? "en" : "kr";
        String reportName = "yonsei-appl-" + lang;

        ExecutionContext ecGenAppl = null;

        try {
            ecGenAppl = birtService.generateBirtFile(applNo, reportName);
        } catch (Exception e) {
            ecGenAppl = new ExecutionContext(ExecutionContext.FAIL);
        } finally {
            map.put("genAppl", ecGenAppl.getResult());
        }

        reportName = "yonsei-adms-" + lang;
        ExecutionContext ecGenAdms = null;
        try {
            ecGenAdms = birtService.generateBirtFile(applNo, reportName);
        } catch (Exception e) {
            ecGenAdms = new ExecutionContext(ExecutionContext.FAIL);
        } finally {
            map.put("genAdms", ecGenAdms.getResult());
        }

        ExecutionContext ecPdfMerge = null;
        try {
            ecPdfMerge = pdfService.genAndUploadPDFByApplicants(application);
        } catch (Exception e) {
            ecPdfMerge = new ExecutionContext(ExecutionContext.FAIL);
            if (e instanceof YSBizNoticeException) {
                YSBizNoticeException ybne = (YSBizNoticeException)e;
                ExecutionContext ec = ybne.getExecutionContext();
                List<Application> encryptedPdfList = (List<Application>)ec.getData();
                map.put("encryptedPdfList", encryptedPdfList);
            }
        } finally {
            map.put("mergePdf", ecPdfMerge.getResult());
        }

        ecResult.setData(map);

        return ecResult;
    }

    public ExecutionContext downloadAllPdf() {

        ExecutionContext ec = new ExecutionContext();
        List<BackUpApplDoc> backUpApplDocList = null;
        AmazonS3Client s3 = new AmazonS3Client();

        try {
            backUpApplDocList = commonDAO.queryForList(NAME_SPACE + "SysAdminMapper.selectAllPdfInfo", BackUpApplDoc.class);
        } catch (YSBizException e) {
            e.printStackTrace();
        }

        Map<String, String> resultMap = savePdf(s3, backUpApplDocList);

        ec.setResult(ExecutionContext.SUCCESS);
        ec.setData(resultMap);

        return ec;
    }

    private Map<String, String> savePdf(AmazonS3Client s3Client, List<BackUpApplDoc> backUpApplDocList) {

        int downloadedCount = 0;
        long start = System.currentTimeMillis();
        long totalVolume = 0;

//        BackUpApplDoc backUpApplDoc = backUpApplDocList.get(0);
//        for (BackUpApplDoc backUpApplDoc : backUpApplDocList) {
//
//            S3Object object = null;
//            Application appl = new Application();
//            appl.setApplNo(backUpApplDoc.getApplNo());
//            appl.setUserId(backUpApplDoc.getUserId());
//            appl.setAdmsNo(backUpApplDoc.getAdmsNo());
//            String fullPath = FileUtil.getFinalMergedFileFullPath(s3BucketName, s3MidPath, appl);
//            String filePath = fullPath.substring(s3BucketName.length() + 1);
//            String applicantName = StringUtil.getEmptyIfNull(backUpApplDoc.getKorName()).equals(StringUtil.EMPTY_STRING) ?
//                    backUpApplDoc.getEngName() + "-" + backUpApplDoc.getEngSur() :
//                    backUpApplDoc.getKorName();
//            String targetFilePath = new StringBuilder().append(s3MidPath).append("/")
//                    .append(backUpApplDoc.getCampName()).append("/")
//                    .append(backUpApplDoc.getCollName()).append("/")
//                    .append(backUpApplDoc.getDeptName()).append("/")
//                    .append(backUpApplDoc.getApplId()).append("_").append(applicantName).append(".pdf")
//                    .toString();
//            try {
//                object = s3Client.getObject(new GetObjectRequest(s3BucketName, filePath));
//                InputStream inputStream = object.getObjectContent();
//                FileUtils.copyInputStreamToFile(inputStream, new File(backupDir, targetFilePath));
//            } catch (Exception e) {
//                ExecutionContext ec = new ExecutionContext(ExecutionContext.FAIL);
//                logger.error("Err in s3Client.getObject in SysAdminServiceImpl.savePdf");
//                logger.error(e.getMessage());
//                logger.error("bucketName : [" + s3BucketName + "]");
//                logger.error("applNo : [" + appl.getApplNo() + "]");
//                logger.error("objectKey : [" + filePath +"]");
//                throw new YSBizException(ec);
//            }
//            downloadedCount++;
//            totalVolume += object.getObjectMetadata().getContentLength();
//            System.out.println(downloadedCount + "/" + backUpApplDocList.size() + ", totalVolume - " + totalVolume + " : " + targetFilePath);
//        }



        BlockingQueue<BackUpApplDoc> applInfoQue = new ArrayBlockingQueue<BackUpApplDoc>(1024);
        BlockingQueue<S3Object> s3ObjQue = new ArrayBlockingQueue<S3Object>(100);

System.out.println("job started : " + System.currentTimeMillis());
        ApplInfoProducer applInfoProducer = new ApplInfoProducer(applInfoQue, backUpApplDocList);
        new Thread(applInfoProducer).start();

        ApplInfoConsumer applInfoConsumer = new ApplInfoConsumer(applInfoQue, s3ObjQue, s3Client, backUpApplDocList.size());
        for ( int i = 0 ; i < 100 ; i++ ) {
            new Thread(applInfoConsumer).start();
        }

        S3ObjConsumer s3ObjConsumer = new S3ObjConsumer(s3ObjQue, backUpApplDocList.size());
        for ( int i = 0 ; i < 10 ; i++ ) {
            new Thread(s3ObjConsumer).start();
        }


        long end = System.currentTimeMillis();
        System.out.println("Backup elapsed time" + (end - start) / 1000 + " seconds");

        Map<String, String> resultMap = new HashMap<String, String>();
        resultMap.put("requestedCount", String.valueOf(backUpApplDocList.size()));
        resultMap.put("downloadedCount", String.valueOf(downloadedCount));
        resultMap.put("elpasedTime", (end - start) / 1000 + " seconds");
        return resultMap;
    }

    private class ApplInfoProducer implements Runnable {

        private final BlockingQueue<BackUpApplDoc> applInfoQue;
        private List<BackUpApplDoc> backUpApplDocList;

        ApplInfoProducer(BlockingQueue<BackUpApplDoc> applInfoQue, List<BackUpApplDoc> backUpApplDocList) {
            this.applInfoQue = applInfoQue;
            this.backUpApplDocList = backUpApplDocList;
        }

        @Override
        public void run() {
            for (BackUpApplDoc backUpApplDoc : backUpApplDocList) {
                try {
                    applInfoQue.put(backUpApplDoc);
                } catch ( InterruptedException e ) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class ApplInfoConsumer implements Runnable {
        private AtomicInteger count = new AtomicInteger();
        private AtomicLong totalVolume = new AtomicLong();

        private final BlockingQueue<BackUpApplDoc> applInfoQue;
        private final BlockingQueue<S3Object> s3ObjQue;
        private final AmazonS3Client s3Client;
        private int fileCount;

        public ApplInfoConsumer( BlockingQueue<BackUpApplDoc> applInfoQue,
                                 BlockingQueue<S3Object> s3ObjQue,
                                 AmazonS3Client s3Client,
                                 int fileCount) {
            this.applInfoQue = applInfoQue;
            this.s3ObjQue = s3ObjQue;
            this.s3Client = s3Client;
            this.fileCount = fileCount;
        }

        @Override
        public void run() {
            try {
                while(true) {
                    BackUpApplDoc backUpApplDoc = applInfoQue.poll(5, TimeUnit.SECONDS);

                    if (backUpApplDoc != null) {
                        S3Object s3Object = null;
                        Application appl = new Application();
                        appl.setApplNo(backUpApplDoc.getApplNo());
                        appl.setUserId(backUpApplDoc.getUserId());
                        appl.setAdmsNo(backUpApplDoc.getAdmsNo());
                        String fullPath = FileUtil.getFinalMergedFileFullPath(s3BucketName, s3MidPath, appl);
                        String filePath = fullPath.substring(s3BucketName.length() + 1);
                        String applicantName = StringUtil.getEmptyIfNull(backUpApplDoc.getKorName()).equals(StringUtil.EMPTY_STRING) ?
                                backUpApplDoc.getEngName() + "-" + backUpApplDoc.getEngSur() :
                                backUpApplDoc.getKorName();
                        String targetFilePath = new StringBuilder().append(s3MidPath).append("/")
                                .append(backUpApplDoc.getCampName()).append("/")
                                .append(backUpApplDoc.getCollName()).append("/")
                                .append(backUpApplDoc.getDeptName()).append("/")
                                .append(backUpApplDoc.getApplId()).append("_").append(applicantName).append(".pdf")
                                .toString();
                        try {
                            s3Object = s3Client.getObject(new GetObjectRequest(s3BucketName, filePath));
                            ObjectMetadata objMeta = s3Object.getObjectMetadata();
                            objMeta.addUserMetadata("applNo", String.valueOf(backUpApplDoc.getApplNo()));
                            objMeta.addUserMetadata("filePath", filePath);
                            objMeta.addUserMetadata("targetFilePath", targetFilePath);
                            s3ObjQue.put(s3Object);
                            System.out.println("[DOWNLOAD] " + count.incrementAndGet() + "/" + fileCount + ", totalVolume - " + totalVolume.addAndGet(objMeta.getContentLength()) + " : " + targetFilePath);
                        } catch (Exception e) {
                            ExecutionContext ec = new ExecutionContext(ExecutionContext.FAIL);
                            logger.error("Err in ApplInfoConsumer.run in SysAdminServiceImpl");
                            logger.error(e.getMessage());
                            logger.error("bucketName : [" + s3BucketName + "]");
                            logger.error("applNo : [" + appl.getApplNo() + "]");
                            logger.error("objectKey : [" + filePath +"]");
                            throw new YSBizException(ec);
                        }
                    } else if (applInfoQue.peek() == null && count.intValue() == fileCount) {
                        return;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private class S3ObjConsumer implements Runnable {
        private AtomicInteger count = new AtomicInteger();
        private AtomicLong totalVolume = new AtomicLong();

        private final BlockingQueue<S3Object> s3ObjQue;
        private final int fileCount;

        public S3ObjConsumer( BlockingQueue<S3Object> s3ObjQue, int fileCount ) {
            this.s3ObjQue = s3ObjQue;
            this.fileCount = fileCount;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    S3Object s3Object = s3ObjQue.poll(5, TimeUnit.SECONDS);

                    if (s3Object != null) {
                        InputStream inputStream = s3Object.getObjectContent();
                        ObjectMetadata s3ObjMeta = s3Object.getObjectMetadata();
                        Map<String, String> s3ObjUserMeta = s3ObjMeta.getUserMetadata();
                        try {
                            FileUtils.copyInputStreamToFile(inputStream, new File(backupDir, s3ObjUserMeta.get("targetFilePath")));
                            System.out.println("[LOCAL SAVE] " + count.incrementAndGet() + "/" + fileCount + ", totalVolume - " + totalVolume.addAndGet(s3ObjMeta.getContentLength()) + " : " + s3ObjUserMeta.get("targetFilePath"));
                        } catch (Exception e) {
                            ExecutionContext ec = new ExecutionContext(ExecutionContext.FAIL);
                            logger.error("Err in S3ObjConsumer.run in SysAdminServiceImpl");
                            logger.error(e.getMessage());
                            logger.error("bucketName : [" + s3BucketName + "]");
                            logger.error("applNo : [" + s3ObjUserMeta.get("applNo") + "]");
                            logger.error("objectKey : [" + s3ObjUserMeta.get("filePath") +"]");
                            throw new YSBizException(ec);
                        }
                    } else if (s3ObjQue.peek() == null && count.intValue() == fileCount) {
System.out.println("job completed : " + (System.currentTimeMillis() - 5000));
                        return;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

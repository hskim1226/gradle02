package com.apexsoft.ysprj.sysadmin.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.exception.YSBizException;
import com.apexsoft.framework.exception.YSBizNoticeException;
import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.ysprj.applicants.application.domain.Application;
import com.apexsoft.ysprj.applicants.common.domain.FileMeta;
import com.apexsoft.ysprj.applicants.common.domain.FileWrapper;
import com.apexsoft.ysprj.applicants.common.service.BirtService;
import com.apexsoft.ysprj.applicants.common.service.FilePersistenceService;
import com.apexsoft.ysprj.applicants.common.service.PDFService;
import com.apexsoft.ysprj.sysadmin.domain.*;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

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
    private FilePersistenceService filePersistenceService;

    @Autowired
    private CommonDAO commonDAO;

    @Value("#{app['file.baseDir']}")
    private String fileBaseDir;

    @Value("#{app['file.midPath']}")
    private String midPath;

    @Value("#{app['file.backupDir']}")
    private String backupDir;

    @Value("#{app['file.picturesDir']}")
    private String picturesDir;



    private static final Logger logger = LoggerFactory.getLogger(SysAdminServiceImpl.class);

    /**
     * Batch로 최종 PDF 파일 다건을 생성하는 loop 내에서 호출
     * 다건 처리를 위해 예외 발생 시 던지지 않고
     * map에 담아 반환
     *
     * @param application
     * @return
     */
    public ExecutionContext processReGenMergeUpload(Application application) {
        ExecutionContext ecResult = new ExecutionContext();
        Map<String, Object> map = new HashMap<String, Object>();

        int applNo = application.getApplNo();
        String lang = application.isForeignAppl() ? "en" : "kr";
        String reportName = "yonsei-appl-" + lang;

        ExecutionContext ecGenAppl = null;

        try {
            ecGenAppl = birtService.generateBirtFile(application, reportName);
        } catch (Exception e) {
            ecGenAppl = new ExecutionContext(ExecutionContext.FAIL);
        } finally {
            map.put("genAppl", ecGenAppl.getResult());
        }

        reportName = "yonsei-adms-" + lang;
        ExecutionContext ecGenAdms = null;
        try {
            ecGenAdms = birtService.generateBirtFile(application, reportName);
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

    @Override
    public ExecutionContext downloadAllPdf() {

        ExecutionContext ec = new ExecutionContext();
        List<BackUpApplDoc> backUpApplDocList = null;

        try {
            backUpApplDocList = commonDAO.queryForList(NAME_SPACE + "SysAdminMapper.selectAllPdfInfo", BackUpApplDoc.class);
        } catch (YSBizException e) {
            e.printStackTrace();
        }

        AbstractS3Consumer s3Consumer = new ApplAllConsumer(midPath, backUpApplDocList.size(), backupDir);
        Map<String, String> resultMap = savePdf(s3Consumer, backUpApplDocList);

        ec.setResult(ExecutionContext.SUCCESS);
        ec.setData(resultMap);

        return ec;
    }

    @Override
    public ExecutionContext downloadAllSlipPdf() {

        ExecutionContext ec = new ExecutionContext();
        List<BackUpApplDoc> backUpApplDocList = null;

        try {
            backUpApplDocList = commonDAO.queryForList(NAME_SPACE + "SysAdminMapper.selectAllSlipPdfInfo", BackUpApplDoc.class);
        } catch (YSBizException e) {
            e.printStackTrace();
        }

        AbstractS3Consumer s3Consumer = new ApplSlipConsumer(midPath, backUpApplDocList.size(), backupDir);
        s3Consumer.setBaseDir(fileBaseDir);
//        s3Consumer.setMidPath(midPath);
        Map<String, String> resultMap = savePdf(s3Consumer, backUpApplDocList);

        ec.setResult(ExecutionContext.SUCCESS);
        ec.setData(resultMap);

        return ec;
    }


    private Map<String, String> savePdf(AbstractS3Consumer s3Consumer, List<BackUpApplDoc> backUpApplDocList) {

        int downloadedCount = 0;
        long start = System.currentTimeMillis();
        long totalVolume = 0;

        BlockingQueue<BackUpApplDoc> applInfoQue = new ArrayBlockingQueue<BackUpApplDoc>(1024);
//        BlockingQueue<S3Object> s3ObjQue = new ArrayBlockingQueue<S3Object>(300);

        System.out.println("job started : " + System.currentTimeMillis());
        ApplInfoProducer applInfoProducer = new ApplInfoProducer(applInfoQue, backUpApplDocList);
        new Thread(applInfoProducer).start();

//        ApplInfoConsumer applInfoConsumer = new ApplInfoConsumer(applInfoQue, s3ObjQue, s3Client, backUpApplDocList.size());
//        for ( int i = 0 ; i < 100 ; i++ ) {
//            new Thread(applInfoConsumer).start();
//        }
        s3Consumer.setApplInfoQue(applInfoQue);
//        s3Consumer.setS3ObjQue(s3ObjQue);
        for ( int i = 0 ; i < 8 ; i++ ) {
            new Thread(s3Consumer).start();
        }

//        S3ObjConsumer s3ObjConsumer = new S3ObjConsumer(s3ObjQue, backUpApplDocList.size(), backupDir, s3BucketName);
//        for ( int i = 0 ; i < 2 ; i++ ) {
//            new Thread(s3ObjConsumer).start();
//        }


//        ExecutorService es = Executors.newCachedThreadPool();
//        for ( int i = 0 ; i < 10 ; i++ ) {
//            es.execute(new Thread(s3ObjConsumer));
//        }
//        es.shutdown();
//        boolean finished = es.awaitTermination(1, TimeUnit.DAYS);


        long end = System.currentTimeMillis();
        System.out.println("Backup elapsed time : " + (end - start) / 1000 + " seconds");

        Map<String, String> resultMap = new HashMap<String, String>();
        resultMap.put("requestedCount", String.valueOf(backUpApplDocList.size()));
        resultMap.put("downloadedCount", String.valueOf(downloadedCount));
        resultMap.put("elpasedTime", (end - start) / 1000 + " seconds");
        return resultMap;
    }

    @Override
    public ExecutionContext<Map<String, Object>> downaloadRenamedPictures() {
        ExecutionContext<Map<String, Object>> ec = new ExecutionContext();
        Map<String, Object> resultMap = new HashMap<>();
        List<StudentNumber> studentNumberList = null;
        FileWrapper fileWrapper = null;
        List<String> failureList = new ArrayList<>();
        int count = 0;

        studentNumberList = commonDAO.queryForList(NAME_SPACE + "SysAdminMapper.selectStudentPicInfo", StudentNumber.class);

        String targetDirPath = picturesDir + "/" + midPath;
long start = System.currentTimeMillis();

        for (StudentNumber studentNumber : studentNumberList) {
            InputStream inputStream = null;
            try {
                fileWrapper = filePersistenceService.getFileWrapperFromFileRepo(studentNumber.getFilePath());
                inputStream = fileWrapper.getInputStream();
                FileMeta fileMeta = fileWrapper.getFileMeta();
                String type = fileMeta.getContentType();
                if (type.startsWith("image/")) {
                    String ext = type.substring(6);
                    if ("jpeg".equals(ext))
                        ext = "jpg";
                        File targetFile = new File(targetDirPath, studentNumber.getStudNo() + "-" + studentNumber.getStudName() + "." + ext);
                        FileUtils.copyInputStreamToFile(inputStream, targetFile);
                        System.out.println("[LOCAL SAVE] " + ++count);

                }
            } catch (Exception e) {
                ec = new ExecutionContext(ExecutionContext.FAIL);
                logger.error("Err in downaloadRenamedPictures() in SysAdminServiceImpl");
                logger.error(e.getMessage());
                logger.error("applId : [" + studentNumber.getApplId() + "]");
                logger.error("filePath : [" + studentNumber.getFilePath() +"]");
                failureList.add(studentNumber.getApplId());
            } finally {
                if (inputStream != null) try { inputStream.close(); } catch (IOException e) {}
            }
        }

        resultMap.put("totalCount", String.valueOf(studentNumberList.size()));
        resultMap.put("successCount", String.valueOf(studentNumberList.size() - failureList.size()));
        resultMap.put("failureCount", failureList.size());
        resultMap.put("failureList", failureList);
        ec.setData(resultMap);
System.out.println("Total Elapsed Time : " + (System.currentTimeMillis() - start)/1000);

        return ec;
    }




}

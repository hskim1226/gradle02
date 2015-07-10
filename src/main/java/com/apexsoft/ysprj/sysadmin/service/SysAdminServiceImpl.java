package com.apexsoft.ysprj.sysadmin.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.exception.ErrorInfo;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            ecPdfMerge = pdfService.getMergedPDFByApplicants(applNo);
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

    private Map<String, String> savePdf(AmazonS3Client s3, List<BackUpApplDoc> backUpApplDocList) {

        int downloadedCount = 0;
        long start = System.currentTimeMillis();
        long totalVolume = 0;
        for (BackUpApplDoc backUpApplDoc : backUpApplDocList) {
            S3Object object = null;
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
                object = s3.getObject(new GetObjectRequest(s3BucketName, filePath));
                InputStream inputStream = object.getObjectContent();
                FileUtils.copyInputStreamToFile(inputStream, new File(backupDir, targetFilePath));
            } catch (Exception e) {
                ExecutionContext ec = new ExecutionContext(ExecutionContext.FAIL);
                logger.error("Err in s3.getObject in SysAdminServiceImpl.savePdf");
                logger.error(e.getMessage());
                logger.error("bucketName : [" + s3BucketName + "]");
                logger.error("applNo : [" + appl.getApplNo() + "]");
                logger.error("objectKey : [" + filePath +"]");
                throw new YSBizException(ec);
            }
            downloadedCount++;
            totalVolume += object.getObjectMetadata().getContentLength();
            System.out.println(downloadedCount + "/" + backUpApplDocList.size() + ", totalVolume - " + totalVolume + " : " + targetFilePath);
        }

        long end = System.currentTimeMillis();
        System.out.println("Backup elapsed time" + (end - start) / 1000 + " seconds");

        Map<String, String> resultMap = new HashMap<String, String>();
        resultMap.put("requestedCount", String.valueOf(backUpApplDocList.size()));
        resultMap.put("downloadedCount", String.valueOf(downloadedCount));
        resultMap.put("elpasedTime", (end - start) / 1000 + " seconds");
        return resultMap;
    }
}

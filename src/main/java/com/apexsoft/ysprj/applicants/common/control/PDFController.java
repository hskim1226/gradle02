package com.apexsoft.ysprj.applicants.common.control;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.exception.ErrorInfo;
import com.apexsoft.framework.exception.YSBizException;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.ysprj.applicants.application.domain.Application;
import com.apexsoft.ysprj.applicants.application.domain.ApplicationDocument;
import com.apexsoft.ysprj.applicants.application.domain.Basis;
import com.apexsoft.ysprj.applicants.application.service.DocumentService;
import com.apexsoft.ysprj.applicants.common.domain.BirtRequest;
import com.apexsoft.ysprj.applicants.common.service.PDFService;
import com.apexsoft.ysprj.applicants.common.util.FilePathUtil;
import com.apexsoft.ysprj.applicants.common.util.WebUtil;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hanmomhanda on 15. 2. 22.
 */
@Controller
@RequestMapping(value="/pdf")
public class PDFController {

    @Autowired
    PDFService pdfService;

    @Autowired
    DocumentService documentService;

    @Autowired
    WebUtil webUtil;

    @Value("#{app['file.baseDir']}")
    private String fileBaseDir;

    @Value("#{app['s3.bucketName']}")
    private String s3BucketName;

    @Value("#{app['s3.midPath']}")
    private String s3MidPath;

    private static final Logger logger = LoggerFactory.getLogger(PDFController.class);

    /**
     * 사용자가 S3로 업로드 한 pdf 파일을 로컬에 다운받아서 하나로 합치고(B파일)
     * #/# 형식의 쪽 번호를 우상단에 추가하여 로컬에 PDF로 저장(C파일)
     * A파일과 C파일을 합친 PDF 파일을 로컬에 저장(D파일)
     * D파일을 S3에 업로드(v03 - 원서 미리보기 시 서버 로컬에서의 다운로드 부하를 S3로 이전)
     * 중간 작업 파일인 A파일, B파일, C파일 삭제
     *
     * @param birtRequest
     * @param request
     * @return
     */
    @RequestMapping(value="/generate/tempMergedApplicationForm")
    @ResponseBody
    public String generateTempMergedApplicationForm(BirtRequest birtRequest, Principal principal, HttpServletRequest request) {

        Application application = birtRequest.getApplication();
//        application.setUserId(principal.getName());
        ExecutionContext ec = pdfService.genAndUploadPDFByApplicants(application);
        if (ExecutionContext.SUCCESS.equals(ec.getResult())) {
            return ExecutionContext.SUCCESS;
        } else {
            return ExecutionContext.FAIL;
        }
    }

    /**
     * DB에 저장된 원서 정보를 토대로 S3에서 원서+첨부파일 PDF 파일 다운로드
     *
     * @param basis
     * @param principal
     * @param response
     * @return
     * @throws java.io.IOException
     */
    @RequestMapping(value="/download/tempMergedApplicationForm", produces = "application/pdf")
    @ResponseBody
    public byte[] fileDownload(Basis basis,
                               Principal principal,
                               HttpServletRequest request,
                               HttpServletResponse response) {
        webUtil.blockGetMethod(request, basis.getApplication());
        String userId = principal.getName();
        Application application = basis.getApplication();
        String admsNo = application.getAdmsNo();
        int applNo = application.getApplNo();
        byte[] bytes = null;

        List<ApplicationDocument> applPaperInfosList =
                documentService.retrieveApplicationPaperInfo(applNo); // DB에서 filePath가져온다
        if (applPaperInfosList.size() == 1) {
            String uploadDirPath = applPaperInfosList.get(0).getFilePath();
            String s3UploadDirPath = FilePathUtil.getS3PathFromLocalFullPath(uploadDirPath, fileBaseDir);
            AmazonS3 s3 = new AmazonS3Client();
            S3Object object = null;
            try {
                object = s3.getObject(new GetObjectRequest(s3BucketName, FilePathUtil.getFinalMergedFileFullPath(s3UploadDirPath, applNo)));
            } catch (Exception e) {
                logger.error("Err in s3.getObject FiledDownload in PDFController");
                logger.error(e.getMessage());
                ExecutionContext ec = new ExecutionContext(ExecutionContext.FAIL);
                ec.setMessage(MessageResolver.getMessage("U00242"));
                Map<String, Object> ecMap = new HashMap<String, Object>();
                ecMap.put("bucketName", "[" + s3BucketName + "]");
                ecMap.put("admsNo", "[" + admsNo + "]");
                ecMap.put("userId", "[" + userId + "]");
                ecMap.put("objectKey", "[" + FilePathUtil.getFinalMergedFileFullPath(s3UploadDirPath, applNo) + "]");
                ec.setErrorInfo(new ErrorInfo(ecMap));
                throw new YSBizException(ec);
            }

            InputStream inputStream = object.getObjectContent();
            ObjectMetadata meta = object.getObjectMetadata();
            try {
                bytes = IOUtils.toByteArray(inputStream);
            } catch (IOException e) {
                ExecutionContext ecError = new ExecutionContext(ExecutionContext.FAIL);

                ecError.setMessage(MessageResolver.getMessage("U350"));
                ecError.setErrCode("ERR0062");

                Map<String, String> errorInfo = new HashMap<String, String>();
                errorInfo.put("applNo", String.valueOf(applNo));
                errorInfo.put("userId", basis.getApplication().getUserId());

                ecError.setErrorInfo(new ErrorInfo(errorInfo));
                throw new YSBizException(ecError);
            }

            response.setContentType(meta.getContentType());
            response.setHeader("Content-Length", String.valueOf(meta.getContentLength()));
            response.setHeader("Content-Encoding", meta.getContentEncoding());
            response.setHeader("ETag", meta.getETag());
            response.setHeader("Last-Modified", meta.getLastModified().toString());
//            아래 헤더 추가하면 파일명은 지정할 수 있으나 미리 보기는 안되고 다운로드만 됨
            try {
                response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(FilePathUtil.getFinalUserDownloadFileName(userId), "UTF-8") + "\"");
            } catch (UnsupportedEncodingException e) {
                throw new YSBizException(MessageResolver.getMessage("U04516"));  /*지원하지 않는 인코딩 방식입니다.*/
            }

        } else {
            ExecutionContext ecError = new ExecutionContext(ExecutionContext.FAIL);

            ecError.setMessage(MessageResolver.getMessage("U349"));
            ecError.setErrCode("ERR0061");

            Map<String, String> errorInfo = new HashMap<String, String>();
            errorInfo.put("applNo", String.valueOf(applNo));
            errorInfo.put("userId", basis.getApplication().getUserId());

            ecError.setErrorInfo(new ErrorInfo(errorInfo));
            throw new YSBizException(ecError);
        }

        return bytes;
    }

    /**
     * DB에 저장된 원서 정보를 토대로 S3에서 원서 PDF 파일 다운로드
     *
     * @param basis
     * @param principal
     * @param response
     * @return
     * @throws java.io.IOException
     */
    @RequestMapping(value="/download/applForm", produces = "application/pdf")
    @ResponseBody
    public byte[] downLoadZip(Basis basis,
                               Principal principal,
                               HttpServletRequest request,
                               HttpServletResponse response) {
        webUtil.blockGetMethod(request, basis.getApplication());
        Application application = basis.getApplication();
        String dirFullPath = FilePathUtil.getUploadDirectoryFullPath(fileBaseDir, s3MidPath, application.getAdmsNo(), application.getUserId(), application.getApplNo());
        String s3DirPath = FilePathUtil.getS3PathFromLocalFullPath(dirFullPath, fileBaseDir);
        String fileName = FilePathUtil.getApplicationFormFileName(application.getUserId());
        byte[] bytes = downLoadFile(application, response, s3DirPath+"/"+fileName);
        // 아래 헤더 추가하면 파일명은 지정할 수 있으나 미리 보기는 안되고 다운로드만 됨
        try {
            response.setHeader("Content-Disposition", "attachment; filename=\"" +
                URLEncoder.encode(FilePathUtil.getApplicationFormFileName(application.getUserId()), "UTF-8") + "\"");
        } catch (UnsupportedEncodingException e) {
            throw new YSBizException(MessageResolver.getMessage("U04516"));  /*지원하지 않는 인코딩 방식입니다.*/
        }
        return bytes;
    }

    private byte[] downLoadFile(Application application,
                                HttpServletResponse response,
                                String s3Key) {

        String admsNo = application.getAdmsNo();
        int applNo = application.getApplNo();
        byte[] bytes = null;
        List<ApplicationDocument> applPaperInfosList =
                documentService.retrieveApplicationPaperInfo(applNo); // DB에서 filePath가져온다
        if (applPaperInfosList.size() == 1) {
            AmazonS3 s3 = new AmazonS3Client();
            S3Object object = null;
            try {
                object = s3.getObject(new GetObjectRequest(s3BucketName, s3Key));
            } catch (Exception e) {
                logger.error("Err in s3.getObject FiledDownload in PDFController");
                logger.error(e.getMessage());
                ExecutionContext ec = new ExecutionContext(ExecutionContext.FAIL);
                ec.setMessage(MessageResolver.getMessage("U00242"));
                Map<String, Object> ecMap = new HashMap<String, Object>();
                ecMap.put("bucketName", "[" + s3BucketName + "]");
                ecMap.put("admsNo", "[" + admsNo + "]");
                ecMap.put("objectKey", "[" + s3Key + "]");
                ec.setErrorInfo(new ErrorInfo(ecMap));
                throw new YSBizException(ec);
            }

            InputStream inputStream = object.getObjectContent();
            ObjectMetadata meta = object.getObjectMetadata();
            try {
                bytes = IOUtils.toByteArray(inputStream);
            } catch (IOException e) {
                ExecutionContext ecError = new ExecutionContext(ExecutionContext.FAIL);

                ecError.setMessage(MessageResolver.getMessage("U350"));
                ecError.setErrCode("ERR0062");

                Map<String, String> errorInfo = new HashMap<String, String>();
                errorInfo.put("applNo", String.valueOf(applNo));

                ecError.setErrorInfo(new ErrorInfo(errorInfo));
                throw new YSBizException(ecError);
            }

            response.setContentType(meta.getContentType());
            response.setHeader("Content-Length", String.valueOf(meta.getContentLength()));
            response.setHeader("Content-Encoding", meta.getContentEncoding());
            response.setHeader("ETag", meta.getETag());
            response.setHeader("Last-Modified", meta.getLastModified().toString());
        } else {
            ExecutionContext ecError = new ExecutionContext(ExecutionContext.FAIL);

            ecError.setMessage(MessageResolver.getMessage("U349"));
            ecError.setErrCode("ERR0061");

            Map<String, String> errorInfo = new HashMap<String, String>();
            errorInfo.put("applNo", String.valueOf(applNo));

            ecError.setErrorInfo(new ErrorInfo(errorInfo));
            throw new YSBizException(ecError);
        }

        return bytes;
    }
}

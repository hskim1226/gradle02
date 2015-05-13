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
import com.apexsoft.ysprj.applicants.common.util.FileUtil;
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
     * 지원자 별 PDF 묶음 파일 생성 및 S3에 업로드
     *
     * @param birtRequest
     * @return
     */
    @RequestMapping(value="/merge/applicant")
    @ResponseBody
    public String mergeByApplicant(BirtRequest birtRequest, HttpServletRequest request) {
        int applNo = birtRequest.getApplication().getApplNo();
        ExecutionContext ec = pdfService.getMergedPDFByApplicants(applNo);
        if (ExecutionContext.SUCCESS.equals(ec.getResult())) {
            return ExecutionContext.SUCCESS;
        } else {
            return ExecutionContext.FAIL;
        }
    }

    /**
     * DB에 저장된 원서 정보를 토대로 S3에서 원서+첨부파일 PDF 파일 미리보기
     *
     * @param basis
     * @param principal
     * @param response
     * @return
     * @throws java.io.IOException
     */
    @RequestMapping(value="/download", produces = "application/pdf")
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
            String applPaperLocalFilePath = applPaperInfosList.get(0).getFilePath();
            String s3FilePath = FileUtil.getS3PathFromLocalFullPath(applPaperLocalFilePath, fileBaseDir);
            AmazonS3 s3 = new AmazonS3Client();
            S3Object object = null;
            try {
                object = s3.getObject(new GetObjectRequest(s3BucketName, FileUtil.getFinalMergedFileFullPath(s3FilePath, applNo)));
            } catch (Exception e) {
                logger.error("Err in s3.getObject FiledDownload in PDFController");
                logger.error(e.getMessage());
                ExecutionContext ec = new ExecutionContext(ExecutionContext.FAIL);
                ec.setMessage(MessageResolver.getMessage("U00242"));
                Map<String, Object> ecMap = new HashMap<String, Object>();
                ecMap.put("bucketName", "[" + s3BucketName + "]");
                ecMap.put("admsNo", "[" + admsNo + "]");
                ecMap.put("userId", "[" + userId + "]");
                ecMap.put("objectKey", "[" + FileUtil.getFinalMergedFileFullPath(s3FilePath, applNo) + "]");
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
                response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(FileUtil.getFinalUserDownloadFileName(userId), "UTF-8") + "\"");
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
}

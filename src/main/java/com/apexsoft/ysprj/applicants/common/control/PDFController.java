package com.apexsoft.ysprj.applicants.common.control;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.exception.ErrorInfo;
import com.apexsoft.framework.exception.YSBizException;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.ysprj.applicants.application.domain.Application;
import com.apexsoft.ysprj.applicants.application.domain.ApplicationDocument;
import com.apexsoft.ysprj.applicants.application.domain.Basis;
import com.apexsoft.ysprj.applicants.application.service.DocumentService;
import com.apexsoft.ysprj.applicants.common.domain.FileMeta;
import com.apexsoft.ysprj.applicants.common.domain.FileWrapper;
import com.apexsoft.ysprj.applicants.common.service.FilePersistenceService;
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
    FilePersistenceService filePersistenceService;

    @Autowired
    WebUtil webUtil;

    @Value("#{app['file.baseDir']}")
    private String fileBaseDir;

    @Value("#{app['file.midPath']}")
    private String midPath;

    private static final Logger logger = LoggerFactory.getLogger(PDFController.class);

    /**
     * 첨부 파일화면에서 원서 미리보기 다운로드 버튼 클릭
     *
     * @param basis
     * @param principal
     * @param response
     * @return
     * @throws java.io.IOException
     */
    @RequestMapping(value="/download/applForm", produces = "application/pdf")
    @ResponseBody
    public byte[] downLoadApplForm(Basis basis,
                                   Principal principal,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {
        webUtil.blockGetMethod(request, basis.getApplication());
        Application application = basis.getApplication();
        String dirFullPath = FilePathUtil.getUploadDirectoryFullPath(fileBaseDir, midPath, application.getAdmsNo(), application.getUserId(), application.getApplNo());
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
                                String filePath) {

        String admsNo = application.getAdmsNo();
        int applNo = application.getApplNo();
        byte[] bytes = null;
        List<ApplicationDocument> applPaperInfosList =
                documentService.retrieveApplicationPaperInfo(applNo); // DB에서 filePath가져온다
        if (applPaperInfosList.size() == 1) {
            FileWrapper fileWrapper = null;
            try {
                fileWrapper = filePersistenceService.getFileWrapperFromFileRepo(filePath);
            } catch (Exception e) {
                logger.error("Err in s3Client.getObject FiledDownload in PDFController");
                logger.error(e.getMessage());
                ExecutionContext ec = new ExecutionContext(ExecutionContext.FAIL);
                ec.setMessage(MessageResolver.getMessage("U00242"));
                Map<String, String> ecMap = new HashMap<>();
                ecMap.put("admsNo", "[" + admsNo + "]");
                ecMap.put("filePath", "[" + filePath + "]");
                ec.setErrorInfo(new ErrorInfo(ecMap));
                throw new YSBizException(ec);
            }

            InputStream inputStream = fileWrapper.getInputStream();
            FileMeta meta = fileWrapper.getFileMeta();
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

package com.apexsoft.ysprj.applicants.common.control;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.exception.ErrorInfo;
import com.apexsoft.framework.exception.YSBizException;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.ysprj.applicants.application.service.DocumentService;
import com.apexsoft.ysprj.applicants.common.service.PDFService;
import com.apexsoft.ysprj.applicants.common.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.Principal;
import java.util.HashMap;
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
    MessageResolver messageResolver;

    @Value("#{app['file.baseDir']}")
    private String fileBaseDir;

    private static final Logger logger = LoggerFactory.getLogger(PDFController.class);

    @RequestMapping(value="/merge/applicant/{applNo}")
    @ResponseBody
    public String mergeByApplicant(@PathVariable("applNo") String applNo) {
        ExecutionContext ec = pdfService.getMergedPDFByApplicants(Integer.parseInt(applNo));
        if (ExecutionContext.SUCCESS.equals(ec.getResult())) {
            return ExecutionContext.SUCCESS;
        } else {
            return ExecutionContext.FAIL;
        }
    }

    @RequestMapping(value="/merge/dept/{deptCode}")
    public void mergeByDept(@PathVariable("deptCode") String deptCode) {

    }

    /**
     * 전체 파일 다운로드
     *
     * @param admsNo
     * @param applNo
     * @param response
     * @return
     * @throws java.io.IOException
     */
//    @RequestMapping(value="/attached/{admsNo}/{applNo}/{fileName:.+}/{originalFileName}")
    @RequestMapping(value="/download/{admsNo}/{applNo}", produces = "application/pdf")
    @ResponseBody
    public byte[] fileDownload(@PathVariable("admsNo") String admsNo,
                               @PathVariable("applNo") int applNo,
                               Principal principal,
                               HttpServletResponse response) {

        String userId = principal.getName();

        String uploadDirectoryFullPath = FileUtil.getUploadDirectoryFullPath(fileBaseDir, admsNo, userId, applNo);
        String fileFileFullPath = FileUtil.getFinalMergedFileFullPath(uploadDirectoryFullPath, applNo);
        String downLoadFileName = FileUtil.getFinalUserDownloadFileName(userId);
        File file =  new File(fileFileFullPath);
        byte[] bytes = null;
        try {
            bytes = org.springframework.util.FileCopyUtils.copyToByteArray(file);
        } catch (IOException e) {
            ExecutionContext ec = new ExecutionContext(ExecutionContext.FAIL);
            ec.setMessage(messageResolver.getMessage("U341"));
            ec.setErrCode("ERR0058");
            Map<String, String> errorInfo = new HashMap<String, String>();
            errorInfo.put("applNo", String.valueOf(applNo));
            errorInfo.put("userId", userId);
            errorInfo.put("fileName", userId);
            errorInfo.put("hint", "수험표, 원서 생성 및 All-in-One 파일 생성에 시간이 걸리므로, 결제 완료 직후에는 오류 발생할 수 있음");
            ec.setErrorInfo(new ErrorInfo(errorInfo));
            throw new YSBizException(ec);
        }

        try {
            response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(downLoadFileName, "UTF-8") + "\"");
        } catch (UnsupportedEncodingException e) {
            ExecutionContext ec = new ExecutionContext(ExecutionContext.FAIL);
            ec.setMessage(messageResolver.getMessage("U342"));
            ec.setErrCode("ERR0059");
            Map<String, String> errorInfo = new HashMap<String, String>();
            errorInfo.put("applNo", String.valueOf(applNo));
            errorInfo.put("userId", userId);
            errorInfo.put("fileName", downLoadFileName);
            errorInfo.put("encoding", "UTF-8");
            ec.setErrorInfo(new ErrorInfo(errorInfo));
            throw new YSBizException(ec);
        }

        response.setHeader("Content-Transfer-Encoding", "binary;");
        response.setHeader("Pragma", "no-cache;");
        response.setHeader("Expires", "-1;");
        response.setHeader("Content-Type", "application/pdf");
        response.setContentLength(bytes.length);

        return bytes;
    }
}

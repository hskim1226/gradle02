package com.apexsoft.ysprj.applicants.common.control;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.ysprj.applicants.application.domain.ApplicationDocumentKey;
import com.apexsoft.ysprj.applicants.application.domain.TotalApplicationDocument;
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
import java.security.Principal;

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
                               HttpServletResponse response)
            throws IOException {

        String userId = principal.getName();

        String dir = FileUtil.getUploadDirectoryFullPath(fileBaseDir, admsNo, userId, String.valueOf(applNo));
        //TODO 파일명 FileUtil 통해 해결하도록 수정 필요
        String fileName = applNo + "-merged-numbered.pdf";
        String downLoadFileName = userId + "_all.pdf";
        File file =  new File(dir, fileName);
        byte[] bytes = org.springframework.util.FileCopyUtils.copyToByteArray(file);

        response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(downLoadFileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");
        response.setHeader("Content-Transfer-Encoding", "binary;");
        response.setHeader("Pragma", "no-cache;");
        response.setHeader("Expires", "-1;");
        response.setHeader("Content-Type", "application/pdf");
        response.setContentLength(bytes.length);

        return bytes;
    }
}

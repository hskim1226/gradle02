package com.apexsoft.framework.persistence.file.control;

import com.apexsoft.framework.persistence.file.service.FileService;
import com.apexsoft.ysprj.applicants.application.domain.ApplicationDocument;
import com.apexsoft.ysprj.applicants.application.domain.ApplicationDocumentKey;
import com.apexsoft.ysprj.applicants.application.domain.ParamForApplicationDocument;
import com.apexsoft.ysprj.applicants.application.service.ApplicationService;
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
 * Created by hanmomhanda on 14. 9. 12.
 */
@Controller
@RequestMapping(value = "/filedownload")
public class FileDownloadController {

    @Autowired
    private FileService fileService;

    @Value("#{app['file.baseDir']}")
    private String fileBaseDir;

//    /**
//     * 파일 다운로드
//     *
//     * @param admsNo
//     * @param applNo
//     * @param fileName
//     * @param originalFileName
//     * @param response
//     * @param principal
//     * @return
//     * @throws IOException
//     */
//    @RequestMapping(value="/attached/{admsNo}/{applNo}/{fileName:.+}/{originalFileName}")
//    @ResponseBody
//    public byte[] downloadAttachedFile(@PathVariable("admsNo") String admsNo,
//                                       @PathVariable("applNo") int applNo,
//                                       @PathVariable("fileName") String fileName,
//                                       @PathVariable("originalFileName") String originalFileName,
//                                       HttpServletResponse response,
//                                       Principal principal)
//            throws IOException {
//        String userId = principal.getName();
//        String firstString = userId.substring(0, 1);
//        File file = new File(fileBaseDir + "/" + admsNo + "/" + firstString + "/" + userId + "/" + applNo, fileName);
//        byte[] bytes = org.springframework.util.FileCopyUtils.copyToByteArray(file);
//
//        response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(originalFileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");
//        response.setHeader("Content-Transfer-Encoding", "binary;");
//        response.setHeader("Pragma", "no-cache;");
//        response.setHeader("Expires", "-1;");
//        response.setHeader("Content-Type", "application/octet-stream");
//        response.setContentLength(bytes.length);
//
//        return bytes;
//    }
}

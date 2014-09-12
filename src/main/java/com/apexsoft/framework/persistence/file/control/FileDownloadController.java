package com.apexsoft.framework.persistence.file.control;

import com.apexsoft.framework.persistence.file.service.FileService;
import com.apexsoft.ysprj.applicants.application.domain.ApplicationDocument;
import com.apexsoft.ysprj.applicants.application.domain.ApplicationDocumentKey;
import com.apexsoft.ysprj.applicants.application.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value="/attached/{applNo}/{docSeq}")
    @ResponseBody
    public byte[] downloadAttachedFile(@PathVariable("applNo") int applNo,
                                       @PathVariable("docSeq") int docSeq,
                                       HttpServletResponse response)
            throws IOException {

        ApplicationDocumentKey adKey = new ApplicationDocumentKey();
        adKey.setApplNo(applNo);
        adKey.setDocSeq(docSeq);
        File file = fileService.getFile(adKey);
        byte[] bytes = org.springframework.util.FileCopyUtils.copyToByteArray(file);

        response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
        response.setContentLength(bytes.length);

        return bytes;
    }
}

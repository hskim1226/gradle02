package com.apexsoft.ysprj.applicants.application.control;

import com.apexsoft.ysprj.applicants.application.domain.Application;
import com.apexsoft.ysprj.applicants.application.service.DocumentService;
import com.apexsoft.ysprj.applicants.common.domain.BirtRequest;
import com.apexsoft.ysprj.applicants.common.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by hanmomhanda on 15. 7. 13.
 */
@Controller
@RequestMapping(value="/postApplication")
public class PostApplicationController {

    @Value("#{app['file.baseDir']}")
    private String fileBaseDir;

    @Value("#{app['s3.bucketName']}")
    private String bucketName;

    @Value("#{app['file.midPath']}")
    private String s3MidPath;

    @Autowired
    private DocumentService documentService;

    /**
     * 신청 완료된 원서나 수험표를 S3에서 다운로드
     *
     * @return
     */
    @RequestMapping(value = "/download/{docType}", method = RequestMethod.POST, produces = "application/pdf")
    @ResponseBody
    public byte[] fileDownload(@PathVariable("docType") String type, BirtRequest birtRequest,
                               HttpServletResponse response)
            throws IOException, InterruptedException {

        int applNo = birtRequest.getApplication().getApplNo();
        Application application = documentService.getApplication(applNo);

        Map<String, byte[]> downloadableFileInfo = documentService.getDownloadableFileAsBytes(application, type);
        Set<String> key = downloadableFileInfo.keySet();
        Iterator<String> iter = key.iterator();
        String fileName = null;
        if (iter.hasNext()) {
            fileName = iter.next();
        }
        byte[] bytes = downloadableFileInfo.get(fileName);
        String downaloadFileName = StringUtil.urlEncodeSpecialCharacter(URLEncoder.encode(fileName, "UTF-8"));

        response.setHeader("Content-Disposition", "attachment; filename=\"" + downaloadFileName + "\"");
        response.setHeader("Content-Transfer-Encoding", "binary;");
        response.setHeader("Pragma", "no-cache;");
        response.setHeader("Expires", "-1;");
        response.setHeader("Content-Type", "application/octet-stream");
        response.setContentLength(bytes.length);

        return bytes;
    }

}

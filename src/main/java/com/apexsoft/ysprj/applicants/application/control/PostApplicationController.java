package com.apexsoft.ysprj.applicants.application.control;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.apexsoft.ysprj.applicants.application.domain.Application;
import com.apexsoft.ysprj.applicants.common.domain.BirtRequest;
import com.apexsoft.ysprj.applicants.common.util.FilePathUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.security.Principal;

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

    @Value("#{app['s3.midPath']}")
    private String s3MidPath;

    @Autowired
    private AmazonS3Client s3Client;

    /**
     * 신청 완료된 원서나 수험표를 S3에서 다운로드
     *
     * @return
     */
    @RequestMapping(value = "/download/{docType}", method = RequestMethod.POST, produces = "application/pdf")
    @ResponseBody
    public byte[] fileDownload(@PathVariable("docType") String docType, BirtRequest birtRequest,
//   public void fileDownload(@PathVariable("docType") String docType, BirtRequest birtRequest,
                               Principal principal,
                               HttpServletRequest request,
                               HttpServletResponse response)
            throws IOException {

        Application application = birtRequest.getApplication();
        String admsNo = application.getAdmsNo();
//        String userId = principal.getName();
        String userId = application.getUserId();
        int applNo = application.getApplNo();

        String uploadDirPath = FilePathUtil.getUploadDirectoryFullPath(fileBaseDir, s3MidPath, admsNo, userId, applNo);
        String fileName = null;

        switch(docType) {
            case "appl":
                fileName = FilePathUtil.getApplicationFormFileName(userId);
                break;
            case "adms":
                fileName = FilePathUtil.getApplicationSlipFileName(userId);
                break;
            case "merged":
                fileName = FilePathUtil.getFinalMergedFileName(applNo);
                break;
        }

        String fileFullPath = uploadDirPath + "/" + fileName;

        ServletContext sc = request.getSession().getServletContext();
        String mimeType = sc.getMimeType(fileName);
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }

        S3Object object = s3Client.getObject(new GetObjectRequest(bucketName, FilePathUtil.getS3PathFromLocalFullPath(fileFullPath, fileBaseDir)));
        InputStream inputStream = object.getObjectContent();
        ObjectMetadata objMeta = object.getObjectMetadata();
        byte[] bytes = IOUtils.toByteArray(inputStream);

        request.setAttribute("Accept", "application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fileName, "UTF-8") + "\"");
        response.setHeader("Content-Transfer-Encoding", "binary;");
        response.setHeader("Pragma", "no-cache;");
        response.setHeader("Expires", "-1;");
        response.setHeader("Content-Type", mimeType);
        response.setContentLength(bytes.length);

        return bytes;



//        OutputStream outputStream = response.getOutputStream();
//        IOUtils.copy(new ByteArrayInputStream(bytes), outputStream);
//        response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fileName, "UTF-8") + "\"");
//        response.setHeader("Content-Transfer-Encoding", "binary;");
//        response.setHeader("Pragma", "no-cache;");
//        response.setHeader("Expires", "-1;");
//        response.setHeader("Content-Type", mimeType);
//        response.setContentLength(bytes.length);
//        response.flushBuffer();
//        inputStream.close();
//        outputStream.close();
    }

}

package com.apexsoft.framework.persistence.file.manager;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.apexsoft.framework.persistence.file.model.FileInfo;
import com.apexsoft.framework.web.file.exception.UploadException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Created by hanmomhanda on 15. 4. 3.
 */
public class S3PersistenceManagerImpl implements FilePersistenceManager {

    private static final Logger logger = LoggerFactory.getLogger(FilePersistenceManagerImpl.class);

    private AmazonS3 s3;
    private Region s3Region;
    private String bucketName;
    private String midPath;

    public S3PersistenceManagerImpl(AmazonS3 s3, String s3Region, String bucketName, String midPath) {
        this.s3 = s3;
        this.s3Region = Region.getRegion(Regions.fromName(s3Region));
        this.bucketName = bucketName;
        this.midPath = midPath;
    }

    @Override
    public File read(String fileName) { // 안쓰임
        return null;
    }

    @Override
    public File read(String folder, String fileName) { // 안쓰임
        return null;
    }

    @Override
    public File makeDirectory(String folder) { // 안쓰임
        return null;
    }

    @Override
    public void delete(String folder) { // 안쓰임

    }

    @Override
    public void deleteFile(String folder, String fileName) { // 오류 시에 파일을 지우는 데 S3 발송 시에는 중간에 파일 저장이 없으므로 오류에도 파일 지울 필요 없음

    }

    @Override
    public FileInfo moveFile(File source, String folder, String fileName) { // 안쓰임
        return null;
    }

    @Override
    public FileInfo copyFile(String srcFolder, String srcFileName, String tarFolder, String tarFileName) { // 안쓰임
        return null;
    }

    @Override
    public FileInfo save(String folder, String fileName, String orgFileName, InputStream inputStream) {

        String filePath = midPath + "/" + folder + "/" + fileName;
        int pageCnt = 0;
        long fileSize = 0;

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            baos.flush();

            fileSize = inputStream.available();
            ObjectMetadata meta = new ObjectMetadata();
            String fileExt = fileName.substring(fileName.lastIndexOf('.')+1);
            if ("pdf".equalsIgnoreCase(fileExt)) {
                meta.setContentType("application/pdf");
                try {
                    PDDocument pdfFile = PDDocument.load(new ByteArrayInputStream(baos.toByteArray()));
                    pageCnt = pdfFile.getNumberOfPages();
                } catch (IOException e) {
                    throw new UploadException("error counting pdf page " + orgFileName, e);
                }
            }
            meta.setContentEncoding("UTF-8");
            meta.setContentLength(fileSize);

            s3.putObject(new PutObjectRequest(bucketName, filePath, new ByteArrayInputStream(baos.toByteArray()), meta)
                    .withCannedAcl(CannedAccessControlList.AuthenticatedRead.PublicRead));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which means your request made it "
                    + "to Amazon S3, but was rejected with an error response for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with S3, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }
        return new FileInfo(filePath.replace("\\", "/"), fileName, orgFileName, fileSize, pageCnt);
    }
}

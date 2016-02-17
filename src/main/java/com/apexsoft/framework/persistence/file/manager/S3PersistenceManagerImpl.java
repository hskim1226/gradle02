package com.apexsoft.framework.persistence.file.manager;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.apexsoft.framework.exception.YSBizException;
import com.apexsoft.framework.persistence.file.exception.WrongFileFormatException;
import com.apexsoft.framework.persistence.file.model.FileInfo;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;

/**
 * Created by hanmomhanda on 15. 4. 3.
 */
public class S3PersistenceManagerImpl implements FilePersistenceManager {

    private static final Logger logger = LoggerFactory.getLogger(S3PersistenceManagerImpl.class);

    @Value("#{app['s3.bucketName']}")
    private String s3BucketName;

    @Value("#{app['file.midPath']}")
    private String midPath;

    @Value("#{app['s3.storageClass']}")
    private String s3StorageClass;

    @Autowired
    private AmazonS3Client s3Client;

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
    public FileInfo save(String folder, String fileName, String orgFileName, InputStream inputStream) throws IOException {

        String filePath = folder.startsWith(midPath) ? folder + "/" + fileName : midPath + "/" + folder + "/" + fileName;
        int pageCnt = 0;
        long fileSize = 0;
        InputStream uplaodFileInputStream = null;

        try {
            fileSize = inputStream.available();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[8 * 1024];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            baos.flush();

            ObjectMetadata meta = new ObjectMetadata();
            String fileExt = fileName.substring(fileName.lastIndexOf('.')+1);
            if ("pdf".equalsIgnoreCase(fileExt)) {
                meta.setContentType("application/pdf");
                PDDocument pdfFile = null;
                try {
//                    pdfFile = PDDocument.loadNonSeq(new ByteArrayInputStream(baos.toByteArray()), null);
                    pdfFile = PDDocument.load(new ByteArrayInputStream(baos.toByteArray()));
                    pageCnt = pdfFile.getNumberOfPages();
//                    AccessPermission pdfPermission = pdfFile.getCurrentAccessPermission();



//                    // PDF 합치지 않고 넘버링만 하기로하여 주석 처리
//                    // TOEFL iBT 등 증빙은 pdfFile.isEncrypted() == true
//                    if (pdfFile.isEncrypted()) {
//                        throw new EncryptedPDFException(orgFileName);
//                    }
//                    if (pdfPermission.isReadOnly()) {
//                        System.out.println("isReadOnly");
//                    }if (pdfPermission.canAssembleDocument()) {
//                        System.out.println("canAssembleDocument");
//                    }if (pdfPermission.canExtractContent()) {
//                        System.out.println("canExtractContent");
//                    }if (pdfPermission.canExtractForAccessibility()) {
//                        System.out.println("canExtractForAccessibility");
//                    }if (pdfPermission.canFillInForm()) {
//                        System.out.println("canFillInForm");
//                    }if (pdfPermission.canModify()) {
//                        System.out.println("canModify");
//                    }
                } catch (IOException e) {
                    throw new WrongFileFormatException(orgFileName, e);
                } finally {
                    if (pdfFile != null) {
                        try {
                            pdfFile.close();
                        } catch (IOException e) {
                            logger.error("PDF is NOT closed, DocumentController.fileUpload()");
                        }
                    }
                }
            } else if ("jpg".equalsIgnoreCase(fileExt)) {
                meta.setContentType("image/jpeg");
            } else if ("png".equalsIgnoreCase(fileExt)) {
                meta.setContentType("image/png");
            } else if ("gif".equalsIgnoreCase(fileExt)) {
                meta.setContentType("image/gif");
            }
            meta.setContentEncoding("UTF-8");
            meta.setContentLength(fileSize);
            meta.setHeader("x-amz-storage-class", s3StorageClass);

            uplaodFileInputStream = new ByteArrayInputStream(baos.toByteArray());

            s3Client.putObject(new PutObjectRequest(s3BucketName, filePath, uplaodFileInputStream, meta)
//                    .withCannedAcl(CannedAccessControlList.AuthenticatedRead.PublicRead));
                    .withCannedAcl(CannedAccessControlList.AuthenticatedRead));
        } catch (IOException e) {
            throw e;
        } catch (AmazonServiceException ase) {
            throw ase;
        } catch (AmazonClientException ace) {
            logger.error("Caught an AmazonClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with S3, "
                    + "such as not being able to access the network.");
            logger.error("Error Message: " + ace.getMessage());
            throw new YSBizException(ace);
        } finally {
            try {
                if (uplaodFileInputStream!= null) uplaodFileInputStream.close();
            } catch (IOException e) {}
        }
        return new FileInfo(filePath.replace("\\", "/"), fileName, orgFileName, fileSize, pageCnt);
    }
}

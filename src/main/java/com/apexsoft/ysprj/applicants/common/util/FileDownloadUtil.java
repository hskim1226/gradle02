package com.apexsoft.ysprj.applicants.common.util;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by hanmomhanda on 16. 2. 15.
 */
public class FileDownloadUtil {

    // S3에서 InputStream 추출 및 반환
    public static InputStream getInputStreamFromS3(AmazonS3Client s3Client, String s3BucketName, String filePath) {
        S3Object object = s3Client.getObject(new GetObjectRequest(s3BucketName, filePath));
        InputStream inputStream = object.getObjectContent();
        return inputStream;
    }

    // S3에서 byte array 다운로드
    public static byte[] getBytesFromS3Object(AmazonS3Client s3Client, String s3BucketName, String filePath) throws IOException {
        InputStream inputStream = getInputStreamFromS3(s3Client, s3BucketName, filePath);
        byte[] bytes = IOUtils.toByteArray(inputStream);
        return bytes;
    }

    // S3에서 다운로드해서 파일 생성
    public static File getFileFromS3(AmazonS3Client s3Client, String s3BucketName, String baseDir, String filePath) throws IOException {
        byte[] bytes = getBytesFromS3Object(s3Client, s3BucketName, filePath);
        File file = new File(baseDir, filePath);
        FileUtils.writeByteArrayToFile(file, bytes);
        return file;
    }
}

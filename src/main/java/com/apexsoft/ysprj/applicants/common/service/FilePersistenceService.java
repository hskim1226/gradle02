package com.apexsoft.ysprj.applicants.common.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.S3Object;
import com.apexsoft.ysprj.applicants.common.domain.FileWrapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by hanmomhanda on 16. 2. 17.
 */
public interface FilePersistenceService {
    // S3에서 S3Object 추출 및 반환
    FileWrapper getS3Object(AmazonS3Client s3Client, String s3BucketName, String filePath);

    // S3에서 InputStream 추출 및 반환
    InputStream getInputStreamFromS3(AmazonS3Client s3Client, String s3BucketName, String filePath);

    // S3에서 byte array 다운로드
    byte[] getBytesFromS3Object(AmazonS3Client s3Client, String s3BucketName, String filePath) throws IOException;

    // S3에서 다운로드해서 파일 생성
    File getFileFromS3(AmazonS3Client s3Client, String s3BucketName, String baseDir, String filePath) throws IOException;

    // S3에 업로드 된 파일 삭제
    boolean deleteFileInS3(AmazonS3Client s3Client, String s3BucketName, String filePath, int applNo, int docSeq);
}

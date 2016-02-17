package com.apexsoft.ysprj.applicants.common.service;

import com.apexsoft.ysprj.applicants.common.domain.FileWrapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by hanmomhanda on 16. 2. 17.
 */
public interface FilePersistenceService {
    // S3에서 S3Object 추출 및 반환
    FileWrapper getFileWrapperFromFileRepo(String filePath);

    // S3에서 InputStream 추출 및 반환
    InputStream getInputStreamFromFileRepo(String filePath);

    // S3에서 byte array 다운로드
    byte[] getBytesFromFileRepo(String filePath) throws IOException;

    // S3에서 다운로드해서 파일 생성
    File getFileFromFileRepo(String baseDir, String filePath) throws IOException;

    void uploadToFileRepo(String fileBaseDir, File file, int applNo);

    // S3에 업로드 된 파일 삭제
    boolean deleteFileInFileRepo(String filePath, int applNo, int docSeq);
}

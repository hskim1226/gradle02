package com.apexsoft.ysprj.applicants.common.service;

import com.apexsoft.ysprj.applicants.common.domain.FileWrapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by hanmomhanda on 16. 2. 17.
 */
public class LocalPersistenceServiceImpl implements FilePersistenceService {
    @Override
    public FileWrapper getFileWrapperFromFileRepo(String repoPath, String filePath) {
        return null;
    }

    @Override
    public InputStream getInputStreamFromFileRepo(String repoPath, String filePath) {
        return null;
    }

    @Override
    public byte[] getBytesFromFileRepo(String repoPath, String filePath) throws IOException {
        return new byte[0];
    }

    @Override
    public File getFileFromFileRepo(String repoPath, String baseDir, String filePath) throws IOException {
        return null;
    }

    @Override
    public void uploadToFileRepo(String bucketName, String fileBaseDir, File file, int applNo) {

    }

    @Override
    public boolean deleteFileInFileRepo(String repoPath, String filePath, int applNo, int docSeq) {
        return false;
    }
}

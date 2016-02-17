package com.apexsoft.ysprj.applicants.common.service;

import com.apexsoft.ysprj.applicants.common.domain.FileWrapper;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by hanmomhanda on 16. 2. 17.
 */
public class LocalPersistenceServiceImpl implements FilePersistenceService {

    @Value("#{app['file.baseDir']}")
    private String fileBaseDir;

    @Override
    public FileWrapper getFileWrapperFromFileRepo(String filePath) {
        
        return null;
    }

    @Override
    public InputStream getInputStreamFromFileRepo(String filePath) {
        return null;
    }

    @Override
    public byte[] getBytesFromFileRepo(String filePath) throws IOException {
        return new byte[0];
    }

    @Override
    public File getFileFromFileRepo(String baseDir, String filePath) throws IOException {
        return null;
    }

    @Override
    public void uploadToFileRepo(String fileBaseDir, File file, int applNo) {

    }

    @Override
    public boolean deleteFileInFileRepo(String filePath, int applNo, int docSeq) {
        return false;
    }
}

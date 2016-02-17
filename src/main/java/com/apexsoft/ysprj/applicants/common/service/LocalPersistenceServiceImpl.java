package com.apexsoft.ysprj.applicants.common.service;

import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.apexsoft.framework.exception.YSBizException;
import com.apexsoft.ysprj.applicants.common.domain.FileMeta;
import com.apexsoft.ysprj.applicants.common.domain.FileWrapper;
import com.apexsoft.ysprj.applicants.common.util.FilePathUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;

import javax.activation.FileTypeMap;
import java.io.*;

/**
 * Created by hanmomhanda on 16. 2. 17.
 */
public class LocalPersistenceServiceImpl implements FilePersistenceService {

    @Value("#{app['file.baseDir']}")
    private String fileBaseDir;

    @Override
    public FileMeta createFileMeta(Object object) {
        FileMeta fileMeta = null;
        if (object instanceof File) {
            fileMeta = new FileMeta();
            File file = (File)object;
            String contentType = FileTypeMap.getDefaultFileTypeMap().getContentType(file);
            fileMeta.setContentType(contentType);
            fileMeta.setContentEncoding("UTF-8");
            fileMeta.setLastModified(String.valueOf(file.lastModified()));
            fileMeta.setContentLength(file.length());
        }
        return fileMeta;
    }

    @Override
    public FileWrapper getFileWrapperFromFileRepo(String filePath) throws FileNotFoundException{
        File file = new File(fileBaseDir, filePath);
        FileInputStream fis = new FileInputStream(file);
        FileWrapper fileWrapper = new FileWrapper(fis, createFileMeta(file));
        return fileWrapper;
    }

    @Override
    public InputStream getInputStreamFromFileRepo(String filePath) throws IOException {
        FileWrapper fileWrapper = getFileWrapperFromFileRepo(filePath);
        return fileWrapper.getInputStream();
    }

    @Override
    public byte[] getBytesFromFileRepo(String filePath) throws IOException {
        InputStream inputStream = getInputStreamFromFileRepo(filePath);
        byte[] bytes = IOUtils.toByteArray(inputStream);
        return bytes;
    }

    @Override
    public File getFileFromFileRepo(String baseDir, String filePath) throws IOException {
        byte[] bytes = getBytesFromFileRepo(filePath);
        File file = new File(baseDir, filePath);
        FileUtils.writeByteArrayToFile(file, bytes);
        return file;
    }

    @Override
    public void uploadToFileRepo(String fileBaseDir, File file, int applNo) {
        // Local에 이미 저장되어 있으므로 별도로 업로드 필요 없음
    }

    @Override
    public boolean deleteFileInFileRepo(String filePath, int applNo, int docSeq) {
        return false;
    }
}

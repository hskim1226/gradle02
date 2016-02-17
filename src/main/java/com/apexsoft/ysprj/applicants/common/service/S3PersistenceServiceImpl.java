package com.apexsoft.ysprj.applicants.common.service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.exception.ErrorInfo;
import com.apexsoft.framework.exception.YSBizException;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.ysprj.applicants.common.domain.FileMeta;
import com.apexsoft.ysprj.applicants.common.domain.FileWrapper;
import com.apexsoft.ysprj.applicants.common.util.FilePathUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hanmomhanda on 16. 2. 17.
 */
@Service
public class S3PersistenceServiceImpl implements FilePersistenceService {

    @Autowired
    AmazonS3Client s3Client;

    @Value("#{app['s3.bucketName']}")
    private String s3BucketName;

    @Value("#{app['s3.storageClass']}")
    private String s3StorageClass;

    @Override
    public FileMeta createFileMeta(Object object) {
        FileMeta fileMeta = null;
        if (object instanceof ObjectMetadata) {
            fileMeta = new FileMeta();
            ObjectMetadata metadata = (ObjectMetadata)object;
            fileMeta.setContentType(metadata.getContentType());
            fileMeta.setContentEncoding(metadata.getContentEncoding());
            fileMeta.setETag(metadata.getETag());
            fileMeta.setLastModified(metadata.getLastModified().toString());
            fileMeta.setContentLength(metadata.getContentLength());
        }
        return fileMeta;
    }

    @Override
    public FileWrapper getFileWrapperFromFileRepo(String filePath) {
        S3Object s3Object = s3Client.getObject(new GetObjectRequest(s3BucketName, filePath));
        FileWrapper fileWrapper = new FileWrapper(s3Object.getObjectContent(),
                                                  createFileMeta(s3Object.getObjectMetadata()));
        return fileWrapper;
    }

    @Override
    public InputStream getInputStreamFromFileRepo(String filePath) {
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
        ObjectMetadata meta = createS3ObjMetaData(file);
        String s3FilePath = FilePathUtil.getS3PathFromLocalFullPath(file.getAbsolutePath(), fileBaseDir);

        s3Client.putObject(new PutObjectRequest(s3BucketName, s3FilePath, file)
                .withMetadata(meta)
                .withCannedAcl(CannedAccessControlList.AuthenticatedRead));
        if (file.exists())
            file.delete();
    }

    private ObjectMetadata createS3ObjMetaData(File file) {
        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentEncoding("UTF-8");
        meta.setContentLength(file.length());
        meta.setHeader("x-amz-storage-class", s3StorageClass);
        return meta;
    }


    @Override
    public boolean deleteFileInFileRepo(String filePath, int applNo, int docSeq) {
        boolean deleteOk = false;
        try {
            s3Client.deleteObject(s3BucketName, filePath);
            deleteOk = true;
        } catch (AmazonServiceException ase) {
            ExecutionContext ec = new ExecutionContext(ExecutionContext.FAIL);
            ec.setMessage(MessageResolver.getMessage("U338"));
            ec.setErrCode("ERR0051");
            Map<String, String> errorInfo = new HashMap<String, String>();
            errorInfo.put("applNo", String.valueOf(applNo));
            errorInfo.put("docSeq", String.valueOf(docSeq));
            errorInfo.put("AWS Error Message", ase.getMessage());
            errorInfo.put("AWS HTTP Status Code", String.valueOf(ase.getStatusCode()));
            errorInfo.put("AWS HTTP Error Code", String.valueOf(ase.getErrorCode()));
            errorInfo.put("AWS Error Type", ase.getErrorType().toString());
            errorInfo.put("AWS Request ID", ase.getRequestId());
            ec.setErrorInfo(new ErrorInfo(errorInfo));
            throw new YSBizException(ec);
        } catch (AmazonClientException ace) {
            ExecutionContext ec = new ExecutionContext(ExecutionContext.FAIL);
            ec.setMessage(MessageResolver.getMessage("U338"));
            ec.setErrCode("ERR0051");
            Map<String, String> errorInfo = new HashMap<String, String>();
            errorInfo.put("applNo", String.valueOf(applNo));
            errorInfo.put("docSeq", String.valueOf(docSeq));
            errorInfo.put("AWS Error Message", ace.getMessage());
            ec.setErrorInfo(new ErrorInfo(errorInfo));
            throw new YSBizException(ec);
        }
        return deleteOk;
    }
}

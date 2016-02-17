package com.apexsoft.ysprj.applicants.common.util;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.exception.ErrorInfo;
import com.apexsoft.framework.exception.YSBizException;
import com.apexsoft.framework.message.MessageResolver;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hanmomhanda on 16. 2. 15.
 */
public class FilePersistenceUtil {

    // S3에서 S3Object 추출 및 반환
    public static S3Object getFileWrapperFromFileRepo(AmazonS3Client s3Client, String s3BucketName, String filePath) {
        S3Object s3Object = s3Client.getObject(new GetObjectRequest(s3BucketName, filePath));
        return s3Object;
    }

    // S3에서 InputStream 추출 및 반환
    public static InputStream getInputStreamFromFileRepo(AmazonS3Client s3Client, String s3BucketName, String filePath) {
        S3Object object = getFileWrapperFromFileRepo(s3Client, s3BucketName, filePath);
        InputStream inputStream = object.getObjectContent();
        return inputStream;
    }

    // S3에서 byte array 다운로드
    public static byte[] getBytesFromFileRepo(AmazonS3Client s3Client, String s3BucketName, String filePath) throws IOException {
        InputStream inputStream = getInputStreamFromFileRepo(s3Client, s3BucketName, filePath);
        byte[] bytes = IOUtils.toByteArray(inputStream);
        return bytes;
    }

    // S3에서 다운로드해서 파일 생성
    public static File getFileFromFileRepo(AmazonS3Client s3Client, String s3BucketName, String baseDir, String filePath) throws IOException {
        byte[] bytes = getBytesFromFileRepo(s3Client, s3BucketName, filePath);
        File file = new File(baseDir, filePath);
        FileUtils.writeByteArrayToFile(file, bytes);
        return file;
    }

    // S3에 업로드 된 파일 삭제
    public static boolean deleteFileInFileRepo(AmazonS3Client s3Client, String s3BucketName, String filePath,
                                               int applNo, int docSeq) {
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

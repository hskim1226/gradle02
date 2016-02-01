package com.apexsoft.ysprj.sysadmin.domain;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by hanmomhanda on 15. 11. 12.
 */
public abstract class AbstractS3Consumer implements Runnable {

    @Autowired
    private AmazonS3Client s3Client;

    protected AtomicInteger count = new AtomicInteger();
    protected AtomicLong totalVolume = new AtomicLong();

    public BlockingQueue<BackUpApplDoc> applInfoQue = null;
    public BlockingQueue<S3Object> s3ObjQue = null;
    public final String s3BucketName;
    public String baseDir;
    public String backupDir;
    public String s3MidPath;
    public int fileCount;

    public AbstractS3Consumer(String s3BucketName, String s3MidPath, int fileCount, String backupDir) {
        this.s3BucketName = s3BucketName;
        this.s3MidPath = s3MidPath;
        this.fileCount = fileCount;
        this.backupDir = backupDir;
    }

    protected abstract String getFilePath(BackUpApplDoc backUpApplDoc);
    protected abstract String getTargetFilePath(BackUpApplDoc backUpApplDoc);
    protected abstract void handleException(Exception e, BackUpApplDoc backUpApplDoc, BlockingQueue<BackUpApplDoc> applInfoQue);

    public BlockingQueue<BackUpApplDoc> getApplInfoQue() {
        return applInfoQue;
    }

    public void setApplInfoQue(BlockingQueue<BackUpApplDoc> applInfoQue) {
        this.applInfoQue = applInfoQue;
    }

    public BlockingQueue<S3Object> getS3ObjQue() {
        return s3ObjQue;
    }

    public void setS3ObjQue(BlockingQueue<S3Object> s3ObjQue) {
        this.s3ObjQue = s3ObjQue;
    }

    public String getBaseDir() {
        return baseDir;
    }

    public void setBaseDir(String baseDir) {
        this.baseDir = baseDir;
    }

    public String getS3MidPath() {
        return s3MidPath;
    }

    public void setS3MidPath(String s3MidPath) {
        this.s3MidPath = s3MidPath;
    }

    @Override
    public void run() {
        BackUpApplDoc backUpApplDoc = null;
        try {
            while(true) {
                backUpApplDoc = applInfoQue.poll(20, TimeUnit.SECONDS);

                if (backUpApplDoc != null) {
                    S3Object s3Object = null;
                    String filePath = getFilePath(backUpApplDoc);
                    String targetFilePath = getTargetFilePath(backUpApplDoc);
                    try {
                        s3Object = s3Client.getObject(new GetObjectRequest(s3BucketName, filePath));
                        ObjectMetadata objMeta = s3Object.getObjectMetadata();
//                        objMeta.addUserMetadata("applNo", String.valueOf(backUpApplDoc.getApplNo()));
//                        objMeta.addUserMetadata("filePath", filePath);
//                        objMeta.addUserMetadata("targetFilePath", targetFilePath);
                        String msg = count.incrementAndGet() + "/" + fileCount + "] " + "<thread-" + Thread.currentThread().getId() + "> " + ", totalVolume - " + totalVolume.addAndGet(objMeta.getContentLength()) + " : " + targetFilePath;
                        System.out.println("[DOWNLOAD " + msg);
                        FileUtils.copyInputStreamToFile(s3Object.getObjectContent(),
                                new File(backupDir, targetFilePath));
                        System.out.println("[LOCAL SAVE " + msg);
//                        s3ObjQue.put(s3Object);
                    } catch (Exception e) {
//                        s3 = null;
//                        s3 = AWSWrapper.getS3Client();
                        handleException(e, backUpApplDoc, applInfoQue);
                    }
                } else if (applInfoQue.peek() == null) {
                    return;
                }
            }
        } catch (InterruptedException e) {
            handleException(e, backUpApplDoc, applInfoQue);
        }
    }
}

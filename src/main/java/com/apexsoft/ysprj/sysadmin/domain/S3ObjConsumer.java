package com.apexsoft.ysprj.sysadmin.domain;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by hanmomhanda on 15. 12. 23.
 */
public class S3ObjConsumer implements Runnable {
    private AtomicInteger count = new AtomicInteger();
    private AtomicLong totalVolume = new AtomicLong();

    private final BlockingQueue<S3Object> s3ObjQue;
    private final int fileCount;
    private final String backupDir;
    private final String s3BucketName;

    public S3ObjConsumer(BlockingQueue<S3Object> s3ObjQue, int fileCount, String backupDir, String s3BucketName) {
        this.s3ObjQue = s3ObjQue;
        this.fileCount = fileCount;
        this.backupDir = backupDir;
        this.s3BucketName = s3BucketName;
    }

    @Override
    public void run() {
        Map<String, String> s3ObjUserMeta = null;
        S3Object s3Object = null;
        try {
            while (true) {
                s3Object = s3ObjQue.poll(5, TimeUnit.SECONDS);
                if (s3Object != null) {
                    InputStream inputStream = s3Object.getObjectContent();
                    ObjectMetadata s3ObjMeta = s3Object.getObjectMetadata();
                    s3ObjUserMeta = s3ObjMeta.getUserMetadata();
                    try {
                        FileUtils.copyInputStreamToFile(inputStream, new File(backupDir, s3ObjUserMeta.get("targetFilePath")));
                        System.out.println("[LOCAL SAVE " + count.incrementAndGet() + "/" + fileCount + "] " + "<thread-" + Thread.currentThread().getId() + "> " + ", totalVolume - " + totalVolume.addAndGet(s3ObjMeta.getContentLength()) + " : " + s3ObjUserMeta.get("targetFilePath"));
                    } catch (Exception e) {
                        handleException(e, s3ObjUserMeta, s3Object, s3ObjQue);
                    }
//                    } else if (s3ObjQue.peek() == null && count.intValue() == fileCount) {
                } else if (s3ObjQue.peek() == null) {
//System.out.println(count.intValue() + " job completed");
                    return;
                }
            }
        } catch (InterruptedException e) {
            System.err.println("InterruptedException out of S3ObjConsumer Thread Loop");
            handleException(e, s3ObjUserMeta, s3Object, s3ObjQue);
        }
    }

    private void handleException(Exception e,
                                 Map<String, String> s3ObjUserMeta,
                                 S3Object s3Object,
                                 BlockingQueue<S3Object> s3ObjQue) {
        String tId = "<Thread-" + Thread.currentThread().getId() + "> ";
        System.err.println(tId +"^^ ERROR in " +
                Thread.currentThread().getStackTrace()[1].getClassName() + "." +
                Thread.currentThread().getStackTrace()[1].getMethodName());
        System.err.println(tId + e.getMessage());
        System.err.println(tId + "bucketName : [" + s3BucketName + "]");
        System.err.println(tId + "applNo : [" + s3ObjUserMeta.get("applNo") + "]");
        System.err.println(tId + "filePath : [" + s3ObjUserMeta.get("filePath") +"]");
        System.err.println(tId + "targetFilePath : [" + s3ObjUserMeta.get("targetFilePath") +"]");
        System.err.println(tId + "Putting s3Object of Err Object back to the s3ObjQue");
        s3ObjQue.add(s3Object);
    }
}
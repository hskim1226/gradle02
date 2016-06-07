package com.apexsoft.ysprj.sysadmin.domain;

import com.amazonaws.services.s3.model.S3Object;
import com.apexsoft.ysprj.applicants.common.domain.FileMeta;
import com.apexsoft.ysprj.applicants.common.domain.FileWrapper;
import com.apexsoft.ysprj.applicants.common.service.FilePersistenceService;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by hanmomhanda on 15. 11. 12.
 */
public abstract class AbstractS3Consumer implements Runnable {

    private FilePersistenceService filePersistenceService;

    protected AtomicInteger count = new AtomicInteger();
    protected AtomicLong totalVolume = new AtomicLong();

    public BlockingQueue<BackUpApplDoc> applInfoQue = null;
    public BlockingQueue<S3Object> s3ObjQue = null;
    public String baseDir;
    public String backupDir;
    public String midPath;
    public int fileCount;

    public AbstractS3Consumer(String midPath, int fileCount, String backupDir) {
        this.midPath = midPath;
        this.fileCount = fileCount;
        this.backupDir = backupDir;
    }

    protected abstract String getFilePath(BackUpApplDoc backUpApplDoc);
    protected abstract String getTargetFilePath(BackUpApplDoc backUpApplDoc);
    protected abstract void handleException(Exception e, BackUpApplDoc backUpApplDoc, BlockingQueue<BackUpApplDoc> applInfoQue);

    public FilePersistenceService getFilePersistenceService() {
        return filePersistenceService;
    }

    public void setFilePersistenceService(FilePersistenceService filePersistenceService) {
        this.filePersistenceService = filePersistenceService;
    }

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

    public String getMidPath() {
        return midPath;
    }

    public void setMidPath(String midPath) {
        this.midPath = midPath;
    }

    @Override
    public void run() {
        BackUpApplDoc backUpApplDoc = null;
        try {
            while(true) {
                backUpApplDoc = applInfoQue.poll(20, TimeUnit.SECONDS);

                if (backUpApplDoc != null) {
                    FileWrapper fileWrapper = null;
                    String filePath = getFilePath(backUpApplDoc);
                    String targetFilePath = getTargetFilePath(backUpApplDoc);
                    try {
                        fileWrapper = filePersistenceService.getFileWrapperFromFileRepo(filePath);
                        FileMeta fileMeta = fileWrapper.getFileMeta();
//                        fileMeta.addUserMetadata("applNo", String.valueOf(backUpApplDoc.getApplNo()));
//                        fileMeta.addUserMetadata("filePath", filePath);
//                        fileMeta.addUserMetadata("targetFilePath", targetFilePath);
                        String msg = count.incrementAndGet() + "/" + fileCount + "] " + "<thread-" + Thread.currentThread().getId() + "> " + ", totalVolume - " + totalVolume.addAndGet(fileMeta.getContentLength()) + " : " + targetFilePath;
                        System.out.println("[DOWNLOAD " + msg);
                        FileUtils.copyInputStreamToFile(fileWrapper.getInputStream(),
                                new File(backupDir, targetFilePath));
                        System.out.println("[LOCAL SAVE " + msg);
//                        s3ObjQue.put(fileWrapper);
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

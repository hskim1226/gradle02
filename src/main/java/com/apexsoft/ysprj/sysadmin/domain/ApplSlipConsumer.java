package com.apexsoft.ysprj.sysadmin.domain;

import com.apexsoft.ysprj.applicants.common.util.FilePathUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;

/**
 * Created by hanmomhanda on 15. 11. 12.
 */
public class ApplSlipConsumer extends AbstractS3Consumer {



    private static final Logger logger = LoggerFactory.getLogger(ApplSlipConsumer.class);

    public ApplSlipConsumer(String s3BucketName,
                            String s3MidPath,
                            int fileCount,
                            String backupDir) {
        super(s3BucketName, s3MidPath, fileCount, backupDir);
    }

    @Override
    protected String getFilePath(BackUpApplDoc backUpApplDoc) {
        String uploadDirFullPath = FilePathUtil.getUploadDirectoryFullPath(baseDir, s3MidPath, backUpApplDoc.getAdmsNo(), backUpApplDoc.getUserId(), backUpApplDoc.getApplNo());
        String fullPath = FilePathUtil.getApplicationSlipFileFullPath(uploadDirFullPath, backUpApplDoc.getUserId());
        String filePath = fullPath.substring(baseDir.length() + 1);
        return filePath;
    }

    @Override
    protected String getTargetFilePath(BackUpApplDoc backUpApplDoc) {
        String slipFileName = FilePathUtil.getApplicationSlipFileName(backUpApplDoc.getUserId());
        StringBuilder sb = new StringBuilder();
        sb.append(s3MidPath).append("/")
            .append(slipFileName);
        return sb.toString();
    }

    @Override
    protected void handleException(Exception e, BackUpApplDoc backUpApplDoc, BlockingQueue<BackUpApplDoc> applInfoQue) {
//        ExecutionContext ec = new ExecutionContext(ExecutionContext.FAIL);
        logger.error(e.getMessage());
        logger.error("bucketName : [" + s3BucketName + "]");
        logger.error("applNo : [" + backUpApplDoc.getApplNo() + "]");
        logger.error("objectKey : [" + getFilePath(backUpApplDoc) +"]");
        logger.error("targetFilePath : [" + getTargetFilePath(backUpApplDoc) +"]");
        logger.error("Putting applInfo of Err Object back to the queue");
        applInfoQue.add(backUpApplDoc);
//        throw new YSBizException(ec);
    }

}

package com.apexsoft.ysprj.sysadmin.domain;

import com.amazonaws.services.s3.AmazonS3Client;
import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.exception.YSBizException;
import com.apexsoft.ysprj.applicants.application.domain.Application;
import com.apexsoft.ysprj.applicants.common.util.FileUtil;
import com.apexsoft.ysprj.applicants.common.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;

/**
 * Created by hanmomhanda on 15. 11. 12.
 */
public class ApplAllConsumer extends AbstractS3Consumer {

    private static final Logger logger = LoggerFactory.getLogger(ApplAllConsumer.class);

    public ApplAllConsumer(String s3BucketName,
                           String s3MidPath,
                           int fileCount) {
        super(s3BucketName, s3MidPath, fileCount);
    }

    @Override
    protected String getFilePath(BackUpApplDoc backUpApplDoc) {
        Application appl = new Application();
        appl.setApplNo(backUpApplDoc.getApplNo());
        appl.setUserId(backUpApplDoc.getUserId());
        appl.setAdmsNo(backUpApplDoc.getAdmsNo());
        String fullPath = FileUtil.getFinalMergedFileFullPath(s3BucketName, s3MidPath, appl);
        String filePath = fullPath.substring(s3BucketName.length() + 1);
        return filePath;
    }

    @Override
    protected String getTargetFilePath(BackUpApplDoc backUpApplDoc) {
        String applicantName = StringUtil.getEmptyIfNull(backUpApplDoc.getKorName()).equals(StringUtil.EMPTY_STRING) ?
                backUpApplDoc.getEngName() + "-" + backUpApplDoc.getEngSur() :
                backUpApplDoc.getKorName();
        String targetFilePath = new StringBuilder().append(s3MidPath).append("/")
                .append(backUpApplDoc.getCampName()).append("/")
                .append(backUpApplDoc.getCollName()).append("/")
                .append(backUpApplDoc.getDeptName()).append("/")
                .append(backUpApplDoc.getApplId()).append("_").append(applicantName).append(".pdf")
                .toString();
        return targetFilePath;
    }

    @Override
    protected void handleException(Exception e, BackUpApplDoc backUpApplDoc, BlockingQueue<BackUpApplDoc> applInfoQue) {
//        ExecutionContext ec = new ExecutionContext(ExecutionContext.FAIL);
        logger.error(e.getMessage());
        logger.error("Thread : " + Thread.currentThread().getId());
        logger.error("bucketName : [" + s3BucketName + "]");
        logger.error("applNo : [" + backUpApplDoc.getApplNo() + "]");
        logger.error("objectKey : [" + getFilePath(backUpApplDoc) +"]");
        logger.error("targetFilePath : [" + getTargetFilePath(backUpApplDoc) +"]");
        logger.error("Putting applInfo of Err Object back to the queue");
        applInfoQue.add(backUpApplDoc);
//        throw new YSBizException(ec);
    }

}

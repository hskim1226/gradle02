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
        String tId = "<Thread-" + Thread.currentThread().getId() + "> ";
        System.err.println(tId +"^^ ERROR in " +
                Thread.currentThread().getStackTrace()[1].getClassName() + "." +
                Thread.currentThread().getStackTrace()[1].getMethodName());
        System.err.println(tId + e.getMessage());
        System.err.println(tId + "bucketName : [" + s3BucketName + "]");
        System.err.println(tId + "applNo : [" + backUpApplDoc.getApplNo() + "]");
        System.err.println(tId + "objectKey : [" + getFilePath(backUpApplDoc) +"]");
        System.err.println(tId + "targetFilePath : [" + getTargetFilePath(backUpApplDoc) +"]");
        System.err.println(tId + "Putting applInfo of Err Object back to the queue");
        applInfoQue.add(backUpApplDoc);
    }

}

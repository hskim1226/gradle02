package com.apexsoft.ysprj.sysadmin.domain;

import com.amazonaws.services.s3.AmazonS3Client;
import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.exception.YSBizException;
import com.apexsoft.ysprj.applicants.common.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by hanmomhanda on 15. 11. 12.
 */
public class ApplSlipConsumer extends AbstractS3Consumer {



    private static final Logger logger = LoggerFactory.getLogger(ApplSlipConsumer.class);

    public ApplSlipConsumer(AmazonS3Client s3Client,
                            String s3BucketName,
                            String s3MidPath,
                            int fileCount) {
        super(s3Client, s3BucketName, s3MidPath, fileCount);
    }

    @Override
    protected String getFilePath(BackUpApplDoc backUpApplDoc) {
        String uploadDirFullPath = FileUtil.getUploadDirectoryFullPath(baseDir, s3MidPath, backUpApplDoc.getAdmsNo(), backUpApplDoc.getUserId(), backUpApplDoc.getApplNo());
        String fullPath = FileUtil.getApplicationSlipFileFullPath(uploadDirFullPath, backUpApplDoc.getUserId());
        String filePath = fullPath.substring(baseDir.length() + 1);
        return filePath;
    }

    @Override
    protected String getTargetFilePath(BackUpApplDoc backUpApplDoc) {
        String slipFileName = FileUtil.getApplicationSlipFileName(backUpApplDoc.getUserId());
        StringBuilder sb = new StringBuilder();
        sb.append(s3MidPath).append("/")
            .append(slipFileName);
        return sb.toString();
    }

    @Override
    protected void handleException(Exception e, BackUpApplDoc backUpApplDoc) {
        ExecutionContext ec = new ExecutionContext(ExecutionContext.FAIL);
        logger.error(e.getMessage());
        logger.error("bucketName : [" + s3BucketName + "]");
        logger.error("applNo : [" + backUpApplDoc.getApplNo() + "]");
        logger.error("objectKey : [" + getFilePath(backUpApplDoc) +"]");
        throw new YSBizException(ec);
    }

}

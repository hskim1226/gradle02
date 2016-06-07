package com.apexsoft.ysprj.sysadmin.domain;

import com.apexsoft.ysprj.applicants.application.domain.Application;
import com.apexsoft.ysprj.applicants.common.util.FilePathUtil;
import com.apexsoft.ysprj.applicants.common.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;

/**
 * Created by go2zo on 16. 06. 07.
 */
public class ZippedFileConsumer extends AbstractS3Consumer {

    private static final Logger logger = LoggerFactory.getLogger(ZippedFileConsumer.class);

    public ZippedFileConsumer(String midPath,
                              int fileCount,
                              String backupDir) {
        super(midPath, fileCount, backupDir);
    }

    @Override
    protected String getFilePath(BackUpApplDoc backUpApplDoc) {
        Application application = new Application();
        application.setApplNo(backUpApplDoc.getApplNo());
        application.setUserId(backUpApplDoc.getUserId());
        application.setAdmsNo(backUpApplDoc.getAdmsNo());
        application.setApplId(backUpApplDoc.getApplId());
        application.setKorName(backUpApplDoc.getKorName());
        application.setEngName(backUpApplDoc.getEngName());
        application.setEngSur(backUpApplDoc.getEngSur());
        String filePath = FilePathUtil.getZippedFilePath(midPath, application);
        return filePath;
    }

    @Override
    protected String getTargetFilePath(BackUpApplDoc backUpApplDoc) {
        String applicantName = StringUtil.getEmptyIfNull(backUpApplDoc.getKorName()).equals(StringUtil.EMPTY_STRING) ?
                backUpApplDoc.getEngName() + "-" + backUpApplDoc.getEngSur() :
                backUpApplDoc.getKorName();
        String targetFilePath = new StringBuilder().append(midPath).append("/")
                .append(backUpApplDoc.getCampName()).append("/")
                .append(backUpApplDoc.getCollName()).append("/")
                .append(backUpApplDoc.getDeptName()).append("/")
                .append(FilePathUtil.getZippedFileName(backUpApplDoc.getApplNo(), backUpApplDoc.getUserId(), applicantName))
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
        System.err.println(tId + "applNo : [" + backUpApplDoc.getApplNo() + "]");
        System.err.println(tId + "filePath : [" + getFilePath(backUpApplDoc) +"]");
        System.err.println(tId + "targetFilePath : [" + getTargetFilePath(backUpApplDoc) +"]");
        System.err.println(tId + "Putting applInfo of Err Object back to the applInfoQue");
        applInfoQue.add(backUpApplDoc);
    }

}

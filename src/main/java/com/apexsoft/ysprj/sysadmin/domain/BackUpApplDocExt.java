package com.apexsoft.ysprj.sysadmin.domain;

/**
 * Created by go2zo on 2016. 6. 7..
 */
public class BackUpApplDocExt extends BackUpApplDoc {
    private String fileName;
    private String targetFileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String filePath) {
        this.fileName = filePath;
    }

    public String getTargetFileName() {
        return targetFileName;
    }

    public void setTargetFileName(String targetFileName) {
        this.targetFileName = targetFileName;
    }

    public BackUpApplDocExt(BackUpApplDoc applDoc) {
        setCampName(applDoc.getCampName());
        setCollName(applDoc.getCollName());
        setDeptName(applDoc.getDeptName());
        setUserId(applDoc.getUserId());
        setApplNo(applDoc.getApplNo());
        setApplId(applDoc.getApplId());
        setAdmsNo(applDoc.getAdmsNo());
        setKorName(applDoc.getKorName());
        setEngSur(applDoc.getEngSur());
        setEngName(applDoc.getEngName());
    }
}

package com.apexsoft.ysprj.applicants.application.domain;

import java.util.List;

/**
 * Created by DhKim on 2014-09-17.
 */
public class DocGroupFile {


    private DocGroupFile subGrp;
    private List<MandatoryNAppliedDoc> mandDocList;
    private String fileGroupName;
    private String groupMsg;


    private boolean subYn =false;

    public DocGroupFile getSubGrp() {
        return subGrp;
    }
    public void setSubGrp(DocGroupFile subGrp) {
        this.subGrp = subGrp;
    }
    public String getFileGroupName() {
        return fileGroupName;
    }

    public void setFileGroupName(String fileGroupName) {
        this.fileGroupName = fileGroupName;
    }

    public String getGroupMsg() {
        return groupMsg;
    }
    public void setGroupMsg(String groupMsg) {
        this.groupMsg = groupMsg;
    }
    public List<MandatoryNAppliedDoc> getMandDocList() {
        return mandDocList;
    }

    public void setMandDocList(List<MandatoryNAppliedDoc> mandDocList) {
        this.mandDocList = mandDocList;
    }
    public boolean isSubYn() {
        return subYn;
    }
    public void setSubYn(boolean subYn) {
        this.subYn = subYn;
    }


}

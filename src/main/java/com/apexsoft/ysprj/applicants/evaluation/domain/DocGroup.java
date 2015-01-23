package com.apexsoft.ysprj.applicants.evaluation.domain;

/**
 * Created by DhKim on 2014-09-17.
 */
public class DocGroup {

    private String fileGroupName;
    private int docGrp =0;
    private String groupMsg;
    private boolean subYn =false;

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


    public boolean isSubYn() {
        return subYn;
    }
    public void setSubYn(boolean subYn) {
        this.subYn = subYn;
    }

    public int getDocGrp() {
        return docGrp;
    }
    public void setDocGrp(int docGrp) {
        this.docGrp = docGrp;
    }

}

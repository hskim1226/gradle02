package com.apexsoft.ysprj.applicants.application.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DhKim on 2014-09-17.
 */
public class DocGroupFile {


    private ArrayList<DocGroupFile> subGrp = new ArrayList<DocGroupFile>();
    private List<TotalApplicationDocument> mandDocList;
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
    public List<TotalApplicationDocument> getMandDocList() {
        return mandDocList;
    }

    public void setMandDocList(List<TotalApplicationDocument> mandDocList) {
        this.mandDocList = mandDocList;
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

    public ArrayList<DocGroupFile> getSubGrp() {
        return subGrp;
    }

    public void setSubGrp(ArrayList<DocGroupFile> subGrp) {
        this.subGrp = subGrp;
    }
}

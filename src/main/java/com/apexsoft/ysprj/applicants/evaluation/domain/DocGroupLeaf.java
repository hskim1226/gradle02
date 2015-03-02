package com.apexsoft.ysprj.applicants.evaluation.domain;

import java.util.List;

/**
 * Created by DhKim on 2014-09-17.
 */
public class DocGroupLeaf extends DocGroup{

    private List<MandatoryNAppliedDoc> mandDocList;
    public DocGroupLeaf(String fileGroupName,int docGrpSeq, String groupMsg ,List mandDocList ){
        setSubYn(false);
        setFileGroupName(fileGroupName);
        setDocGrp(docGrpSeq);
        setGroupMsg(groupMsg);
        this.mandDocList = mandDocList;
    }
    public DocGroupLeaf(String fileGroupName,int docGrpSeq, String groupMsg ){
        setSubYn(false);
        setFileGroupName(fileGroupName);
        setDocGrp(docGrpSeq);
        setGroupMsg(groupMsg);
    }
    public  DocGroupLeaf(){
        new DocGroupNode("",0,"");
    }
    public DocGroupLeaf(String fileGroupName){
        new DocGroupNode(fileGroupName,0,"");
    }
    public DocGroupLeaf(String fileGroupName,int docGrp){
        new DocGroupNode(fileGroupName, docGrp,"");
    }

    public List<MandatoryNAppliedDoc> getMandDocList() {
        return mandDocList;
    }
    public void setMandDocList(List<MandatoryNAppliedDoc> mandDocList) {
        this.mandDocList = mandDocList;
    }

}

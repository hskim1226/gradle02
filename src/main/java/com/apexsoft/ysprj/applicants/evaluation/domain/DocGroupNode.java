package com.apexsoft.ysprj.applicants.evaluation.domain;

import com.apexsoft.ysprj.applicants.application.domain.MandatoryNAppliedDoc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DhKim on 2014-09-17.
 */
public class DocGroupNode extends DocGroup{

    private ArrayList<DocGroup> subGrp = new ArrayList<DocGroup>();

    public DocGroupNode(String fileGroupName,int docGrpSeq, String groupMsg ,ArrayList subGrp ){
        setSubYn(true);
        setFileGroupName(fileGroupName);
        setDocGrp(docGrpSeq);
        setGroupMsg(groupMsg);
        this.subGrp = subGrp;
    }
    public DocGroupNode(String fileGroupName,int docGrpSeq, String groupMsg ){
        setSubYn(true);
        setFileGroupName(fileGroupName);
        setDocGrp(docGrpSeq);
        setGroupMsg(groupMsg);
    }
    public  DocGroupNode(){
        new DocGroupNode("",0,"");
    }
    public DocGroupNode(String fileGroupName){
        new DocGroupNode(fileGroupName,0,"");
    }
    public DocGroupNode(String fileGroupName,int docGrp){
        new DocGroupNode(fileGroupName, docGrp,"");
    }

    public ArrayList<DocGroup> getSubGrp() {
        return subGrp;
    }
    public void setSubGrp(ArrayList<DocGroup> subGrp) {
        this.subGrp = subGrp;
    }

    public DocGroup getDocGroupByName(String groupName) {
        for (DocGroup tmpGroup : this.subGrp) {
            if (tmpGroup.getFileGroupName().equals(groupName)) {
                return tmpGroup;
            }
        }
        return null;
    }
    public void addDocGroup( DocGroup docGroup ){
        subGrp.add(docGroup);
    }

}

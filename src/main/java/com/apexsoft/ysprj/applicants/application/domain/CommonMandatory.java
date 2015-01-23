package com.apexsoft.ysprj.applicants.application.domain;

import java.util.List;

public class CommonMandatory {

    private String grpCode;
    private String grpName;
    private int grpLevel;
    private String itemCodeGrp;
    private String itemGrpName;
    private String itemCode;
    private String itemName;
    private String upCodeGrp;
    private String upCode;
    private String selCodeGrp;
    private String chnYn;
    private String lastYn;
    private String mdtYn;
    private String multiYn;
    private String uploadYn;
    private int sendCnt;
    private String orgnSendYn;
    private String tmpltYn;
    private String msgNo;

    private List<CommonMandatory> subMandList;

    public String getGrpCode() {
        return grpCode;
    }

    public void setGrpCode(String grpCode) {
        this.grpCode = grpCode;
    }

    public String getChnYn() {
        return chnYn;
    }

    public void setChnYn(String chnYn) {
        this.chnYn = chnYn;
    }

    public String getLastYn() {
        return lastYn;
    }

    public void setLastYn(String lastYn) {
        this.lastYn = lastYn;
    }

    public String getMdtYn() {
        return mdtYn;
    }

    public void setMdtYn(String mdtYn) {
        this.mdtYn = mdtYn;
    }

    public String getUploadYn() {
        return uploadYn;
    }

    public void setUploadYn(String uploadYn) {
        this.uploadYn = uploadYn;
    }

    public int getSendCnt() {
        return sendCnt;
    }

    public void setSendCnt(int sendCnt) {
        this.sendCnt = sendCnt;
    }

    public String getOrgnSendYn() {
        return orgnSendYn;
    }

    public void setOrgnSendYn(String orgnSendYn) {
        this.orgnSendYn = orgnSendYn;
    }

    public String getTmpltYn() {
        return tmpltYn;
    }

    public void setTmpltYn(String tmpltYn) {
        this.tmpltYn = tmpltYn;
    }

    public String getMsgNo() {
        return msgNo;
    }

    public void setMsgNo(String msgNo) {
        this.msgNo = msgNo;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getGrpName() {
        return grpName;
    }

    public void setGrpName(String grpName) {
        this.grpName = grpName;
    }

    public String getItemGrpName() {
        return itemGrpName;
    }

    public void setItemGrpName(String itemGrpName) {
        this.itemGrpName = itemGrpName;
    }

    private String belong;

    public String getBelong() {
        return belong;
    }

    public void setBelong(String belong) {
        this.belong = belong;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getUpCode() {        return upCode;    }

    public void setUpCode(String upCode) {        this.upCode = upCode;    }


    public String getMultiYn() {        return multiYn;    }

    public void setMultiYn(String multiYn) {        this.multiYn = multiYn;    }

    public int getGrpLevel() {
        return grpLevel;
    }

    public void setGrpLevel(int grpLevel) {
        this.grpLevel = grpLevel;
    }

    public String getItemCodeGrp() {
        return itemCodeGrp;
    }

    public void setItemCodeGrp(String itemCodeGrp) {
        this.itemCodeGrp = itemCodeGrp;
    }

    public String getUpCodeGrp() {
        return upCodeGrp;
    }

    public void setUpCodeGrp(String upCodeGrp) {
        this.upCodeGrp = upCodeGrp;
    }

    public String getSelCodeGrp() {
        return selCodeGrp;
    }

    public void setSelCodeGrp(String selCodeGrp) {
        this.selCodeGrp = selCodeGrp;
    }

    public List<CommonMandatory> getSubMandList() {
        return subMandList;
    }

    public void setSubMandList(List<CommonMandatory> subMandList) {
        this.subMandList = subMandList;
    }
}
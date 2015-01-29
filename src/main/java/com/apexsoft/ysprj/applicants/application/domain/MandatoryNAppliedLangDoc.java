package com.apexsoft.ysprj.applicants.application.domain;

public class MandatoryNAppliedLangDoc extends ApplicationDocument{

    //Mandatory  항목
    private String grpCode;
    private String grpName;
    private String itemGrpCode;
    private String itemGrpName;
    private String itemCode;
    private String itemName;
    private String chnYn;
    private String lastYn;
    private String mdtYn;
    private String uploadYn;
    private int sendCnt;
    private String orgnSendYn;
    private String tmpltYn;
    private String msgNo;

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

    public String getItemGrpCode() {
        return itemGrpCode;
    }

    public void setItemGrpCode(String itemGrpCode) {
        this.itemGrpCode = itemGrpCode;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }


    private String langExamName;//추가 항목

    //Application Language Key 항목
    private Integer applNo;
    private Integer langSeq;
    public Integer getApplNo() {
        return applNo;
    }
    public void setApplNo(Integer applNo) {
        this.applNo = applNo;
    }
    public Integer getLangSeq() {
        return langSeq;
    }
    public void setLangSeq(Integer langSeq) {
        this.langSeq = langSeq;
    }

    //Application Language 항목
    private String langExamCode;
    private String toflTypeCode;
    private String langGrad;
    private String examNo;
    private String examDay;
    private String exprDay;

    public String getLangExamName() {
        return langExamName;
    }

    public void setLangExamName(String langExamName) {
        this.langExamName = langExamName;
    }

    public String getLangExamCode() {
        return langExamCode;
    }


    public void setLangExamCode(String langExamCode) {
        this.langExamCode = langExamCode == null ? null : langExamCode.trim();
    }


    public String getToflTypeCode() {
        return toflTypeCode;
    }

    public void setToflTypeCode(String toflTypeCode) {
        this.toflTypeCode = toflTypeCode == null ? null : toflTypeCode.trim();
    }

    public String getLangGrad() {
        return langGrad;
    }

    public void setLangGrad(String langGrad) {
        this.langGrad = langGrad == null ? null : langGrad.trim();
    }

    public String getExamNo() {
        return examNo;
    }

    public void setExamNo(String examNo) {
        this.examNo = examNo == null ? null : examNo.trim();
    }

    public String getExamDay() {
        return examDay;
    }

    public void setExamDay(String examDay) {
        this.examDay = examDay == null ? null : examDay.trim();
    }

    public String getExprDay() {
        return exprDay;
    }

    public void setExprDay(String exprDay) {
        this.exprDay = exprDay == null ? null : exprDay.trim();
    }




}
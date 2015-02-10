package com.apexsoft.ysprj.applicants.application.domain;

import java.util.List;

public class LanguageGroup {

    private String examCodeGrp;
    private String examGrpName;
    private String examCode;
    private String selGrpCode;
    private int langSeq =0;
    private String mdtYn = "N";
    private String multiYn ="Y";
    private String canYn ="Y";
    private String msgNo;
    private String msg;
    private List<TotalApplicationLanguageContainer> langList;


    public String getExamCodeGrp() {
        return examCodeGrp;
    }

    public void setExamCodeGrp(String examCodeGrp) {
        this.examCodeGrp = examCodeGrp;
    }

    public String getExamGrpName() {
        return examGrpName;
    }

    public void setExamGrpName(String examGrpName) {
        this.examGrpName = examGrpName;
    }

    public String getExamCode() {
        return examCode;
    }

    public void setExamCode(String examCode) {
        this.examCode = examCode;
    }

    public String getSelGrpCode() {
        return selGrpCode;
    }

    public void setSelGrpCode(String selGrpCode) {
        this.selGrpCode = selGrpCode;
    }

    public int getLangSeq() {
        return langSeq;
    }

    public void setLangSeq(int langSeq) {
        this.langSeq = langSeq;
    }

    public String getMdtYn() {
        return mdtYn;
    }

    public void setMdtYn(String mdtYn) {
        this.mdtYn = mdtYn;
    }

    public String getMultiYn() {
        return multiYn;
    }

    public void setMultiYn(String multiYn) {
        this.multiYn = multiYn;
    }

    public String getCanYn() {
        return canYn;
    }

    public void setCanYn(String canYn) {
        this.canYn = canYn;
    }

    public String getMsgNo() {
        return msgNo;
    }

    public void setMsgNo(String msgNo) {
        this.msgNo = msgNo;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<TotalApplicationLanguageContainer> getLangList() {
        return langList;
    }

    public void setLangList(List<TotalApplicationLanguageContainer> langList) {
        this.langList = langList;
    }
}
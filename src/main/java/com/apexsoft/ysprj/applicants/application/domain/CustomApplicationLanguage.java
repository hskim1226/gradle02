package com.apexsoft.ysprj.applicants.application.domain;
/*
*  입력된 언어성적정보 +  전공별 언어성적요건정보
*/
public class CustomApplicationLanguage extends ApplicationLanguage {

    private String itemGrpCode;
    private String itemCode;
    private String itemName;
    private String upCodeGrp;
    private String selGrpCode;
    private String canYn = "N";
    private String mdtYn = "N";
    private String uploadYn = "N";
    private String originSentYn = "N";
    private String msgNo;
    private String msg;
    private boolean langInfoSaveFg =false;
    private String maxScr;

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

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getSelGrpCode() {
        return selGrpCode;
    }

    public void setSelGrpCode(String selGrpCode) {
        this.selGrpCode = selGrpCode;
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

    public String getOriginSentYn() {
        return originSentYn;
    }

    public void setOriginSentYn(String originSentYn) {
        this.originSentYn = originSentYn;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isLangInfoSaveFg() {
        return langInfoSaveFg;
    }

    public void setLangInfoSaveFg(boolean langInfoSaveFg) {
        this.langInfoSaveFg = langInfoSaveFg;
    }

    public String getUpCodeGrp() {        return upCodeGrp;    }

    public void setUpCodeGrp(String upCodeGrp) {        this.upCodeGrp = upCodeGrp;    }

    public String getMsgNo() {
        return msgNo;
    }

    public void setMsgNo(String msgNo) {
        this.msgNo = msgNo;
    }

    public String getCanYn() {
        return canYn;
    }

    public void setCanYn(String canYn) {
        this.canYn = canYn;
    }

    public String getMaxScr() {
        return maxScr;
    }

    public void setMaxScr(String maxScr) {
        this.maxScr = maxScr;
    }
}
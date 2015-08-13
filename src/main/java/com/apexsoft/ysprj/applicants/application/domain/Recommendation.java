package com.apexsoft.ysprj.applicants.application.domain;

/**
 * Created by hanmomhanda on 15. 8. 12.
 */
public class Recommendation {

    private int applNo;

    private int docSeq;

    private String profName;

    private String profMailAddr;

    private String reqText;

    private String recKey;

    private String recStsCode;

    public int getApplNo() {
        return applNo;
    }

    public void setApplNo(int applNo) {
        this.applNo = applNo;
    }

    public int getDocSeq() {
        return docSeq;
    }

    public void setDocSeq(int docSeq) {
        this.docSeq = docSeq;
    }

    public String getProfName() {
        return profName;
    }

    public void setProfName(String profName) {
        this.profName = profName;
    }

    public String getProfMailAddr() {
        return profMailAddr;
    }

    public void setProfMailAddr(String profMailAddr) {
        this.profMailAddr = profMailAddr;
    }

    public String getReqText() {
        return reqText;
    }

    public void setReqText(String reqText) {
        this.reqText = reqText;
    }

    public String getRecKey() {
        return recKey;
    }

    public void setRecKey(String recKey) {
        this.recKey = recKey;
    }

    public String getRecStsCode() {
        return recStsCode;
    }

    public void setRecStsCode(String recStsCode) {
        this.recStsCode = recStsCode;
    }
}

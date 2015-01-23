package com.apexsoft.ysprj.applicants.application.domain;

/**
 * Created by hanmomhanda on 14. 8. 28.
 */
public class ParamForCodeDocument {
    private int applNo;
    private String docTypeCode;
    private String grpCode;
    private int grpLevel;
    private String upCodeGrp;
    private String upCode;

    public int getApplNo() {
        return applNo;
    }

    public void setApplNo(int applNo) {
        this.applNo = applNo;
    }

    public String getDocTypeCode() {
        return docTypeCode;
    }

    public void setDocTypeCode(String docTypeCode) {
        this.docTypeCode = docTypeCode;
    }

    public String getGrpCode() {
        return grpCode;
    }

    public void setGrpCode(String grpCode) {
        this.grpCode = grpCode;
    }

    public int getGrpLevel() {
        return grpLevel;
    }

    public void setGrpLevel(int grpLevel) {
        this.grpLevel = grpLevel;
    }

    public String getUpCodeGrp() {
        return upCodeGrp;
    }

    public void setUpCodeGrp(String upCodeGrp) {
        this.upCodeGrp = upCodeGrp;
    }

    public String getUpCode() {
        return upCode;
    }

    public void setUpCode(String upCode) {
        this.upCode = upCode;
    }
}

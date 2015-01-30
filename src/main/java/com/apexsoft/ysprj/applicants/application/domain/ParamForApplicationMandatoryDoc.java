package com.apexsoft.ysprj.applicants.application.domain;

/**
 * Created by hanmomhanda on 14. 9. 14.
 */
public class ParamForApplicationMandatoryDoc {



    private String admsNo;
    private String deptCode;
    private int admsCorsNo;
    private String detlMajCode;
    private String corsTypeCode;
    private String grpCode;
    private int grpLevel;
    private String upCodeGrp;
    private String upCode;



    public int getAdmsCorsNo() {
        return admsCorsNo;
    }

    public void setAdmsCorsNo(int admsCorsNo) {
        this.admsCorsNo = admsCorsNo;
    }

    public String getDetlMajCode() {
        return detlMajCode;
    }

    public void setDetlMajCode(String detlMajCode) {
        this.detlMajCode = detlMajCode;
    }

    public String getAdmsNo() {
        return admsNo;
    }

    public void setAdmsNo(String admsNo) {
        this.admsNo = admsNo;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getCorsTypeCode() {
        return corsTypeCode;
    }

    public void setCorsTypeCode(String corsTypeCode) {
        this.corsTypeCode = corsTypeCode;
    }

    public String getGrpCode() { return grpCode; }

    public void setGrpCode(String grpCode) { this.grpCode = grpCode; }


    public int getGrpLevel() { return grpLevel;  }

    public void setGrpLevel(int grpLevel) { this.grpLevel = grpLevel;  }

    public String getUpCodeGrp() {  return upCodeGrp;    }

    public void setUpCodeGrp(String upCodeGrp) {     this.upCodeGrp = upCodeGrp;    }

    public String getUpCode() {        return upCode;    }

    public void setUpCode(String upCode) {        this.upCode = upCode;    }
}

package com.apexsoft.ysprj.applicants.evaluation.domain;

import java.util.List;

/**
 * Created by hanmomhanda on 14. 9. 14.
 */
public class ParamForApplicationMandatoryDoc {

    private int admsCorsNo;
    private String detlMajCode;
    private String admsNo;
    private String deptCode;
    private String corsTypeCode;

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
}

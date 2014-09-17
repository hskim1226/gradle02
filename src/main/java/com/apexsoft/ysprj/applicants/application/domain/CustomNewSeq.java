package com.apexsoft.ysprj.applicants.application.domain;

/**
 * Created by hanmomhanda on 14. 9. 16.
 */
public class CustomNewSeq {
    private String admsNo;
    private String deptCode;
    private String corsTypeCode;
    private int newSeq;

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

    public int getNewSeq() {
        return newSeq;
    }

    public void setNewSeq(int newSeq) {
        this.newSeq = newSeq;
    }
}

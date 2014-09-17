package com.apexsoft.ysprj.applicants.application.domain;

/**
 * Created by hanmomhanda on 14. 9. 17.
 */
public class CustomApplNoStsCode {

    private int applNo;
    private String applStsCode;
    private String errCode;

    public CustomApplNoStsCode(int applNo, String applStsCode) {
        this.applNo = applNo;
        this.applStsCode = applStsCode;
    }

    public CustomApplNoStsCode(int applNo, String applStsCode, String errCode) {
        this.applNo = applNo;
        this.applStsCode = applStsCode;
        this.errCode = errCode;
    }

    public int getApplNo() {
        return applNo;
    }

    public void setApplNo(int applNo) {
        this.applNo = applNo;
    }

    public String getApplStsCode() {
        return applStsCode;
    }

    public void setApplStsCode(String applStsCode) {
        this.applStsCode = applStsCode;
    }
}

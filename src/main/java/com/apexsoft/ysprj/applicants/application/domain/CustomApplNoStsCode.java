package com.apexsoft.ysprj.applicants.application.domain;

/**
 * Created by hanmomhanda on 14. 9. 17.
 */
public class CustomApplNoStsCode {

    private int applNo;
    private String applStsCode;
    private String admsNo;
    private String entrYear;
    private String admsTypeCode;
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

    public CustomApplNoStsCode(int applNo, String applStsCode,
                               String admsNo, String entrYear,
                               String admsTypeCode) {
        this.applNo = applNo;
        this.applStsCode = applStsCode;
        this.admsNo = admsNo;
        this.entrYear = entrYear;
        this.admsTypeCode = admsTypeCode;
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

    public String getAdmsNo() {
        return admsNo;
    }

    public void setAdmsNo(String admsNo) {
        this.admsNo = admsNo;
    }

    public String getEntrYear() {
        return entrYear;
    }

    public void setEntrYear(String entrYear) {
        this.entrYear = entrYear;
    }

    public String getAdmsTypeCode() {
        return admsTypeCode;
    }

    public void setAdmsTypeCode(String admsTypeCode) {
        this.admsTypeCode = admsTypeCode;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }
}

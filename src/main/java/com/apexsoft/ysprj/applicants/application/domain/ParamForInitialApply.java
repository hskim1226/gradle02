package com.apexsoft.ysprj.applicants.application.domain;

/**
 * Created by hanmomhanda on 14. 8. 26.
 */
public class ParamForInitialApply {

    private String userId;
    private String admsNo;
    private String applStsCode;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAdmsNo() {
        return admsNo;
    }

    public void setAdmsNo(String admsNo) {
        this.admsNo = admsNo;
    }

    public String getApplStsCode() {
        return applStsCode;
    }

    public void setApplStsCode(String applStsCode) {
        this.applStsCode = applStsCode;
    }
}

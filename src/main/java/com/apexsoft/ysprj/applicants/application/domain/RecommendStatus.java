package com.apexsoft.ysprj.applicants.application.domain;

/**
 * Created by hanmomhanda on 15. 8. 24.
 */
public enum RecommendStatus {

    TEMP ("00001"), SENT ("00002"), OPENED ("00003"), COMPLETED ("00004");

    private String codeVal;

    private RecommendStatus() {

    }

    private RecommendStatus(String codeVal) {
        this.codeVal = codeVal;
    }

    public String codeVal() {
        return this.codeVal;
    }
}

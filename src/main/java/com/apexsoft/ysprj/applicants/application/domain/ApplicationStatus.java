package com.apexsoft.ysprj.applicants.application.domain;

/**
 * Created by hanmomhanda on 16. 1. 12.
 */
public enum ApplicationStatus {
    BASIS_SAVED ("00001"),
    ACAD_SAVED ("00002"),
    LANG_CAREER_SAVED ("00003"),
    DOC_SAVED ("00004"),
    SUBMITTED ("00010"),
    COMPLETED ("00020"),
    PAYMENT_WAIT ("00021"),
    CANCEL ("00022")
    ;

    private String codeVal;

    ApplicationStatus(String codeVal) {
        this.codeVal = codeVal;
    }

    public String codeVal() {
        return this.codeVal;
    }
}

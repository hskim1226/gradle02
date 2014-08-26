package com.apexsoft.ysprj.applicants.common.domain;

/**
 * Created by hanmomhanda on 14. 8. 26.
 *
 * 학과에 따른 과정 코드, 과정 이름
 */
public class CodeNameCourse {

    private String corsTypeCode;
    private String codeVal;

    public String getCorsTypeCode() {
        return corsTypeCode;
    }

    public void setCorsTypeCode(String corsTypeCode) {
        this.corsTypeCode = corsTypeCode;
    }

    public String getCodeVal() {
        return codeVal;
    }

    public void setCodeVal(String codeVal) {
        this.codeVal = codeVal;
    }
}

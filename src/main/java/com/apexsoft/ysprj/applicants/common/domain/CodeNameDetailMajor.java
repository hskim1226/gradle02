package com.apexsoft.ysprj.applicants.common.domain;

/**
 * Created by hanmomhanda on 14. 8. 26.
 *
 * 학과, 과정에 따른 세부 전공 코드, 세부 전공 이름
 */
public class CodeNameDetailMajor {

    private String detlMajCode;
    private String detlMajName;
    private String detlMajDesc;
    private String partTimeYn;

    public String getDetlMajCode() {
        return detlMajCode;
    }

    public void setDetlMajCode(String detlMajCode) {
        this.detlMajCode = detlMajCode;
    }

    public String getDetlMajName() {
        return detlMajName;
    }

    public void setDetlMajName(String detlMajName) {
        this.detlMajName = detlMajName;
    }

    public String getDetlMajDesc() {
        return detlMajDesc;
    }

    public void setDetlMajDesc(String detlMajDesc) {
        this.detlMajDesc = detlMajDesc;
    }

    public String getPartTimeYn() {
        return partTimeYn;
    }

    public void setPartTimeYn(String partTimeYn) {
        this.partTimeYn = partTimeYn;
    }
}

package com.apexsoft.ysprj.applicants.common.domain;

/**
 * Created by hanmomhanda on 14. 8. 26.
 *
 * 원서 작성 화면에서 캠퍼스, 대학, 지원학과, 지원과정, 세부전공 등을 조회할 때
 * 조회 조건으로 쓰이는 객체
 */
public class ParamForSetupCourses {

    private String admsNo;
    private String campCode;
    private String collCode;
    private String deptCode;
    private String corsTypeCode;
    private String ariInstCode;

    public String getAdmsNo() {
        return admsNo;
    }

    public void setAdmsNo(String admsNo) {
        this.admsNo = admsNo;
    }

    public String getCampCode() {
        return campCode;
    }

    public void setCampCode(String campCode) {
        this.campCode = campCode;
    }

    public String getCollCode() {
        return collCode;
    }

    public void setCollCode(String collCode) {
        this.collCode = collCode;
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

    public String getAriInstCode() {
        return ariInstCode;
    }

    public void setAriInstCode(String ariInstCode) {
        this.ariInstCode = ariInstCode;
    }
}

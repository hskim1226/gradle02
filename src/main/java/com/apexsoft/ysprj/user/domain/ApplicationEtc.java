package com.apexsoft.ysprj.user.domain;

/**
 * Created by hanmomhanda on 14. 8. 21.
 * TABLE : APPL_ETC
 */
public class ApplicationEtc {

    private int applNo;         // 입학지원번호
    private String covLett;     // 자기소개서
    private String studPlan;    // 학업계획서
    private String creId;       // 생성자
    private String creDate;     // 생성일자
    private String modId;       // 수정자
    private String modDate;     // 수정일자

    public int getApplNo() {
        return applNo;
    }

    public void setApplNo(int applNo) {
        this.applNo = applNo;
    }

    public String getCovLett() {
        return covLett;
    }

    public void setCovLett(String covLett) {
        this.covLett = covLett;
    }

    public String getStudPlan() {
        return studPlan;
    }

    public void setStudPlan(String studPlan) {
        this.studPlan = studPlan;
    }

    public String getCreId() {
        return creId;
    }

    public void setCreId(String creId) {
        this.creId = creId;
    }

    public String getCreDate() {
        return creDate;
    }

    public void setCreDate(String creDate) {
        this.creDate = creDate;
    }

    public String getModId() {
        return modId;
    }

    public void setModId(String modId) {
        this.modId = modId;
    }

    public String getModDate() {
        return modDate;
    }

    public void setModDate(String modDate) {
        this.modDate = modDate;
    }
}

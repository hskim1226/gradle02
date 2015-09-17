package com.apexsoft.ysprj.applicants.application.domain;

/**
 * Created by hanmomhanda on 15. 8. 15.
 */
public class RecommendationApplicationInfo {

    private int recNo;

    private int applNo;

    private int recSeq;

    private String admsNo;

    private String userId;

    private String korName;

    private String chnName;

    private String engName;

    private String nationality;

    private String degree;

    private String dept;

    private String recStsCode;

    private String fileUploadedYn;

    public int getRecNo() {
        return recNo;
    }

    public void setRecNo(int recNo) {
        this.recNo = recNo;
    }

    public int getApplNo() {
        return applNo;
    }

    public void setApplNo(int applNo) {
        this.applNo = applNo;
    }

    public int getRecSeq() {
        return recSeq;
    }

    public void setRecSeq(int recSeq) {
        this.recSeq = recSeq;
    }

    public String getAdmsNo() {
        return admsNo;
    }

    public void setAdmsNo(String admsNo) {
        this.admsNo = admsNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getKorName() {
        return korName;
    }

    public void setKorName(String korName) {
        this.korName = korName;
    }

    public String getChnName() {
        return chnName;
    }

    public void setChnName(String chnName) {
        this.chnName = chnName;
    }

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getRecStsCode() {
        return recStsCode;
    }

    public void setRecStsCode(String recStsCode) {
        this.recStsCode = recStsCode;
    }

    public String getFileUploadedYn() {
        return fileUploadedYn;
    }

    public void setFileUploadedYn(String fileUploadedYn) {
        this.fileUploadedYn = fileUploadedYn;
    }
}

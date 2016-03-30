package com.apexsoft.ysprj.applicants.application.domain;

import java.util.Date;

/**
 * Created by hanmomhanda on 15. 8. 12.
 */
public class Recommendation {

    private Integer recNo;

    private Integer applNo;

    private Integer recSeq;

    private String applicantName;

    private String applicantNationality;

    private String degree;

    private String major;

    private String profName;

    private String profMailAddr;

    private String profInst;

    private String profMaj;

    private String profPhone;

    private String reqSubject;

    private String recKey;

    private String mailContents;

    private String recStsCode;

    private String recStsName;

    private String recStsNameXxen;

    private String dueDate;

    private String fileUploadedYn;

    private String creId;

    private Date creDate;

    private String modId;

    private Date modDate;

    public Integer getRecNo() {
        return recNo;
    }

    public void setRecNo(Integer recNo) {
        this.recNo = recNo;
    }

    public Integer getApplNo() {
        return applNo;
    }

    public void setApplNo(Integer applNo) {
        this.applNo = applNo;
    }

    public Integer getRecSeq() {
        return recSeq;
    }

    public void setRecSeq(Integer recSeq) {
        this.recSeq = recSeq;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getApplicantNationality() {
        return applicantNationality;
    }

    public void setApplicantNationality(String applicantNationality) {
        this.applicantNationality = applicantNationality;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getProfName() {
        return profName;
    }

    public void setProfName(String profName) {
        this.profName = profName;
    }

    public String getProfMailAddr() {
        return profMailAddr;
    }

    public void setProfMailAddr(String profMailAddr) {
        this.profMailAddr = profMailAddr;
    }

    public String getProfInst() {
        return profInst;
    }

    public void setProfInst(String profInst) {
        this.profInst = profInst;
    }

    public String getProfMaj() {
        return profMaj;
    }

    public void setProfMaj(String profMaj) {
        this.profMaj = profMaj;
    }

    public String getProfPhone() {
        return profPhone;
    }

    public void setProfPhone(String profPhone) {
        this.profPhone = profPhone;
    }

    public String getReqSubject() {
        return reqSubject;
    }

    public void setReqSubject(String reqSubject) {
        this.reqSubject = reqSubject;
    }

    public String getRecKey() {
        return recKey;
    }

    public void setRecKey(String recKey) {
        this.recKey = recKey;
    }

    public String getMailContents() {
        return mailContents;
    }

    public void setMailContents(String mailContents) {
        this.mailContents = mailContents;
    }

    public String getRecStsCode() {
        return recStsCode;
    }

    public void setRecStsCode(String recStsCode) {
        this.recStsCode = recStsCode;
    }

    public String getRecStsName() {
        return recStsName;
    }

    public void setRecStsName(String recStsName) {
        this.recStsName = recStsName;
    }

    public String getRecStsNameXxen() {
        return recStsNameXxen;
    }

    public void setRecStsNameXxen(String recStsNameXxen) {
        this.recStsNameXxen = recStsNameXxen;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getFileUploadedYn() {
        return fileUploadedYn;
    }

    public void setFileUploadedYn(String fileUploadedYn) {
        this.fileUploadedYn = fileUploadedYn;
    }

    public String getCreId() {
        return creId;
    }

    public void setCreId(String creId) {
        this.creId = creId;
    }

    public Date getCreDate() {
        return creDate;
    }

    public void setCreDate(Date creDate) {
        this.creDate = creDate;
    }

    public String getModId() {
        return modId;
    }

    public void setModId(String modId) {
        this.modId = modId;
    }

    public Date getModDate() {
        return modDate;
    }

    public void setModDate(Date modDate) {
        this.modDate = modDate;
    }

    public boolean isEditable() {
        boolean isEditable = false;
        if (RecommendStatus.TEMP.codeVal().equals(recStsCode)
                || RecommendStatus.SENT.codeVal().equals(recStsCode)
                || RecommendStatus.OPENED.codeVal().equals(recStsCode)) {
            isEditable = true;
        }
        return isEditable;
    }
}

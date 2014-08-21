package com.apexsoft.ysprj.user.domain;

import java.util.Date;

/**
 * Created by go2zo on 2014. 8. 17.
 * TABLE : APPL_ACAD
 */
public class ApplicationAcademy {

    private String applNo;         // 입학지원번호
    private String acadSeq;        // 학력순번
    private String acadTypeCode;   // 학력구분코드
    private String entrDay;        // 입학일자
    private String grdaDay;        // 졸업일자
    private String schlUnivCode;   // 출신대학코드
    private String schlName;       // 출신학교 이름
    private String majName;        // 전공학과 이름
    private String dualMajName;    // 복수전공학과 이름
    private String grdaTypeCode;   // 졸업구분코드
    private String qualExamDay;    // 검정고시합격일
    private String qualAreaCode;   // 검정고시합격지구코드
    private String creId;          // 생성자
    private Date   creDate;        // 생성일자
    private String modId;          // 수정자
    private Date   modDate;        // 수정일자

    public String getApplNo() {
        return applNo;
    }

    public void setApplNo(String applNo) {
        this.applNo = applNo;
    }

    public String getAcadSeq() {
        return acadSeq;
    }

    public void setAcadSeq(String acadSeq) {
        this.acadSeq = acadSeq;
    }

    public String getAcadTypeCode() {
        return acadTypeCode;
    }

    public void setAcadTypeCode(String acadTypeCode) {
        this.acadTypeCode = acadTypeCode;
    }

    public String getEntrDay() {
        return entrDay;
    }

    public void setEntrDay(String entrDay) {
        this.entrDay = entrDay;
    }

    public String getGrdaDay() {
        return grdaDay;
    }

    public void setGrdaDay(String grdaDay) {
        this.grdaDay = grdaDay;
    }

    public String getSchlUnivCode() {
        return schlUnivCode;
    }

    public void setSchlUnivCode(String schlUnivCode) {
        this.schlUnivCode = schlUnivCode;
    }

    public String getSchlName() {
        return schlName;
    }

    public void setSchlName(String schlName) {
        this.schlName = schlName;
    }

    public String getMajName() {
        return majName;
    }

    public void setMajName(String majName) {
        this.majName = majName;
    }

    public String getDualMajName() {
        return dualMajName;
    }

    public void setDualMajName(String dualMajName) {
        this.dualMajName = dualMajName;
    }

    public String getGrdaTypeCode() {
        return grdaTypeCode;
    }

    public void setGrdaTypeCode(String grdaTypeCode) {
        this.grdaTypeCode = grdaTypeCode;
    }

    public String getQualExamDay() {
        return qualExamDay;
    }

    public void setQualExamDay(String qualExamDay) {
        this.qualExamDay = qualExamDay;
    }

    public String getQualAreaCode() {
        return qualAreaCode;
    }

    public void setQualAreaCode(String qualAreaCode) {
        this.qualAreaCode = qualAreaCode;
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
}

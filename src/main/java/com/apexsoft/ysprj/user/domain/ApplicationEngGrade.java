package com.apexsoft.ysprj.user.domain;

/**
 * TABLE : APPL_FORL
 */
public class ApplicationEngGrade {

    private int applNo;             // 입학지원번호
    private String forlSeq;         // 외국어 성적 순번
    private String forlTypeCode;    // 외국어 성적 구분코드
    private String toflTypeCode;    // 토플 유형 구분코드
    private String forlGrad;        // 성적
    private String examDay;         // 응시일자
    private String exprDay;         // 만료일자
    private String creId;           // 생성자
    private String creDate;         // 생성일
    private String modId;           // 수정자
    private String modDate;         // 수정일

    public int getApplNo() {
        return applNo;
    }

    public void setApplNo(int applNo) {
        this.applNo = applNo;
    }

    public String getForlSeq() {
        return forlSeq;
    }

    public void setForlSeq(String forlSeq) {
        this.forlSeq = forlSeq;
    }

    public String getForlTypeCode() {
        return forlTypeCode;
    }

    public void setForlTypeCode(String forlTypeCode) {
        this.forlTypeCode = forlTypeCode;
    }

    public String getToflTypeCode() {
        return toflTypeCode;
    }

    public void setToflTypeCode(String toflTypeCode) {
        this.toflTypeCode = toflTypeCode;
    }

    public String getForlGrad() {
        return forlGrad;
    }

    public void setForlGrad(String forlGrad) {
        this.forlGrad = forlGrad;
    }

    public String getExamDay() {
        return examDay;
    }

    public void setExamDay(String examDay) {
        this.examDay = examDay;
    }

    public String getExprDay() {
        return exprDay;
    }

    public void setExprDay(String exprDay) {
        this.exprDay = exprDay;
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

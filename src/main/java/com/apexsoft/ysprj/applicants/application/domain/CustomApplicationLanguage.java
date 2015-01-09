package com.apexsoft.ysprj.applicants.application.domain;

import java.util.Date;

public class CustomApplicationLanguage  {

    private String langExamName;//추가 항목

    //Application Language Key 항목
    private Integer applNo;
    private Integer langSeq;
    public Integer getApplNo() {
        return applNo;
    }
    public void setApplNo(Integer applNo) {
        this.applNo = applNo;
    }
    public Integer getLangSeq() {
        return langSeq;
    }
    public void setLangSeq(Integer langSeq) {
        this.langSeq = langSeq;
    }

    //Application Language 항목
    private String langExamCode;
    private String toflTypeCode;
    private String langGrad;
    private String examNo;
    private String examDay;
    private String exprDay;

    public String getLangExamName() {
        return langExamName;
    }

    public void setLangExamName(String langExamName) {
        this.langExamName = langExamName;
    }

    public String getLangExamCode() {
        return langExamCode;
    }


    public void setLangExamCode(String langExamCode) {
        this.langExamCode = langExamCode == null ? null : langExamCode.trim();
    }


    public String getToflTypeCode() {
        return toflTypeCode;
    }

    public void setToflTypeCode(String toflTypeCode) {
        this.toflTypeCode = toflTypeCode == null ? null : toflTypeCode.trim();
    }

    public String getLangGrad() {
        return langGrad;
    }

    public void setLangGrad(String langGrad) {
        this.langGrad = langGrad == null ? null : langGrad.trim();
    }

    public String getExamNo() {
        return examNo;
    }

    public void setExamNo(String examNo) {
        this.examNo = examNo == null ? null : examNo.trim();
    }

    public String getExamDay() {
        return examDay;
    }

    public void setExamDay(String examDay) {
        this.examDay = examDay == null ? null : examDay.trim();
    }

    public String getExprDay() {
        return exprDay;
    }

    public void setExprDay(String exprDay) {
        this.exprDay = exprDay == null ? null : exprDay.trim();
    }


}
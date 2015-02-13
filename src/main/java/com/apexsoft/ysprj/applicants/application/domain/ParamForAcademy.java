package com.apexsoft.ysprj.applicants.application.domain;

/**
 * Created by hanmomhanda on 14. 8. 28.
 */
public class ParamForAcademy {
    private int applNo;
    private String acadTypeCode;
    private int acadSeq;
    private String orderBy;

    public int getApplNo() {
        return applNo;
    }

    public void setApplNo(int applNo) {
        this.applNo = applNo;
    }

    public String getAcadTypeCode() {
        return acadTypeCode;
    }

    public void setAcadTypeCode(String acadTypeCode) {
        this.acadTypeCode = acadTypeCode;
    }

    public int getAcadSeq() {
        return acadSeq;
    }

    public void setAcadSeq(int acadSeq) {
        this.acadSeq = acadSeq;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}

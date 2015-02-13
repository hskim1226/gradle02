package com.apexsoft.ysprj.admin.control.form;

/**
 * Created by DhKim on 2014-09-14.
 */
public class ChangeInfoForm {

    private int applNo = 0;
    private String admsNo;
    private String befVal = null;
    private String aftVal = null;
    private String infoRadio = null;
    private String colName;
    private String cnclResn;


    public String getAdmsNo() {
        return admsNo;
    }
    public void setAdmsNo(String admsNo) {
        this.admsNo = admsNo;
    }


    public String getInfoRadio() {
        return infoRadio;
    }

    public void setInfoRadio(String infoRadio) {
        this.infoRadio = infoRadio;
    }


    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

    public int getApplNo() {
        return applNo;
    }

    public void setApplNo(int applNo) {
        this.applNo = applNo;
    }

    public String getCnclResn() {
        return cnclResn;
    }

    public void setCnclResn(String cnclResn) {
        this.cnclResn = cnclResn;
    }

    public String getBefVal() {
        return befVal;
    }

    public void setBefVal(String befVal) {
        this.befVal = befVal;
    }

    public String getAftVal() {
        return aftVal;
    }

    public void setAftVal(String aftVal) {
        this.aftVal = aftVal;
    }
}

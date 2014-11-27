package com.apexsoft.ysprj.admin.domain;


public class ChangeInfo extends ApplicationChange {

    private String campName;
    private String collName;
    private String deptName;
    private String admsTypeName;
    private String korName;
    private String applChgName;
    private String chgStsName;
    private String beforeInfo;
    private String afterInfo;

    public String getAfterInfo() {
        return afterInfo;
    }

    public void setAfterInfo(String afterInfo) {
        this.afterInfo = afterInfo;
    }

    public String getCampName() {
        return campName;
    }

    public void setCampName(String campName) {
        this.campName = campName;
    }

    public String getCollName() {
        return collName;
    }

    public void setCollName(String collName) {
        this.collName = collName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getAdmsTypeName() {
        return admsTypeName;
    }

    public void setAdmsTypeName(String admsTypeName) {
        this.admsTypeName = admsTypeName;
    }

    public String getKorName() {
        return korName;
    }

    public void setKorName(String korName) {
        this.korName = korName;
    }

    public String getApplChgName() {
        return applChgName;
    }

    public void setApplChgName(String applChgName) {
        this.applChgName = applChgName;
    }

    public String getChgStsName() {
        return chgStsName;
    }

    public void setChgStsName(String chgStsName) {
        this.chgStsName = chgStsName;
    }

    public String getBeforeInfo() {
        return beforeInfo;
    }

    public void setBeforeInfo(String beforeInfo) {
        this.beforeInfo = beforeInfo;
    }
}
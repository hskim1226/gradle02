package com.apexsoft.ysprj.admin.domain;

import com.apexsoft.ysprj.applicants.application.domain.Application;
import com.apexsoft.ysprj.applicants.application.domain.ApplicationDocument;

import java.util.Date;
import java.util.List;

public class ApplicantInfo extends Application{
    

    private String admsName;
    private String admsType;
    private String admsTypeName;
    private String applAttrName;
    private String campCode;
    private String campName;
    private String collName;
    private String deptName;
    private String ariInstName;
    private String corsTypeName;  //
    private String detlMajName; //

    private String emerContCode;
	private String emerContName;
    private String emerContTel;
    private String applStsName;

    private String citzCntrCodeName;
    private String checkYn;
    private String payTypeCode;
    private String payTypeName;
    private int admsFee;

    public String getAdmsName() {
        return admsName;
    }

    public void setAdmsName(String admsName) {
        this.admsName = admsName;
    }

    public String getAdmsTypeName() {
        return admsTypeName;
    }

    public void setAdmsTypeName(String admsTypeName) {
        this.admsTypeName = admsTypeName;
    }

    public String getApplAttrName() {
        return applAttrName;
    }

    public void setApplAttrName(String applAttrName) {
        this.applAttrName = applAttrName;
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

    public String getAriInstName() {
        return ariInstName;
    }

    public void setAriInstName(String ariInstName) {
        this.ariInstName = ariInstName;
    }

    public String getCorsTypeName() {
        return corsTypeName;
    }

    public void setCorsTypeName(String corsTypeName) {
        this.corsTypeName = corsTypeName;
    }

    public String getDetlMajName() {
        return detlMajName;
    }

    public void setDetlMajName(String detlMajName) {
        this.detlMajName = detlMajName;
    }

    public String getEmerContCode() {
        return emerContCode;
    }

    public void setEmerContCode(String emerContCode) {
        this.emerContCode = emerContCode;
    }

    public String getEmerContName() {
        return emerContName;
    }

    public void setEmerContName(String emerContName) {
        this.emerContName = emerContName;
    }

    public String getEmerContTel() {
        return emerContTel;
    }

    public void setEmerContTel(String emerContTel) {
        this.emerContTel = emerContTel;
    }

    public String getApplStsName() {
        return applStsName;
    }

    public void setApplStsName(String applStsName) {
        this.applStsName = applStsName;
    }

    public String getCitzCntrCodeName() {
        return citzCntrCodeName;
    }

    public void setCitzCntrCodeName(String citzCntrCodeName) {
        this.citzCntrCodeName = citzCntrCodeName;
    }

    public String getCheckYn() {
        return checkYn;
    }

    public void setCheckYn(String checkYn) {
        this.checkYn = checkYn;
    }

    public String getAdmsType() {
        return admsType;
    }

    public void setAdmsType(String admsType) {
        this.admsType = admsType;
    }

    public String getPayTypeCode() {
        return payTypeCode;
    }

    public void setPayTypeCode(String payTypeCode) {
        this.payTypeCode = payTypeCode;
    }

    public String getPayTypeName() {
        return payTypeName;
    }

    public void setPayTypeName(String payTypeName) {
        this.payTypeName = payTypeName;
    }

    public int getAdmsFee() {
        return admsFee;
    }

    public void setAdmsFee(int admsFee) {
        this.admsFee = admsFee;
    }

    @Override
    public String getCampCode() {
        return campCode;
    }

    @Override
    public void setCampCode(String campCode) {
        this.campCode = campCode;
    }
}

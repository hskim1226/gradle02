package com.apexsoft.ysprj.user.domain;

import java.util.Date;

public class Users {

    private String userId;
    private String userType;
    private String name;
    private String bornDay;
    private String gend;
    private String mobiNum;
    private String pswd;
    private String mailAddr;
    private String smsRecvYn;
    private String mailRecvYn;
    private String userDivsCode;
    private String userAgreYn;
    private String privInfoYn;
    private String userStsCode;
    private boolean enabled;
    private String creId;
    private Date   creDate;
    private String modId;
    private Date   modDate;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBornDay() {
        return bornDay;
    }

    public void setBornDay(String bornDay) {
        this.bornDay = bornDay;
    }

    public String getGend() {
        return gend;
    }

    public void setGend(String gend) {
        this.gend = gend;
    }

    public String getMobiNum() {
        return mobiNum;
    }

    public void setMobiNum(String mobiNum) {
        this.mobiNum = mobiNum;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public String getMailAddr() {
        return mailAddr;
    }

    public void setMailAddr(String mailAddr) {
        this.mailAddr = mailAddr;
    }

    public String getSmsRecvYn() {
        return smsRecvYn;
    }

    public void setSmsRecvYn(String smsRecvYn) {
        this.smsRecvYn = smsRecvYn;
    }

    public String getMailRecvYn() {
        return mailRecvYn;
    }

    public void setMailRecvYn(String mailRecvYn) {
        this.mailRecvYn = mailRecvYn;
    }

    public String getUserDivsCode() {
        return userDivsCode;
    }

    public void setUserDivsCode(String userDivsCode) {
        this.userDivsCode = userDivsCode;
    }

    public String getUserAgreYn() {
        return userAgreYn;
    }

    public void setUserAgreYn(String userAgreYn) {
        this.userAgreYn = userAgreYn;
    }

    public String getPrivInfoYn() {
        return privInfoYn;
    }

    public void setPrivInfoYn(String privInfoYn) {
        this.privInfoYn = privInfoYn;
    }

    public String getUserStsCode() {
        return userStsCode;
    }

    public void setUserStsCode(String userStsCode) {
        this.userStsCode = userStsCode;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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

    @Override
    public String toString() {
        return "Users{" +
                "userId=" + userId +
                ", userType=" + userType +
                ", name=" + name +
                ", bornDay=" + bornDay +
                ", gend=" + gend +
                ", mobiNum=" + mobiNum +
                ", pswd=" + pswd +
                ", mailAddr=" + mailAddr +
                ", smsRecvYn=" + smsRecvYn +
                ", mailRecvYn=" + mailRecvYn +
                ", userDivsCode=" + userDivsCode +
                ", userAgreYn=" + userAgreYn +
                ", privInfoYn=" + privInfoYn +
                ", userStsCode=" + userStsCode +
                ", enable=" + enabled +
                ", creId=" + creId +
                ", createDate=" + creDate +
                ", modId=" + modId +
                ", modifyDate=" + modDate +
                "}";
    }
}

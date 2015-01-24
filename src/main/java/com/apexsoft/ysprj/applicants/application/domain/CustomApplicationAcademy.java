package com.apexsoft.ysprj.applicants.application.domain;

public class CustomApplicationAcademy extends ApplicationAcademy {

    private String korCntrName ;

    private String engCntrName ;

    private UserDataType userDataType;

    public String getKorCntrName() {
        return korCntrName;
    }

    public void setKorCntrName(String korCntrName) {
        this.korCntrName = korCntrName;
    }

    public String getEngCntrName() {
        return engCntrName;
    }

    public void setEngCntrName(String engCntrName) {
        this.engCntrName = engCntrName;
    }

    public UserDataType getUserDataType() {
        return userDataType;
    }

    public void setUserDataType(UserDataType userDataType) {
        this.userDataType = userDataType;
    }
}
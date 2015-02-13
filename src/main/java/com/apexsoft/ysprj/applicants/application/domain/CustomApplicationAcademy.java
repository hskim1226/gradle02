package com.apexsoft.ysprj.applicants.application.domain;

public class CustomApplicationAcademy extends ApplicationAcademy {

    private String korCntrName ;

    private String engCntrName ;

    private UserCUDType userCUDType;

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

    public UserCUDType getUserCUDType() {
        return userCUDType;
    }

    public void setUserCUDType(UserCUDType userCUDType) {
        this.userCUDType = userCUDType;
    }
}
package com.apexsoft.ysprj.applicants.application.domain;

import java.util.Date;

public class CustomApplicationAcademy extends ApplicationAcademy {

    private String korCntrName ;

    private String engCntrName ;

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
}
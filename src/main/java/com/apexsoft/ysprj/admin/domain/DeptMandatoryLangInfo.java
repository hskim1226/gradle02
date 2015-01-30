package com.apexsoft.ysprj.admin.domain;

import com.apexsoft.ysprj.applicants.application.domain.ApplicationAcademy;
import com.apexsoft.ysprj.applicants.application.domain.ApplicationDocument;
import com.apexsoft.ysprj.applicants.application.domain.ApplicationLanguage;
import com.apexsoft.ysprj.applicants.application.domain.CommonMandatory;

import java.util.List;

public class DeptMandatoryLangInfo extends MajorHiarachyInfo{

    private String grpCode;
    private String mandYn;
    private String multiYn;
    private List<String> langList;

    public String getGrpCode() {
        return grpCode;
    }

    public void setGrpCode(String grpCode) {
        this.grpCode = grpCode;
    }

    public String getMandYn() {
        return mandYn;
    }

    public void setMandYn(String mandYn) {
        this.mandYn = mandYn;
    }

    public String getMultiYn() {
        return multiYn;
    }

    public void setMultiYn(String multiYn) {
        this.multiYn = multiYn;
    }

    public List<String> getLangList() {
        return langList;
    }

    public void setLangList(List<String> langList) {
        this.langList = langList;
    }
}

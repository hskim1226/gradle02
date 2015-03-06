package com.apexsoft.ysprj.admin.domain;

import com.apexsoft.ysprj.applicants.application.domain.ApplicationAcademy;
import com.apexsoft.ysprj.applicants.application.domain.ApplicationDocument;
import com.apexsoft.ysprj.applicants.application.domain.ApplicationExperience;
import com.apexsoft.ysprj.applicants.application.domain.ApplicationLanguage;
import com.apexsoft.ysprj.applicants.test.Academy;
import com.apexsoft.ysprj.applicants.test.Career;

import java.util.Date;
import java.util.List;

public class ApplicantInfoEntire extends ApplicantInfoAll{

    private List <ApplicationDocument> docList;
    private List <ApplicationAcademy> acadList;
    private List <ApplicationLanguage> langList;
    private List <ApplicationExperience> exprList;

    @Override
    public List<ApplicationDocument> getDocList() {
        return docList;
    }

    @Override
    public void setDocList(List<ApplicationDocument> docList) {
        this.docList = docList;
    }

    public List<ApplicationAcademy> getAcadList() {
        return acadList;
    }

    public void setAcadList(List<ApplicationAcademy> acadList) {
        this.acadList = acadList;
    }

    public List<ApplicationLanguage> getLangList() {
        return langList;
    }

    public void setLangList(List<ApplicationLanguage> langList) {
        this.langList = langList;
    }

    public List<ApplicationExperience> getExprList() {
        return exprList;
    }

    public void setExprList(List<ApplicationExperience> exprList) {
        this.exprList = exprList;
    }
}

package com.apexsoft.ysprj.admin.domain.old;

import com.apexsoft.ysprj.applicants.application.domain.*;
import com.apexsoft.ysprj.applicants.test.Academy;
import com.apexsoft.ysprj.applicants.test.Career;

import java.util.Date;
import java.util.List;

public class ApplicantInfoEntire extends ApplicantInfoAll{

    private List <ApplicationDocument> docList;
    private List <CustomApplicationAcademy> acadList;
    private List <ApplicationLanguage> langList;
    private List <CustomApplicationExperience> exprList;


    public List<ApplicationDocument> getDocList() {
        return docList;
    }

    public void setDocList(List<ApplicationDocument> docList) {
        this.docList = docList;
    }

    public List<CustomApplicationAcademy> getAcadList() {
        return acadList;
    }

    public void setAcadList(List<CustomApplicationAcademy> acadList) {
        this.acadList = acadList;
    }

    public List<ApplicationLanguage> getLangList() {
        return langList;
    }

    public void setLangList(List<ApplicationLanguage> langList) {
        this.langList = langList;
    }

    public List<CustomApplicationExperience> getExprList() {
        return exprList;
    }

    public void setExprList(List<CustomApplicationExperience> exprList) {
        this.exprList = exprList;
    }
}

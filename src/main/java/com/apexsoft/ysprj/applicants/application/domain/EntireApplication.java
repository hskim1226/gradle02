package com.apexsoft.ysprj.applicants.application.domain;

import java.util.List;

/**
 * Created by hanmomhanda on 14. 8. 20.
 */
public class EntireApplication {

    private String campCode;
    private String collCode;

    private Application application;
    private ApplicationGeneral applicationGeneral;
    private ApplicationETCWithBLOBs applicationETCWithBLOBs;
    private ApplicationAcademy highSchool;
    private List<ApplicationAcademy> collegeList;
    private List<ApplicationAcademy> graduateList;
    private List<ApplicationExperience> applicationExperienceList;
    private List<ApplicationLanguage> applicationLanguageList;
    private List<ApplicationDocument> generalDocList;
    private List<ApplicationDocument> collegeDocList;
    private List<ApplicationDocument> graduageDocList;
    private List<ApplicationDocument> languageDocList;
    private List<ApplicationDocument> ariInstDocList;
    private List<ApplicationDocument> deptDocList;
    private List<ApplicationDocument> foreignCollegeDocList;
    private List<ApplicationDocument> foreignGraduageDocList;
    private List<ApplicationDocument> foreignDocList;
    private List<ApplicationDocument> etcDocList;


    public String getCampCode() {
        return campCode;
    }

    public void setCampCode(String campCode) {
        this.campCode = campCode;
    }

    public String getCollCode() {
        return collCode;
    }

    public void setCollCode(String collCode) {
        this.collCode = collCode;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public ApplicationGeneral getApplicationGeneral() {
        return applicationGeneral;
    }

    public void setApplicationGeneral(ApplicationGeneral applicationGeneral) {
        this.applicationGeneral = applicationGeneral;
    }

    public ApplicationETCWithBLOBs getApplicationETCWithBLOBs() {
        return applicationETCWithBLOBs;
    }

    public void setApplicationETCWithBLOBs(ApplicationETCWithBLOBs applicationETCWithBLOBs) {
        this.applicationETCWithBLOBs = applicationETCWithBLOBs;
    }

    public ApplicationAcademy getHighSchool() {
        return highSchool;
    }

    public void setHighSchool(ApplicationAcademy highSchool) {
        this.highSchool = highSchool;
    }

    public List<ApplicationAcademy> getCollegeList() {
        return collegeList;
    }

    public void setCollegeList(List<ApplicationAcademy> collegeList) {
        this.collegeList = collegeList;
    }

    public List<ApplicationAcademy> getGraduateList() {
        return graduateList;
    }

    public void setGraduateList(List<ApplicationAcademy> graduateList) {
        this.graduateList = graduateList;
    }

    public List<ApplicationExperience> getApplicationExperienceList() {
        return applicationExperienceList;
    }

    public void setApplicationExperienceList(List<ApplicationExperience> applicationExperienceList) {
        this.applicationExperienceList = applicationExperienceList;
    }

    public List<ApplicationLanguage> getApplicationLanguageList() {
        return applicationLanguageList;
    }

    public void setApplicationLanguageList(List<ApplicationLanguage> applicationLanguageList) {
        this.applicationLanguageList = applicationLanguageList;
    }

    public List<ApplicationDocument> getGeneralDocList() {
        return generalDocList;
    }

    public void setGeneralDocList(List<ApplicationDocument> generalDocList) {
        this.generalDocList = generalDocList;
    }

    public List<ApplicationDocument> getCollegeDocList() {
        return collegeDocList;
    }

    public void setCollegeDocList(List<ApplicationDocument> collegeDocList) {
        this.collegeDocList = collegeDocList;
    }

    public List<ApplicationDocument> getGraduageDocList() {
        return graduageDocList;
    }

    public void setGraduageDocList(List<ApplicationDocument> graduageDocList) {
        this.graduageDocList = graduageDocList;
    }

    public List<ApplicationDocument> getLanguageDocList() {
        return languageDocList;
    }

    public void setLanguageDocList(List<ApplicationDocument> languageDocList) {
        this.languageDocList = languageDocList;
    }

    public List<ApplicationDocument> getAriInstDocList() {
        return ariInstDocList;
    }

    public void setAriInstDocList(List<ApplicationDocument> ariInstDocList) {
        this.ariInstDocList = ariInstDocList;
    }

    public List<ApplicationDocument> getDeptDocList() {
        return deptDocList;
    }

    public void setDeptDocList(List<ApplicationDocument> deptDocList) {
        this.deptDocList = deptDocList;
    }

    public List<ApplicationDocument> getForeignCollegeDocList() {
        return foreignCollegeDocList;
    }

    public void setForeignCollegeDocList(List<ApplicationDocument> foreignCollegeDocList) {
        this.foreignCollegeDocList = foreignCollegeDocList;
    }

    public List<ApplicationDocument> getForeignGraduageDocList() {
        return foreignGraduageDocList;
    }

    public void setForeignGraduageDocList(List<ApplicationDocument> foreignGraduageDocList) {
        this.foreignGraduageDocList = foreignGraduageDocList;
    }

    public List<ApplicationDocument> getForeignDocList() {
        return foreignDocList;
    }

    public void setForeignDocList(List<ApplicationDocument> foreignDocList) {
        this.foreignDocList = foreignDocList;
    }

    public List<ApplicationDocument> getEtcDocList() {
        return etcDocList;
    }

    public void setEtcDocList(List<ApplicationDocument> etcDocList) {
        this.etcDocList = etcDocList;
    }
}

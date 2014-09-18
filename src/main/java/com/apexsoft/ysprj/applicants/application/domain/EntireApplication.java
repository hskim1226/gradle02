package com.apexsoft.ysprj.applicants.application.domain;

import com.apexsoft.ysprj.applicants.payment.domain.ApplicationPayment;

import java.util.List;

/**
 * Created by hanmomhanda on 14. 8. 20.
 */
public class EntireApplication {

    private Application application;
    private ApplicationGeneral applicationGeneral;
    private ApplicationForeigner applicationForeigner;
    private ApplicationETCWithBLOBs applicationETCWithBLOBs;
    private ApplicationAcademy highSchool;
    private List<CustomApplicationAcademy> collegeList;
    private List<CustomApplicationAcademy> graduateList;
    private List<ApplicationExperience> applicationExperienceList;
    private List<ApplicationLanguage> applicationLanguageList;
    private List<ApplicationDocument> generalDocList;
    private List<ApplicationDocument> foreignDegreeDocList;
    private List<ApplicationDocument> collegeDocList;
    private List<ApplicationDocument> graduateDocList;
    private List<ApplicationDocument> languageDocList;
    private List<ApplicationDocument> ariInstDocList;
    private List<ApplicationDocument> foreignerDocList;
    private List<ApplicationDocument> deptDocList;
    private List<ApplicationDocument> etcDocList;
    private List<DocGroupFile> docGroupList;
    private ApplicationPayment applicationPayment;

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

    public ApplicationForeigner getApplicationForeigner() {
        return applicationForeigner;
    }

    public void setApplicationForeigner(ApplicationForeigner applicationForeigner) {
        this.applicationForeigner = applicationForeigner;
    }

    public ApplicationETCWithBLOBs getApplicationETCWithBLOBs() {
        return applicationETCWithBLOBs;
    }

    public void setApplicationETCWithBLOBs(ApplicationETCWithBLOBs applicationETCWithBLOBs) {
        this.applicationETCWithBLOBs = applicationETCWithBLOBs;
    }

//    public CustomApplicationAcademy getHighSchool() {
//        return highSchool;
//    }

    public void setHighSchool(CustomApplicationAcademy highSchool) {
        this.highSchool = highSchool;
    }

    public List<CustomApplicationAcademy> getCollegeList() {
        return collegeList;
    }

    public void setCollegeList(List<CustomApplicationAcademy> collegeList) {
        this.collegeList = collegeList;
    }

    public List<CustomApplicationAcademy> getGraduateList() {
        return graduateList;
    }

    public void setGraduateList(List<CustomApplicationAcademy> graduateList) {
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

    public List<ApplicationDocument> getForeignDegreeDocList() {
        return foreignDegreeDocList;
    }

    public void setForeignDegreeDocList(List<ApplicationDocument> foreignDegreeDocList) {
        this.foreignDegreeDocList = foreignDegreeDocList;
    }

    public List<ApplicationDocument> getCollegeDocList() {
        return collegeDocList;
    }

    public void setCollegeDocList(List<ApplicationDocument> collegeDocList) {
        this.collegeDocList = collegeDocList;
    }

    public List<ApplicationDocument> getGraduateDocList() {
        return graduateDocList;
    }

    public void setGraduateDocList(List<ApplicationDocument> graduateDocList) {
        this.graduateDocList = graduateDocList;
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

    public List<ApplicationDocument> getForeignerDocList() {
        return foreignerDocList;
    }

    public void setForeignerDocList(List<ApplicationDocument> foreignerDocList) {
        this.foreignerDocList = foreignerDocList;
    }

    public List<ApplicationDocument> getDeptDocList() {
        return deptDocList;
    }

    public void setDeptDocList(List<ApplicationDocument> deptDocList) {
        this.deptDocList = deptDocList;
    }

    public List<ApplicationDocument> getEtcDocList() {
        return etcDocList;
    }

    public void setEtcDocList(List<ApplicationDocument> etcDocList) {
        this.etcDocList = etcDocList;
    }

    public ApplicationPayment getApplicationPayment() {
        return applicationPayment;
    }

    public void setApplicationPayment(ApplicationPayment applicationPayment) {
        this.applicationPayment = applicationPayment;
    }
    public List<DocGroupFile> getDocGroupList() {
        return docGroupList;
    }

    public void setDocGroupList(List<DocGroupFile> docGroupList) {
        this.docGroupList = docGroupList;
    }
}

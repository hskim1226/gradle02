package com.apexsoft.ysprj.unused;

import com.apexsoft.ysprj.applicants.application.domain.*;
import com.apexsoft.ysprj.applicants.payment.domain.ApplicationPayment;

import java.util.List;

/**
 * Created by hanmomhanda on 14. 8. 20.
 */
@Deprecated
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
    private List<ApplicationLanguage> currApplicationLanguageList;
    private List<LanguageGroup> langGroupList;
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

    public ApplicationAcademy getHighSchool() {
        return highSchool;
    }

    public void setHighSchool(ApplicationAcademy highSchool) {
        this.highSchool = highSchool;
    }


    public List<ApplicationLanguage> getCurrApplicationLanguageList() {
        return currApplicationLanguageList;
    }

    public void setCurrApplicationLanguageList(List<ApplicationLanguage> currApplicationLanguageList) {
        this.currApplicationLanguageList = currApplicationLanguageList;
    }

    public List<LanguageGroup> getLangGroupList() {   return langGroupList;  }

    public void setLangGroupList(List<LanguageGroup> langGroupList) { this.langGroupList = langGroupList; }
}

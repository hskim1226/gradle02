package com.apexsoft.ysprj.applicants.application.domain;

import java.util.List;

/**
 * Created by hanmomhanda on 14. 8. 20.
 */
public class EntireApplication {

    private Application application;
    private ApplicationGeneral applicationGeneral;
    private ApplicationETCWithBLOBs applicationETCWithBLOBs;
    private ApplicationAcademy highSchool;
    private List<ApplicationAcademy> collegeList;
    private List<ApplicationAcademy> graduateList;
    private List<ApplicationExperience> applicationExperienceList;
    private List<ApplicationLanguage> applicationLanguageList;

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
}

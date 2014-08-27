package com.apexsoft.ysprj.applicants.application.domain;

import java.util.List;

/**
 * Created by hanmomhanda on 14. 8. 20.
 */
public class EntireApplication extends Application {

    private ApplicationGeneral applicationGeneral;
    private ApplicationETCWithBLOBs applicationETCWithBLOBs;
    private List<ApplicationAcademy> applicationAcademyList;
    private List<ApplicationExperience> applicationExperienceList;
    private List<ApplicationLanguage> applicationLanguageList;

    public Application getApplication() { return super.getApplication(); }

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

    public List<ApplicationAcademy> getApplicationAcademyList() {
        return applicationAcademyList;
    }

    public void setApplicationAcademyList(List<ApplicationAcademy> applicationAcademyList) {
        this.applicationAcademyList = applicationAcademyList;
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

package com.apexsoft.ysprj.applicants.application.domain;

import java.util.List;

/**
 * Created by hanmomhanda on 14. 8. 20.
 */
public class EntireApplication extends Application {

    private ApplicationGeneral applicationGeneral;
    private ApplicationETC applicationETC;
    private List<ApplicationAcademy> applicationAcademyList;
    private List<ApplicationExperience> applicationExperienceList;
    private List<ApplicationLanguage> applicationLanguageList;

    public ApplicationGeneral getApplicationGeneral() {
        return applicationGeneral;
    }

    public void setApplicationGeneral(ApplicationGeneral applicationGeneral) {
        this.applicationGeneral = applicationGeneral;
    }

    public ApplicationETC getApplicationETC() {
        return applicationETC;
    }

    public void setApplicationETC(ApplicationETC applicationETC) {
        this.applicationETC = applicationETC;
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

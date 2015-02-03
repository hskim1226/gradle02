package com.apexsoft.ysprj.applicants.application.domain;

import java.util.List;

/**
 * Created by hanmomhanda on 15. 1. 13.
 */
public class LangCareer {

    private Application application;
    private ApplicationGeneral applicationGeneral;
    private List<LanguageGroup> languageGroupList;
    private List<CustomApplicationExperience> applicationExperienceList;

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

    public List<LanguageGroup> getLanguageGroupList() {
        return languageGroupList;
    }

    public void setLanguageGroupList(List<LanguageGroup> languageGroupList) {
        this.languageGroupList = languageGroupList;
    }

    public List<CustomApplicationExperience> getApplicationExperienceList() {
        return applicationExperienceList;
    }

    public void setApplicationExperienceList(List<CustomApplicationExperience> applicationExperienceList) {
        this.applicationExperienceList = applicationExperienceList;
    }
    public String checkForlExmp;

    public String getCheckForlExmp() {
        return checkForlExmp;
    }

    public void setCheckForlExmp(String checkForlExmp) {
        this.checkForlExmp = checkForlExmp;
    }
}

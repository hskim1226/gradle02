package com.apexsoft.ysprj.user.domain;

import java.util.List;

/**
 * Created by hanmomhanda on 14. 8. 20.
 */
public class EntireApplication extends Application {

    private ApplicationGeneral applicationGeneral;
    private ApplicationEtc applicationEtc;
    private List<ApplicationAcademy> applicationAcademyList;
    private List<ApplicationExperiences> applicationExperiencesList;
    private List<ApplicationEngGrade> applicationEngGradeList;

    public ApplicationGeneral getApplicationGeneral() {
        return applicationGeneral;
    }

    public void setApplicationGeneral(ApplicationGeneral applicationGeneral) {
        this.applicationGeneral = applicationGeneral;
    }

    public ApplicationEtc getApplicationEtc() {
        return applicationEtc;
    }

    public void setApplicationEtc(ApplicationEtc applicationEtc) {
        this.applicationEtc = applicationEtc;
    }

    public List<ApplicationAcademy> getApplicationAcademyList() {
        return applicationAcademyList;
    }

    public void setApplicationAcademyList(List<ApplicationAcademy> applicationAcademyList) {
        this.applicationAcademyList = applicationAcademyList;
    }

    public List<ApplicationExperiences> getApplicationExperiencesList() {
        return applicationExperiencesList;
    }

    public void setApplicationExperiencesList(List<ApplicationExperiences> applicationExperiencesList) {
        this.applicationExperiencesList = applicationExperiencesList;
    }

    public List<ApplicationEngGrade> getApplicationEngGradeList() {
        return applicationEngGradeList;
    }

    public void setApplicationEngGradeList(List<ApplicationEngGrade> applicationEngGradeList) {
        this.applicationEngGradeList = applicationEngGradeList;
    }
}

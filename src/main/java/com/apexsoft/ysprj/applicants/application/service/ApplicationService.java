package com.apexsoft.ysprj.applicants.application.service;

import com.apexsoft.ysprj.applicants.application.domain.*;

import java.util.List;

/**
 * Created by Administrator on 2014-08-12.
 */
public interface ApplicationService {

    int createApplication(Application application);
    int createApplicationGeneral(ApplicationGeneral applicationGeneral);
    int createApplicationETCWithBLOBs(ApplicationETCWithBLOBs applicationETCWithBLOBs);
    int createApplicationHighSchool(ApplicationAcademy applicationAcademy);
    int createApplicationAcademy(List<ApplicationAcademy> applicationAcademyList);
    int createExperience(List<ApplicationExperience> applicationExperienceList);
    int createLanguage(List<ApplicationLanguage> applicationLanguageList);
    String createEntireApplication(EntireApplication entireApplication);

    Application retrieveEntireApplication(int applNo);
    List<ApplicationAcademy> retrieveCollegeList(ParamForAcademy paramForAcademy);
    List<ApplicationExperience> retrieveExperienceList(ApplicationExperienceKey applicationAcademyKey);
    List<ApplicationLanguage> retrieveLanguageList(ApplicationLanguageKey applicationLanguageKey);
    Application retrieveApplication(int applNo);

    Application retrieveApplicationForInsertOthers(ParamForInitialApply paramForInitialApply);
}

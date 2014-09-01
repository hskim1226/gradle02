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

    EntireApplication retrieveEntireApplication(int applNo);
    List<ApplicationAcademy> retrieveAcademyList(ParamForAcademy paramForAcademy);
    List<ApplicationExperience> retrieveExperienceList(int applNo);
    List<ApplicationLanguage> retrieveLanguageList(int applNo);
    Application retrieveApplication(int applNo);

    Application retrieveApplicationForInsertOthers(ParamForInitialApply paramForInitialApply);
    Application retrieveApplicationForInsertOthers(Application application);
    List<CustomMyList> retrieveMyList(ParamForApplication paramForApplication);
}

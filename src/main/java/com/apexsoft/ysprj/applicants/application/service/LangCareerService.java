package com.apexsoft.ysprj.applicants.application.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.ysprj.applicants.application.domain.Application;
import com.apexsoft.ysprj.applicants.application.domain.ApplicationExperience;
import com.apexsoft.ysprj.applicants.application.domain.ApplicationLanguage;
import com.apexsoft.ysprj.applicants.application.domain.CustomApplicationAcademy;

import java.util.List;

/**
 * Created by hanmomhanda on 15. 1. 13.
 *
 * 원서 기본 정보 서비스
 */
public interface LangCareerService {

    ExecutionContext createLangCareer(Application application,
                                      List<ApplicationLanguage> applicationLanguageList,
                                      List<ApplicationExperience> applicationExperienceList);

    ExecutionContext retrieveLangCareer(int applNo);

    ExecutionContext updateLangCareer(Application application,
                                      List<ApplicationLanguage> applicationLanguageList,
                                      List<ApplicationExperience> applicationExperienceList);

    ExecutionContext deleteLangCareer(Application application,
                                      List<ApplicationLanguage> applicationLanguageList,
                                      List<ApplicationExperience> applicationExperienceList);

    int deleteListByApplNo(int applNo, String MapperName);
    <T> List<T> retrieveInfoListByApplNo(int applNo, String mapperName, Class<T> clazz);
}

package com.apexsoft.ysprj.applicants.application.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.ysprj.applicants.application.domain.*;

import java.util.List;

/**
 * Created by Administrator on 2014-08-12.
 */
public interface ApplicationService {

    <T> int insertItem(T item, String MapperName);
    <T> int insertList(List<T> list, String MapperName);
    ExecutionContext createEntireApplication(EntireApplication entireApplication);

    <T> int updateItem(T item, String MapperName);
    <T> int updateList(List<T> list, String MapperName);
    int deleteListByApplNo(int applNo, String MapperName);
    ExecutionContext updateEntireApplication(EntireApplication entireApplication);

    EntireApplication retrieveEntireApplication(int applNo);
    List<ApplicationAcademy> retrieveAcademyList(ParamForAcademy paramForAcademy);
    List<ApplicationExperience> retrieveExperienceList(int applNo);
    List<ApplicationLanguage> retrieveLanguageList(int applNo);
    Application retrieveApplication(int applNo);

    Application retrieveApplicationForInsertOthers(ParamForInitialApply paramForInitialApply);
    Application retrieveApplicationForInsertOthers(Application application);
    List<CustomMyList> retrieveMyList(ParamForApplication paramForApplication);

    CampusCollege retrieveCampusCollege(int applNo);
}

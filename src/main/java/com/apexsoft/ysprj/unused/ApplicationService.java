package com.apexsoft.ysprj.unused;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.ysprj.applicants.application.domain.*;
import com.apexsoft.ysprj.unused.EntireApplication;

import java.util.List;

/**
 * Created by Administrator on 2014-08-12.
 */
public interface ApplicationService {

    ExecutionContext createAppInfo(Application application,
                                   ApplicationGeneral applicationGeneral,
                                   ApplicationForeigner applicationForeigner);
    ExecutionContext updateAppInfo(Application application,
                                   ApplicationGeneral applicationGeneral,
                                   ApplicationForeigner applicationForeigner);
    ExecutionContext createAcademy(Application application,
                                   List<CustomApplicationAcademy> collegeList,
                                   List<CustomApplicationAcademy> graduateList);
    ExecutionContext updateAcademy(Application application,
                                   List<CustomApplicationAcademy> collegeList,
                                   List<CustomApplicationAcademy> graduateList);
    ExecutionContext createLangCareer(Application application,
                                      List<ApplicationLanguage> applicationLanguageList,
                                      List<ApplicationExperience> applicationExperienceList);
    ExecutionContext updateLangCareer(Application application,
                                      List<ApplicationLanguage> applicationLanguageList,
                                      List<ApplicationExperience> applicationExperienceList);
    ExecutionContext createFileUpload(Application application,
                                      List<DocGroupFile> docGroupFileList);
    ExecutionContext updateFileUpload(Application application,
                                      List<DocGroupFile> docGroupFileList);
    ExecutionContext createEntireApplication(EntireApplication entireApplication);

    EntireApplication retrieveEntireApplication(int applNo);
    <T> T retrieveInfoByApplNo(int applNo, String mapperNameSqlId, Class<T> clazz);
    <T> T retrieveInfoByParamObj(Object parameter, String mapperNameSqlId, Class<T> clazz);
    <T> List<T> retrieveInfoListByApplNo(int applNo, String mapperName, Class<T> clazz);
    <T> List<T> retrieveInfoListByParamObj(Object parameter, String mapperNameSqlId, Class<T> clazz);

    ExecutionContext updateEntireApplication(EntireApplication entireApplication);

    ExecutionContext confirmEntireApplication(EntireApplication entireApplication);

    int deleteListByApplNo(int applNo, String MapperName);

    List<DocGroupFile> retrieveManApplDocListByApplNo( int applNo);

    ApplicationDocument retrieveApplicationDocumentPhoto(int applNo);
}

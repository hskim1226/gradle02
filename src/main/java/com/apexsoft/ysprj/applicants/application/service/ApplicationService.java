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
    int createApplicationAcademy(List<ApplicationAcademy> applicationAcademyList);

    Application retrieveEntireApplication(int applNo);
    Application retrieveApplication(int applNo);

    int createEntireApplication(EntireApplication entireApplication);
    Application retrieveApplicationForInsertOthers(ParamForInitialApply paramForInitialApply);


//    void registerApplication(Application application);
//    void createApplication(Application application);
//    Application retrieveApplication(int applNo);
//    Integer update(Application application);



// 아래는 일단 미사용




//    PageInfo<Application> getApplicationsPaginatedList(String username);
//    Integer updateApplication(Application application);
//    Integer deleteApplication(Application application);
//    Integer disposalApplication(Application application);
//
//    List<Department> retrieveDepartmentsByAdmission(String admsNo);
//
//    Map<String, String> getGraduationTypes();
}

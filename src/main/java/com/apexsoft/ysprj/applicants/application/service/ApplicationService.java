package com.apexsoft.ysprj.applicants.application.service;

import com.apexsoft.ysprj.applicants.application.domain.Application;
import com.apexsoft.ysprj.applicants.application.domain.ApplicationGeneral;

/**
 * Created by Administrator on 2014-08-12.
 */
public interface ApplicationService {



    void createApplication(com.apexsoft.ysprj.applicants.application.domain.Application application);
    void createApplicationGeneral(ApplicationGeneral applicationGeneral);
    Application retrieveEntireApplication(int applNo);
    Application retrieveApplication(int applNo);


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

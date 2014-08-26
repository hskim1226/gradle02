package com.apexsoft.ysprj.applicants.application.service;

import com.apexsoft.framework.persistence.dao.page.PageInfo;
import com.apexsoft.ysprj.applicants.application.domain.ApplicationGeneral;
import com.apexsoft.ysprj.user.domain.Application;
import com.apexsoft.ysprj.user.domain.Department;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2014-08-12.
 */
public interface ApplicationService {

    Application retrieveEntireApplication(int applNo);

    void createApplication(com.apexsoft.ysprj.applicants.application.domain.Application application);
    void createApplicationGeneral(ApplicationGeneral applicationGeneral);
    //void createApplicationAcademy



//    void registerApplication(Application application);
//    void createApplication(Application application);
    Application retrieveApplication(int applNo);
    Integer update(Application application);



// 아래는 일단 미사용



    Application retrieveApplication(Application application);
    PageInfo<Application> getApplicationsPaginatedList(String username);
    Integer updateApplication(Application application);
    Integer deleteApplication(Application application);
    Integer disposalApplication(Application application);

    List<Department> retrieveDepartmentsByAdmission(String admsNo);

    Map<String, String> getGraduationTypes();
}

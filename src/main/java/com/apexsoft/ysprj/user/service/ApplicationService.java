package com.apexsoft.ysprj.user.service;

import com.apexsoft.framework.persistence.dao.page.PageInfo;
import com.apexsoft.ysprj.user.domain.Application;
import com.apexsoft.ysprj.user.domain.Department;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2014-08-12.
 */
public interface ApplicationService {

    void registerApplication(Application application);
    Application retrieveApplication(int applNo);
    Application retrieveApplication(Application application);
    PageInfo<Application> getApplicationsPaginatedList(String username);
    Integer updateApplication(Application application);
    Integer deleteApplication(Application application);
    Integer disposalApplication(Application application);

    List<Department> retrieveDepartmentsByAdmission(String admsNo);

    Map<String, String> getGraduationTypes();
}

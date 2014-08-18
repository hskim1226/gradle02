package com.apexsoft.ysprj.application.service;

import com.apexsoft.framework.persistence.dao.page.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2014-08-12.
 */
public interface ApplicationService {

    void registerApplication(ApplicationVO applicationVO);
    ApplicationVO retrieveApplication(int applNo);
    ApplicationVO retrieveApplication(ApplicationVO applicationVO);
    PageInfo<ApplicationVO> getApplicationsPaginatedList(String username);
    Integer updateApplication(ApplicationVO applicationVO);
    Integer deleteApplication(ApplicationVO applicationVO);
    Integer disposalApplication(ApplicationVO applicationVO);

    List<DepartmentVO> retrieveDepartmentsByAdmission(String admsNo);

    Map<String, String> getGraduationTypes();
}

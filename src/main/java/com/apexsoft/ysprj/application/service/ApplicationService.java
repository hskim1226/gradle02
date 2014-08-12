package com.apexsoft.ysprj.application.service;

import com.apexsoft.framework.persistence.dao.page.PageInfo;

/**
 * Created by Administrator on 2014-08-12.
 */
public interface ApplicationService {

    void registerApplication(ApplicationVO applicationVO);
    ApplicationVO retrieveApplication(String id);
    PageInfo<ApplicationVO> retrieveApplications(String username);
    Integer updateApplication(ApplicationVO applicationVO);
    Integer deleteApplication(ApplicationVO applicationVO);
    Integer disposalApplication(ApplicationVO applicationVO);

}

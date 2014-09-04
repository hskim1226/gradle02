package com.apexsoft.ysprj.preview.service;

import com.apexsoft.framework.persistence.dao.page.PageInfo;
import com.apexsoft.ysprj.applicants.application.domain.Application;
import com.apexsoft.ysprj.applicants.application.domain.EntireApplication;

/**
 * Created by Administrator on 2014-08-01.
 */
public interface BirtService {
    PageInfo<Application> getApplications();
    Application getApplication(String id);
    EntireApplication retrieveEntireApplication(int applNo);
}

package com.apexsoft.ysprj.preview.service;

import com.apexsoft.framework.persistence.dao.page.PageInfo;
import com.apexsoft.ysprj.application.service.ApplicationVO;

/**
 * Created by Administrator on 2014-08-01.
 */
public interface BirtService {
    PageInfo<ApplicationVO> getApplications();
    ApplicationVO getApplication(String id);
}

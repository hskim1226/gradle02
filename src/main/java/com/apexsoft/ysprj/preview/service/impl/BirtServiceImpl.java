package com.apexsoft.ysprj.preview.service.impl;

import com.apexsoft.framework.persistence.dao.page.PageInfo;
import com.apexsoft.framework.persistence.dao.page.PageStatement;
import com.apexsoft.ysprj.application.service.ApplicationVO;
import com.apexsoft.ysprj.preview.service.BirtService;
import com.apexsoft.framework.persistence.dao.CommonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2014-08-01.
 */
@Service
public class BirtServiceImpl implements BirtService {

    private final static String NAME_SPACE = "APP.";

    @Autowired
    private CommonDAO birtDao;

    public PageInfo<ApplicationVO> getApplications() {
        return birtDao.queryForPagenatedList( new PageStatement(), null, 0, 0);
    }

    public ApplicationVO getApplication(String id) {
        return birtDao.queryForObject("", id, ApplicationVO.class);
    }
}

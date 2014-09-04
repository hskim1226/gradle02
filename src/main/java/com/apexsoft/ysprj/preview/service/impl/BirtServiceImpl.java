package com.apexsoft.ysprj.preview.service.impl;

import com.apexsoft.framework.persistence.dao.page.PageInfo;
import com.apexsoft.framework.persistence.dao.page.PageStatement;
import com.apexsoft.ysprj.applicants.application.domain.Application;
import com.apexsoft.ysprj.applicants.application.domain.EntireApplication;
import com.apexsoft.ysprj.applicants.application.service.ApplicationService;
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

    @Autowired
    private ApplicationService applicationService;

    public PageInfo<Application> getApplications() {
        return birtDao.queryForPagenatedList( new PageStatement(), null, 0, 0);
    }

    public Application getApplication(String id) {
        /*
         * 임시 데이터.
         */
        Application application = new Application();
        application.setApplNo(1);
        application.setDeptCode("010");
        application.setKorName("김지호");
        application.setEngName("Jiho");
        application.setEngSur("Kim");
        application.setRgstNo("830219-1446915");
        application.setMailAddr("go2zo@apexsoft.co.kr");
        application.setTelNum("02-2222-2222");
        application.setMobiNum("010-2207-1441");
        application.setAddr("서울시 마포구");
        application.setDetlAddr("동교동 LG팰리스빌딩 1121호");
        return application;
    }

    public EntireApplication retrieveEntireApplication(int applNo) {
        return applicationService.retrieveEntireApplication(applNo);
    }
}

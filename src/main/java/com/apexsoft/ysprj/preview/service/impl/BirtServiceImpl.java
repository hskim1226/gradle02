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
        /*
         * 임시 데이터.
         */
        ApplicationVO applicationVO = new ApplicationVO();
        applicationVO.setApplNo("15A10001-10001");
        applicationVO.setDeptCode("010");
        applicationVO.setKorName("김지호");
        applicationVO.setEngName("Jiho");
        applicationVO.setEngSurName("Kim");
        applicationVO.setResidentNumber("830219-1446915");
        applicationVO.setEmail("go2zo@apexsoft.co.kr");
        applicationVO.setTelephone("02-2222-2222");
        applicationVO.setMobile("010-2207-1441");
        applicationVO.setAddress("서울시 마포구");
        applicationVO.setDetailAddr("동교동 LG팰리스빌딩 1121호");
        return applicationVO;
    }
}

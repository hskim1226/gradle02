package com.apexsoft.ysprj.application.service.impl;

import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.framework.persistence.dao.page.PageInfo;
import com.apexsoft.framework.persistence.dao.page.PageStatement;
import com.apexsoft.ysprj.application.service.ApplicationService;
import com.apexsoft.ysprj.application.service.ApplicationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2014-08-12.
 */
@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final static String NAME_SPACE = "com.apexsoft.ysprj.application.Mapper.";

    @Autowired
    private CommonDAO commonDAO;

    @Override
    public void registerApplication(ApplicationVO applicationVO) {
        commonDAO.insert(NAME_SPACE + "insertApp", applicationVO);
    }

    @Override
    public ApplicationVO retrieveApplication(String id) {
//        return commonDAO.queryForObject(NAME_SPACE + "selectByPk", id, ApplicationVO.class);
        ApplicationVO applicationVO = new ApplicationVO();
        applicationVO.setKorName("홍길동");
        applicationVO.setEngName("Gil dong");
        applicationVO.setEngSurName("Hong");
        return applicationVO;
    }

    @Override
    public PageInfo<ApplicationVO> getApplicationPagenatedList(String username) {
        return commonDAO.queryForPagenatedList( new PageStatement() {
            @Override
            public String getTotalCountStatementId() {
                return super.getTotalCountStatementId();
            }

            @Override
            public String getDataStatementId() {
                return super.getDataStatementId();
            }
        }, null, 0, 0 );
    }

    @Override
    public Integer updateApplication(ApplicationVO applicationVO) {
        return commonDAO.update(NAME_SPACE + "updateApp", applicationVO);
    }

    @Override
    public Integer deleteApplication(ApplicationVO applicationVO) {
        return commonDAO.delete(NAME_SPACE + "deleteApp", applicationVO);
    }

    @Override
    public Integer disposalApplication(ApplicationVO applicationVO) {
        return commonDAO.update(NAME_SPACE + "disposal", applicationVO);
    }
}

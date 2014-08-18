package com.apexsoft.ysprj.application.service.impl;

import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.framework.persistence.dao.page.PageInfo;
import com.apexsoft.framework.persistence.dao.page.PageStatement;
import com.apexsoft.ysprj.application.service.AcademyVO;
import com.apexsoft.ysprj.application.service.ApplicationService;
import com.apexsoft.ysprj.application.service.ApplicationVO;
import com.apexsoft.ysprj.application.service.DepartmentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
        List<AcademyVO> academies = applicationVO.getAcademies();
        for( AcademyVO academyVO : academies ) {
            commonDAO.insert(NAME_SPACE + "insertAcad", academyVO);
        }
    }

    @Override
    public ApplicationVO retrieveApplication(int appNo) {
        return commonDAO.queryForObject(NAME_SPACE + "selectByPk", appNo, ApplicationVO.class);
    }

    @Override
    public ApplicationVO retrieveApplication(ApplicationVO applicationVO) {
        return commonDAO.queryForObject(NAME_SPACE + "selectByUsername", applicationVO, ApplicationVO.class);
    }

    @Override
    public PageInfo<ApplicationVO> getApplicationsPaginatedList(String username) {
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


    @Override
    public List<DepartmentVO> retrieveDepartmentsByAdmission(String admsNo) {
        return commonDAO.queryForList(NAME_SPACE + "selectDepartments", admsNo, DepartmentVO.class);
    }


    @Override
    public Map<String, String> getGraduationTypes() {
        return (Map<String, String>) commonDAO.queryForMap(NAME_SPACE + "", "", "");
    }
}

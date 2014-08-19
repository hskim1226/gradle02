package com.apexsoft.ysprj.user.impl;

import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.framework.persistence.dao.page.PageInfo;
import com.apexsoft.framework.persistence.dao.page.PageStatement;
import com.apexsoft.ysprj.user.domain.Academy;
import com.apexsoft.ysprj.user.domain.Application;
import com.apexsoft.ysprj.user.domain.Department;
import com.apexsoft.ysprj.user.service.ApplicationService;
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
    public void registerApplication(Application application) {
        commonDAO.insert(NAME_SPACE + "insertApp", application);
        List<Academy> academies = application.getAcademies();
        for( Academy academy : academies ) {
            commonDAO.insert(NAME_SPACE + "insertAcad", academy);
        }
    }

    @Override
    public Application retrieveApplication(int appNo) {
        return commonDAO.queryForObject(NAME_SPACE + "selectByPk", appNo, Application.class);
    }

    @Override
    public Application retrieveApplication(Application application) {
        return commonDAO.queryForObject(NAME_SPACE + "selectByUsername", application, Application.class);
    }

    @Override
    public PageInfo<Application> getApplicationsPaginatedList(String username) {
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
    public Integer updateApplication(Application application) {
        return commonDAO.update(NAME_SPACE + "updateApp", application);
    }

    @Override
    public Integer deleteApplication(Application application) {
        return commonDAO.delete(NAME_SPACE + "deleteApp", application);
    }

    @Override
    public Integer disposalApplication(Application application) {
        return commonDAO.update(NAME_SPACE + "disposal", application);
    }


    @Override
    public List<Department> retrieveDepartmentsByAdmission(String admsNo) {
        return commonDAO.queryForList(NAME_SPACE + "selectDepartments", admsNo, Department.class);
    }


    @Override
    public Map<String, String> getGraduationTypes() {
        return (Map<String, String>) commonDAO.queryForMap(NAME_SPACE + "", "", "");
    }
}

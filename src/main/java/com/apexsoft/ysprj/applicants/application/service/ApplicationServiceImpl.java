package com.apexsoft.ysprj.applicants.application.service;

import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.ysprj.applicants.application.domain.Application;
import com.apexsoft.ysprj.applicants.application.domain.ApplicationGeneral;
import com.apexsoft.ysprj.applicants.application.domain.EntireApplication;
import com.apexsoft.ysprj.applicants.application.domain.ParamForInitialApply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2014-08-12.
 */
@Service
public class ApplicationServiceImpl implements ApplicationService {

    // TODO 제3자 정보제공 동의 여부 providePrivateInfo 처리

    private final static String NAME_SPACE = "com.apexsoft.ysprj.applicants.application.sqlmap.";

    @Autowired
    private CommonDAO commonDAO;

    @Override
    public int createApplication(Application application) {
        return commonDAO.insert(NAME_SPACE + "ApplicationMapper.insert", application);
    }

    @Override
    public int createApplicationGeneral(ApplicationGeneral applicationGeneral) {
        return commonDAO.insert(NAME_SPACE + "ApplicationGeneralMapper.insert", applicationGeneral);
    }

    @Override
    public Application retrieveEntireApplication(int applNo) {
        return commonDAO.queryForObject(NAME_SPACE + "EntireApplicationMapper.entireApplicationByNestedSelect", applNo, EntireApplication.class);
    }

    @Override
    public Application retrieveApplication(int applNo) {
        Application application = null;
        try {
            application = commonDAO.queryForObject(NAME_SPACE + "ApplicationMapper.selectByPrimaryKey", applNo, Application.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return application;
    }

    @Override
    public Application retrieveApplicationForInsertOthers(ParamForInitialApply paramForInitialApply) {
        Application application = null;
        try {
            application = commonDAO.queryForObject(NAME_SPACE + "CustomApplicationMapper.selectApplForInsertOthers", paramForInitialApply, Application.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return application;
    }

    @Override
    public int createEntireApplication(EntireApplication entireApplication) {

        int resultNo1=0;
        int applNo=0;
        int resultNo2=0;

        try {
            resultNo1 = createApplication(entireApplication.getApplication());

            applNo = retrieveApplicationForInsertOthers(new ParamForInitialApply()/*TODO 원서 작성 화면 뜰때부터 가져와야 함*/).getApplNo();

            resultNo2 = createApplicationGeneral(entireApplication.getApplicationGeneral());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return applNo;
    }

//    @Override
//    public Application retrieveApplication(int applNo) {
//        Application application = null;
////        try {
//            application = commonDAO.queryForObject(NAME_SPACE + "selectApplicationByApplNo", applNo, Application.class);
////        } catch (Exception e) {
//////            e.printStackTrace();
////        }
//        return application;
//    }

//    @Override
//    public Integer update(Application application) {
//        return commonDAO.update(NAME_SPACE + "updateApplicationByApplNo", application);
//    }











//    @Override
//    public void registerApplication(Application application) {
//        commonDAO.insert(NAME_SPACE + "insertApp", application);
//        List<ApplicationAcademy> academies = application.getAcademies();
//        for( ApplicationAcademy applicationAcademy : academies ) {
//            commonDAO.insert(NAME_SPACE + "insertAcad", applicationAcademy);
//        }
//    }




//
//    @Override
//    public PageInfo<Application> getApplicationsPaginatedList(String username) {
//        return commonDAO.queryForPagenatedList( new PageStatement() {
//            @Override
//            public String getTotalCountStatementId() {
//                return super.getTotalCountStatementId();
//            }
//
//            @Override
//            public String getDataStatementId() {
//                return super.getDataStatementId();
//            }
//        }, null, 0, 0 );
//    }
//
//    @Override
//    public Integer updateApplication(Application application) {
//        return commonDAO.update(NAME_SPACE + "updateApp", application);
//    }
//
//    @Override
//    public Integer deleteApplication(Application application) {
//        return commonDAO.delete(NAME_SPACE + "deleteApp", application);
//    }
//
//    @Override
//    public Integer disposalApplication(Application application) {
//        return commonDAO.update(NAME_SPACE + "disposal", application);
//    }
//
//
//    @Override
//    public List<Department> retrieveDepartmentsByAdmission(String admsNo) {
//        return commonDAO.queryForList(NAME_SPACE + "selectDepartments", admsNo, Department.class);
//    }
//
//
//    @Override
//    public Map<String, String> getGraduationTypes() {
//        return (Map<String, String>) commonDAO.queryForMap(NAME_SPACE + "", "", "");
//    }
}

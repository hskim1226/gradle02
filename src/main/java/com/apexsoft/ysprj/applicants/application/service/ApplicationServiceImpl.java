package com.apexsoft.ysprj.applicants.application.service;

import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.ysprj.applicants.application.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return commonDAO.insert(NAME_SPACE + "ApplicationMapper.insertSelective", application);
    }

    @Override
    public int createApplicationGeneral(ApplicationGeneral applicationGeneral) {
        return commonDAO.insert(NAME_SPACE + "ApplicationGeneralMapper.insertSelective", applicationGeneral);
    }

    @Override
    public int createApplicationETCWithBLOBs(ApplicationETCWithBLOBs applicationETCWithBLOBs) {
        return commonDAO.insert(NAME_SPACE + "ApplicationETCMapper.insertSelective", applicationETCWithBLOBs);
    }

    @Override
    public int createApplicationAcademy(List<ApplicationAcademy> applicationAcademyList) {
        int i = 0;
        for ( ApplicationAcademy applicationAcademy : applicationAcademyList) {
            commonDAO.insert(NAME_SPACE + "ApplicationAcademyMapper.insertSelective", applicationAcademy);
            i++;
        }
        return i;
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
            entireApplication.getApplicationGeneral().setApplNo(applNo);
            resultNo2 = createApplicationGeneral(entireApplication.getApplicationGeneral());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return applNo;
    }

}

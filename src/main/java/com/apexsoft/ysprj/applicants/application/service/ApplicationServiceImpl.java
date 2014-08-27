package com.apexsoft.ysprj.applicants.application.service;

import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.ysprj.applicants.application.domain.*;
import org.apache.ibatis.annotations.Param;
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
    public int createApplicationHighSchool(ApplicationAcademy applicationAcademy) {
        return commonDAO.insert(NAME_SPACE + "ApplicationAcademyMapper.insertSelective", applicationAcademy);
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
    public int createLanguage(List<ApplicationLanguage> applicationLanguageList) {
        int i = 0;
        for ( ApplicationLanguage applicationLanguage : applicationLanguageList) {
            commonDAO.insert(NAME_SPACE + "ApplicationLanguageMapper.insertSelective", applicationLanguage);
            i++;
        }
        return i;
    }

    @Override
    public int createExperience(List<ApplicationExperience> applicationExperienceList) {
        int i = 0;
        for ( ApplicationExperience applicationExprience : applicationExperienceList) {
            commonDAO.insert(NAME_SPACE + "ApplicationExperienceMapper.insertSelective", applicationExprience);
            i++;
        }
        return i;
    }

    @Override
    public String createEntireApplication(EntireApplication entireApplication) {

        int r1 = 0;
        int applNo = 0;
        int r2 = 0;
        int r3 = 0;
        int r4 = 0;
        int r5 = 0;
        int r6 = 0;
        int r7 = 0;
        int r8 = 0;

        try {
            r1 = createApplication(entireApplication.getApplication());
            applNo = retrieveApplicationForInsertOthers(new ParamForInitialApply()/*TODO 원서 작성 화면 뜰때부터 가져와야 함*/).getApplNo();

            entireApplication.getApplicationGeneral().setApplNo(applNo);
            r2 = createApplicationGeneral(entireApplication.getApplicationGeneral());

            entireApplication.getApplicationETCWithBLOBs().setApplNo(applNo);
            r3 = createApplicationETCWithBLOBs(entireApplication.getApplicationETCWithBLOBs());

            entireApplication.getHighSchool().setApplNo(applNo);
            r4 = createApplicationHighSchool(entireApplication.getHighSchool());

            List<ApplicationAcademy> collegeList = entireApplication.getCollegeList();
            for( ApplicationAcademy college : collegeList) {
                college.setApplNo(applNo);
            }
            r5 = createApplicationAcademy(collegeList);

            List<ApplicationAcademy> graduateList = entireApplication.getGraduateList();
            for( ApplicationAcademy graduate : graduateList) {
                graduate.setApplNo(applNo);
            }
            r6 = createApplicationAcademy(graduateList);

            List<ApplicationExperience> applicationExperienceList = entireApplication.getApplicationExperienceList();
            for( ApplicationExperience experience : applicationExperienceList) {
                experience.setApplNo(applNo);
            }
            r7 = createExperience(applicationExperienceList);

            List<ApplicationLanguage> applicationLanguageList = entireApplication.getApplicationLanguageList();
            for( ApplicationLanguage applicationLanguage : applicationLanguageList) {
                applicationLanguage.setApplNo(applNo);
            }
            r8 = createLanguage(applicationLanguageList);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "" + r1 + r2 + r3 + r4 + r5 + r6 + r7 + r8;
    }




    @Override
    public EntireApplication retrieveEntireApplication(int applNo) {
        EntireApplication entireApplication = commonDAO.queryForObject(NAME_SPACE + "EntireApplicationMapper.entireApplicationByNestedSelect", applNo, EntireApplication.class);
        ParamForAcademy paramForAcademy = new ParamForAcademy();
        paramForAcademy.setApplNo(applNo);
        paramForAcademy.setAcadTypeCode("00002");
        entireApplication.setCollegeList(retrieveCollegeList(paramForAcademy));
        return entireApplication;
    }

    @Override
    public List<ApplicationAcademy> retrieveCollegeList(ParamForAcademy paramForAcademy) {
        List<ApplicationAcademy> applicationAcademyList = null;
        try {
            applicationAcademyList = commonDAO.queryForList(NAME_SPACE + "CustomApplicationAcademyMapper.selectByApplNoAcadTypeCode",
                    paramForAcademy, ApplicationAcademy.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return applicationAcademyList;
    }

    @Override
    public List<ApplicationExperience> retrieveExperienceList(ApplicationExperienceKey applicationAcademyKey) {
        List<ApplicationExperience> applicationExperienceList = null;
        try {
            applicationExperienceList = commonDAO.queryForList(NAME_SPACE + "ApplicationExperienceMapper.selectByPrimaryKey",
                    applicationAcademyKey, ApplicationExperience.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return applicationExperienceList;
    }

    @Override
    public List<ApplicationLanguage> retrieveLanguageList(ApplicationLanguageKey applicationLanguageKey) {
        List<ApplicationLanguage> applicationLanguageList = null;
        try {
            applicationLanguageList = commonDAO.queryForList(NAME_SPACE + "ApplicationLanguageMapper.selectByPrimaryKey",
                    applicationLanguageKey, ApplicationLanguage.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return applicationLanguageList;
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



}

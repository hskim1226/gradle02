package com.apexsoft.ysprj.applicants.application.service;

import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.ysprj.applicants.application.domain.*;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        if ( application != null)
            return commonDAO.insert(NAME_SPACE + "ApplicationMapper.insertSelective", application);
        return 0;
    }

    @Override
    public int createApplicationGeneral(ApplicationGeneral applicationGeneral) {
        if ( applicationGeneral != null)
            return commonDAO.insert(NAME_SPACE + "ApplicationGeneralMapper.insertSelective", applicationGeneral);
        return 0;
    }

    @Override
    public int createApplicationETCWithBLOBs(ApplicationETCWithBLOBs applicationETCWithBLOBs) {
        if ( applicationETCWithBLOBs != null )
            return commonDAO.insert(NAME_SPACE + "ApplicationETCMapper.insertSelective", applicationETCWithBLOBs);
        return 0;
    }

    @Override
    public int createApplicationHighSchool(ApplicationAcademy applicationAcademy) {
        if ( applicationAcademy != null )
            return commonDAO.insert(NAME_SPACE + "ApplicationAcademyMapper.insertSelective", applicationAcademy);
        return 0;
    }

    @Override
    public int createApplicationAcademy(List<ApplicationAcademy> applicationAcademyList) {
        int i = 0;
        if ( applicationAcademyList != null ) {
            for ( ApplicationAcademy applicationAcademy : applicationAcademyList) {
                commonDAO.insert(NAME_SPACE + "ApplicationAcademyMapper.insertSelective", applicationAcademy);
                i++;
            }
        }

        return i;
    }

    @Override
    public int createLanguage(List<ApplicationLanguage> applicationLanguageList) {
        int i = 0;
        if ( applicationLanguageList != null ) {
            for ( ApplicationLanguage applicationLanguage : applicationLanguageList) {
                commonDAO.insert(NAME_SPACE + "ApplicationLanguageMapper.insertSelective", applicationLanguage);
                i++;
            }
        }

        return i;
    }

    @Override
    public int createExperience(List<ApplicationExperience> applicationExperienceList) {
        int i = 0;
        if ( applicationExperienceList != null ) {
            for ( ApplicationExperience applicationExprience : applicationExperienceList) {
                commonDAO.insert(NAME_SPACE + "ApplicationExperienceMapper.insertSelective", applicationExprience);
                i++;
            }
        }

        return i;
    }

    @Override
    public String createEntireApplication(EntireApplication entireApplication) {

        int r1 = 0;
        int applNo;
        int r2 = 0;
        int r3 = 0;
        int r4 = 0;
        int r5 = 0;
        int r6 = 0;
        int r7 = 0;
        int r8 = 0;
        ParamForInitialApply p = new ParamForInitialApply();
        p.setUserId(entireApplication.getUserId());
        p.setAdmsNo(entireApplication.getAdmsNo());
        p.setApplStsCode(entireApplication.getApplStsCode());

//        try {

            r1 = createApplication(entireApplication.getApplication());
            applNo = retrieveApplicationForInsertOthers(p).getApplNo();

            entireApplication.getApplicationGeneral().setApplNo(applNo);
            r2 = createApplicationGeneral(entireApplication.getApplicationGeneral());

            entireApplication.getApplicationETCWithBLOBs().setApplNo(applNo);
            r3 = createApplicationETCWithBLOBs(entireApplication.getApplicationETCWithBLOBs());

            entireApplication.getHighSchool().setApplNo(applNo);
            entireApplication.getHighSchool().setAcadSeq(1);
            r4 = createApplicationHighSchool(entireApplication.getHighSchool());

            List<ApplicationAcademy> collegeList = entireApplication.getCollegeList();
            int idx = 1;
            if ( collegeList != null ) {
                for( ApplicationAcademy college : collegeList) {
                    college.setApplNo(applNo);
                    college.setAcadSeq(++idx);
                }
                r5 = createApplicationAcademy(collegeList);
            }

            List<ApplicationAcademy> graduateList = entireApplication.getGraduateList();
            if ( graduateList != null ) {
                for( ApplicationAcademy graduate : graduateList) {
                    graduate.setApplNo(applNo);
                    graduate.setAcadSeq(++idx);
                }
                r6 = createApplicationAcademy(graduateList);
            }

            List<ApplicationExperience> applicationExperienceList = entireApplication.getApplicationExperienceList();
            idx = 0;
            if ( applicationExperienceList != null ) {
                for( ApplicationExperience experience : applicationExperienceList) {
                    experience.setApplNo(applNo);
                    experience.setExprSeq(++idx);
                }
                r7 = createExperience(applicationExperienceList);
            }

            List<ApplicationLanguage> applicationLanguageList = entireApplication.getApplicationLanguageList();
            idx = 0;
            if ( applicationLanguageList != null ) {
                for( ApplicationLanguage applicationLanguage : applicationLanguageList) {
                    applicationLanguage.setApplNo(applNo);
                    applicationLanguage.setLangSeq(++idx);
                }
                r8 = createLanguage(applicationLanguageList);
            }


//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        String parity = "" + r1 + r2 + r3 + r4 + r5 + r6 + r7 + r8;
        System.out.println(parity);
        return parity;
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

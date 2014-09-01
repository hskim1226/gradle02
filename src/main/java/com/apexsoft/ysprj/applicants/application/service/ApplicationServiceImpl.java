package com.apexsoft.ysprj.applicants.application.service;

import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.ysprj.applicants.application.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        p.setUserId(entireApplication.getApplication().getUserId());
        p.setAdmsNo(entireApplication.getApplication().getAdmsNo());
        p.setApplStsCode(entireApplication.getApplication().getApplStsCode());

        try {
            entireApplication.getApplication().setCreDate(new Date());
            r1 = createApplication(entireApplication.getApplication());
            Application tA = retrieveApplicationForInsertOthers(entireApplication.getApplication());
            applNo = tA.getApplNo();

            entireApplication.getApplicationGeneral().setApplNo(applNo);
            entireApplication.getApplicationGeneral().setCreDate(new Date());
            r2 = createApplicationGeneral(entireApplication.getApplicationGeneral());

            entireApplication.getApplicationETCWithBLOBs().setApplNo(applNo);
            entireApplication.getApplicationETCWithBLOBs().setCreDate(new Date());
            r3 = createApplicationETCWithBLOBs(entireApplication.getApplicationETCWithBLOBs());

            entireApplication.getHighSchool().setApplNo(applNo);
            entireApplication.getHighSchool().setAcadSeq(1);
            entireApplication.getHighSchool().setCreDate(new Date());
            r4 = createApplicationHighSchool(entireApplication.getHighSchool());

            List<ApplicationAcademy> collegeList = entireApplication.getCollegeList();
            int idx = 1;
            if ( collegeList != null ) {
                Date date = new Date();
                for( ApplicationAcademy college : collegeList) {
                    college.setApplNo(applNo);
                    college.setAcadSeq(++idx);
                    college.setCreDate(date);
                }
                r5 = createApplicationAcademy(collegeList);
            }

            List<ApplicationAcademy> graduateList = entireApplication.getGraduateList();
            if ( graduateList != null ) {
                Date date = new Date();
                for( ApplicationAcademy graduate : graduateList) {
                    graduate.setApplNo(applNo);
                    graduate.setAcadSeq(++idx);
                    graduate.setCreDate(date);
                }
                r6 = createApplicationAcademy(graduateList);
            }

            List<ApplicationExperience> applicationExperienceList = entireApplication.getApplicationExperienceList();
            idx = 0;
            if ( applicationExperienceList != null ) {
                Date date = new Date();
                for( ApplicationExperience experience : applicationExperienceList) {
                    experience.setApplNo(applNo);
                    experience.setExprSeq(++idx);
                    experience.setCreDate(date);
                }
                r7 = createExperience(applicationExperienceList);
            }

            List<ApplicationLanguage> applicationLanguageList = entireApplication.getApplicationLanguageList();
            idx = 0;
            if ( applicationLanguageList != null ) {
                Date date = new Date();
                for( ApplicationLanguage applicationLanguage : applicationLanguageList) {
                    applicationLanguage.setApplNo(applNo);
                    applicationLanguage.setLangSeq(++idx);
                    applicationLanguage.setCreDate(date);
                }
                r8 = createLanguage(applicationLanguageList);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        String parity = "" + r1 + r2 + r3 + r4 + r5 + r6 + r7 + r8;
        System.out.println(parity);
        return parity;
    }




    @Override
    public EntireApplication retrieveEntireApplication(int applNo) {
        EntireApplication entireApplication = null;
        try {
            entireApplication = commonDAO.queryForObject(NAME_SPACE + "EntireApplicationMapper.selectOneToOneEntireApplicationByApplNo",
                    new Integer(applNo),
                    EntireApplication.class);
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        ParamForAcademy paramForAcademy = new ParamForAcademy();
        paramForAcademy.setApplNo(applNo);
        paramForAcademy.setAcadTypeCode("00002");
        entireApplication.setCollegeList(retrieveAcademyList(paramForAcademy));
        paramForAcademy.setAcadTypeCode(("00003"));
        entireApplication.setGraduateList(retrieveAcademyList(paramForAcademy));
        entireApplication.setApplicationExperienceList(retrieveExperienceList(applNo));
        entireApplication.setApplicationLanguageList(retrieveLanguageList(applNo));
        return entireApplication;
    }

    @Override
    public List<ApplicationAcademy> retrieveAcademyList(ParamForAcademy paramForAcademy) {
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
    public List<ApplicationExperience> retrieveExperienceList(int applNo) {
        List<ApplicationExperience> applicationExperienceList = null;
        try {
            applicationExperienceList = commonDAO.queryForList(NAME_SPACE + "CustomApplicationExperienceMapper.selectByApplNo",
                    applNo, ApplicationExperience.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return applicationExperienceList;
    }

    @Override
    public List<ApplicationLanguage> retrieveLanguageList(int applNo) {
        List<ApplicationLanguage> applicationLanguageList = null;
        try {
            applicationLanguageList = commonDAO.queryForList(NAME_SPACE + "CustomApplicationLanguageMapper.selectByApplNo",
                    applNo, ApplicationLanguage.class);
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

    @Override
    public Application retrieveApplicationForInsertOthers(Application application) {
        Application rApplication = null;
        try {
            rApplication = commonDAO.queryForObject(NAME_SPACE + "CustomApplicationMapper.selectApplByApplForInsertOthers",
                    application,
                    Application.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rApplication;
    }

    public List<CustomMyList> retrieveMyList(ParamForApplication paramForApplication) {
        List<CustomMyList> customMyLists = null;
        try {
            customMyLists = commonDAO.queryForList(NAME_SPACE + "CustomApplicationMapper.selectApplByUserId",
                    paramForApplication, CustomMyList.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return customMyLists;
    }

}

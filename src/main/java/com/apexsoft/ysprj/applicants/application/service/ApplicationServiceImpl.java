package com.apexsoft.ysprj.applicants.application.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.ysprj.applicants.application.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    @Resource(name = "messageResolver")
    MessageResolver messageResolver;

    @Override
    public <T> int insertItem(T item, String MapperName) {
        if ( item != null) {
            return commonDAO.insert(NAME_SPACE + MapperName + ".insertSelective", item);
        }
        return 0;
    }

    @Override
    public <T> int insertList(List<T> list, String MapperName) {
        int i = 0;
        if ( list != null ) {
            for ( T item : list) {
                insertItem(item, MapperName);
                i++;
            }
        }
        return i;
    }

    @Override
    public ExecutionContext createEntireApplication(EntireApplication entireApplication) {

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
            Date date = new Date();

            entireApplication.getApplication().setCreDate(date);
            r1 = insertItem(entireApplication.getApplication(), "ApplicationMapper");
            Application tA = retrieveApplicationForInsertOthers(entireApplication.getApplication());
            applNo = tA.getApplNo();

            entireApplication.getApplicationGeneral().setApplNo(applNo);
            entireApplication.getApplicationGeneral().setCreDate(date);
            r2 = insertItem(entireApplication.getApplicationGeneral(), "ApplicationGeneralMapper");

            entireApplication.getApplicationETCWithBLOBs().setApplNo(applNo);
            entireApplication.getApplicationETCWithBLOBs().setCreDate(date);
            r3 = insertItem(entireApplication.getApplicationETCWithBLOBs(), "ApplicationETCMapper");

            entireApplication.getHighSchool().setApplNo(applNo);
            entireApplication.getHighSchool().setAcadSeq(1);
            entireApplication.getHighSchool().setCreDate(date);
            r4 = insertItem(entireApplication.getHighSchool(), "ApplicationAcademyMapper");

            List<ApplicationAcademy> collegeList = entireApplication.getCollegeList();
            int idx = 1;
            if ( collegeList != null ) {
                for( ApplicationAcademy college : collegeList) {
                    college.setApplNo(applNo);
                    college.setAcadSeq(++idx);
                    college.setCreDate(date);
                }
                r5 = insertList(collegeList, "ApplicationAcademyMapper");
            }

            List<ApplicationAcademy> graduateList = entireApplication.getGraduateList();
            if ( graduateList != null ) {
                for( ApplicationAcademy graduate : graduateList) {
                    graduate.setApplNo(applNo);
                    graduate.setAcadSeq(++idx);
                    graduate.setCreDate(date);
                }
                r6 = insertList(graduateList, "ApplicationAcademyMapper");
            }

            List<ApplicationExperience> applicationExperienceList = entireApplication.getApplicationExperienceList();
            idx = 0;
            if ( applicationExperienceList != null ) {
                for( ApplicationExperience experience : applicationExperienceList) {
                    experience.setApplNo(applNo);
                    experience.setExprSeq(++idx);
                    experience.setCreDate(date);
                }
                r7 = insertList(applicationExperienceList, "ApplicationExperienceMapper");
            }


            List<ApplicationLanguage> applicationLanguageList = entireApplication.getApplicationLanguageList();
            idx = 0;
            if ( applicationLanguageList != null ) {
                for( ApplicationLanguage applicationLanguage : applicationLanguageList) {
                    applicationLanguage.setApplNo(applNo);
                    applicationLanguage.setLangSeq(++idx);
                    applicationLanguage.setCreDate(date);
                }
                r8 = insertList(applicationLanguageList, "ApplicationLanguageMapper");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String parity = "" + r1 + r2 + r3 + r4 + r5 + r6 + r7 + r8;

        ExecutionContext ec = null;
        int errPosition = parity.indexOf('0');
        if (errPosition  > 0) {
            ec = new ExecutionContext(ExecutionContext.FAIL,
                    messageResolver.getMessage("U306") + " : " + errPosition);
        } else {
            ec = new ExecutionContext(ExecutionContext.SUCCESS,
                    messageResolver.getMessage("U301"));
        }
        return ec;
    }

    @Override
    public <T> int updateItem(T item, String MapperName) {
        int idx = 0;
        if ( item != null) {
            idx = commonDAO.update(NAME_SPACE + MapperName + ".updateByPrimaryKeySelective", item);
        }
        return idx;
    }

    @Override
    public <T> int updateList(List<T> list, String MapperName) {
        int idx = 0;
        if ( list != null) {

            for (T item : list) {
                updateItem(item, MapperName);
                ++idx;
            }
        }
        return idx;
    }

    @Override
    public int deleteListByApplNo(int applNo, String MapperName) {
        return commonDAO.delete(NAME_SPACE + MapperName + ".deleteListByApplNo", applNo);
    }

    @Override
    public ExecutionContext updateEntireApplication(EntireApplication entireApplication) {
        int r1 = 0;
        int applNo = entireApplication.getApplication().getApplNo();
        int r2 = 0;
        int r3 = 0;
        int r4 = 0;
        int r5 = 0;
        int r6 = 0;
        int r7 = 0;
        int r8 = 0;

        try {
            Date date = new Date();
            entireApplication.getApplication().setModDate(date);
            r1 = updateItem(entireApplication.getApplication(), "ApplicationMapper");

            entireApplication.getApplicationGeneral().setModDate(date);
            r2 = updateItem(entireApplication.getApplicationGeneral(), "ApplicationGeneralMapper");

            entireApplication.getApplicationETCWithBLOBs().setModDate(date);
            r3 = updateItem(entireApplication.getApplicationETCWithBLOBs(), "ApplicationETCMapper");

            deleteListByApplNo(applNo, "CustomApplicationAcademyMapper");
            entireApplication.getHighSchool().setApplNo(applNo);
            entireApplication.getHighSchool().setAcadSeq(1);
            entireApplication.getHighSchool().setModDate(date);
            r4 = insertItem(entireApplication.getHighSchool(), "ApplicationAcademyMapper");

            List<ApplicationAcademy> collegeList = entireApplication.getCollegeList();
            int idx = 1;
            if ( collegeList != null ) {
                for( ApplicationAcademy college : collegeList) {
                    college.setApplNo(applNo);
                    college.setAcadSeq(++idx);
                    college.setModDate(date);
                }
                r5 = insertList(collegeList, "ApplicationAcademyMapper");
            }

            List<ApplicationAcademy> graduateList = entireApplication.getGraduateList();
            if ( graduateList != null ) {
                for( ApplicationAcademy graduate : graduateList) {
                    graduate.setApplNo(applNo);
                    graduate.setAcadSeq(++idx);
                    graduate.setModDate(date);
                }
                r6 = insertList(graduateList, "ApplicationAcademyMapper");
            }

            deleteListByApplNo(applNo, "CustomApplicationExperienceMapper");
            List<ApplicationExperience> experienceList = entireApplication.getApplicationExperienceList();
            idx = 0;
            if ( experienceList != null ) {
                for(ApplicationExperience item : experienceList) {
                    item.setApplNo(applNo);
                    item.setModDate(date);
                    item.setExprSeq(++idx);
                }
                r7 = insertList(experienceList, "ApplicationExperienceMapper");
            }

            deleteListByApplNo(applNo, "CustomApplicationLanguageMapper");
            List<ApplicationLanguage> languageList = entireApplication.getApplicationLanguageList();
            idx = 0;
            if ( languageList != null ) {
                for(ApplicationLanguage item : languageList) {
                    item.setApplNo(applNo);
                    item.setModDate(date);
                    item.setLangSeq(++idx);
                }
                r8 = insertList(languageList, "ApplicationLanguageMapper");
            }

        } catch ( Exception e ) {
            e.printStackTrace();
        }
        String parity = "" + r1 + r2 + r3 + r4 + r5 + r6 + r7 + r8;

        ExecutionContext ec = null;
        int errPosition = parity.indexOf('0');
        if (errPosition  > 0) {
            ec = new ExecutionContext(ExecutionContext.FAIL,
                    messageResolver.getMessage("U307") + " : " + errPosition);
        } else {
            ec = new ExecutionContext(ExecutionContext.SUCCESS,
                    messageResolver.getMessage("U301"));
        }
        return ec;
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
    public CampusCollege retriveCampusCollege(int applNo) {
        return commonDAO.queryForObject(NAME_SPACE + "EntireApplicationMapper.selectCampusCollegeCode",
                applNo, CampusCollege.class);
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

package com.apexsoft.ysprj.applicants.application.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.ysprj.applicants.application.domain.*;
import com.apexsoft.ysprj.applicants.payment.domain.ApplicationPayment;
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

    private final static String NAME_SPACE = "com.apexsoft.ysprj.applicants.application.sqlmap.";
    private final static String PAYMENT_NAME_SPACE = "com.apexsoft.ysprj.applicants.payment.sqlmap.";

    @Autowired
    private CommonDAO commonDAO;

    @Resource(name = "messageResolver")
    MessageResolver messageResolver;

    @Override
    public ExecutionContext createEntireApplication(EntireApplication entireApplication) {

        int r1 = 0;
        int applNo = 0;
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
            r1 = commonDAO.insertItem(entireApplication.getApplication(), NAME_SPACE, "ApplicationMapper");
            Application tA = retrieveInfoByParamObj(entireApplication.getApplication(),
                    "CustomApplicationMapper.selectApplByApplForInsertOthers",
                    Application.class);
            applNo = tA.getApplNo();

            entireApplication.getApplicationGeneral().setApplNo(applNo);
            entireApplication.getApplicationGeneral().setCreDate(date);
            r2 = commonDAO.insertItem(entireApplication.getApplicationGeneral(), NAME_SPACE, "ApplicationGeneralMapper");

            entireApplication.getApplicationETCWithBLOBs().setApplNo(applNo);
            entireApplication.getApplicationETCWithBLOBs().setCreDate(date);
            r3 = commonDAO.insertItem(entireApplication.getApplicationETCWithBLOBs(), NAME_SPACE, "ApplicationETCMapper");

            entireApplication.getHighSchool().setApplNo(applNo);
            entireApplication.getHighSchool().setAcadSeq(1);
            entireApplication.getHighSchool().setCreDate(date);
            r4 = commonDAO.insertItem(entireApplication.getHighSchool(), NAME_SPACE, "ApplicationAcademyMapper");

            List<ApplicationAcademy> collegeList = entireApplication.getCollegeList();
            int idx = 1;
            if ( collegeList != null ) {
                for( ApplicationAcademy college : collegeList) {
                    college.setApplNo(applNo);
                    college.setAcadSeq(++idx);
                    college.setCreDate(date);
                }
                r5 = commonDAO.insertList(collegeList, NAME_SPACE, "ApplicationAcademyMapper");
            }

            List<ApplicationAcademy> graduateList = entireApplication.getGraduateList();
            if ( graduateList != null ) {
                for( ApplicationAcademy graduate : graduateList) {
                    graduate.setApplNo(applNo);
                    graduate.setAcadSeq(++idx);
                    graduate.setCreDate(date);
                }
                r6 = commonDAO.insertList(graduateList, NAME_SPACE, "ApplicationAcademyMapper");
            }

            List<ApplicationExperience> applicationExperienceList = entireApplication.getApplicationExperienceList();
            idx = 0;
            if ( applicationExperienceList != null ) {
                for( ApplicationExperience experience : applicationExperienceList) {
                    experience.setApplNo(applNo);
                    experience.setExprSeq(++idx);
                    experience.setCreDate(date);
                }
                r7 = commonDAO.insertList(applicationExperienceList, NAME_SPACE, "ApplicationExperienceMapper");
            }


            List<ApplicationLanguage> applicationLanguageList = entireApplication.getApplicationLanguageList();
            idx = 0;
            if ( applicationLanguageList != null ) {
                for( ApplicationLanguage applicationLanguage : applicationLanguageList) {
                    applicationLanguage.setApplNo(applNo);
                    applicationLanguage.setLangSeq(++idx);
                    applicationLanguage.setCreDate(date);
                }
                r8 = commonDAO.insertList(applicationLanguageList, NAME_SPACE, "ApplicationLanguageMapper");
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
                    messageResolver.getMessage("U301"),
                    new Integer(applNo));
        }
        return ec;
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
            r1 = commonDAO.updateItem(entireApplication.getApplication(), NAME_SPACE, "ApplicationMapper");

            entireApplication.getApplicationGeneral().setModDate(date);
            r2 = commonDAO.updateItem(entireApplication.getApplicationGeneral(), NAME_SPACE, "ApplicationGeneralMapper");

            entireApplication.getApplicationETCWithBLOBs().setModDate(date);
            r3 = commonDAO.updateItem(entireApplication.getApplicationETCWithBLOBs(), NAME_SPACE, "ApplicationETCMapper");

            deleteListByApplNo(applNo, "CustomApplicationAcademyMapper");
            entireApplication.getHighSchool().setApplNo(applNo);
            entireApplication.getHighSchool().setAcadSeq(1);
            entireApplication.getHighSchool().setModDate(date);
            r4 = commonDAO.insertItem(entireApplication.getHighSchool(), NAME_SPACE, "ApplicationAcademyMapper");

            List<ApplicationAcademy> collegeList = entireApplication.getCollegeList();
            int idx = 1;
            if ( collegeList != null ) {
                for( ApplicationAcademy college : collegeList) {
                    college.setApplNo(applNo);
                    college.setAcadSeq(++idx);
                    college.setModDate(date);
                }
                r5 = commonDAO.insertList(collegeList, NAME_SPACE, "ApplicationAcademyMapper");
            }

            List<ApplicationAcademy> graduateList = entireApplication.getGraduateList();
            if ( graduateList != null ) {
                for( ApplicationAcademy graduate : graduateList) {
                    graduate.setApplNo(applNo);
                    graduate.setAcadSeq(++idx);
                    graduate.setModDate(date);
                }
                r6 = commonDAO.insertList(graduateList, NAME_SPACE, "ApplicationAcademyMapper");
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
                r7 = commonDAO.insertList(experienceList, NAME_SPACE, "ApplicationExperienceMapper");
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
                r8 = commonDAO.insertList(languageList, NAME_SPACE, "ApplicationLanguageMapper");
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
                    messageResolver.getMessage("U301"),
                    new Integer(applNo));
        }
        return ec;
    }

    @Override
    public ExecutionContext confirmEntireApplication(EntireApplication entireApplication) {
        int r1 = 0;
        int applNo = entireApplication.getApplication().getApplNo();
        int r2 = 0;
        int r3 = 0;
        int r4 = 0;
        int r5 = 0;
        int r6 = 0;
        int r7 = 0;
        int r8 = 0;
        int r9 = 0;

        try {
            Date date = new Date();
            entireApplication.getApplication().setModDate(date);
            entireApplication.getApplication().setApplStsCode("00010");
            r1 = commonDAO.updateItem(entireApplication.getApplication(), NAME_SPACE, "ApplicationMapper");

//            entireApplication.getApplicationGeneral().setModDate(date);
//            r2 = commonDAO.updateItem(entireApplication.getApplicationGeneral(), NAME_SPACE, "ApplicationGeneralMapper");
//
//            entireApplication.getApplicationETCWithBLOBs().setModDate(date);
//            r3 = commonDAO.updateItem(entireApplication.getApplicationETCWithBLOBs(), NAME_SPACE, "ApplicationETCMapper");
//
//            deleteListByApplNo(applNo, "CustomApplicationAcademyMapper");
//            entireApplication.getHighSchool().setApplNo(applNo);
//            entireApplication.getHighSchool().setAcadSeq(1);
//            entireApplication.getHighSchool().setModDate(date);
//            r4 = commonDAO.insertItem(entireApplication.getHighSchool(),NAME_SPACE,  "ApplicationAcademyMapper");
//
//            List<ApplicationAcademy> collegeList = entireApplication.getCollegeList();
//            int idx = 1;
//            if ( collegeList != null ) {
//                for( ApplicationAcademy college : collegeList) {
//                    college.setApplNo(applNo);
//                    college.setAcadSeq(++idx);
//                    college.setModDate(date);
//                }
//                r5 = commonDAO.insertList(collegeList, NAME_SPACE, "ApplicationAcademyMapper");
//            }
//
//            List<ApplicationAcademy> graduateList = entireApplication.getGraduateList();
//            if ( graduateList != null ) {
//                for( ApplicationAcademy graduate : graduateList) {
//                    graduate.setApplNo(applNo);
//                    graduate.setAcadSeq(++idx);
//                    graduate.setModDate(date);
//                }
//                r6 = commonDAO.insertList(graduateList, NAME_SPACE, "ApplicationAcademyMapper");
//            }
//
//            deleteListByApplNo(applNo, "CustomApplicationExperienceMapper");
//            List<ApplicationExperience> experienceList = entireApplication.getApplicationExperienceList();
//            idx = 0;
//            if ( experienceList != null ) {
//                for(ApplicationExperience item : experienceList) {
//                    item.setApplNo(applNo);
//                    item.setModDate(date);
//                    item.setExprSeq(++idx);
//                }
//                r7 = commonDAO.insertList(experienceList, NAME_SPACE, "ApplicationExperienceMapper");
//            }
//
//            deleteListByApplNo(applNo, "CustomApplicationLanguageMapper");
//            List<ApplicationLanguage> languageList = entireApplication.getApplicationLanguageList();
//            idx = 0;
//            if ( languageList != null ) {
//                for(ApplicationLanguage item : languageList) {
//                    item.setApplNo(applNo);
//                    item.setModDate(date);
//                    item.setLangSeq(++idx);
//                }
//                r8 = commonDAO.insertList(languageList, NAME_SPACE, "ApplicationLanguageMapper");
//            }

            //TODO APPL_DOC 관련

            CustomPayInfo customPayInfo = commonDAO.queryForObject(NAME_SPACE + "CustomPayInfoMapper.selectPayInfoByApplNo",
                    applNo, CustomPayInfo.class);
            int paySeq = customPayInfo.getPaySeq();
            int admsFee = customPayInfo.getAdmsFee();
            ApplicationPayment ap = entireApplication.getApplicationPayment();
            if (paySeq == 0) {
                int seqFromDB = commonDAO.queryForInt(PAYMENT_NAME_SPACE + "CustomApplicationPaymentMapper.getSeq", applNo);
                ap.setApplNo(applNo);
                ap.setPaySeq(seqFromDB + 1);
                ap.setExpPayAmt(admsFee);
                ap.setPayStsCode("00001");
                ap.setCreDate(date);
                r9 = commonDAO.insertItem(ap, PAYMENT_NAME_SPACE, "ApplicationPaymentMapper");
            } else {
                ap.setApplNo(applNo);
                ap.setPaySeq(paySeq);
                ap.setExpPayAmt(admsFee);
                ap.setModDate(date);
                r9 = commonDAO.updateItem(ap, PAYMENT_NAME_SPACE, "ApplicationPaymentMapper");
            }

        } catch ( Exception e ) {
            e.printStackTrace();
        }
        String parity = "" + r1 + r2 + r3 + r4 + r5 + r6 + r7 + r8 + r9;

        ExecutionContext ec = null;
        int errPosition = parity.indexOf('0');
        if (errPosition  > 0) {
            ec = new ExecutionContext(ExecutionContext.FAIL,
                    messageResolver.getMessage("U307") + " : " + errPosition);
        } else {
            ec = new ExecutionContext(ExecutionContext.SUCCESS,
                    messageResolver.getMessage("U301"),
                    new Integer(applNo));
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

        String aaMapperSqlId = "CustomApplicationAcademyMapper.selectByApplNoAcadTypeCode";
        ParamForAcademy paramForAcademy = new ParamForAcademy();
        paramForAcademy.setApplNo(applNo);
        paramForAcademy.setAcadTypeCode("00002");
        entireApplication.setCollegeList(retrieveInfoListByParamObj(paramForAcademy, aaMapperSqlId, ApplicationAcademy.class));
        paramForAcademy.setAcadTypeCode(("00003"));
        entireApplication.setGraduateList(retrieveInfoListByParamObj(paramForAcademy, aaMapperSqlId, ApplicationAcademy.class));

        entireApplication.setApplicationExperienceList(retrieveInfoListByApplNo(applNo, "CustomApplicationExperienceMapper", ApplicationExperience.class));
        entireApplication.setApplicationLanguageList(retrieveInfoListByApplNo(applNo, "CustomApplicationLanguageMapper", ApplicationLanguage.class));

        String adMapperSqlId = "CustomApplicationDocumentMapper.selectByApplNoDocTypeCode";
        ParamForApplicationDocument paramForApplicationDocument = new ParamForApplicationDocument();
        paramForApplicationDocument.setApplNo(applNo);
        paramForApplicationDocument.setDocTypeCode("00001");
        entireApplication.setGeneralDocList(retrieveInfoListByParamObj(paramForApplicationDocument,
                adMapperSqlId,
                ApplicationDocument.class));
        paramForApplicationDocument.setDocTypeCode("00002");
        entireApplication.setForeignDegreeDocList(retrieveInfoListByParamObj(paramForApplicationDocument,
                adMapperSqlId,
                ApplicationDocument.class));
        paramForApplicationDocument.setDocTypeCode("00003");
        entireApplication.setCollegeDocList(retrieveInfoListByParamObj(paramForApplicationDocument,
                adMapperSqlId,
                ApplicationDocument.class));
        paramForApplicationDocument.setDocTypeCode("00004");
        entireApplication.setGraduateDocList(retrieveInfoListByParamObj(paramForApplicationDocument,
                adMapperSqlId,
                ApplicationDocument.class));
        paramForApplicationDocument.setDocTypeCode("00005");
        entireApplication.setLanguageDocList(retrieveInfoListByParamObj(paramForApplicationDocument,
                adMapperSqlId,
                ApplicationDocument.class));
        paramForApplicationDocument.setDocTypeCode("00006");
        entireApplication.setAriInstDocList(retrieveInfoListByParamObj(paramForApplicationDocument,
                adMapperSqlId,
                ApplicationDocument.class));
        paramForApplicationDocument.setDocTypeCode("00007");
        entireApplication.setForeignerDocList(retrieveInfoListByParamObj(paramForApplicationDocument,
                adMapperSqlId,
                ApplicationDocument.class));
        paramForApplicationDocument.setDocTypeCode("00008");
        entireApplication.setDeptDocList(retrieveInfoListByParamObj(paramForApplicationDocument,
                adMapperSqlId,
                ApplicationDocument.class));
        paramForApplicationDocument.setDocTypeCode("00099");
        entireApplication.setEtcDocList(retrieveInfoListByParamObj(paramForApplicationDocument,
                adMapperSqlId,
                ApplicationDocument.class));

        return entireApplication;
    }

    @Override
    public <T> T retrieveInfoByApplNo(int applNo, String mapperNameSqlId, Class<T> clazz) {
        T item = null;
        try {
            item = commonDAO.queryForObject(NAME_SPACE + "EntireApplicationMapper.selectCampusCollegeCode",
                    applNo, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return item;
    }

    @Override
    public <T> T retrieveInfoByParamObj(Object parameter, String mapperNameSqlId, Class<T> clazz) {
        T item = null;
        try {
            item = commonDAO.queryForObject(NAME_SPACE + "EntireApplicationMapper.selectCampusCollegeCode",
                    parameter, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return item;
    }

    @Override
    public <T> List<T> retrieveInfoListByApplNo(int applNo, String mapperName, Class<T> clazz) {
        List<T> infoList = null;
        try {
            infoList = commonDAO.queryForList(NAME_SPACE + mapperName + ".selectByApplNo",
                                              applNo, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return infoList;
    }

    @Override
    public <T> List<T> retrieveInfoListByParamObj(Object parameter, String mapperNameSqlId, Class<T> clazz) {
        List<T> infoList = null;
        try {
            infoList = commonDAO.queryForList(NAME_SPACE + mapperNameSqlId,
                    parameter, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return infoList;
    }
}

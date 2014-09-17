package com.apexsoft.ysprj.applicants.application.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.ysprj.applicants.application.domain.*;
import com.apexsoft.ysprj.applicants.payment.domain.ApplicationPayment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2014-08-12.
 */
@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final static String NAME_SPACE = "com.apexsoft.ysprj.applicants.application.sqlmap.";
    private final static String PAYMENT_NAME_SPACE = "com.apexsoft.ysprj.applicants.payment.sqlmap.";
    private final static String DOC_NAME_SPACE = "appplicaiton.doc.";

    @Autowired
    private CommonDAO commonDAO;

    @Resource(name = "messageResolver")
    MessageResolver messageResolver;

    private final String APP_NULL_STATUS = "00000";      // 에러일 때 반환값
    private final String APP_INFO_SAVED = "00001";       // 기본정보 저장
    private final String ACAD_SAVED = "00002";           // 학력 저장
    private final String LANG_CAREER_SAVED = "00003";    // 어학 및 경력 저장
    private final String FILEUPLOADE_SAVED = "00004";    // 첨부파일 저장

    /**
     * 기본 정보 탭 생성
     *
     * @param application
     * @param applicationGeneral
     * @return
     */
    @Override
    public ExecutionContext createAppInfo(Application application, ApplicationGeneral applicationGeneral) {

        ExecutionContext ec = new ExecutionContext();
        int r1 = 0, r2 = 0, applNo = 0;
        Date date = new Date();

        application.setApplStsCode(APP_INFO_SAVED);
        application.setCreDate(date);
        r1 = commonDAO.insertItem(application, NAME_SPACE, "ApplicationMapper");

        Application tA = commonDAO.queryForObject(NAME_SPACE + "CustomApplicationMapper.selectApplByApplForInsertOthers",
                application, Application.class);
        applNo = tA.getApplNo();

        applicationGeneral.setApplNo(applNo);
        applicationGeneral.setCreDate(date);
        r2 = commonDAO.insertItem(applicationGeneral, NAME_SPACE, "ApplicationGeneralMapper");

        if ( r1 > 0 && r2 > 0 ) {
            ec.setResult(ExecutionContext.SUCCESS);
            ec.setMessage(messageResolver.getMessage("U312"));
            ec.setData(new CustomApplNoStsCode(applNo, tA.getApplStsCode()));
        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(messageResolver.getMessage("U306"));
            String errMsg = null;
            if ( r1 == 0 ) errMsg = messageResolver.getMessage("ERR00001");
            else if ( r2 == 0 ) errMsg = messageResolver.getMessage("ERR00006");
            ec.setData(new CustomApplNoStsCode(0, APP_NULL_STATUS, errMsg));
        }
        return ec;
    }

    /**
     * 기본 정보 탭 수정
     *
     * @param application
     * @param applicationGeneral
     * @return
     */
    @Override
    public ExecutionContext updateAppInfo(Application application, ApplicationGeneral applicationGeneral) {

        ExecutionContext ec = new ExecutionContext();
        int r1 = 0, r2 = 0;
        Date date = new Date();

        application.setModDate(date);
        r1 = commonDAO.updateItem(application, NAME_SPACE, "ApplicationMapper");
        r2 = commonDAO.updateItem(applicationGeneral, NAME_SPACE, "ApplicationGeneralMapper");

        if ( r1 > 0 && r2 > 0 ) {
            ec.setResult(ExecutionContext.SUCCESS);
            ec.setMessage(messageResolver.getMessage("U315"));
            ec.setData(new CustomApplNoStsCode(application.getApplNo(), application.getApplStsCode()));
        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(messageResolver.getMessage("U316"));
            String errMsg = null;
            if ( r1 == 0 ) errMsg = messageResolver.getMessage("ERR00003");
            else if ( r2 == 0 ) errMsg = messageResolver.getMessage("ERR00008");
            ec.setData(new CustomApplNoStsCode(application.getApplNo(), APP_NULL_STATUS, errMsg));
        }
        return ec;
    }

    /**
     * 학력 탭 생성
     * @param application
     * @param collegeList
     * @param graduateList
     * @return
     */
    @Override
    public ExecutionContext createAcademy(Application application,
                                          List<ApplicationAcademy> collegeList,
                                          List<ApplicationAcademy> graduateList) {
        List<ApplicationAcademy> acadList = new ArrayList<ApplicationAcademy>();
        acadList.addAll(collegeList);
        acadList.addAll(graduateList);

        ExecutionContext ec = new ExecutionContext();
        int r1, r2 = 0, applNo = application.getApplNo(), idx = 0;
        Date date = new Date();

        application.setApplStsCode(ACAD_SAVED);
        application.setModDate(date);
        r1 = commonDAO.updateItem(application, NAME_SPACE, "ApplicationMapper");

        if ( acadList != null ) {
            for( ApplicationAcademy academy : acadList) {
                academy.setApplNo(applNo);
                academy.setAcadSeq(++idx);
                academy.setCreDate(date);
            }
            r2 = commonDAO.insertList(acadList, NAME_SPACE, "ApplicationAcademyMapper");
        }

        if ( r1 > 0 && r2 > 0 ) {
            ec.setResult(ExecutionContext.SUCCESS);
            ec.setMessage(messageResolver.getMessage("U317"));
            ec.setData(new CustomApplNoStsCode(applNo, application.getApplStsCode()));
        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(messageResolver.getMessage("U318"));
            String errMsg = null;
            if ( r1 == 0 ) errMsg = messageResolver.getMessage("ERR00003");
            else if ( r2 == 0 ) errMsg = messageResolver.getMessage("ERR00011");
            ec.setData(new CustomApplNoStsCode(applNo, APP_NULL_STATUS, errMsg));
        }
        return ec;
    }

    /**
     * 학력 탭 수정
     *
     * @param application
     * @param collegeList
     * @param graduateList
     * @return
     */
    @Override
    public ExecutionContext updateAcademy(Application application,
                                          List<ApplicationAcademy> collegeList,
                                          List<ApplicationAcademy> graduateList) {
        List<ApplicationAcademy> acadList = new ArrayList<ApplicationAcademy>();
        acadList.addAll(collegeList);
        acadList.addAll(graduateList);

        ExecutionContext ec = new ExecutionContext();
        int r1 = 0, applNo = application.getApplNo(), idx = 0;
        Date date = new Date();

        deleteListByApplNo(applNo, "CustomApplicationAcademyMapper");
        if ( acadList != null ) {
            for( ApplicationAcademy academy : acadList) {
                academy.setApplNo(applNo);
                academy.setAcadSeq(++idx);
                academy.setModDate(date);
            }
            r1 = commonDAO.insertList(acadList, NAME_SPACE, "ApplicationAcademyMapper");
        }

        if ( r1 > 0 ) {
            ec.setResult(ExecutionContext.SUCCESS);
            ec.setMessage(messageResolver.getMessage("U317"));
            ec.setData(new CustomApplNoStsCode(applNo, application.getApplStsCode()));
        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(messageResolver.getMessage("U318"));
            ec.setData(new CustomApplNoStsCode(applNo, APP_NULL_STATUS,
                    messageResolver.getMessage("ERR00013")));
        }
        return ec;
    }

    @Override
    public ExecutionContext createLangCareer(Application application,
                                             List<ApplicationLanguage> applicationLanguageList,
                                             List<ApplicationExperience> applicationExperienceList) {

        ExecutionContext ec = new ExecutionContext();
        int r1, r2 = 0, r3 = 0, applNo = application.getApplNo(), idx = 0;
        Date date = new Date();

        application.setApplStsCode(LANG_CAREER_SAVED);
        application.setModDate(date);
        r1 = commonDAO.updateItem(application, NAME_SPACE, "ApplicationMapper");

        if ( applicationLanguageList != null ) {
            for( ApplicationLanguage applicationLanguage : applicationLanguageList) {
                applicationLanguage.setApplNo(applNo);
                applicationLanguage.setLangSeq(++idx);
                applicationLanguage.setCreDate(date);
            }
            r2 = commonDAO.insertList(applicationLanguageList, NAME_SPACE, "ApplicationLanguageMapper");
        }
        idx = 0;
        if ( applicationExperienceList != null ) {
            for( ApplicationExperience applicationExperience : applicationExperienceList) {
                applicationExperience.setApplNo(applNo);
                applicationExperience.setExprSeq(++idx);
                applicationExperience.setCreDate(date);
            }
            r3 = commonDAO.insertList(applicationExperienceList, NAME_SPACE, "ApplicationExperienceMapper");
        }

        if ( r1 > 0 && r2 > 0 && r3 > 0) {
            ec.setResult(ExecutionContext.SUCCESS);
            ec.setMessage(messageResolver.getMessage("U319"));
            ec.setData(new CustomApplNoStsCode(applNo, application.getApplStsCode()));
        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(messageResolver.getMessage("U320"));
            String errMsg = null;
            if ( r1 == 0 ) errMsg = messageResolver.getMessage("ERR00003");
            else if ( r2 == 0 ) errMsg = messageResolver.getMessage("ERR00016");
            else if ( r3 == 0 ) errMsg = messageResolver.getMessage("ERR00021");
            ec.setData(new CustomApplNoStsCode(applNo, APP_NULL_STATUS, errMsg));
        }
        return ec;
    }

    @Override
    public ExecutionContext updateLangCareer(Application application,
                                             List<ApplicationLanguage> applicationLanguageList,
                                             List<ApplicationExperience> applicationExperienceList) {

        ExecutionContext ec = new ExecutionContext();
        int r1 = 0, r2 = 0, applNo = application.getApplNo(), idx = 0;
        Date date = new Date();

        deleteListByApplNo(applNo, "CustomApplicationLanguageMapper");
        if ( applicationLanguageList != null ) {
            for( ApplicationLanguage applicationLanguage : applicationLanguageList) {
                applicationLanguage.setApplNo(applNo);
                applicationLanguage.setLangSeq(++idx);
                applicationLanguage.setModDate(date);
            }
            r1 = commonDAO.insertList(applicationLanguageList, NAME_SPACE, "ApplicationLanguageMapper");
        }
        idx = 0;
        deleteListByApplNo(applNo, "CustomApplicationExperienceMapper");
        if ( applicationExperienceList != null ) {
            for( ApplicationExperience applicationExperience : applicationExperienceList) {
                applicationExperience.setApplNo(applNo);
                applicationExperience.setExprSeq(++idx);
                applicationExperience.setModDate(date);
            }
            r2 = commonDAO.insertList(applicationExperienceList, NAME_SPACE, "ApplicationExperienceMapper");
        }

        if ( r1 > 0 && r2 > 0 ) {
            ec.setResult(ExecutionContext.SUCCESS);
            ec.setMessage(messageResolver.getMessage("U319"));
            ec.setData(new CustomApplNoStsCode(applNo, application.getApplStsCode()));
        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(messageResolver.getMessage("U320"));
            String errMsg = null;
            if ( r1 == 0 ) errMsg = messageResolver.getMessage("ERR00018");
            else if ( r2 == 0 ) errMsg = messageResolver.getMessage("ERR00023");
            ec.setData(new CustomApplNoStsCode(applNo, APP_NULL_STATUS, errMsg));
        }
        return ec;
    }

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
        int r9 = 0;

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

            List<ApplicationDocument> generalDocList = entireApplication.getGeneralDocList();
            idx = 0;
            if ( generalDocList != null ) {
                for(ApplicationDocument item : generalDocList) {
                    item.setApplNo(applNo);
                    item.setModDate(date);
                    item.setDocSeq(++idx);
                }
                r9 = commonDAO.insertList(generalDocList, NAME_SPACE, "ApplicationDocumentMapper");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String parity = "" + r1 + r2 + r3 + r4 + r5 + r6 + r7 + r8 + r9;

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
        int r1 = 0, r2 = 0, r3 = 0, r4 = 0, r5 = 0, r6 = 0, r7 = 0, r8 = 0;
        int r9 = 0, r10 = 0, r11 = 0, r12 = 0, r13 = 0, r14 = 0, r15 = 0, r16 = 0;
        int applNo = entireApplication.getApplication().getApplNo();

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

            deleteListByApplNo(applNo, "CustomApplicationDocumentMapper");
            List<ApplicationDocument> generalDocList = entireApplication.getGeneralDocList();
            idx = 0;
            if ( generalDocList != null ) {
                for(ApplicationDocument item : generalDocList) {
                    item.setApplNo(applNo);
                    item.setModDate(date);
                    item.setDocSeq(++idx);
                }
                r9 = commonDAO.insertList(generalDocList, NAME_SPACE, "ApplicationDocumentMapper");
            }

            List<ApplicationDocument> foreignDegreeDocList = entireApplication.getForeignDegreeDocList();
            if ( foreignDegreeDocList != null ) {
                if ( foreignDegreeDocList.size() > 0) {
                    for(ApplicationDocument item : foreignDegreeDocList) {
                        item.setApplNo(applNo);
                        item.setModDate(date);
                        item.setDocSeq(++idx);
                    }
                    r10 = commonDAO.insertList(foreignDegreeDocList, NAME_SPACE, "ApplicationDocumentMapper");
                } else {
                    r10 = 1;
                }

            }

            List<ApplicationDocument> collegeDocList = entireApplication.getCollegeDocList();
            if ( collegeDocList != null ) {
                for(ApplicationDocument item : collegeDocList) {
                    item.setApplNo(applNo);
                    item.setModDate(date);
                    item.setDocSeq(++idx);
                }
                r11 = commonDAO.insertList(collegeDocList, NAME_SPACE, "ApplicationDocumentMapper");
            }

            List<ApplicationDocument> graduateDocList = entireApplication.getGraduateDocList();
            if ( graduateDocList != null ) {
                for(ApplicationDocument item : graduateDocList) {
                    item.setApplNo(applNo);
                    item.setModDate(date);
                    item.setDocSeq(++idx);
                }
                r12 = commonDAO.insertList(graduateDocList, NAME_SPACE, "ApplicationDocumentMapper");
            }

            List<ApplicationDocument> languageDocList = entireApplication.getLanguageDocList();
            if ( languageDocList != null ) {
                //TODO 어학은 일반/외국인 모두 다 있으므로 아래 size 검사 if는 제거해야함
                if (languageDocList.size() > 0) {
                    for(ApplicationDocument item : languageDocList) {
                        item.setApplNo(applNo);
                        item.setModDate(date);
                        item.setDocSeq(++idx);
                    }
                    r13 = commonDAO.insertList(languageDocList, NAME_SPACE, "ApplicationDocumentMapper");
                } else {
                    r13 = 1;
                }
            }

            List<ApplicationDocument> ariInstDocList = entireApplication.getAriInstDocList();
            if ( ariInstDocList != null ) {
                if ( ariInstDocList.size() > 0 ) {
                    for(ApplicationDocument item : ariInstDocList) {
                        item.setApplNo(applNo);
                        item.setModDate(date);
                        item.setDocSeq(++idx);
                    }
                    r14 = commonDAO.insertList(ariInstDocList, NAME_SPACE, "ApplicationDocumentMapper");
                }
                r14 = 1;
            }

            List<ApplicationDocument> foreignerDocList = entireApplication.getForeignerDocList();
            if ( foreignerDocList != null ) {
                if ( foreignerDocList.size() > 0 ) {
                    for(ApplicationDocument item : foreignerDocList) {
                        item.setApplNo(applNo);
                        item.setModDate(date);
                        item.setDocSeq(++idx);
                    }
                    r15 = commonDAO.insertList(foreignerDocList, NAME_SPACE, "ApplicationDocumentMapper");
                } else {
                    r15 = 1;
                }
            }

            List<ApplicationDocument> deptDocList = entireApplication.getDeptDocList();
            if ( deptDocList != null ) {
                if ( deptDocList.size() > 0 ) {
                    for(ApplicationDocument item : deptDocList) {
                        item.setApplNo(applNo);
                        item.setModDate(date);
                        item.setDocSeq(++idx);
                    }
                    r16 = commonDAO.insertList(deptDocList, NAME_SPACE, "ApplicationDocumentMapper");
                } else {
                    r16 = 1;
                }
            }

        } catch ( Exception e ) {
            e.printStackTrace();
        }
        String parity = "" + r1 + r2 + r3 + r4 + r5 + r6 + r7 + r8 + r9 + r10 + r11 + r12 + r13 + r14 + r15 + r16;

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
        int r1 = 0, r2 = 0, r3 = 0, r4 = 0, r5 = 0, r6 = 0, r7 = 0, r8 = 0;
        int r9 = 0, r10 = 0, r11 = 0, r12 = 0, r13 = 0, r14 = 0, r15 = 0, r16 = 0, r17 = 0;
        int applNo = entireApplication.getApplication().getApplNo();

        try {
            Date date = new Date();
            entireApplication.getApplication().setModDate(date);
            entireApplication.getApplication().setApplStsCode("00010");
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

            deleteListByApplNo(applNo, "CustomApplicationDocumentMapper");
            List<ApplicationDocument> generalDocList = entireApplication.getGeneralDocList();
            idx = 0;
            if ( generalDocList != null ) {
                for(ApplicationDocument item : generalDocList) {
                    item.setApplNo(applNo);
                    item.setModDate(date);
                    item.setDocSeq(++idx);
                }
                r9 = commonDAO.insertList(generalDocList, NAME_SPACE, "ApplicationDocumentMapper");
            }

            List<ApplicationDocument> foreignDegreeDocList = entireApplication.getForeignDegreeDocList();
            if ( foreignDegreeDocList != null ) {
                if ( foreignDegreeDocList.size() > 0) {
                    for(ApplicationDocument item : foreignDegreeDocList) {
                        item.setApplNo(applNo);
                        item.setModDate(date);
                        item.setDocSeq(++idx);
                    }
                    r10 = commonDAO.insertList(foreignDegreeDocList, NAME_SPACE, "ApplicationDocumentMapper");
                } else {
                    r10 = 1;
                }

            }

            List<ApplicationDocument> collegeDocList = entireApplication.getCollegeDocList();
            if ( collegeDocList != null ) {
                for(ApplicationDocument item : collegeDocList) {
                    item.setApplNo(applNo);
                    item.setModDate(date);
                    item.setDocSeq(++idx);
                }
                r11 = commonDAO.insertList(collegeDocList, NAME_SPACE, "ApplicationDocumentMapper");
            }

            List<ApplicationDocument> graduateDocList = entireApplication.getGraduateDocList();
            if ( graduateDocList != null ) {
                for(ApplicationDocument item : graduateDocList) {
                    item.setApplNo(applNo);
                    item.setModDate(date);
                    item.setDocSeq(++idx);
                }
                r12 = commonDAO.insertList(graduateDocList, NAME_SPACE, "ApplicationDocumentMapper");
            }

            List<ApplicationDocument> languageDocList = entireApplication.getLanguageDocList();
            if ( languageDocList != null ) {
                //TODO 어학은 일반/외국인 모두 다 있으므로 아래 size 검사 if는 제거해야함
                if (languageDocList.size() > 0) {
                    for(ApplicationDocument item : languageDocList) {
                        item.setApplNo(applNo);
                        item.setModDate(date);
                        item.setDocSeq(++idx);
                    }
                    r13 = commonDAO.insertList(languageDocList, NAME_SPACE, "ApplicationDocumentMapper");
                } else {
                    r13 = 1;
                }
            }

            List<ApplicationDocument> ariInstDocList = entireApplication.getAriInstDocList();
            if ( ariInstDocList != null ) {
                if ( ariInstDocList.size() > 0 ) {
                    for(ApplicationDocument item : ariInstDocList) {
                        item.setApplNo(applNo);
                        item.setModDate(date);
                        item.setDocSeq(++idx);
                    }
                    r14 = commonDAO.insertList(ariInstDocList, NAME_SPACE, "ApplicationDocumentMapper");
                }
                r14 = 1;
            }

            List<ApplicationDocument> foreignerDocList = entireApplication.getForeignerDocList();
            if ( foreignerDocList != null ) {
                if ( foreignerDocList.size() > 0 ) {
                    for(ApplicationDocument item : foreignerDocList) {
                        item.setApplNo(applNo);
                        item.setModDate(date);
                        item.setDocSeq(++idx);
                    }
                    r15 = commonDAO.insertList(foreignerDocList, NAME_SPACE, "ApplicationDocumentMapper");
                } else {
                    r15 = 1;
                }
            }

            List<ApplicationDocument> deptDocList = entireApplication.getDeptDocList();
            if ( deptDocList != null ) {
                if ( deptDocList.size() > 0 ) {
                    for(ApplicationDocument item : deptDocList) {
                        item.setApplNo(applNo);
                        item.setModDate(date);
                        item.setDocSeq(++idx);
                    }
                    r16 = commonDAO.insertList(deptDocList, NAME_SPACE, "ApplicationDocumentMapper");
                } else {
                    r16 = 1;
                }
            }

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
                r17 = commonDAO.insertItem(ap, PAYMENT_NAME_SPACE, "ApplicationPaymentMapper");
            } else {
                ap.setApplNo(applNo);
                ap.setPaySeq(paySeq);
                ap.setExpPayAmt(admsFee);
                ap.setModDate(date);
                r17 = commonDAO.updateItem(ap, PAYMENT_NAME_SPACE, "ApplicationPaymentMapper");
            }

        } catch ( Exception e ) {
            e.printStackTrace();
        }
        String parity = "" + r1 + r2 + r3 + r4 + r5 + r6 + r7 + r8 + r9 + r10 + r11 + r12 + r13 + r14 + r15 + r16 + r17;

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
        EntireApplication entireApplication = new EntireApplication();
        try {
//            entireApplication = commonDAO.queryForObject(NAME_SPACE + "EntireApplicationMapper.selectOneToOneEntireApplicationByApplNo",
//                    new Integer(applNo),
//                    EntireApplication.class);
            Application application = commonDAO.queryForObject(NAME_SPACE + "ApplicationMapper.selectByPrimaryKey",
                    applNo, Application.class);
            application = application == null ? new Application() : application;
            entireApplication.setApplication(application);

            ApplicationGeneral applicationGeneral = commonDAO.queryForObject(NAME_SPACE + "ApplicationGeneralMapper.selectByPrimaryKey",
                    applNo, ApplicationGeneral.class);
            applicationGeneral = applicationGeneral == null ? new ApplicationGeneral() : applicationGeneral;
            entireApplication.setApplicationGeneral(applicationGeneral);

            ApplicationForeigner applicationForeigner = commonDAO.queryForObject(NAME_SPACE + "ApplicationForeignerMapper.selectByPrimaryKey",
                    applNo, ApplicationForeigner.class);
            applicationForeigner = applicationForeigner == null ? new ApplicationForeigner() : applicationForeigner;
            entireApplication.setApplicationForeigner(applicationForeigner);

            String aaMapperSqlId = "CustomApplicationAcademyMapper.selectByApplNoAcadTypeCode";
            ParamForAcademy paramForAcademy = new ParamForAcademy();
            paramForAcademy.setApplNo(applNo);
            paramForAcademy.setAcadTypeCode("00002");
            entireApplication.setCollegeList(retrieveInfoListByParamObj(paramForAcademy, aaMapperSqlId, ApplicationAcademy.class));
            paramForAcademy.setAcadTypeCode(("00003"));
            entireApplication.setGraduateList(retrieveInfoListByParamObj(paramForAcademy, aaMapperSqlId, ApplicationAcademy.class));

            entireApplication.setApplicationExperienceList(retrieveInfoListByApplNo(applNo, "CustomApplicationExperienceMapper", ApplicationExperience.class));
            entireApplication.setApplicationLanguageList(retrieveInfoListByApplNo(applNo, "CustomApplicationLanguageMapper", ApplicationLanguage.class));

            //TODO 아래는 첨부파일 테이블 가져오는 부분이며, 변경될 로직으로 덮어써져야 함
//            String adMapperSqlId = "CustomApplicationDocumentMapper.selectByApplNoDocTypeCode";
//            ParamForApplicationDocument paramForApplicationDocument = new ParamForApplicationDocument();
//            paramForApplicationDocument.setApplNo(applNo);
//            paramForApplicationDocument.setDocTypeCode("00001");
//            entireApplication.setGeneralDocList(retrieveInfoListByParamObj(paramForApplicationDocument,
//                    adMapperSqlId,
//                    ApplicationDocument.class));
//            paramForApplicationDocument.setDocTypeCode("00002");
//            entireApplication.setForeignDegreeDocList(retrieveInfoListByParamObj(paramForApplicationDocument,
//                    adMapperSqlId,
//                    ApplicationDocument.class));
//            paramForApplicationDocument.setDocTypeCode("00003");
//            entireApplication.setCollegeDocList(retrieveInfoListByParamObj(paramForApplicationDocument,
//                    adMapperSqlId,
//                    ApplicationDocument.class));
//            paramForApplicationDocument.setDocTypeCode("00004");
//            entireApplication.setGraduateDocList(retrieveInfoListByParamObj(paramForApplicationDocument,
//                    adMapperSqlId,
//                    ApplicationDocument.class));
//            paramForApplicationDocument.setDocTypeCode("00005");
//            entireApplication.setLanguageDocList(retrieveInfoListByParamObj(paramForApplicationDocument,
//                    adMapperSqlId,
//                    ApplicationDocument.class));
//            paramForApplicationDocument.setDocTypeCode("00006");
//            entireApplication.setAriInstDocList(retrieveInfoListByParamObj(paramForApplicationDocument,
//                    adMapperSqlId,
//                    ApplicationDocument.class));
//            paramForApplicationDocument.setDocTypeCode("00007");
//            entireApplication.setForeignerDocList(retrieveInfoListByParamObj(paramForApplicationDocument,
//                    adMapperSqlId,
//                    ApplicationDocument.class));
//            paramForApplicationDocument.setDocTypeCode("00008");
//            entireApplication.setDeptDocList(retrieveInfoListByParamObj(paramForApplicationDocument,
//                    adMapperSqlId,
//                    ApplicationDocument.class));
//            paramForApplicationDocument.setDocTypeCode("00099");
//            entireApplication.setEtcDocList(retrieveInfoListByParamObj(paramForApplicationDocument,
//                    adMapperSqlId,
//                    ApplicationDocument.class));

        } catch ( Exception e ) {
            e.printStackTrace();
        }

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
    @Override
    public  ArrayList<List> retrieveManApplDocListByApplNo( int applNo) {
        List<MandatoryNAppliedDoc> docList = null;

        ArrayList<List> mandDoc = new ArrayList<List>();
        int cnt;
        try {
            mandDoc.add( commonDAO.queryForList(DOC_NAME_SPACE + "selectBasicDocListByApplNoWTMandatory", applNo, MandatoryNAppliedDoc.class));
            mandDoc.add( commonDAO.queryForList(DOC_NAME_SPACE + "selectOverSeaDocListByApplNoWTMandatory", applNo, MandatoryNAppliedDoc.class));
            mandDoc.add( commonDAO.queryForList(DOC_NAME_SPACE + "selectUnderDocListByApplNoWTMandatory", applNo, MandatoryNAppliedDoc.class));
            mandDoc.add( commonDAO.queryForList(DOC_NAME_SPACE + "selectGradDocListByApplNoWTMandatory", applNo, MandatoryNAppliedDoc.class ));
            mandDoc.add( commonDAO.queryForList(DOC_NAME_SPACE + "selectLangDocListByApplNoWTMandatory", applNo, MandatoryNAppliedDoc.class ));
            mandDoc.add( commonDAO.queryForList(DOC_NAME_SPACE + "selectInstDocListByApplNoWTMandatory", applNo, MandatoryNAppliedDoc.class ));
            mandDoc.add( commonDAO.queryForList(DOC_NAME_SPACE + "selectDeptDocListByApplNoWTMandatory", applNo, MandatoryNAppliedDoc.class ));
            mandDoc.add( commonDAO.queryForList(DOC_NAME_SPACE + "selectEtcDocListByApplNoWTMandatory", applNo, MandatoryNAppliedDoc.class ));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mandDoc;
    }

}

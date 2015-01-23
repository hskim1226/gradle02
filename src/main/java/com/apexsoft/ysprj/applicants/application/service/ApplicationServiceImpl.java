package com.apexsoft.ysprj.applicants.application.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.ysprj.applicants.application.domain.*;
import com.apexsoft.ysprj.applicants.evaluation.domain.DocGroup;
import com.apexsoft.ysprj.applicants.payment.domain.ApplicationPayment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

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

    private final String APP_NULL_STATUS = "00000";      // 에러일 때 반환값
    private final String APP_INFO_SAVED = "00001";       // 기본정보 저장
    private final String ACAD_SAVED = "00002";           // 학력 저장
    private final String LANG_CAREER_SAVED = "00003";    // 어학 및 경력 저장
    private final String FILEUPLOAD_SAVED = "00004";    // 첨부파일 저장

    /**
     * 기본 정보 생성
     *
     * @param application
     * @param applicationGeneral
     * @return
     */
    @Override
    public ExecutionContext createAppInfo(Application application,
                                          ApplicationGeneral applicationGeneral,
                                          ApplicationForeigner applicationForeigner) {

        ExecutionContext ec = new ExecutionContext();
        int r1 = 0, r2 = 0, r3 = 0, applNo = 0;
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

        applicationForeigner.setApplNo(applNo);
        applicationForeigner.setCreDate(date);
        r3 = commonDAO.insertItem(applicationForeigner, NAME_SPACE, "ApplicationForeignerMapper");

        if ( r1 > 0 && r2 > 0 && r3 > 0 ) {
            ec.setResult(ExecutionContext.SUCCESS);
            ec.setMessage(messageResolver.getMessage("U312"));
            ec.setData(new CustomApplNoStsCode(applNo, tA.getApplStsCode(), tA.getAdmsNo(), tA.getEntrYear(), tA.getAdmsTypeCode()));
        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(messageResolver.getMessage("U306"));
            String errMsg = null;
            if ( r1 == 0 ) errMsg = messageResolver.getMessage("ERR0001");
            else if ( r2 == 0 ) errMsg = messageResolver.getMessage("ERR0006");
            else if ( r3 == 0 ) errMsg = messageResolver.getMessage("ERR0026");
            ec.setData(new CustomApplNoStsCode(0, APP_NULL_STATUS, errMsg));
        }
        return ec;
    }

    /**
     * 기본 정보 수정
     *
     * @param application
     * @param applicationGeneral
     * @return
     */
    @Override
    public ExecutionContext updateAppInfo(Application application,
                                          ApplicationGeneral applicationGeneral,
                                          ApplicationForeigner applicationForeigner) {

        ExecutionContext ec = new ExecutionContext();
        int r1 = 0, r2 = 0, r3 = 0;
        Date date = new Date();

        application.setModDate(date);
        applicationGeneral.setModDate(date);
        applicationForeigner.setModDate(date);
        r1 = commonDAO.updateItem(application, NAME_SPACE, "ApplicationMapper");
        r2 = commonDAO.updateItem(applicationGeneral, NAME_SPACE, "ApplicationGeneralMapper");
        r3 = commonDAO.updateItem(applicationForeigner, NAME_SPACE, "ApplicationForeignerMapper");

        if ( r1 > 0 && r2 > 0 && r3 > 0 ) {
            ec.setResult(ExecutionContext.SUCCESS);
            ec.setMessage(messageResolver.getMessage("U315"));
            ec.setData(new CustomApplNoStsCode(application.getApplNo(), application.getApplStsCode(),
                    application.getAdmsNo(), application.getEntrYear(), application.getAdmsTypeCode()));
        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(messageResolver.getMessage("U316"));
            String errMsg = null;
            if ( r1 == 0 ) errMsg = messageResolver.getMessage("ERR0003");
            else if ( r2 == 0 ) errMsg = messageResolver.getMessage("ERR0008");
            else if ( r3 == 0 ) errMsg = messageResolver.getMessage("ERR0028");
            ec.setData(new CustomApplNoStsCode(application.getApplNo(), APP_NULL_STATUS, errMsg));
        }
        return ec;
    }

    /**
     * 학력 정보 생성
     * @param application
     * @param collegeList
     * @param graduateList
     * @return
     */
    @Override
    public ExecutionContext createAcademy(Application application,
                                          List<CustomApplicationAcademy> collegeList,
                                          List<CustomApplicationAcademy> graduateList) {
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
            ec.setData(new CustomApplNoStsCode(applNo, application.getApplStsCode(),
                    application.getAdmsNo(), application.getEntrYear(), application.getAdmsTypeCode()));
        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(messageResolver.getMessage("U318"));
            String errMsg = null;
            if ( r1 == 0 ) errMsg = messageResolver.getMessage("ERR0003");
            else if ( r2 == 0 ) errMsg = messageResolver.getMessage("ERR0011");
            ec.setData(new CustomApplNoStsCode(applNo, APP_NULL_STATUS, errMsg));
        }
        return ec;
    }

    /**
     * 학력 정보 수정
     *
     * @param application
     * @param collegeList
     * @param graduateList
     * @return
     */
    @Override
    public ExecutionContext updateAcademy(Application application,
                                          List<CustomApplicationAcademy> collegeList,
                                          List<CustomApplicationAcademy> graduateList) {
        List<ApplicationAcademy> acadList = new ArrayList<ApplicationAcademy>();
        acadList.addAll(collegeList);
        acadList.addAll(graduateList);

        ExecutionContext ec = new ExecutionContext();
        int applNo = application.getApplNo();
        ParamForAcademy param = new ParamForAcademy();
        param.setApplNo(applNo);
        param.setOrderBy("ACAD_SEQ");

        param.setAcadTypeCode("00002");
        int r1 = processAcademy(application, collegeList, applNo, new Date(), param);

        param.setAcadTypeCode("00003");
        int r2 = processAcademy(application, graduateList, applNo, new Date(), param);//

        if ( r1 == collegeList.size() && r2 == graduateList.size() ) {
            ec.setResult(ExecutionContext.SUCCESS);
            ec.setMessage(messageResolver.getMessage("U317"));
            ec.setData(new CustomApplNoStsCode(applNo, application.getApplStsCode(),
                    application.getAdmsNo(), application.getEntrYear(), application.getAdmsTypeCode()));
        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(messageResolver.getMessage("U318"));
            ec.setData(new CustomApplNoStsCode(applNo, APP_NULL_STATUS,
                    messageResolver.getMessage("ERR0013")));
        }
        return ec;
    }

    /**
     * 현재 ACAD LIST 가져와서 HashMap을 만들고
     * 화면에서 가져온 리스트 돌면서 HashMap 체크해서
     * 케이스 별로 CRUD 처리
     * 화면 seq 있고, DB seq 있으면 화면 레코드로 수정 하고 Map에서 삭제
     * 화면 seq 있고, DB seq 없는 케이스는 없음
     * 화면 seq 공백, DB seq 없으면 화면 레코드로 생성
     * 화면 seq 공백, DB seq 있으면 Map에 남아있으므로 DB레코드 삭제 하고 Map에서 삭제
     *
     * @param application
     * @param academyList
     * @param applNo
     * @param date
     * @param param
     * @return
     */
    private int processAcademy(Application application,
                               List<CustomApplicationAcademy> academyList,
                               int applNo,
                               Date date,
                               ParamForAcademy param) {

        int c1 = 0, u1 = 0, d1 = 0, lastSeq = 1;
        Map<Integer, Integer> seqMap = new HashMap<Integer, Integer>();

        List<ApplicationAcademy> academiesFromDB = commonDAO.queryForList(NAME_SPACE+"CustomApplicationAcademyMapper.selectByApplNoAcadTypeCode", param, ApplicationAcademy.class);
        if ( academiesFromDB.size() > 0 ) {
            lastSeq = academiesFromDB.get(academiesFromDB.size()-1).getAcadSeq();

            if ( academiesFromDB != null ) {
                for (ApplicationAcademy academy : academiesFromDB) {
                    seqMap.put(academy.getAcadSeq(), academy.getAcadSeq());
                }
            }
        }


        if ( academyList != null ) {
            for( ApplicationAcademy academyFromView : academyList) {
                int acadSeqFromView = academyFromView.getAcadSeq() == null ? -1 : academyFromView.getAcadSeq();
                if ( acadSeqFromView > 0) { //화면 seq 있는 경우
                    if ( seqMap.containsKey(acadSeqFromView) ) { //화면 seq 값이 DB에도 있는 경우
                        //update
                        academyFromView.setModId(application.getUserId());
                        academyFromView.setModDate(date);
                        u1 += commonDAO.updateItem(academyFromView, NAME_SPACE, "ApplicationAcademyMapper");
                        seqMap.remove(acadSeqFromView);
                    }
                } else { // 화면 seq 가 숫자가 아닌 경우
                    // 새 seq 부여해서 insert
                    academyFromView.setApplNo(applNo);
                    academyFromView.setAcadSeq(++lastSeq);
                    academyFromView.setCreId(application.getUserId());
                    academyFromView.setCreDate(date);
                    c1 += commonDAO.insertItem(academyFromView, NAME_SPACE, "ApplicationAcademyMapper");
                }
            }
            //Map에 남아있는 레코드는 delete
            Set<Map.Entry<Integer, Integer>> entrySet = seqMap.entrySet();
            for(Map.Entry<Integer, Integer> entry : entrySet) {
                int seqFromDB = entry.getKey();
                param.setAcadSeq(seqFromDB);
                d1 += commonDAO.delete(NAME_SPACE + "CustomApplicationAcademyMapper.deleteByApplNoAcadTypeCodeAcadSeq", param);
            }
        }
System.out.println(param.getAcadTypeCode() + " : " + c1+u1+d1);
        return c1 + u1;
    }

    /**
     * 어학 경력 정보 생성
     *
     * @param application
     * @param applicationLanguageList
     * @param applicationExperienceList
     * @return
     */
    @Override
    public ExecutionContext createLangCareer(Application application,
                                             List<ApplicationLanguage> applicationLanguageList,
                                             List<ApplicationExperience> applicationExperienceList) {

        ExecutionContext ec = new ExecutionContext();
        int r1, r2 = 0, r3 = 0, applNo = application.getApplNo(), idx = 0;
        Date date = new Date();
        String userId = application.getUserId();

        application.setApplStsCode(LANG_CAREER_SAVED);
        application.setModDate(date);
        r1 = commonDAO.updateItem(application, NAME_SPACE, "ApplicationMapper");

        if ( applicationLanguageList != null ) {
            for( ApplicationLanguage applicationLanguage : applicationLanguageList) {
                applicationLanguage.setApplNo(applNo);
                applicationLanguage.setLangSeq(++idx);
                applicationLanguage.setCreId(userId);
                applicationLanguage.setCreDate(date);
            }
            r2 = commonDAO.insertList(applicationLanguageList, NAME_SPACE, "ApplicationLanguageMapper");
        }
        idx = 0;
        if ( applicationExperienceList != null ) {
            for( ApplicationExperience applicationExperience : applicationExperienceList) {
                applicationExperience.setApplNo(applNo);
                applicationExperience.setExprSeq(++idx);
                applicationExperience.setCreId(userId);
                applicationExperience.setCreDate(date);
            }
            r3 = commonDAO.insertList(applicationExperienceList, NAME_SPACE, "ApplicationExperienceMapper");
        }

        if ( r1 > 0 && r2 > 0 && r3 > 0) {
            ec.setResult(ExecutionContext.SUCCESS);
            ec.setMessage(messageResolver.getMessage("U319"));
            ec.setData(new CustomApplNoStsCode(applNo, application.getApplStsCode(),
                    application.getAdmsNo(), application.getEntrYear(), application.getAdmsTypeCode()));
        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(messageResolver.getMessage("U320"));
            String errMsg = null;
            if ( r1 == 0 ) errMsg = messageResolver.getMessage("ERR0003");
            else if ( r2 == 0 ) errMsg = messageResolver.getMessage("ERR0016");
            else if ( r3 == 0 ) errMsg = messageResolver.getMessage("ERR0021");
            ec.setData(new CustomApplNoStsCode(applNo, APP_NULL_STATUS, errMsg));
        }
        return ec;
    }

    /**
     * 어학 경력 정보 수정
     *
     * @param application
     * @param applicationLanguageList
     * @param applicationExperienceList
     * @return
     */
    @Override
    public ExecutionContext updateLangCareer(Application application,
                                             List<ApplicationLanguage> applicationLanguageList,
                                             List<ApplicationExperience> applicationExperienceList) {

        ExecutionContext ec = new ExecutionContext();
        int r1 = 0, r2 = 0, applNo = application.getApplNo(), idx = 0;
        Date date = new Date();
        String userId = application.getUserId();

        deleteListByApplNo(applNo, "CustomApplicationLanguageMapper");
        if ( applicationLanguageList != null ) {
            for( ApplicationLanguage applicationLanguage : applicationLanguageList) {
                applicationLanguage.setApplNo(applNo);
                applicationLanguage.setLangSeq(++idx);
                applicationLanguage.setModId(userId);
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
                applicationExperience.setModId(userId);
                applicationExperience.setModDate(date);
            }
            r2 = commonDAO.insertList(applicationExperienceList, NAME_SPACE, "ApplicationExperienceMapper");
        }

        if ( r1 > 0 && r2 > 0 ) {
            ec.setResult(ExecutionContext.SUCCESS);
            ec.setMessage(messageResolver.getMessage("U319"));
            ec.setData(new CustomApplNoStsCode(applNo, application.getApplStsCode(),
                    application.getAdmsNo(), application.getEntrYear(), application.getAdmsTypeCode()));
        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(messageResolver.getMessage("U320"));
            String errMsg = null;
            if ( r1 == 0 ) errMsg = messageResolver.getMessage("ERR0018");
            else if ( r2 == 0 ) errMsg = messageResolver.getMessage("ERR0023");
            ec.setData(new CustomApplNoStsCode(applNo, APP_NULL_STATUS, errMsg));
        }
        return ec;
    }

    /**
     * 파일 업로드 정보 생성
     *
     * @param application
     * @param docGroupFileList
     * @return
     */
    @Override
    public ExecutionContext createFileUpload(Application application,
                                             List<DocGroupFile> docGroupFileList) {

        ExecutionContext ec = new ExecutionContext();
        int r1 = 0, applNo = application.getApplNo(), idx = 0;
        Date date = new Date();
        String userId = application.getUserId();

        application.setApplStsCode(FILEUPLOAD_SAVED);
        application.setModDate(date);
        r1 = commonDAO.updateItem(application, NAME_SPACE, "ApplicationMapper");

        if ( docGroupFileList != null ) {
            for( DocGroupFile docGroupFile : docGroupFileList) {
                List<TotalApplicationDocument> mDocList = docGroupFile.getMandDocList();
                if ( mDocList != null ) {
                    for (TotalApplicationDocument mDoc : mDocList) {
                        mDoc.setApplNo(applNo);
                        mDoc.setDocSeq(++idx);
                        mDoc.setCreId(userId);
                        mDoc.setCreDate(date);
                    }
                }
                r1 += commonDAO.insertList(mDocList, NAME_SPACE, "ApplicationDocumentMapper");
            }
        }

        if ( r1 == idx ) {
            ec.setResult(ExecutionContext.SUCCESS);
            ec.setMessage(messageResolver.getMessage("U325"));
            ec.setData(new CustomApplNoStsCode(applNo, application.getApplStsCode(),
                    application.getAdmsNo(), application.getEntrYear(), application.getAdmsTypeCode()));
        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(messageResolver.getMessage("U326"));
            String errMsg = messageResolver.getMessage("ERR0031");
            ec.setData(new CustomApplNoStsCode(applNo, APP_NULL_STATUS, errMsg));
        }

        return ec;
    }

    /**
     * 파일 업로드 정보 수정
     *
     * @param application
     * @param docGroupFileList
     * @return
     */
    @Override
    public ExecutionContext updateFileUpload(Application application,
                                             List<DocGroupFile> docGroupFileList) {

        ExecutionContext ec = new ExecutionContext();
        int r1 = 0, applNo = application.getApplNo(), idx = 0;
        Date date = new Date();
        String userId = application.getUserId();

        deleteListByApplNo(applNo, "CustomApplicationDocumentMapper");
        if ( docGroupFileList != null ) {
            for( DocGroupFile docGroupFile : docGroupFileList) {
                List<TotalApplicationDocument> mDocList = docGroupFile.getMandDocList();
                if ( mDocList != null ) {
                    for (TotalApplicationDocument mDoc : mDocList) {
                        mDoc.setApplNo(applNo);
                        mDoc.setDocSeq(++idx);
                        mDoc.setModId(userId);
                        mDoc.setModDate(date);
                    }
                }
                r1 += commonDAO.insertList(mDocList, NAME_SPACE, "ApplicationDocumentMapper");
            }
        }

        if ( r1 == idx ) {
            ec.setResult(ExecutionContext.SUCCESS);
            ec.setMessage(messageResolver.getMessage("U325"));
            ec.setData(new CustomApplNoStsCode(applNo, application.getApplStsCode(),
                    application.getAdmsNo(), application.getEntrYear(), application.getAdmsTypeCode()));
        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(messageResolver.getMessage("U326"));
            String errMsg = messageResolver.getMessage("ERR0033");
            ec.setData(new CustomApplNoStsCode(applNo, APP_NULL_STATUS, errMsg));
        }
        return ec;
    }

    /**
     * 입학원서 전체 저장
     *
     * @param entireApplication
     * @return
     */
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
//
//            entireApplication.getHighSchool().setApplNo(applNo);
//            entireApplication.getHighSchool().setAcadSeq(1);
//            entireApplication.getHighSchool().setCreDate(date);
//            r4 = commonDAO.insertItem(entireApplication.getHighSchool(), NAME_SPACE, "ApplicationAcademyMapper");

            List<CustomApplicationAcademy> collegeList = entireApplication.getCollegeList();
            int idx = 1;
            if ( collegeList != null ) {
                for( ApplicationAcademy college : collegeList) {
                    college.setApplNo(applNo);
                    college.setAcadSeq(++idx);
                    college.setCreDate(date);
                }
                r5 = commonDAO.insertList(collegeList, NAME_SPACE, "ApplicationAcademyMapper");
            }

            List<CustomApplicationAcademy> graduateList = entireApplication.getGraduateList();
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
/*
            List<ApplicationDocument> generalDocList = entireApplication.getGeneralDocList();
            idx = 0;
            if ( generalDocList != null ) {
                for(ApplicationDocument item : generalDocList) {
                    item.setApplNo(applNo);
                    item.setModDate(date);
                    item.setDocSeq(++idx);
                }
                r9 = commonDAO.insertList(generalDocList, NAME_SPACE, "ApplicationDocumentMapper");
            }*/
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

    /**
     * 입학원서 부분 정보 삭제
     *
     * @param applNo
     * @param MapperName
     * @return
     */
    @Override
    public int deleteListByApplNo(int applNo, String MapperName) {
        return commonDAO.delete(NAME_SPACE + MapperName + ".deleteListByApplNo", applNo);
    }

    /**
     * 입학원서 전체 정보 수정
     *
     * @param entireApplication
     * @return
     */
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
//            entireApplication.getHighSchool().setApplNo(applNo);
//            entireApplication.getHighSchool().setAcadSeq(1);
//            entireApplication.getHighSchool().setModDate(date);
//            r4 = commonDAO.insertItem(entireApplication.getHighSchool(), NAME_SPACE, "ApplicationAcademyMapper");

            List<CustomApplicationAcademy> collegeList = entireApplication.getCollegeList();
            int idx = 1;
            if ( collegeList != null ) {
                for( ApplicationAcademy college : collegeList) {
                    college.setApplNo(applNo);
                    college.setAcadSeq(++idx);
                    college.setModDate(date);
                }
                r5 = commonDAO.insertList(collegeList, NAME_SPACE, "ApplicationAcademyMapper");
            }

            List<CustomApplicationAcademy> graduateList = entireApplication.getGraduateList();
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
/*
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
            }*/

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

    /**
     * 입학원서 전체 정보 확정
     *
     * @param entireApplication
     * @return
     */
    @Override
    public ExecutionContext confirmEntireApplication(EntireApplication entireApplication) {
        int r1 = 0, r2 = 0, r3 = 0, r4 = 0, r5 = 0;
        ExecutionContext ec1 = null;
        ExecutionContext ec2 = null;
        ExecutionContext ec3 = null;
        ExecutionContext ec = new ExecutionContext();
        int applNo = entireApplication.getApplication().getApplNo();

        try {
            Date date = new Date();

            Application application = entireApplication.getApplication();
            ApplicationGeneral applicationGeneral = entireApplication.getApplicationGeneral();
            ApplicationForeigner applicationForeigner = entireApplication.getApplicationForeigner();

            application.setModDate(date);
            application.setApplStsCode("00010");
            applicationGeneral.setModDate(date);
            applicationForeigner.setModDate(date);
            r1 = commonDAO.updateItem(application, NAME_SPACE, "ApplicationMapper");
            r2 = commonDAO.updateItem(applicationGeneral, NAME_SPACE, "ApplicationGeneralMapper");
            r3 = commonDAO.updateItem(applicationForeigner, NAME_SPACE, "ApplicationForeignerMapper");

            ec1 = updateAcademy(application,
                    entireApplication.getCollegeList(),
                    entireApplication.getGraduateList());

            ec2 = updateLangCareer(application,
                    entireApplication.getApplicationLanguageList(),
                    entireApplication.getApplicationExperienceList());

            ec3 = updateFileUpload(application,
                    entireApplication.getDocGroupList());

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
                r4 = commonDAO.insertItem(ap, PAYMENT_NAME_SPACE, "ApplicationPaymentMapper");
            } else {
                ap.setApplNo(applNo);
                ap.setPaySeq(paySeq);
                ap.setExpPayAmt(admsFee);
                ap.setModDate(date);
                r4 = commonDAO.updateItem(ap, PAYMENT_NAME_SPACE, "ApplicationPaymentMapper");
            }

        } catch ( Exception e ) {
            e.printStackTrace();
        }

        // TODO 모두 성공 검출 조건 보완 필요
        if ( r3 >= 0 &&
                ec1.getResult().equals(ExecutionContext.SUCCESS) &&
                ec2.getResult().equals(ExecutionContext.SUCCESS) &&
                ec3.getResult().equals(ExecutionContext.SUCCESS) ) {
            ec.setResult(ExecutionContext.SUCCESS);
            ec.setMessage(messageResolver.getMessage("U327"));
            ec.setData(new CustomApplNoStsCode(applNo, entireApplication.getApplication().getApplStsCode()));
        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(messageResolver.getMessage("U328"));
            String errMsg = messageResolver.getMessage("ERR0033");
            ec.setData(new CustomApplNoStsCode(applNo, APP_NULL_STATUS, errMsg));
        }
        return ec;
    }

    /**
     * 입학원서 전체 정보 조회
     *
     * @param applNo
     * @return
     */
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
            entireApplication.setCollegeList(retrieveInfoListByParamObj(paramForAcademy, aaMapperSqlId, CustomApplicationAcademy.class));
            paramForAcademy.setAcadTypeCode(("00003"));
            entireApplication.setGraduateList(retrieveInfoListByParamObj(paramForAcademy, aaMapperSqlId, CustomApplicationAcademy.class));

            entireApplication.setApplicationExperienceList(retrieveInfoListByApplNo(applNo, "CustomApplicationExperienceMapper", ApplicationExperience.class));
            entireApplication.setLangGroupList(retrieveLanguageGroupListByApplNo(applNo));



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



    public DocumentGroup retrieveManApplDocListByApplNo_2( int applNo) {

        DocumentGroup docGrp = null;
        List<TotalApplicationDocumentContainer> applContList = new ArrayList<TotalApplicationDocumentContainer>();


        try {

            Application tempApp = commonDAO.queryForObject(NAME_SPACE + "ApplicationMapper.selectByPrimaryKey", applNo, Application.class);
            String admsNo = tempApp.getAdmsNo();
            //학과별정보 조회
            applContList.add(retrieveDeptDocumentByApplNo(applNo ));

            //학력정보 조회
            applContList.add(retrieveAcademyDocumentByApplNo(applNo, admsNo));


            //어학정보 조회
            applContList.add(retrieveLanguageDocumentByApplNo(applNo));

            //코드별 조건 조회
            applContList.addAll(retrieveCodeDocumentByApplNo(applNo,admsNo));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return docGrp;
    }
    @Override
    public List<DocGroupFile> retrieveManApplDocListByApplNo( int applNo) {

        try {
            retrieveManApplDocListByApplNo_2( applNo);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }




    //학과별 문서요건 정보 조회
    private  TotalApplicationDocumentContainer retrieveDeptDocumentByApplNo( int applNo) {
        TotalApplicationDocumentContainer rApplDoc = null;
        List<TotalApplicationDocumentContainer> applDocList;
        try{

            applDocList = commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectBasicTotalDocListByApplNo", applNo, TotalApplicationDocumentContainer.class);
            if( applDocList != null){
                for ( TotalApplicationDocumentContainer aCont : applDocList){
                    aCont.setSubContainer( getSubDeptDocumentContainer(aCont));
                }
            }
            rApplDoc = new TotalApplicationDocumentContainer();
            rApplDoc.setSubContainer(applDocList);
            rApplDoc.setGrpLabel("기본-학과지정 제출서류");
            rApplDoc.setDisplayGrpFg(true);


        }catch (Exception e) {
            e.printStackTrace();

        }
        return rApplDoc;
    }

    //점수가 입력된 어학문서 정보 조회
    private  TotalApplicationDocumentContainer retrieveLanguageDocumentByApplNo( int applNo) {
        TotalApplicationDocumentContainer rApplDoc = null;
        List<TotalApplicationDocumentContainer> applDocList;
        try{
            int i =1;
            applDocList = commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectLanguageTotalDocListByApplNo", applNo, TotalApplicationDocumentContainer.class);
            if( applDocList != null){
                for ( TotalApplicationDocumentContainer aCont : applDocList){
                   aCont.setDocItemName(aCont.getDocItemName()+" 성적표(증명)");
                }
            }
            rApplDoc = new TotalApplicationDocumentContainer();
            rApplDoc.setSubContainer(applDocList);
            rApplDoc.setGrpLabel("어학 관련서류");
            rApplDoc.setDisplayGrpFg(true);


        }catch (Exception e) {
            e.printStackTrace();

        }
        return rApplDoc;
    }

    //입력된 학력정보별 문서요건 정보 조회
    private  TotalApplicationDocumentContainer retrieveAcademyDocumentByApplNo( int applNo, String admsNo ) {
        TotalApplicationDocumentContainer aCont = null;
        List<TotalApplicationDocumentContainer> applDocList = new ArrayList<TotalApplicationDocumentContainer>();
        ParamForAcademy param = new ParamForAcademy();

        try{
            aCont.setDisplayGrpFg(true);
            param.setApplNo(applNo);
            param.setAcadTypeCode("00002");//대학
            List<CustomApplicationAcademy>acadList;
            acadList =commonDAO.queryForList(NAME_SPACE + "CustomApplicationAcademyMapper.selectByApplNoAcadTypeCode", param, CustomApplicationAcademy.class);

            //대학 갯수만큼 켄네이너 생성
            for(CustomApplicationAcademy aAcad : acadList){

                aCont = new TotalApplicationDocumentContainer();
                aCont.setApplNo( aAcad.getApplNo());
                aCont.setGrpLabel(aAcad.getSchlName() + " 관련서류");

                applDocList.add(aCont);

                //학위별 필수서류 셋팅
                aAcad.getAcadSeq();
                aAcad.getGrdaTypeCode();
                aAcad.getSchlCntrCode();
                aAcad.getSchlName();
                ParamForCodeDocument codeParam = new ParamForCodeDocument();
                codeParam.setApplNo(applNo);
                codeParam.setAdmsNo(admsNo);
                codeParam.setAdmsCodeGrp("ACAD_TYPE");
                codeParam.setAdmsCode(aAcad.getAcadTypeCode());
                codeParam.setGrpLevel(1);
                codeParam.setItemTypeCode("00003");

                List<TotalApplicationDocumentContainer> subDocList;
                subDocList = commonDAO.queryForList(NAME_SPACE +"CustomApplicationDocumentMapper.selectCodeMandatoryGroupByCode",codeParam,TotalApplicationDocumentContainer.class);

                //해외학위 필수서류 셋팅
                codeParam.setAdmsCodeGrp("SCHL_CNTR");
                codeParam.setAdmsCode(aAcad.getSchlCntrCode());
                subDocList.addAll(commonDAO.queryForList(NAME_SPACE +"CustomApplicationDocumentMapper.selectCodeMandatoryGroupByCode",codeParam,TotalApplicationDocumentContainer.class));

                for( TotalApplicationDocumentContainer aSubDoc : subDocList ){
                    aSubDoc.setDocGrp(aAcad.getAcadSeq());
                    aSubDoc.setAdmsNo(admsNo);
                    aSubDoc.setSubContainer( getSubCodeDocumentContainer(aSubDoc));
                }

            }
            //대학원 갯수만큼 켄네이너 생성
            param.setAcadTypeCode("00004");//대학원
            acadList =commonDAO.queryForList(NAME_SPACE + "CustomApplicationAcademyMapper.selectByApplNoAcadTypeCode",param,CustomApplicationAcademy.class);

            for(CustomApplicationAcademy aAcad : acadList){

                aCont = new TotalApplicationDocumentContainer();
                aCont.setApplNo( aAcad.getApplNo());
                aCont.setGrpLabel( aAcad.getSchlName() + " 관련서류" );
                applDocList.add(aCont);
                //필수서류 셋팅
                aAcad.getAcadSeq();
                aAcad.getGrdaTypeCode();
                aAcad.getSchlCntrCode();
                aAcad.getSchlName();
                ParamForCodeDocument codeParam = new ParamForCodeDocument();
                codeParam.setApplNo(applNo);
                codeParam.setAdmsNo(admsNo);
                codeParam.setAdmsCodeGrp( "ACAD_TYPE");
                codeParam.setAdmsCode(aAcad.getAcadTypeCode());
                codeParam.setGrpLevel(1);
                codeParam.setItemTypeCode("00004");//대학원

                List<TotalApplicationDocumentContainer> subDocList;
                subDocList = commonDAO.queryForList(NAME_SPACE +"CustomApplicationDocumentMapper.selectCodeMandatoryGroupByCode",codeParam,TotalApplicationDocumentContainer.class);

                //해외학위 필수서류 셋팅
                codeParam.setAdmsCodeGrp("SCHL_CNTR");
                codeParam.setAdmsCode(aAcad.getSchlCntrCode());
                subDocList.addAll(commonDAO.queryForList(NAME_SPACE +"CustomApplicationDocumentMapper.selectCodeMandatoryGroupByCode",codeParam,TotalApplicationDocumentContainer.class));

                for( TotalApplicationDocumentContainer aSubDoc : subDocList ){
                    aSubDoc.setDocGrp(aAcad.getAcadSeq());
                    aSubDoc.setAdmsNo(admsNo);
                    aSubDoc.setSubContainer( getSubCodeDocumentContainer(aSubDoc));
                }

            }
        }catch (Exception e) {
            e.printStackTrace();

        }
        return aCont;
    }

    //지원특성별 문서요건 정보 조회 - from mad_code 테이블
    private  List<TotalApplicationDocumentContainer> retrieveCodeDocumentByApplNo( int applNo, String admsNo  ) {
        List<TotalApplicationDocumentContainer> rContList = new ArrayList<TotalApplicationDocumentContainer>() ;
        TotalApplicationDocumentContainer aCont = null;

        try{
            Application tempApp = commonDAO.queryForObject(NAME_SPACE + "ApplicationMapper.selectByPrimaryKey", applNo, Application.class);
            ParamForCodeDocument codeParam = new ParamForCodeDocument();
            codeParam.setApplNo(applNo);
            codeParam.setAdmsNo(admsNo);

            //학연산 조회
            if( "00006".equals(tempApp.getApplAttrCode())) {

                codeParam.setAdmsCodeGrp("APPL_ATTR");
                codeParam.setAdmsCode("00003");
                codeParam.setGrpLevel(1);
                codeParam.setItemTypeCode("00006");//학연산

                List<TotalApplicationDocumentContainer> subDocList;
                subDocList = commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectCodeMandatoryGroupByCode", codeParam, TotalApplicationDocumentContainer.class);
                for (TotalApplicationDocumentContainer aSubDoc : subDocList) {
                    aSubDoc.setAdmsNo(admsNo);
                    aSubDoc.setSubContainer(getSubCodeDocumentContainer(aSubDoc));
                }
                aCont = new TotalApplicationDocumentContainer();
                aCont.setGrpLabel("학연산 관련서류");
                aCont.setDisplayGrpFg(true);
                aCont.setSubContainer(subDocList);
                rContList.add(aCont);
            }


            //외국인 조회
            if(  "00001".equals(tempApp.getFornTypeCode())||"00002".equals(tempApp.getFornTypeCode())||"00003".equals(tempApp.getFornTypeCode()) ) {
                codeParam.setAdmsCodeGrp("FORN_TYPE");
                codeParam.setAdmsCode(tempApp.getApplAttrCode());
                codeParam.setGrpLevel(1);
                codeParam.setItemTypeCode("00007");//

                List<TotalApplicationDocumentContainer> subDocList;
                subDocList = commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectCodeMandatoryGroupByCode", codeParam, TotalApplicationDocumentContainer.class);
                for (TotalApplicationDocumentContainer aSubDoc : subDocList) {
                    aSubDoc.setAdmsNo(admsNo);
                    aSubDoc.setSubContainer(getSubCodeDocumentContainer(aSubDoc));
                }
                aCont = new TotalApplicationDocumentContainer();
                aCont.setGrpLabel("외국인전형 관련서류");
                aCont.setDisplayGrpFg(true);
                aCont.setSubContainer(subDocList);
                rContList.add(aCont);
            }

            //기타 및 자유입력 조회

                codeParam.setGrpLevel(1);
                codeParam.setItemTypeCode("00009");// 기타 및 추가제출
                List<TotalApplicationDocumentContainer> subDocList;
                subDocList = commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectMandatoryDocumentByDocType", codeParam, TotalApplicationDocumentContainer.class);
                aCont = new TotalApplicationDocumentContainer();
                for (TotalApplicationDocumentContainer aSubDoc : subDocList) {
                    aSubDoc.setAdmsNo(admsNo);
                }
                aCont.setGrpLabel("기타 및 추가제출");
                aCont.setDisplayGrpFg(true);
                aCont.setSubContainer(subDocList);
                rContList.add(aCont);


        }catch (Exception e) {
            e.printStackTrace();

        }
        return rContList;
    }

    private  List<TotalApplicationDocumentContainer> getSubDeptDocumentContainer( TotalApplicationDocumentContainer pCont){
        List<TotalApplicationDocumentContainer> rContList = null;

        try{
            if (!"Y".equals( pCont.getLastYn())) {
                rContList = commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectTotalApplicationDocumentList", pCont, TotalApplicationDocumentContainer.class);
                if (rContList != null) {
                    for (TotalApplicationDocumentContainer aCont : rContList) {
                        aCont.setSubContainer(getSubDeptDocumentContainer(aCont));

                    }
                }
            }
        }catch (Exception e) {
            e.printStackTrace();

        }
        return rContList;
    }

    private  List<TotalApplicationDocumentContainer> getSubCodeDocumentContainer( TotalApplicationDocumentContainer pCont){
        List<TotalApplicationDocumentContainer> rContList = null;

        try{
            if (!"Y".equals( pCont.getLastYn())) {
                rContList = commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectTotalCodeApplicationDocumentList", pCont, TotalApplicationDocumentContainer.class);
                if (rContList != null) {
                    for (TotalApplicationDocumentContainer aCont : rContList) {
                        aCont.setSubContainer(getSubCodeDocumentContainer(aCont));

                    }
                }
            }else{
                ApplicationDocument aDoc;

                aDoc = commonDAO.queryForObject(NAME_SPACE + "CustomApplicationDocumentMapper.selectCodeApplicationDocumentByTotalDocumentContainner", pCont, ApplicationDocument.class);
                if( aDoc != null){
                    pCont.setDocSeq( aDoc.getDocSeq());
                    pCont.setDocName( aDoc.getDocName());
                    pCont.setFileExt( aDoc.getFileExt());
                    pCont.setImgYn( aDoc.getImgYn());
                    pCont.setFilePath( aDoc.getFilePath());
                    pCont.setFileName(aDoc.getFileName());
                    pCont.setOrgFileName(aDoc.getOrgFileName());
                    pCont.setDocItemNameXxen( aDoc.getDocItemNameXxen());
                    pCont.setDocGrpName( aDoc.getDocGrpName());
                    pCont.setFileUploadFg(true);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();

        }
        return rContList;
    }

    private  List<LanguageGroup> retrieveLanguageGroupListByApplNo( int applNo){

        List<LanguageGroup> langGroupList = null;
        try {

            langGroupList = commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectLanguageGroupByApplNo", applNo, LanguageGroup.class);
            if( langGroupList == null || langGroupList.size()==0){
                LanguageGroup aGroup  = new LanguageGroup();
                aGroup.setExamCodeGrp("LANG_EXAM");
                aGroup.setExamGrpName("영어");
                aGroup.setSelGrpCode("LANG_EXAM");
                aGroup.setExamCode("00001");
                langGroupList.add(aGroup );
            }
            for (LanguageGroup alangGroup : langGroupList) {

                alangGroup.getExamCodeGrp();
                ParamForTotalLang param = new ParamForTotalLang();
                param.setApplNo(applNo);
                param.setSelGrpCode(alangGroup.getSelGrpCode());
                param.setUpCodeGrp(alangGroup.getExamCodeGrp());
                param.setUpCode(alangGroup.getExamCode());
                List<TotalApplicationLanguage> aLangList;
                aLangList = commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectTotalLanguageInfoByApplNo", param, TotalApplicationLanguage.class);

                for( TotalApplicationLanguage alang : aLangList){

                    if( alang.getLangSeq() != null && alang.getLangSeq() > 0 )
                        alang.setLangInfoSaveFg(true);
                    else
                        alang.setLangInfoSaveFg(false);

                    if( alang.getDocSeq() > 0 )
                        alang.setFileUploadFg(true);
                    else
                        alang.setFileUploadFg(false);

                }
                alangGroup.setLangList(aLangList);



            }
        }catch (Exception e) {
            e.printStackTrace();

        }
        return langGroupList;
    }

    @Override
    public ApplicationDocument retrieveApplicationDocumentPhoto(int applNo) {
        ApplicationDocument applicationDocument = null;
        try {
            applicationDocument = commonDAO.queryForObject(NAME_SPACE + "CustomApplicationDocumentMapper.selectPhotoFileByApplNo",
                    applNo, ApplicationDocument.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return applicationDocument;
    }
    private LanguageGroup makeLangGroup( CommonMandatory mandGroup){
        return null;
    }
    private TotalApplicationLanguage makeCustomLang( CommonMandatory mandItem){
        return null;
    }


}

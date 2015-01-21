package com.apexsoft.ysprj.applicants.application.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.ysprj.applicants.application.domain.*;
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
            ec.setData(new ApplicationIdentifier(applNo, tA.getApplStsCode(), tA.getAdmsNo(), tA.getEntrYear(), tA.getAdmsTypeCode()));
        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(messageResolver.getMessage("U306"));
            String errCode = null;
            if ( r1 == 0 ) errCode = messageResolver.getMessage("ERR0001");
            else if ( r2 == 0 ) errCode = messageResolver.getMessage("ERR0006");
            else if ( r3 == 0 ) errCode = messageResolver.getMessage("ERR0026");
            ec.setData(new ApplicationIdentifier(0, APP_NULL_STATUS));
            ec.setErrCode(errCode);
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
            ec.setData(new ApplicationIdentifier(application.getApplNo(), application.getApplStsCode(),
                    application.getAdmsNo(), application.getEntrYear(), application.getAdmsTypeCode()));
        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(messageResolver.getMessage("U316"));
            String errCode = null;
            if ( r1 == 0 ) errCode = messageResolver.getMessage("ERR0003");
            else if ( r2 == 0 ) errCode = messageResolver.getMessage("ERR0008");
            else if ( r3 == 0 ) errCode = messageResolver.getMessage("ERR0028");
            ec.setData(new ApplicationIdentifier(application.getApplNo(), APP_NULL_STATUS));
            ec.setErrCode(errCode);
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
            ec.setData(new ApplicationIdentifier(applNo, application.getApplStsCode(),
                    application.getAdmsNo(), application.getEntrYear(), application.getAdmsTypeCode()));
        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(messageResolver.getMessage("U318"));
            String errCode = null;
            if ( r1 == 0 ) errCode = "ERR0003";
            else if ( r2 == 0 ) errCode = "ERR0011";
            ec.setData(new ApplicationIdentifier(applNo, APP_NULL_STATUS));
            ec.setErrCode(errCode);
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
            ec.setData(new ApplicationIdentifier(applNo, application.getApplStsCode(),
                    application.getAdmsNo(), application.getEntrYear(), application.getAdmsTypeCode()));
        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(messageResolver.getMessage("U318"));
            ec.setData(new ApplicationIdentifier(applNo, APP_NULL_STATUS));
            ec.setErrCode("ERR0013");
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
            ec.setData(new ApplicationIdentifier(applNo, application.getApplStsCode(),
                    application.getAdmsNo(), application.getEntrYear(), application.getAdmsTypeCode()));
        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(messageResolver.getMessage("U320"));
            String errCode = null;
            if ( r1 == 0 ) errCode = "ERR0003";
            else if ( r2 == 0 ) errCode = "ERR0016";
            else if ( r3 == 0 ) errCode = "ERR0021";
            ec.setData(new ApplicationIdentifier(applNo, APP_NULL_STATUS));
            ec.setErrCode(errCode);
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
            ec.setData(new ApplicationIdentifier(applNo, application.getApplStsCode(),
                    application.getAdmsNo(), application.getEntrYear(), application.getAdmsTypeCode()));
        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(messageResolver.getMessage("U320"));
            String errCode = null;
            if ( r1 == 0 ) errCode = "ERR0018";
            else if ( r2 == 0 ) errCode = "ERR0023";
            ec.setData(new ApplicationIdentifier(applNo, APP_NULL_STATUS));
            ec.setErrCode(errCode);
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
                List<MandatoryNAppliedDoc> mDocList = docGroupFile.getMandDocList();
                if ( mDocList != null ) {
                    for (MandatoryNAppliedDoc mDoc : mDocList) {
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
            ec.setData(new ApplicationIdentifier(applNo, application.getApplStsCode(),
                    application.getAdmsNo(), application.getEntrYear(), application.getAdmsTypeCode()));
        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(messageResolver.getMessage("U326"));
            String errMsg = messageResolver.getMessage("ERR0031");
            ec.setData(new ApplicationIdentifier(applNo, APP_NULL_STATUS));
            ec.setErrCode("ERR0031");
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
                List<MandatoryNAppliedDoc> mDocList = docGroupFile.getMandDocList();
                if ( mDocList != null ) {
                    for (MandatoryNAppliedDoc mDoc : mDocList) {
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
            ec.setData(new ApplicationIdentifier(applNo, application.getApplStsCode(),
                    application.getAdmsNo(), application.getEntrYear(), application.getAdmsTypeCode()));
        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(messageResolver.getMessage("U326"));
            ec.setData(new ApplicationIdentifier(applNo, APP_NULL_STATUS));
            ec.setErrCode("ERR0033");
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
            ec.setData(new ApplicationIdentifier(applNo, entireApplication.getApplication().getApplStsCode()));
        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(messageResolver.getMessage("U328"));
            ec.setData(new ApplicationIdentifier(applNo, APP_NULL_STATUS));
            ec.setErrCode("ERR0033");
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
            entireApplication.setApplicationLanguageList(retrieveInfoListByApplNo(applNo, "CustomApplicationLanguageMapper", ApplicationLanguage.class));
            entireApplication.setCurrApplicationLanguageList(retrieveCurrApplLangListByApplNo(applNo));
            entireApplication.setMandLangDocList(retrieveMandLangDocListByApplNo(applNo));

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
    public  List<DocGroupFile> retrieveManApplDocListByApplNo( int applNo) {
        List<DocGroupFile> docGrpList = new ArrayList<DocGroupFile>();
        DocGroupFile docGrp;
        DocGroupFile docSubGrp;
        List<MandatoryNAppliedDoc> tmpDocList;

        try {

            ParamForApplicationMandatoryDoc paramMand = new ParamForApplicationMandatoryDoc();
            Application tempApp = commonDAO.queryForObject(NAME_SPACE + "ApplicationMapper.selectByPrimaryKey", applNo, Application.class);
            paramMand.setAdmsNo( tempApp.getAdmsNo());
            paramMand.setDeptCode(tempApp.getDeptCode());
            paramMand.setCorsTypeCode(tempApp.getCorsTypeCode());
            paramMand.setDetlMajCode(tempApp.getDetlMajCode());


            docGrp = new DocGroupFile();
            docSubGrp = new DocGroupFile();
            List<CommonMandatory> tempCommList = new ArrayList<CommonMandatory>();
            List<CommonMandatory> admsDocList = new ArrayList<CommonMandatory>();
            List<CommonMandatory> admsDeptDocList  = new ArrayList<CommonMandatory>();
            List<CommonMandatory> admsCorsDocList = new ArrayList<CommonMandatory>();
            List<CommonMandatory> admsCorsMajDocList  = new ArrayList<CommonMandatory>();

            admsDocList = commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectAmdsMandatoryList", paramMand, CommonMandatory.class);
            admsDeptDocList = commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectDeptMandatoryList", paramMand, CommonMandatory.class);
            admsCorsDocList = commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectAmdsCorsMandatoryList", paramMand, CommonMandatory.class);
            admsCorsMajDocList = commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectAmdsCorsMajMandatoryList", paramMand, CommonMandatory.class);
            tempCommList = getValidDocItem(  admsDocList,admsDeptDocList, admsCorsDocList, admsCorsMajDocList );

            List<MandatoryNAppliedDoc> tempList = new ArrayList<MandatoryNAppliedDoc>();
            List<ApplicationDocument> appDocList = ( commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectByApplNo", applNo, ApplicationDocument.class));

            tempList =  makeMandatoryNAppliedDoc( tempCommList, appDocList);

            //학과는 기본에 포함
            docGrp.setFileGroupName("기본");
            docGrp.setGroupMsg("");
            docGrp.setMandDocList(tempList);
            //docGrp.setMandDocList(commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectBasicDocListByApplNoWTMandatory", applNo, MandatoryNAppliedDoc.class));
            docGrpList.add(docGrp);

            docGrp = new DocGroupFile();
            docGrp.setFileGroupName("해외학위");
            docGrp.setGroupMsg("");
            docGrp.setMandDocList(commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectOverSeaDocListByApplNoWTMandatory", applNo, MandatoryNAppliedDoc.class));
            docGrpList.add(docGrp);

            docGrp = new DocGroupFile();
            docGrp.setFileGroupName("대학");
            docGrp.setGroupMsg("");
            docGrpList.add(docGrp);

            tmpDocList = commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectUnderDocListByApplNoWTMandatory", applNo, MandatoryNAppliedDoc.class);
            int prevGgrpNo = -1;
            for(MandatoryNAppliedDoc doc :  tmpDocList ) {

                if( prevGgrpNo !=  doc.getDocGrp()){

                    docSubGrp = new DocGroupFile();
                    tmpDocList =new ArrayList<MandatoryNAppliedDoc>();
                    docSubGrp.setDocGrp(doc.getDocGrp());
                    docSubGrp.setFileGroupName( doc.getDocGrpName());
                    docGrp.getSubGrp().add( docSubGrp );
                    prevGgrpNo = doc.getDocGrp();
                }
                    docSubGrp.setMandDocList(tmpDocList);
                    tmpDocList.add(doc);
            }

            docGrp = new DocGroupFile();
            docGrp.setFileGroupName("대학원");
            docGrp.setGroupMsg("");
            docGrpList.add(docGrp);

            tmpDocList = commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectGradDocListByApplNoWTMandatory", applNo, MandatoryNAppliedDoc.class);
            prevGgrpNo = -1;
            for(MandatoryNAppliedDoc doc :  tmpDocList ) {

                if( prevGgrpNo !=  doc.getDocGrp()){

                    docSubGrp = new DocGroupFile();
                    tmpDocList =new ArrayList<MandatoryNAppliedDoc>();
                    docSubGrp.setDocGrp(doc.getDocGrp());
                    docSubGrp.setFileGroupName( doc.getDocGrpName());
                    docGrp.getSubGrp().add( docSubGrp );
                    prevGgrpNo = doc.getDocGrp();
                }
                docSubGrp.setMandDocList(tmpDocList);
                tmpDocList.add(doc);
            }
            tempList = new ArrayList<MandatoryNAppliedDoc>();
            List<CustomApplicationLanguage> appLangList = (commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectCustomApplLangList", applNo, CustomApplicationLanguage.class));
            tempList =  makeMandatoryNAppliedLangDoc( appLangList, appDocList);


            docGrp = new DocGroupFile();
            docGrp.setFileGroupName("어학");
            docGrp.setGroupMsg("");
            docGrp.setMandDocList(tempList);
            //docGrp.setMandDocList(commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectLangDocListByApplNoWTMandatory", applNo, MandatoryNAppliedDoc.class));
            docGrpList.add(docGrp);

            docGrp = new DocGroupFile();
            docGrp.setFileGroupName("학연산");
            docGrp.setGroupMsg("");
            docGrp.setMandDocList(commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectInstDocListByApplNoWTMandatory", applNo, MandatoryNAppliedDoc.class));
            docGrpList.add(docGrp);

            docGrp = new DocGroupFile();
            docGrp.setFileGroupName("외국인");
            docGrp.setGroupMsg("");
            docGrp.setMandDocList(commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectFrgnDocListByApplNoWTMandatory", applNo, MandatoryNAppliedDoc.class));
            docGrpList.add(docGrp);

            docGrp = new DocGroupFile();
            docGrp.setFileGroupName("기타");
            docGrp.setGroupMsg("");
            docGrp.setMandDocList(commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectEtcDocListByApplNoWTMandatory", applNo, MandatoryNAppliedDoc.class));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return docGrpList;
    }
    //중복된 문서는 하부 설정에 우선하므로 상위에서 설정된 조건을 제거한다
    private  List<CommonMandatory> getValidDocItem(List<CommonMandatory> admsDocList,
                                                        List<CommonMandatory> admsDeptDocList,
                                                        List<CommonMandatory> admsCorsDocList,
                                                        List<CommonMandatory> admsCorsMajDocList){

        List<CommonMandatory> manDocList = new ArrayList<CommonMandatory>();
        for( CommonMandatory manDoc : admsCorsMajDocList ){
            manDoc.setBelong( "세부전공");
            manDocList.add( manDoc);
        }
        for( CommonMandatory manDoc : admsCorsDocList ){
            boolean newFg = true;
            for ( CommonMandatory prevManDoc: manDocList) {
                if(  prevManDoc.getGrpCode().equals(manDoc.getGrpCode()) &&
                        prevManDoc.getItemGrpCode().equals(manDoc.getItemGrpCode()) &&
                        prevManDoc.getItemCode().equals(manDoc.getItemCode())){
                    newFg = false;
                    break;
                }
            }
            if(newFg){
                manDoc.setBelong( "지원과정");
                manDocList.add(manDoc);
            }

        }
        for( CommonMandatory manDoc : admsDeptDocList ){
            boolean newFg = true;
            for ( CommonMandatory prevManDoc: manDocList) {
                if (prevManDoc.getGrpCode().equals(manDoc.getGrpCode()) &&
                        prevManDoc.getItemGrpCode().equals(manDoc.getItemGrpCode()) &&
                        prevManDoc.getItemCode().equals(manDoc.getItemCode())){
                    newFg = false;
                    break;
                }
            }
            if(newFg) {
                manDoc.setBelong("지원학과");
                manDocList.add(manDoc);
            }
        }
        for( CommonMandatory manDoc : admsDocList ){
            boolean newFg = true;
            for ( CommonMandatory prevManDoc: manDocList) {
                if(prevManDoc.getGrpCode().equals(manDoc.getGrpCode()) &&
                        prevManDoc.getItemGrpCode().equals(manDoc.getItemGrpCode()) &&
                        prevManDoc.getItemCode().equals(manDoc.getItemCode())){
                    newFg = false;
                    break;
                }
            }
            if(newFg) {
                manDoc.setBelong("지원전형");
                manDocList.add(manDoc);
            }
        }
        return manDocList;
    }

    private  List<MandatoryNAppliedDoc> makeMandatoryNAppliedDoc(List<CommonMandatory> commList,
                                                        List<ApplicationDocument> appDocList){
        List<MandatoryNAppliedDoc> tempList = new ArrayList<MandatoryNAppliedDoc>();
        for (CommonMandatory commDoc : commList) {

            boolean newFg = true;
            for( ApplicationDocument appDoc : appDocList ) {

                if (appDoc.getDocTypeCode()!= null && appDoc.getDocItemCode()!=null && commDoc.getItemGrpCode()!=null &&
                        appDoc.getDocTypeCode().equals(commDoc.getGrpCode()) &&
                        appDoc.getDocItemCode().equals(commDoc.getItemCode()) &&
                        commDoc.getItemGrpCode().equals("DOC_ITEM")) {

                    MandatoryNAppliedDoc multiMandDoc = new MandatoryNAppliedDoc();
                    multiMandDoc.setComMandInfo(commDoc);
                    multiMandDoc.setAppDocInfo(appDoc);
                    tempList.add( multiMandDoc);
                    newFg =false;
                }
            }
            if(newFg && ( "00008".equals(commDoc.getGrpCode()) ||"00001".equals( commDoc.getGrpCode()))){
                MandatoryNAppliedDoc mandDoc = new MandatoryNAppliedDoc();
                mandDoc.setComMandInfo(commDoc);
                tempList.add( mandDoc);
            }
        }
        return tempList;
    }
    private  List<MandatoryNAppliedLangDoc> makeMandatoryNAppliedLangDoc(List<CommonMandatory> commList,
                                                                         List<CustomApplicationLanguage> appLangList,
                                                                         List<ApplicationDocument> appDocList) {
        List<MandatoryNAppliedLangDoc> tempList = new ArrayList<MandatoryNAppliedLangDoc>();

        for (CommonMandatory commDoc : commList) {
            if( "00005".equals(commDoc.getGrpCode()) ){
                MandatoryNAppliedLangDoc mandLangDoc = new MandatoryNAppliedLangDoc();
                mandLangDoc.setComMandInfo(commDoc);


                for (CustomApplicationLanguage appLang : appLangList) {
                    if (appLang.getLangExamCode() != null &&
                            "00005".equals(commDoc.getGrpCode()) &&
                            "LANG_EXAM".equals(commDoc.getItemGrpCode()) &&
                            appLang.getLangExamCode().equals(commDoc.getItemCode())) {
                        mandLangDoc.setCustomAppLangInfo(appLang);
                        for (ApplicationDocument appDoc : appDocList) {
                            if (appDoc.getDocGrp() != null &&
                                    "00005".equals(appDoc.getDocTypeCode()) &&
                                    "00016".equals(appDoc.getDocItemCode()) &&
                                    appDoc.getDocGrp() == appLang.getLangSeq()) {
                                mandLangDoc.setAppDocInfo(appDoc);
                            }
                        }
                    }
                }
                tempList.add( mandLangDoc);
            }
        }
        return tempList;
    }

    private  List<MandatoryNAppliedLangDoc> makeMandatoryNAppliedLangDoc(List<MandatoryNAppliedLangDoc> codeList,
                                                                         List<CommonMandatory> commList,
                                                                         List<CustomApplicationLanguage> appLangList,
                                                                         List<ApplicationDocument> appDocList) {

        for( MandatoryNAppliedLangDoc code : codeList)
            for (CommonMandatory commDoc : commList) {
                if( "00005".equals(commDoc.getGrpCode()) &&  code.getLangExamCode() !=null && code.getLangExamCode().equals(commDoc.getItemCode())){
                    code.setComMandInfo(commDoc);
                    for (CustomApplicationLanguage appLang : appLangList) {
                        if (appLang.getLangExamCode() != null &&
                                "00005".equals(commDoc.getGrpCode()) &&
                                "LANG_EXAM".equals(commDoc.getItemGrpCode()) &&
                                appLang.getLangExamCode().equals(commDoc.getItemCode())) {
                            code.setCustomAppLangInfo(appLang);
                            for (ApplicationDocument appDoc : appDocList) {
                                if (appDoc.getDocGrp() != null &&
                                        "00005".equals(appDoc.getDocTypeCode()) &&
                                        "00016".equals(appDoc.getDocItemCode()) &&
                                        appDoc.getDocGrp() == appLang.getLangSeq()) {
                                    code.setAppDocInfo(appDoc);
                                }
                            }
                        }
                    }
                }
            }
        return codeList;
    }

    private  List<MandatoryNAppliedDoc> makeMandatoryNAppliedLangDoc( List<CustomApplicationLanguage> appLangList,
                                                                         List<ApplicationDocument> appDocList) {
        List<MandatoryNAppliedDoc> tempList = new ArrayList<MandatoryNAppliedDoc>();

        for (CustomApplicationLanguage appLang : appLangList) {
            MandatoryNAppliedDoc mandDoc = new MandatoryNAppliedDoc();
            mandDoc.setDocGrp( appLang.getLangSeq() );
//            mandDoc.setDocName( appLang.getLangExamName());
            mandDoc.setMdtYn("Y");
            mandDoc.setUploadYn("Y");
            mandDoc.setSendCnt(1);
//            mandDoc.setDocItemName(appLang.getLangExamName() +"성적표(증명)");
            for (ApplicationDocument appDoc : appDocList) {
                if (appDoc.getDocGrp() != null &&
                        "00005".equals(appDoc.getDocTypeCode()) &&
                        "00016".equals(appDoc.getDocItemCode()) &&
                        appDoc.getDocGrp() == appLang.getLangSeq()) {
                    mandDoc.setAppDocInfo(appDoc);
                }
            }
            tempList.add(mandDoc);
        }
        return tempList;
    }

    private  List<MandatoryNAppliedLangDoc>  retrieveMandLangDocListByApplNo(int applNo) {

        ParamForApplicationMandatoryDoc paramMand = new ParamForApplicationMandatoryDoc();
        Application tempApp = commonDAO.queryForObject(NAME_SPACE + "ApplicationMapper.selectByPrimaryKey", applNo, Application.class);

        paramMand.setAdmsNo(tempApp.getAdmsNo());
        paramMand.setDeptCode(tempApp.getDeptCode());
        paramMand.setCorsTypeCode(tempApp.getCorsTypeCode());
        paramMand.setDetlMajCode(tempApp.getDetlMajCode());

        List<CommonMandatory> tempCommList = new ArrayList<CommonMandatory>();
        List<CommonMandatory> admsDocList = new ArrayList<CommonMandatory>();
        List<CommonMandatory> admsDeptDocList = new ArrayList<CommonMandatory>();
        List<CommonMandatory> admsCorsDocList = new ArrayList<CommonMandatory>();
        List<CommonMandatory> admsCorsMajDocList = new ArrayList<CommonMandatory>();

        //admsDocList = commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectAmdsMandatoryList", paramMand, CommonMandatory.class);

        admsDeptDocList = commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectDeptMandatoryList", paramMand, CommonMandatory.class);
        admsCorsDocList = commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectAmdsCorsMandatoryList", paramMand, CommonMandatory.class);
        admsCorsMajDocList = commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectAmdsCorsMajMandatoryList", paramMand, CommonMandatory.class);
        tempCommList = getValidDocItem(admsDocList, admsDeptDocList, admsCorsDocList, admsCorsMajDocList);

        List<MandatoryNAppliedLangDoc> tempList = new ArrayList<MandatoryNAppliedLangDoc>();
        List<CustomApplicationLanguage> appLangList = (commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectCustomApplLangList", applNo, CustomApplicationLanguage.class));
        List<ApplicationDocument> appDocList = (commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectByApplNo", applNo, ApplicationDocument.class));

        //tempList = makeMandatoryNAppliedLangDoc(tempCommList, appLangList, appDocList);
        MandatoryNAppliedLangDoc temp1 = new MandatoryNAppliedLangDoc();
        temp1.setLangExamCode("00001");
        temp1.setLangExamName("TOEFL");
        MandatoryNAppliedLangDoc temp2 = new MandatoryNAppliedLangDoc();
        temp2.setLangExamCode("00002");
        temp2.setLangExamName("TOEIC");
        MandatoryNAppliedLangDoc temp3 = new MandatoryNAppliedLangDoc();
        temp3.setLangExamCode("00003");
        temp3.setLangExamName("TEPS");
        MandatoryNAppliedLangDoc temp4 = new MandatoryNAppliedLangDoc();
        temp4.setLangExamCode("00004");
        temp4.setLangExamName("IELTS");
        MandatoryNAppliedLangDoc temp5 = new MandatoryNAppliedLangDoc();
        temp5.setLangExamCode("00005");
        temp5.setLangExamName("GRE");
        List<MandatoryNAppliedLangDoc> tempCodeList = new ArrayList<MandatoryNAppliedLangDoc>();
        tempCodeList.add(temp1);
        tempCodeList.add(temp2);
        tempCodeList.add(temp3);
        tempCodeList.add(temp4);
        tempCodeList.add(temp5);

        tempList = makeMandatoryNAppliedLangDoc(tempCodeList, tempCommList, appLangList, appDocList);
        return tempList;

    }

    private  List<ApplicationLanguage> retrieveCurrApplLangListByApplNo(int applNo) {

        List<ApplicationLanguage> appLangList = (commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectCurrApplLangList", applNo, ApplicationLanguage.class));
        return appLangList;
    }
    /*
    public  List<DocGroupFile> retrieveManApplDocListByApplNo_old( int applNo) {
        List<DocGroupFile> docGrpList = new ArrayList<DocGroupFile>();
        DocGroupFile docGrp;
        DocGroupFile docSubGrp;
        List<MandatoryNAppliedDoc> tmpDocList;

        try {
            docGrp = new DocGroupFile();
            docSubGrp = new DocGroupFile();
            tmpDocList = new ArrayList<MandatoryNAppliedDoc>();
            docGrp.setFileGroupName("기본");
            docGrp.setGroupMsg("");
            docGrp.setMandDocList(commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectBasicDocListByApplNoWTMandatory", applNo, MandatoryNAppliedDoc.class));
            docGrpList.add(docGrp);

            //학과는 기본에 포함
            docGrp.setGroupMsg("");
            docGrp.setMandDocList(commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectBasicDocListByApplNoWTMandatory", applNo, MandatoryNAppliedDoc.class));
            docGrpList.add(docGrp);


            docGrp = new DocGroupFile();
            docGrp.setFileGroupName("해외학위");
            docGrp.setGroupMsg("");
            docGrp.setMandDocList(commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectOverSeaDocListByApplNoWTMandatory", applNo, MandatoryNAppliedDoc.class));
            docGrpList.add(docGrp);

            docGrp = new DocGroupFile();
            docGrp.setFileGroupName("대학");
            docGrp.setGroupMsg("");
            docGrpList.add(docGrp);

            tmpDocList = commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectUnderDocListByApplNoWTMandatory", applNo, MandatoryNAppliedDoc.class);
            int prevGgrpNo = -1;
            for(MandatoryNAppliedDoc doc :  tmpDocList ) {

                if( prevGgrpNo !=  doc.getDocGrp()){

                    docSubGrp = new DocGroupFile();
                    tmpDocList =new ArrayList<MandatoryNAppliedDoc>();
                    docSubGrp.setDocGrp(doc.getDocGrp());
                    docSubGrp.setFileGroupName( doc.getDocGrpName());
                    docGrp.getSubGrp().add( docSubGrp );
                    prevGgrpNo = doc.getDocGrp();
                }
                docSubGrp.setMandDocList(tmpDocList);
                tmpDocList.add(doc);
            }


            docGrp = new DocGroupFile();
            docGrp.setFileGroupName("대학원");
            docGrp.setGroupMsg("");
            docGrpList.add(docGrp);

            tmpDocList = commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectGradDocListByApplNoWTMandatory", applNo, MandatoryNAppliedDoc.class);
            prevGgrpNo = -1;
            for(MandatoryNAppliedDoc doc :  tmpDocList ) {

                if( prevGgrpNo !=  doc.getDocGrp()){

                    docSubGrp = new DocGroupFile();
                    tmpDocList =new ArrayList<MandatoryNAppliedDoc>();
                    docSubGrp.setDocGrp(doc.getDocGrp());
                    docSubGrp.setFileGroupName( doc.getDocGrpName());
                    docGrp.getSubGrp().add( docSubGrp );
                    prevGgrpNo = doc.getDocGrp();
                }
                docSubGrp.setMandDocList(tmpDocList);
                tmpDocList.add(doc);
            }

            docGrp = new DocGroupFile();
            docGrp.setFileGroupName("어학");
            docGrp.setGroupMsg("");
            docGrp.setMandDocList(commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectLangDocListByApplNoWTMandatory", applNo, MandatoryNAppliedDoc.class));
            docGrpList.add(docGrp);

            docGrp = new DocGroupFile();
            docGrp.setFileGroupName("학연산");
            docGrp.setGroupMsg("");
            docGrp.setMandDocList(commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectInstDocListByApplNoWTMandatory", applNo, MandatoryNAppliedDoc.class));
            docGrpList.add(docGrp);

            docGrp = new DocGroupFile();
            docGrp.setFileGroupName("외국인");
            docGrp.setGroupMsg("");
            docGrp.setMandDocList(commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectDeptDocListByApplNoWTMandatory", applNo, MandatoryNAppliedDoc.class));
            docGrpList.add(docGrp);


            docGrp = new DocGroupFile();
            docGrp.setFileGroupName("기타");
            docGrp.setGroupMsg("");
            docGrp.setMandDocList(commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectEtcDocListByApplNoWTMandatory", applNo, MandatoryNAppliedDoc.class));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return docGrpList;
    }
    */
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
}

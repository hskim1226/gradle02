package com.apexsoft.ysprj.applicants.application.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.exception.ErrorInfo;
import com.apexsoft.framework.exception.YSBizException;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.ysprj.applicants.application.domain.Application;
import com.apexsoft.ysprj.applicants.application.domain.ApplicationForeigner;
import com.apexsoft.ysprj.applicants.application.domain.ApplicationGeneral;
import com.apexsoft.ysprj.applicants.application.domain.Basis;
import com.apexsoft.ysprj.applicants.common.domain.*;
import com.apexsoft.ysprj.applicants.common.service.CommonService;
import com.apexsoft.ysprj.applicants.common.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by hanmomhanda on 15. 1. 9.
 *
 * 원서 기본 정보 CRUD
 */
@Service
public class BasisServiceImpl implements BasisService {

    private final static String NAME_SPACE = "com.apexsoft.ysprj.applicants.application.sqlmap.";

    @Autowired
    private CommonDAO commonDAO;

    @Autowired
    private CommonService commonService;

    private final String APP_INFO_SAVED = "00001";       // 기본정보 저장

    /**
     * 기본 정보 입력 화면의 select box에 표시될 정보 조회
     * @param basis
     * @return
     */
    @Override
    public ExecutionContext<Map<String, Object>> retrieveSelectionMap(Basis basis) {
        ExecutionContext<Map<String, Object>> ec = new ExecutionContext<>();
        Map<String, Object> ecDataMap = new HashMap<String, Object>();
        Map<String, Object> selectionMap = new HashMap<String, Object>();


        Application application = basis.getApplication();
        // 아래 ParamForSetCourses는 그냥 Application으로 하는 것이 나으나
        // CommonServiceImpl에 너무 많은 메서드가 이미 ParamForSetupCourses에 의존하고 있어 그냥 유지
        ParamForSetupCourses param = new ParamForSetupCourses();
        param.setAdmsNo(application.getAdmsNo());
        param.setCampCode(application.getCampCode());
        param.setCollCode(application.getCollCode());
        param.setDeptCode(application.getDeptCode());
        param.setCorsTypeCode(application.getCorsTypeCode());
        param.setAriInstCode(application.getAriInstCode());

        // 지원 과정 정보
        setupCourseInfo(param, application, selectionMap);

        // 지원자 구분 종류
        selectionMap.put("applAttrList", commonService.retrieveCommonCodeByCodeGroup("APPL_ATTR"));

        // 임시연락처 구분 종류
        selectionMap.put("emerContList", commonService.retrieveCommonCodeByCodeGroup("EMER_CONT"));

        // 외국인 전형 관련 정보
        setupForeignInfo(basis, ecDataMap);

        ec.setResult(ExecutionContext.SUCCESS);
        ecDataMap.put("basis", basis);
        ecDataMap.put("selection", selectionMap);
        ecDataMap.put("ctznCntr", commonService.retrieveCountryByCode(application.getCitzCntrCode()));

        ec.setData(ecDataMap);

        return ec;
    }

    /**
     * 지원과정 관련 정보 셋업
     * @param param
     * @param application
     * @param selectionMap
     */
    private void setupCourseInfo(ParamForSetupCourses param, Application application, Map<String, Object> selectionMap) {
        // 지원사항 select 초기값 설정
        List<Campus> campList = null;
        List<AcademyResearchIndustryInstitution> ariInstList = null;
        List<College> collList = null;
        List<CodeNameDepartment> deptList = null;
        List<CodeNameCourse> corsTypeList = null;
        List<CodeNameDetailMajor> detlMajList = null;

        if (application.isApplicantAriInst()) { // "00002" : 학연산 지원자
            ariInstList = commonService.retrieveAriInst();
            deptList = commonService.retrieveAriInstDepartmentByAdmsAriInst(param);
            corsTypeList = commonService.retrieveAriInstCourseByAdmsDeptAriInst(param);
            detlMajList = commonService.retrieveAriInstDetailMajorByAdmsDeptAriInst(param);
        } else {
            campList = commonService.retrieveCampus();
            collList = commonService.retrieveCollegeByAdmsCamp(param);
            deptList = commonService.retrieveGeneralDepartmentByAdmsColl(param);
            detlMajList = commonService.retrieveGeneralDetailMajorByAdmsDeptCors(param);
            if (application.isApplicantGeneral()) // "00001" : 일반 지원자
                corsTypeList = commonService.retrieveGeneralCourseByAdmsDept(param);
            if (application.isApplicantMilitary()) // "00003" : 군위탁 지원자
                corsTypeList = commonService.retrieveCommissionCourseByAdmsDept(param);
            if (application.isApplicantNKDefector())  // "00004" : 새터민
                corsTypeList = commonService.retrieveNorthDefectorCourseByAdmsDept(param);
        }

        if (campList != null)      selectionMap.put("campList", campList);
        if (collList != null)      selectionMap.put("collList", collList);
        if (ariInstList != null)   selectionMap.put("ariInstList", ariInstList);
        if (deptList != null)      selectionMap.put("deptList", deptList);
        if (corsTypeList != null)  selectionMap.put("corsTypeList", corsTypeList);
        if (detlMajList != null)   selectionMap.put("detlMajList", detlMajList);
    }

    /**
     * 외국인전형 지원자 관련 정보
     * @param basis
     * @param ecDataMap
     */
    private void setupForeignInfo(Basis basis, Map<String, Object> ecDataMap) {
        Map<String, Object> foreignMap = new HashMap<String, Object>();

        Application application = basis.getApplication();
        ApplicationForeigner applicationForeigner = basis.getApplicationForeigner();

        //        if ("C".equals(basis.getApplication().getAdmsTypeCode()) || "D".equals(basis.getApplication().getAdmsTypeCode())) {
        if (application.isForeignAppl()) {
            String cntrCode = applicationForeigner.getBornCntrCode();
            cntrCode = cntrCode == null ? "" : cntrCode;
            Country bornCntr = commonService.retrieveCountryByCode(cntrCode);

            foreignMap.put("foreignTypeList", commonService.retrieveCommonCodeByCodeGroup("FORN_TYPE"));
            foreignMap.put("visaTypeList", commonService.retrieveCommonCodeByCodeGroup("VISA_TYPE"));

            ecDataMap.put("bornCntr", bornCntr);
            ecDataMap.put("foreign", foreignMap);
        }
    }

    /**
     * applNo로 기본 정보 작성 화면을 위한 데이터 조회 및 구성
     * @param applNo
     * @return
     */
    @Override
    public ExecutionContext retrieveBasis(int applNo) {
        Basis basis = new Basis();
        Application application;

        application = commonDAO.queryForObject(NAME_SPACE + "ApplicationMapper.selectByPrimaryKey",
                applNo, Application.class);
        application = application == null ? new Application() : application;
        basis.setApplication(application);
        ExecutionContext ec = retrieveBasis(basis);

        return ec;
    }

    /**
     * 기본 정보 작성 화면을 위한 데이터 조회 및 구성
     *
     * @param basis
     * @return
     */
    @Override
    public ExecutionContext retrieveBasis(Basis basis) {

        ExecutionContext ec = new ExecutionContext();

        Map<String, Object> ecDataMap = new HashMap<String, Object>();
        Map<String, Object> selectionMap = new HashMap<String, Object>();
        Map<String, Object> foreignMap = new HashMap<String, Object>();

        Application application = basis.getApplication();

        int applNo = application == null ? 0 : application.getApplNo() == null ? 0 : application.getApplNo();
        String detlMajCode = (application == null ? null : application.getDetlMajCode());

        if ((detlMajCode != null && !"".equals(detlMajCode)) || applNo > 0) {

            if (applNo > 0) {
                application = commonDAO.queryForObject(NAME_SPACE + "ApplicationMapper.selectByPrimaryKey",
                        applNo, Application.class);
                application = application == null ? new Application() : application;
                basis.setApplication(application);

                ApplicationForeigner applicationForeigner = commonDAO.queryForObject(NAME_SPACE + "ApplicationForeignerMapper.selectByPrimaryKey",
                        applNo, ApplicationForeigner.class);
                applicationForeigner = applicationForeigner == null ? new ApplicationForeigner() : applicationForeigner;

                basis.setApplicationForeigner(applicationForeigner);

                ApplicationGeneral applicationGeneral = commonDAO.queryForObject(NAME_SPACE + "ApplicationGeneralMapper.selectByPrimaryKey",
                        applNo, ApplicationGeneral.class);
                applicationGeneral = applicationGeneral == null ? new ApplicationGeneral() : applicationGeneral;

                basis.setApplicationGeneral(applicationGeneral);
            }

            Map<String, Object> map =  (Map<String, Object>) retrieveSelectionMap(basis).getData();
            Map<String, Object> tmpMap = (Map<String, Object>) map.get("selection");
            List<CommonCode> applAttrList = (List<CommonCode>)tmpMap.get("applAttrList");
            // 캐쉬에서 가져온 리스트를 변형하면 다른 요청에 영향을 미치므로
            // 캐쉬에서 가져온 리스트를 복사한 다른 리스트 저장
            tmpMap.put("applAttrList", getApplAttrList(basis, applAttrList));
            selectionMap.putAll((Map<String, Object>) map.get("selection"));

        } else {

            basis.setApplicationForeigner(new ApplicationForeigner());
            basis.setApplicationGeneral(new ApplicationGeneral());

//            List<Campus> campList = commonService.retrieveCampus();
//            List<AcademyResearchIndustryInstitution> ariInstList = commonService.retrieveAriInst();
//            if (campList != null)      selectionMap.put("campList", campList);
//            if (ariInstList != null)   selectionMap.put("ariInstList", ariInstList);
            // 외국인 전형에서는 지원자 타입이 일반 지원자 밖에 없음
            selectionMap.put("applAttrList", commonService.retrieveApplAttrList("APPL_ATTR"));
            List<CommonCode> applAttrList = (List<CommonCode>)selectionMap.get("applAttrList");
            // 캐쉬에서 가져온 리스트를 변형하면 다른 요청에 영향을 미치므로
            // 캐쉬에서 가져온 리스트를 복사한 다른 리스트 저장
            selectionMap.put("applAttrList", getApplAttrList(basis, applAttrList));
            selectionMap.put("emerContList", commonService.retrieveCommonCodeByCodeGroup("EMER_CONT"));
//            if( "15C".equals(basis.getApplication().getAdmsNo())){
//
//                ArrayList<CommonCode> attrList = (ArrayList<CommonCode>)selectionMap.get("applAttrList");
//                for(int i = attrList.size()-1; i >= 0 ; i--){
//                    if( !"00001".equals(attrList.get(i).getCode())){
//                        attrList.remove(i);
//                    }
//                }
//
//            }
        }

        String cntrCode = basis.getApplication().getCitzCntrCode();
        cntrCode = cntrCode == null ? "" : cntrCode;
        Country ctznCntr = commonService.retrieveCountryByCode(cntrCode);

        cntrCode = basis.getApplicationForeigner().getBornCntrCode();
        cntrCode = cntrCode == null ? "" : cntrCode;
        Country bornCntr = commonService.retrieveCountryByCode(cntrCode);

        foreignMap.put("foreignTypeList", commonService.retrieveCommonCodeByCodeGroup("FORN_TYPE"));
        foreignMap.put("visaTypeList", commonService.retrieveCommonCodeByCodeGroup("VISA_TYPE"));

        ec.setResult(ExecutionContext.SUCCESS);
        ecDataMap.put("basis", basis);
        ecDataMap.put("selection", selectionMap);
        ecDataMap.put("ctznCntr", ctznCntr);
        ecDataMap.put("bornCntr", bornCntr);
        ecDataMap.put("foreign", foreignMap);
        ec.setData(ecDataMap);

        return ec;
    }

    /**
     * 기본 정보 저장
     *
     * @param basis
     * @return
     */
    @Override
    public ExecutionContext saveBasis(Basis basis) {
        ExecutionContext ec = new ExecutionContext();
        Application application = basis.getApplication();
        ApplicationGeneral applicationGeneral = basis.getApplicationGeneral();
        ApplicationForeigner applicationForeigner = basis.getApplicationForeigner();

//        String userId = application.getUserId();
        String modId = application.getModId();
        String admsTypeCode = application.getAdmsTypeCode();
        boolean isMultipleApplicationAllowed = true;
        boolean isValidInsertRequest = false;
        boolean isInsert;
        int r1 = 0, r2 = 0, r3 = 0, applNo = 0;
        Date date = new Date();

        if (application.getApplNo() == null) {
            isInsert = true;
            if (isMultipleApplicationAllowed) {
                isValidInsertRequest = true;
            } else {
                if (hasApplication(modId)) {
                    isValidInsertRequest = false;
                } else {
                    isValidInsertRequest = true;
                }
            }
            if (isValidInsertRequest) {
                application.setApplStsCode(APP_INFO_SAVED);
                application.setUserId(modId);
                application.setCreId(modId);
                application.setModId(null);
                application.setCreDate(date);
                r1 = commonDAO.insertItem(application, NAME_SPACE, "CustomApplicationMapper");
                applNo = application.getApplNo();

//                if ("C".equals(admsTypeCode) || "D".equals(admsTypeCode)) {
                if (application.isForeignAppl()) {
                    applicationForeigner.setApplNo(applNo);
                    applicationForeigner.setCreId(modId);
                    applicationForeigner.setModId(null);
                    applicationForeigner.setCreDate(date);
                    r3 = commonDAO.insertItem(applicationForeigner, NAME_SPACE, "ApplicationForeignerMapper");
                } else {
                    applicationGeneral.setApplNo(applNo);
                    applicationForeigner.setCreId(modId);
                    applicationForeigner.setModId(null);
                    applicationGeneral.setCreDate(date);
                    r2 = commonDAO.insertItem(applicationGeneral, NAME_SPACE, "ApplicationGeneralMapper");
                }
            }
        } else {
            isInsert = false;
            applNo = application.getApplNo();

            application.setModId(modId);
            application.setModDate(date);
            r1 = commonDAO.updateItem(application, NAME_SPACE, "ApplicationMapper");

//            if ("C".equals(admsTypeCode) || "D".equals(admsTypeCode)) {
            if (application.isForeignAppl()) {
                applicationForeigner.setApplNo(applNo);
                applicationForeigner.setModId(modId);
                applicationForeigner.setModDate(date);
                if (applicationForeigner.getVisaExprDay() == null)
                    applicationForeigner.setVisaExprDay("");
                if (applicationForeigner.getFornRgstNo() == null)
                    applicationForeigner.setFornRgstNo("");
                r3 = commonDAO.updateItem(applicationForeigner, NAME_SPACE, "ApplicationForeignerMapper");
            } else {
                applicationGeneral.setApplNo(applNo);
                applicationGeneral.setModId(modId);
                applicationGeneral.setModDate(date);
                r2 = commonDAO.updateItem(applicationGeneral, NAME_SPACE, "ApplicationGeneralMapper");
            }
        }

        if ( r1 == 1 && (r2 + r3 == 1) ) {
            ec.setResult(ExecutionContext.SUCCESS);
//            if ("C".equals(admsTypeCode) || "D".equals(admsTypeCode)) {
            if (application.isForeignAppl()) {
                ec.setMessage(MessageResolver.getMessage("U315") + "\\n\\n" + MessageResolver.getMessage("U01701"));
            } else {
                ec.setMessage(MessageResolver.getMessage("U315"));
            }
        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(MessageResolver.getMessage("U316"));
            String errCode = null;
            if (isInsert) {
                if (r1 == 0) errCode = "ERR0001";
//                else if (r2 == 0 && !"C".equals(admsTypeCode) && !"D".equals(admsTypeCode)) errCode = "ERR0006";
                else if (r2 == 0 && !(application.isForeignAppl())) errCode = "ERR0006";
                else if (r3 == 0 && (application.isForeignAppl())) errCode = "ERR0026";
            } else {
                if (r1 == 0) errCode = "ERR0003";
//                else if (r2 == 0 && !"C".equals(admsTypeCode) && !"D".equals(admsTypeCode)) errCode = "ERR0008";
                else if (r2 == 0 && !(application.isForeignAppl())) errCode = "ERR0008";
                else if (r3 == 0 && (application.isForeignAppl())) errCode = "ERR0028";
            }
            exceptionThrower(ec, application, modId, applNo, errCode);
//            if (isInsert && !isValidInsertRequest) {
//                원래 페이지로 돌아가는 테스트는 성공,
//                단순 데이터는 복원해서 원래 페이지, 원래 상태로 되돌릴 수 있으나,
//                SelectBox 선택사항, 리스트 데이터 등을 원상복구하기는 매우 번거로움

//                YSNoRedirectBizException nrBizException = new YSNoRedirectBizException(ec);
//                Map<String, Object> map = nrBizException.getPreviousDataMap();
//                map.put("basis", basis);
//                throw nrBizException;
//            } else {
//                applNo = application.getApplNo() == null ? 0 : application.getApplNo();
//                ec.setData(new ApplicationIdentifier(applNo));
//                throw new YSBizException(ec);
//            }
        }

        return ec;
    }

    private void exceptionThrower(ExecutionContext ec, Application application, String modId, int applNo, String errCode) {
        ec.setErrCode(errCode);
        Map<String, String> errorInfo = new HashMap<String, String>();
        errorInfo.put("applNo", String.valueOf(applNo));
        errorInfo.put("userId", StringUtil.getEmptyIfNull(application.getUserId()));
        errorInfo.put("modId", modId);
        ec.setErrorInfo(new ErrorInfo(errorInfo));

        throw new YSBizException(ec);
    }

    /**
     * 지원 사항 취소
     *
     * @param basis
     * @return
     */
    @Override
    public ExecutionContext cancelBasis(Basis basis) {
        ExecutionContext ec = new ExecutionContext();
        Application application = basis.getApplication();

//        String userId = application.getUserId();
        String modId = application.getModId();
        String admsTypeCode = application.getAdmsTypeCode();
        boolean isMultipleApplicationAllowed = true;
        boolean isValidInsertRequest = false;
        boolean isInsert;
        int r1 = 0, applNo = 0;
        Date date = new Date();

        if (application.getApplNo() == null) {
            isInsert = true;
            // applNo도 없는 상태이면 insert 할 필요 없이 그냥 아무것도 안함
            r1 = 1;
//            if (isMultipleApplicationAllowed) {
//                isValidInsertRequest = true;
//            } else {
//                if (hasApplication(userId)) {
//                    isValidInsertRequest = false;
//                } else {
//                    isValidInsertRequest = true;
//                }
//            }
//            if (isValidInsertRequest) {
//                application.setApplStsCode(APP_INFO_SAVED);
//                application.setCreId(userId);
//                application.setCreDate(date);
//                application.setApplStsCode("00022");
//                r1 = commonDAO.insertItem(application, NAME_SPACE, "CustomApplicationMapper");
//            }
        } else {
            isInsert = false;
//            application.setModId(userId);
            application.setModId(modId);
            application.setModDate(date);
            application.setApplStsCode("00022");
            r1 = commonDAO.updateItem(application, NAME_SPACE, "ApplicationMapper");

        }

        if ( r1 == 1 ) {
            ec.setResult(ExecutionContext.SUCCESS);
            ec.setMessage(MessageResolver.getMessage("U315"));
        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(MessageResolver.getMessage("U316"));
            String errCode = null;
            if (isInsert) {
                if (r1 == 0) errCode = "ERR0001";
            } else {
                if (r1 == 0) errCode = "ERR0003";
            }
            exceptionThrower(ec, application, modId, applNo, errCode);
//            if (isInsert && !isValidInsertRequest) {
//                원래 페이지로 돌아가는 테스트는 성공,
//                단순 데이터는 복원해서 원래 페이지, 원래 상태로 되돌릴 수 있으나,
//                SelectBox 선택사항, 리스트 데이터 등을 원상복구하기는 매우 번거로움

//                YSNoRedirectBizException nrBizException = new YSNoRedirectBizException(ec);
//                Map<String, Object> map = nrBizException.getPreviousDataMap();
//                map.put("basis", basis);
//                throw nrBizException;
//            } else {
//                applNo = application.getApplNo() == null ? 0 : application.getApplNo();
//                ec.setData(new ApplicationIdentifier(applNo));
//                throw new YSBizException(ec);
//            }
        }

        return ec;
    }

    /**
     * 해당 userId를 가진 기존 지원서가 있는 지 확인
     *
     * @param userId
     * @return
     */
    private boolean hasApplication(String userId) {
        List<Application> applicationList = commonDAO.queryForList(NAME_SPACE + "CustomApplicationMapper.selectApplByUserId",
                userId, Application.class);

        return applicationList.size() > 0;
    }

    @Override
    public <T> ExecutionContext retrieveInfoListByParamObj(Object parameter, String mapperNameSqlId, Class<T> clazz) {
        List<T> infoList = null;
        ExecutionContext ec = new ExecutionContext();
        infoList = commonDAO.queryForList(NAME_SPACE + mapperNameSqlId,
                    parameter, clazz);

        ec.setResult(ExecutionContext.SUCCESS);
        ec.setData(infoList);
        return ec;
    }

    private List<CommonCode> getApplAttrList(Basis basis, List<CommonCode> applAttrList) {

        Application application = basis.getApplication();
        if( application.isForeignAppl() || application.isEarlyAppl() ) {

            List<CommonCode> newApplAttrList = new ArrayList<CommonCode>();
            for (CommonCode commonCode : applAttrList) {
                newApplAttrList.add(new CommonCode(commonCode));
            }
            for(int i = newApplAttrList.size()-1; i >= 0 ; i--){
                if( !"00001".equals(newApplAttrList.get(i).getCode())){
                    newApplAttrList.remove(i);
                } else {
                    if (application.isForeignAppl()) {
                        CommonCode applAttr = newApplAttrList.get(i);
                        applAttr.setCodeVal("외국인 전형 지원자");
                        applAttr.setCodeValXxen("Foreigner Applicants");
                    } else if (application.isEarlyAppl()) {
                        CommonCode applAttr = newApplAttrList.get(i);
                        applAttr.setCodeVal("조기 전형 지원자");
                        applAttr.setCodeValXxen("Early Applicants");
                    }
                }
            }
            return newApplAttrList;
        }
        return applAttrList;
    }


    @Override
    public String findApplIdByEmail(String email) {
        String applId = null;
        applId = commonDAO.queryForObject(NAME_SPACE + "ApplicationMapper.findApplIdByEmail", email, String.class);
        return applId;
    }
}

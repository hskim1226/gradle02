package com.apexsoft.ysprj.applicants.application.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.exception.YSBizException;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.ysprj.applicants.application.domain.*;
import com.apexsoft.ysprj.applicants.common.domain.*;
import com.apexsoft.ysprj.applicants.common.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    @Resource(name = "messageResolver")
    MessageResolver messageResolver;

    private final String APP_INFO_SAVED = "00001";       // 기본정보 저장

    @Override
    public ExecutionContext retrieveSelectionMap(Basis basis) {
        ExecutionContext ec = new ExecutionContext();
        Map<String, Object> ecDataMap = new HashMap<String, Object>();
        Map<String, Object> selectionMap = new HashMap<String, Object>();
        Map<String, Object> foreignMap = new HashMap<String, Object>();

        // 지원사항 select 초기값 설정
        List<Campus> campList = null;
        List<AcademyResearchIndustryInstitution> ariInstList = null;
        List<College> collList = null;
        List<CodeNameDepartment> deptList = null;
        List<CodeNameCourse> corsTypeList = null;
        List<CodeNameDetailMajor> detlMajList = null;

        ParamForSetupCourses param = new ParamForSetupCourses();
        param.setAdmsNo(basis.getApplication().getAdmsNo());
        param.setCollCode(basis.getApplication().getCollCode());
        param.setDeptCode(basis.getApplication().getDeptCode());
        param.setCorsTypeCode(basis.getApplication().getCorsTypeCode());
        param.setAriInstCode(basis.getApplication().getAriInstCode());

        String applAttrCode = basis.getApplication().getApplAttrCode();
        if ("00002".equals(applAttrCode)) {
            ariInstList = commonService.retrieveAriInst();
            deptList = commonService.retrieveAriInstDepartmentByAdmsAriInst(param);
            corsTypeList = commonService.retrieveAriInstCourseByAdmsDeptAriInst(param);
            detlMajList = commonService.retrieveAriInstDetailMajorByAdmsDeptAriInst(param);
        } else {
            campList = commonService.retrieveCampus();
            collList = commonService.retrieveCollegeByCampus( basis.getApplication().getCampCode() );
            deptList = commonService.retrieveGeneralDepartmentByAdmsColl(param);
            detlMajList = commonService.retrieveGeneralDetailMajorByAdmsDeptCors(param);
            if ("00001".equals(applAttrCode))
                corsTypeList = commonService.retrieveGeneralCourseByAdmsDept(param);
            if ("00003".equals(applAttrCode))
                corsTypeList = commonService.retrieveCommissionCourseByAdmsDept(param);
        }

        if (campList != null)      selectionMap.put("campList", campList);
        if (collList != null)      selectionMap.put("collList", collList);
        if (ariInstList != null)   selectionMap.put("ariInstList", ariInstList);
        if (deptList != null)      selectionMap.put("deptList", deptList);
        if (corsTypeList != null)  selectionMap.put("corsTypeList", corsTypeList);
        if (detlMajList != null)   selectionMap.put("detlMajList", detlMajList);

        selectionMap.put("applAttrList", commonService.retrieveCommonCodeValueByCodeGroup("APPL_ATTR"));
        selectionMap.put("emerContList", commonService.retrieveCommonCodeValueByCodeGroup("EMER_CONT"));

        String cntrCode = basis.getApplication().getCitzCntrCode();
        cntrCode = cntrCode == null ? "" : cntrCode;
        Country ctznCntr = commonService.retrieveCountryByCode(cntrCode);

        cntrCode = basis.getApplicationForeigner().getBornCntrCode();
        cntrCode = cntrCode == null ? "" : cntrCode;
        Country bornCntr = commonService.retrieveCountryByCode(cntrCode);

        foreignMap.put("foreignTypeList", commonService.retrieveCommonCodeValueByCodeGroup("FORN_TYPE"));

        ec.setResult(ExecutionContext.SUCCESS);
        ecDataMap.put("basis", basis);
        ecDataMap.put("selection", selectionMap);
        ecDataMap.put("ctznCntr", ctznCntr);
        ecDataMap.put("bornCntr", bornCntr);
        ecDataMap.put("foreign", foreignMap);
        ec.setData(ecDataMap);

        return ec;
    }

    @Override
    public ExecutionContext retrieveBasis(int applNo) {
        ExecutionContext ec = new ExecutionContext();

        Map<String, Object> ecDataMap = new HashMap<String, Object>();
        Map<String, Object> selectionMap = new HashMap<String, Object>();
        Map<String, Object> foreignMap = new HashMap<String, Object>();

        Basis basis = new Basis();
        Application application;

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

            Map<String, Object> map =  (Map<String, Object>) retrieveSelectionMap(basis).getData();
            selectionMap.putAll((Map<String, Object>) map.get("selection"));

        } else {
            // TODO : application 초기값 세팅
            basis.setApplicationForeigner(new ApplicationForeigner());
            basis.setApplicationGeneral(new ApplicationGeneral());

            List<Campus> campList = commonService.retrieveCampus();
            List<AcademyResearchIndustryInstitution> ariInstList = commonService.retrieveAriInst();
            if (campList != null)      selectionMap.put("campList", campList);
            if (ariInstList != null)   selectionMap.put("ariInstList", ariInstList);
            selectionMap.put("applAttrList", commonService.retrieveCommonCodeValueByCodeGroup("APPL_ATTR"));
            selectionMap.put("emerContList", commonService.retrieveCommonCodeValueByCodeGroup("EMER_CONT"));
        }

        String cntrCode = basis.getApplication().getCitzCntrCode();
        cntrCode = cntrCode == null ? "" : cntrCode;
        Country ctznCntr = commonService.retrieveCountryByCode(cntrCode);

        cntrCode = basis.getApplicationForeigner().getBornCntrCode();
        cntrCode = cntrCode == null ? "" : cntrCode;
        Country bornCntr = commonService.retrieveCountryByCode(cntrCode);

        foreignMap.put("foreignTypeList", commonService.retrieveCommonCodeValueByCodeGroup("FORN_TYPE"));

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

        int applNo = application.getApplNo() == null ? 0 : application.getApplNo();
        String detlMajCode = application.getDetlMajCode();

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
            selectionMap.putAll((Map<String, Object>) map.get("selection"));

        } else {

            basis.setApplicationForeigner(new ApplicationForeigner());
            basis.setApplicationGeneral(new ApplicationGeneral());

            List<Campus> campList = commonService.retrieveCampus();
            List<AcademyResearchIndustryInstitution> ariInstList = commonService.retrieveAriInst();
            if (campList != null)      selectionMap.put("campList", campList);
            if (ariInstList != null)   selectionMap.put("ariInstList", ariInstList);
            selectionMap.put("applAttrList", commonService.retrieveCommonCodeValueByCodeGroup("APPL_ATTR"));
            selectionMap.put("emerContList", commonService.retrieveCommonCodeValueByCodeGroup("EMER_CONT"));
        }

        String cntrCode = basis.getApplication().getCitzCntrCode();
        cntrCode = cntrCode == null ? "" : cntrCode;
        Country ctznCntr = commonService.retrieveCountryByCode(cntrCode);

        cntrCode = basis.getApplicationForeigner().getBornCntrCode();
        cntrCode = cntrCode == null ? "" : cntrCode;
        Country bornCntr = commonService.retrieveCountryByCode(cntrCode);

        foreignMap.put("foreignTypeList", commonService.retrieveCommonCodeValueByCodeGroup("FORN_TYPE"));

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

        String userId = application.getUserId();
        boolean isMultipleApplicationAllowed = true;
        boolean isValidInsertRequest = false;
        boolean isInsert;
        int r1 = 0, r2 = 0, r3 = 0, applNo;
        Date date = new Date();

        if (application.getApplNo() == null) {
            isInsert = true;
            if (isMultipleApplicationAllowed) {
                isValidInsertRequest = true;
            } else {
                if (hasApplication(userId)) {
                    isValidInsertRequest = false;
                } else {
                    isValidInsertRequest = true;
                }
            }
            if (isValidInsertRequest) {
                application.setApplStsCode(APP_INFO_SAVED);
                application.setCreId(userId);
                application.setCreDate(date);
                r1 = commonDAO.insertItem(application, NAME_SPACE, "CustomApplicationMapper");
                applNo = application.getApplNo();

                applicationGeneral.setApplNo(applNo);
                applicationGeneral.setCreId(userId);
                applicationGeneral.setCreDate(date);
                r2 = commonDAO.insertItem(applicationGeneral, NAME_SPACE, "ApplicationGeneralMapper");

                applicationForeigner.setApplNo(applNo);
                applicationForeigner.setCreId(userId);
                applicationForeigner.setCreDate(date);
                r3 = commonDAO.insertItem(applicationForeigner, NAME_SPACE, "ApplicationForeignerMapper");
            }
        } else {
            isInsert = false;

            application.setModId(userId);
            application.setModDate(date);

            applicationGeneral.setApplNo(application.getApplNo());
            applicationGeneral.setModId(userId);
            applicationGeneral.setModDate(date);

            applicationForeigner.setApplNo(application.getApplNo());
            applicationForeigner.setModId(userId);
            applicationForeigner.setModDate(date);

            r1 = commonDAO.updateItem(application, NAME_SPACE, "ApplicationMapper");
            r2 = commonDAO.updateItem(applicationGeneral, NAME_SPACE, "ApplicationGeneralMapper");
            r3 = commonDAO.updateItem(applicationForeigner, NAME_SPACE, "ApplicationForeignerMapper");
        }

        if ( r1 > 0 && r2 > 0 && r3 > 0) {
            ec.setResult(ExecutionContext.SUCCESS);
            ec.setMessage(messageResolver.getMessage("U315"));
        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(messageResolver.getMessage("U316"));
            String errCode = null;
            if (isInsert) {
                if (r1 == 0) errCode = "ERR0001";
                else if (r2 == 0) errCode = "ERR0006";
                else if (r3 == 0) errCode = "ERR0026";
            } else {
                if (r1 == 0) errCode = "ERR0003";
                else if (r2 == 0) errCode = "ERR0008";
                else if (r3 == 0) errCode = "ERR0028";
            }
            ec.setErrCode(errCode);
            throw new YSBizException(ec);
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
}

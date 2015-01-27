package com.apexsoft.ysprj.applicants.application.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.exception.YSBizException;
import com.apexsoft.framework.exception.YSNoRedirectBizException;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.ysprj.applicants.application.domain.*;
import com.apexsoft.ysprj.applicants.common.domain.*;
import com.apexsoft.ysprj.applicants.common.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ExecutionContext retrieveBasis(Basis basis) {

        ExecutionContext ec = new ExecutionContext();

        Map<String, Object> ecDataMap = new HashMap<String, Object>();
        Map<String, Object> selectionMap = new HashMap<String, Object>();

        Application application = basis.getApplication();

        int applNo = application.getApplNo() == null ? 0 : application.getApplNo();

        if (applNo > 0) {
            application = commonDAO.queryForObject(NAME_SPACE + "ApplicationMapper.selectByPrimaryKey",
                    applNo, Application.class);
            application = application == null ? new Application() : application;
            basis.setApplication(application);

            ApplicationGeneral applicationGeneral = commonDAO.queryForObject(NAME_SPACE + "ApplicationGeneralMapper.selectByPrimaryKey",
                    applNo, ApplicationGeneral.class);
            applicationGeneral = applicationGeneral == null ? new ApplicationGeneral() : applicationGeneral;
            basis.setApplicationGeneral(applicationGeneral);

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
            if (applAttrCode.equals("00002")) {
                ariInstList = commonService.retrieveAriInst();
                deptList = commonService.retrieveAriInstDepartmentByAdmsAriInst(param);
                corsTypeList = commonService.retrieveAriInstCourseByAdmsDeptAriInst(param);
                detlMajList = commonService.retrieveAriInstDetailMajorByAdmsDeptAriInst(param);
            } else {
                campList = commonService.retrieveCampus();
                collList = commonService.retrieveCollegeByCampus( basis.getApplication().getCampCode() );
                deptList = commonService.retrieveGeneralDepartmentByAdmsColl(param);
                detlMajList = commonService.retrieveGeneralDetailMajorByAdmsDeptCors(param);
                if (applAttrCode.equals("00001"))
                    corsTypeList = commonService.retrieveGeneralCourseByAdmsDept(param);
                if (applAttrCode.equals("00003"))
                    corsTypeList = commonService.retrieveCommissionCourseByAdmsDept(param);
            }

            if (campList != null)      selectionMap.put("campList", campList);
            if (collList != null)      selectionMap.put("collList", collList);
            if (ariInstList != null)   selectionMap.put("ariInstList", ariInstList);
            if (deptList != null)      selectionMap.put("deptList", deptList);
            if (corsTypeList != null)  selectionMap.put("corsTypeList", corsTypeList);
            if (detlMajList != null)   selectionMap.put("detlMajList", detlMajList);

        } else {
            basis.setApplicationGeneral(new ApplicationGeneral());

            List<Campus> campList = commonService.retrieveCampus();
            List<AcademyResearchIndustryInstitution> ariInstList = commonService.retrieveAriInst();
            if (campList != null)      selectionMap.put("campList", campList);
            if (ariInstList != null)   selectionMap.put("ariInstList", ariInstList);
        }

        selectionMap.put("applAttrList", commonService.retrieveCommonCodeValueByCodeGroup("APPL_ATTR"));
        selectionMap.put("emerContList", commonService.retrieveCommonCodeValueByCodeGroup("EMER_CONT"));

        String cntrCode = basis.getApplication().getCitzCntrCode();
        cntrCode = cntrCode == null ? "" : cntrCode;
        Country country = commonService.retrieveCountryByCode(cntrCode);

        ec.setResult(ExecutionContext.SUCCESS);
        ecDataMap.put("basis", basis);
        ecDataMap.put("selection", selectionMap);
        ecDataMap.put("country", country);
        ec.setData(ecDataMap);

        return ec;
    }

    @Override
    public ExecutionContext saveBasis(Basis basis) {
        ExecutionContext ec = new ExecutionContext();
        Application application = basis.getApplication();
        ApplicationGeneral applicationGeneral = basis.getApplicationGeneral();
        String userId = application.getUserId();
        boolean isMultipleApplicationAllowed = true;
        boolean isValidInsertRequest = false;
        boolean isInsert;
        int r1 = 0, r2 = 0, applNo;
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
            }
        } else {
            isInsert = false;
            application.setModId(userId);
            application.setModDate(date);
            applicationGeneral.setApplNo(application.getApplNo());
            applicationGeneral.setModId(userId);
            applicationGeneral.setModDate(date);
            r1 = commonDAO.updateItem(application, NAME_SPACE, "ApplicationMapper");
            r2 = commonDAO.updateItem(applicationGeneral, NAME_SPACE, "ApplicationGeneralMapper");
        }

        if ( r1 > 0 && r2 > 0 ) {
            ec.setResult(ExecutionContext.SUCCESS);
            ec.setMessage(messageResolver.getMessage("U315"));
        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(messageResolver.getMessage("U316"));
            String errCode = null;
            if (isInsert) {
                if (r1 == 0) errCode = "ERR0001";
                else if (r2 == 0) errCode = "ERR0006";
            } else {
                if (r1 == 0) errCode = "ERR0003";
                else if (r2 == 0) errCode = "ERR0008";
            }
            ec.setErrCode(errCode);
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
                throw new YSBizException(ec);
//            }
        }

        return ec;
    }

    private boolean hasApplication(String userId) {
        List<Application> applicationList = commonDAO.queryForList(NAME_SPACE + "CustomApplicationMapper.selectApplByUserId",
                userId, Application.class);

        return applicationList.size() > 0;
    }
}

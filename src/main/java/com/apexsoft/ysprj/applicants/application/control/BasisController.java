package com.apexsoft.ysprj.applicants.application.control;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.ysprj.applicants.application.domain.*;
import com.apexsoft.ysprj.applicants.application.service.BasisService;
import com.apexsoft.ysprj.applicants.common.domain.*;
import com.apexsoft.ysprj.applicants.common.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hanmomhanda on 15. 1. 9.
 *
 * 원서 기본 정보 컨트롤러
 */
@Controller
@RequestMapping(value="/application/basis")
public class BasisController {

    @Autowired
    private BasisService basisService;

    @Autowired
    private CommonService commonService;

    @Resource(name = "messageResolver")
    MessageResolver messageResolver;

    private final String TARGET_VIEW = "application/basis";



    /**
     * 기본 정보를 조회해서 기본 정보 화면에 뿌려질 데이터 구성
     *
     * @param applicationIdentifier
     * @return
     */
    private ExecutionContext setupBasis(ApplicationIdentifier applicationIdentifier) {
        ExecutionContext ec;

        Map<String, Object> ecDataMap = new HashMap<String, Object>();
        Map<String, Object> selectionMap = new HashMap<String, Object>();
        Basis basis;

        int applNo = applicationIdentifier.getApplNo();
        String admsNo = applicationIdentifier.getAdmsNo();
        String entrYear = applicationIdentifier.getEntrYear();
        String admsTypeCode = applicationIdentifier.getAdmsTypeCode();

        if (applNo > 0) {

            ec = basisService.retrieveBasis(applNo);
            basis = (Basis)ec.getData();

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

            ec = new ExecutionContext();
            basis = new Basis();
            Application application = new Application();
            application.setAdmsNo(admsNo);
            application.setEntrYear(entrYear);
            application.setAdmsTypeCode(admsTypeCode);
            ApplicationGeneral applicationGeneral = new ApplicationGeneral();
            basis.setApplication(application);
            basis.setApplicationGeneral(applicationGeneral);

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

        ecDataMap.put("basis", basis);
        ecDataMap.put("selection", selectionMap);
        ecDataMap.put("country", country);
        ec.setData(ecDataMap);

        return ec;
    }

    /**
     * 기본정보 최초작성/수정 화면
     *
     * @return
     */
    @RequestMapping(value="/edit")
    public ModelAndView getBasis(@ModelAttribute Basis model) {
        ModelAndView mv = new ModelAndView(TARGET_VIEW);

        Application application = model.getApplication();
        int applNo = application.getApplNo();
        String admsNo = application.getAdmsNo();
        String entrYear = application.getEntrYear();
        String admsTypeCode = application.getAdmsTypeCode();

        ExecutionContext ec = setupBasis(new ApplicationIdentifier(applNo, admsNo, entrYear, admsTypeCode));

//        ExecutionContext ec = setupBasis(new ApplicationIdentifier(applNo == null ? 0 : applNo, admsNo, entrYear, admsTypeCode));

        Map<String, Object> map = (Map<String, Object>)ec.getData();
        addObjectToMV(mv, map, ec);

        return mv;
    }

    /**
     * 기본 정보 저장
     *
     * @param basis
     * @param principal
     * @return
     */
    @RequestMapping(value="/save", method = RequestMethod.POST)
    public ModelAndView saveBasis(@ModelAttribute Basis basis,
                                  Principal principal) {
        ModelAndView mv = new ModelAndView(TARGET_VIEW);
        ExecutionContext ec = null;
        String userId = principal.getName();
        Application application = basis.getApplication();
        application.setUserId(userId);
        ApplicationGeneral applicationGeneral = basis.getApplicationGeneral();
        applicationGeneral.setApplNo(application.getApplNo());

        if (application.getApplNo() == null) { //insert
            application.setCreId(userId);
            applicationGeneral.setCreId(userId);
            ec = basisService.createBasis(application,
                                          applicationGeneral);
        } else { //update
            application.setModId(userId);
            applicationGeneral.setModId(userId);
            ec = basisService.updateBasis(application,
                                          applicationGeneral);
        }

        if (ec.getResult().equals(ExecutionContext.SUCCESS)) {
            ApplicationIdentifier data = (ApplicationIdentifier)ec.getData();
            ExecutionContext ecSetupBasis = setupBasis(data);

            if (ecSetupBasis.getResult().equals(ExecutionContext.SUCCESS)) {
                Map<String, Object> map = (Map<String, Object>)ecSetupBasis.getData();
                addObjectToMV(mv, map, ec);
            } else {
                mv = getErrorMV("common/error", ecSetupBasis);
            }
        } else {
            mv = getErrorMV("common/error", ec);
        }

        return mv;
    }

    /**
     * 에러 발생 시 ExecutionContext를 model에 넣고 에러 페이지로 전달
     *
     * @param errorViewName
     * @param ec
     * @return
     */
    private ModelAndView getErrorMV(String errorViewName, ExecutionContext ec) {
        ModelAndView mv = new ModelAndView(errorViewName);
        mv.addObject("ec", ec);
        return mv;
    }

    /**
     * ModelAndView에 데이터 추가
     *
     * @param mv
     * @param map
     * @param ec
     */
    private void addObjectToMV(ModelAndView mv, Map<String, Object> map, ExecutionContext ec) {
        mv.addObject("basis", map.get("basis"));
        mv.addObject("selection", map.get("selection"));
        mv.addObject("country", map.get("country"));
        mv.addObject("resultMsg", ec.getMessage());
    }
}

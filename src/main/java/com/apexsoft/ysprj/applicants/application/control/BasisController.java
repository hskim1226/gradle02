package com.apexsoft.ysprj.applicants.application.control;

import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.ysprj.applicants.application.domain.Application;
import com.apexsoft.ysprj.applicants.application.domain.CustomBasis;
import com.apexsoft.ysprj.applicants.application.service.BasisService;
import com.apexsoft.ysprj.applicants.common.domain.*;
import com.apexsoft.ysprj.applicants.common.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
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

    private final String APP_INFO_SAVED = "00001";

    @RequestMapping(value="/edit")
    public ModelAndView getBasis(@RequestParam(value = "applNo", required = false) Integer applNo,
                                 @RequestParam(value = "admsNo", required = false) String admsNo,
                                 @RequestParam(value = "entrYear", required = false) String entrYear,
                                 @RequestParam(value = "admsTypeCode", required = false) String admsTypeCode) {
        ModelAndView mv = new ModelAndView("application/basis/edit");

        CustomBasis basis = null;
        Map<String, Object> selectionMap = new HashMap<String, Object>();

        if (applNo != null) {

            basis = basisService.retrieveBasis(applNo);

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

            basis = new CustomBasis();
            Application application = new Application();
            application.setAdmsNo(admsNo);
            application.setEntrYear(entrYear);
            application.setAdmsTypeCode(admsTypeCode);
            basis.setApplication(application);

            List<Campus> campList = commonService.retrieveCampus();
            List<AcademyResearchIndustryInstitution> ariInstList = commonService.retrieveAriInst();
            if (campList != null)      selectionMap.put("campList", campList);
            if (ariInstList != null)   selectionMap.put("ariInstList", ariInstList);
        }

        selectionMap.put("emerContList", commonService.retrieveCommonCodeValueByCodeGroup("EMER_CONT"));

        mv.addObject("basis", basis);
        mv.addObject("selection", selectionMap);

        mv.addObject("msgRgstNo", messageResolver.getMessage("U304"));
        mv.addObject("msgPhoneNo", messageResolver.getMessage("U305"));
        mv.addObject("msgImageOnly", messageResolver.getMessage("U308"));
        mv.addObject("msgPDFOnly", messageResolver.getMessage("U309"));
        mv.addObject("msgGrad", messageResolver.getMessage("U324"));

        return mv;
    }
}

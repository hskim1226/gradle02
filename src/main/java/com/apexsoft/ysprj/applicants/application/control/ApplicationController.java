package com.apexsoft.ysprj.applicants.application.control;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.ysprj.applicants.application.domain.*;
import com.apexsoft.ysprj.applicants.application.service.ApplicationService;
import com.apexsoft.ysprj.applicants.common.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by hanmomhanda on 14. 8. 6.
 */
@Controller
@SessionAttributes("entireApplication")
@RequestMapping(value="/application")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private CommonService commonService;

    @RequestMapping(value="/mylist")
    public String myApplicationList() {
        return "application/mylist";
    }

    @RequestMapping(value="/apply-work")
    public String displayApplyWork(Model model) {
        model.addAttribute("application", applicationService.retrieveApplication(1));
        return "application/appinfo-work";
    }

    @RequestMapping(value = "/apply"/*, /method = RequestMethod.POST(*/)
    public String displayAppInfo(@RequestParam(value = "applNo", required = false) Integer applNo,
                                 @RequestParam(value = "admsNo", required = false) String admsNo,
                                 @RequestParam(value = "entrYear", required = false) String entrYear,
                                 @RequestParam(value = "admsTypeCode", required = false) String admsTypeCode,
                                 @ModelAttribute("entireApplication") EntireApplication entireApplication,
                                 Model model) {

        if (applNo != null) {
            model.addAttribute("entireApplication", applicationService.retrieveEntireApplication(applNo));
        } else {
            entireApplication.setAdmsNo(admsNo);
            entireApplication.setEntrYear(entrYear);
            entireApplication.setAdmsTypeCode(admsTypeCode);
            model.addAttribute("entireApplication", entireApplication);
        }

        /* 지원구분 공통코드로 수정 필요 */
        Map<String, String> applAttrMap = new LinkedHashMap<String, String>();
        applAttrMap.put("01", "일반 지원자");
        applAttrMap.put("02", "학·연·산 지원자");
        applAttrMap.put("03", "위탁 지원자");

        Map<String, Object> commonCodeMap = new HashMap<String, Object>();
        commonCodeMap.put( "applAttrList", applAttrMap );
        commonCodeMap.put( "mltrServList", commonService.retrieveCommonCodeValueByCodeGroup("MLTR_SERV") );
        commonCodeMap.put( "mltrTypeList", commonService.retrieveCommonCodeValueByCodeGroup("MLTR_TYPE") );
        commonCodeMap.put( "mltrRankList", commonService.retrieveCommonCodeValueByCodeGroup("MLTR_RANK") );
        commonCodeMap.put( "emerContList", commonService.retrieveCommonCodeValueByCodeGroup("EMER_CONT") );

        model.addAttribute( "common", commonCodeMap );

        return "application/appinfo";
    }

    @RequestMapping(value = "/apply/save", method = RequestMethod.POST)
    @ResponseBody
    public ExecutionContext saveAcademy(@Valid @ModelAttribute EntireApplication entireApplication, BindingResult binding, Principal principal) {
        if( binding.hasErrors() ) {
            return new ExecutionContext(ExecutionContext.FAIL);
        }

        if( principal == null ) {
            return new ExecutionContext(ExecutionContext.FAIL);
        }

        if( entireApplication.getApplNo() == null ) {   // insert
            applicationService.createEntireApplication( entireApplication );
        } else {    // update
        }
        return new ExecutionContext();
    }

    @ModelAttribute("entireApplication")
    public EntireApplication entireApplication() {
        EntireApplication entireApplication = new EntireApplication();
        entireApplication.setApplicationGeneral(new ApplicationGeneral());
        entireApplication.setApplicationETCWithBLOBs(new ApplicationETCWithBLOBs());
        entireApplication.setHighSchool(new ApplicationAcademy());
        entireApplication.setCollegeList(new ArrayList<ApplicationAcademy>());
        entireApplication.setGraduateList(new ArrayList<ApplicationAcademy>());
        entireApplication.setApplicationExperienceList(new ArrayList<ApplicationExperience>());
        entireApplication.setApplicationLanguageList(new ArrayList<ApplicationLanguage>());
        return entireApplication;
    }

}

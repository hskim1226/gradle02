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

import javax.servlet.http.HttpSession;
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
@RequestMapping(value="/application")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private CommonService commonService;

    @RequestMapping(value="/create", method = RequestMethod.POST)
    public String createApplication(@ModelAttribute("application") Application application,
                                    @ModelAttribute("campuses") Map campuses,
                                    @ModelAttribute("schoolTypes") Map schoolTypes,
                                    @ModelAttribute("graduationTypes") Map graduationTypes) {
        return "application/appinfo";
    }

    @RequestMapping(value="/mylist")
    public String myApplicationList() {
        return "application/mylist";
    }

    @RequestMapping(value="/selfintro/save")
    public String saveSelfIntro() {
        // TODO DB에 저장, AJAX로 처리
        return "application/selfintro";
    }

    @RequestMapping(value="/studyplan/save")
    public String saveStudyPlan() {
        // TODO DB에 저장, AJAX로 처리
        return "application/studyplan";
    }

    @RequestMapping(value="/apply-work")
    public String displayApplyWork(Model model) {
        model.addAttribute("application", applicationService.retrieveApplication(1));
        return "application/appinfo-work";
    }

    @RequestMapping(value = "/apply"/*, /method = RequestMethod.POST(*/)
    public String displayAppInfo(@RequestParam("admsNo") String admsNo,
                                 @RequestParam("entrYear") String entrYear,
                                 @RequestParam("admsTypeCode") String admsTypeCode,
                                 @ModelAttribute("entireApplication") EntireApplication entireApplication,
                                 Model model) {

        entireApplication.setAdmsNo(admsNo);
        /*TODO VO 변경 후 주석제거*/
//        entireApplication.setEntrYear(entrYear);
//        entireApplication.setAdmsTypeCode(admsTypeCode);
        /*TODO VO 변경 후 주석제거*/

        Map<String, String> applAttrMap = new LinkedHashMap<String, String>();
        applAttrMap.put("AK01", "일반 지원자");
        applAttrMap.put("AK02", "학·연·산 지원자");
        applAttrMap.put("AK03", "위탁 지원자");
        model.addAttribute( "applAttrList", applAttrMap );
//        model.addAttribute( "applAttrList", commonService.retrieveCommonCodeValueByCodeGroup("APPL_ATTR")  );
//        model.addAttribute( "mltrServList", commonService.retrieveCommonCodeValueByCodeGroup("MLTR_SERV") );
//        model.addAttribute( "mltrTypeList", commonService.retrieveCommonCodeValueByCodeGroup("MLTR_TYPE") );
//        model.addAttribute( "mltrRankList", commonService.retrieveCommonCodeValueByCodeGroup("MLTR_RANK") );
//        model.addAttribute( "emerContList", commonService.retrieveCommonCodeValueByCodeGroup("EMER_CONT") );

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
    public ExecutionContext saveAcademy(@Valid @ModelAttribute EntireApplication entireApplication, BindingResult binding, Principal principal, HttpSession httpSession) {
        if( binding.hasErrors() ) {
            return new ExecutionContext(ExecutionContext.FAIL);
        }
//        SecurityContext sc = (SecurityContext)httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
//        Authentication auth = sc.getAuthentication();
//        UserSessionVO userSessionVO = (UserSessionVO)auth.getPrincipal();
//        String userId = principal != null ? principal.getName() : userSessionVO.getUsername();
//        if( entireApplication.getApplNo() == null ) {   // insert
//            applicationService.createApplication(null);
//            applicationService.createApplicationGeneral(null);
//        } else {    // update
//
//        }
        return new ExecutionContext();
    }

    @ModelAttribute("entireApplication")
    public EntireApplication entireApplication() {
        EntireApplication entireApplication = new EntireApplication();
        entireApplication.setApplicationGeneral(new ApplicationGeneral());
        entireApplication.setApplicationETC(new ApplicationETC());
        entireApplication.setApplicationAcademyList(new ArrayList<ApplicationAcademy>());
        entireApplication.setApplicationExperienceList(new ArrayList<ApplicationExperience>());
        entireApplication.setApplicationLanguageList(new ArrayList<ApplicationLanguage>());
        return entireApplication;
    }

//    @ModelAttribute("application")
//    public Application application() {
//        Application application = new Application();
//        application.setKorName("홍길동");
//        ApplicationAcademy applicationAcademy = new ApplicationAcademy();
//        applicationAcademy.setSchlUnivCode("SCH02");
//        applicationAcademy.setSchlName("서울대");
//        application.getAcademies().add(applicationAcademy);
//        ApplicationExperiences career = new ApplicationExperiences();
//        career.setCorpName("에이펙스소프트");
//        career.setJoinYmd("2012.04.01");
//        career.setRetrYmd("2014.08.15");
//        career.setExprDesc("솔루션사업부 책임");
//        application.getCareers().add(career);
//        return application;
//    }
//
//    @ModelAttribute("schoolTypes")
//    public Map<String, String> schoolTypes() {
//        Map<String, String> schoolTypes = new HashMap<String, String>();
//        schoolTypes.put("SCH01", "고등학교");
//        schoolTypes.put("SCH02", "대학교");
//        schoolTypes.put("SCH03", "대학원");
//        return schoolTypes;
//    }
//
//    @ModelAttribute("graduationTypes")
//    public Map<String, String> graduationTypes() {
//        Map<String, String> result = new HashMap<String, String>();
//        result.put("GT01", "졸업");
//        result.put("GT01", "졸업예정");
//        return result;
//    }
//
//    @ModelAttribute("campuses")
//    public Map<String, String> campuses() {
//        Map<String, String> result = new HashMap<String, String>();
//        result.put("CAM01", "서울");
//        result.put("CAM02", "원주");
//        return result;
//    }
//
////    @ModelAttribute("campuses")
////    public List<Campus> campuses() {
////        return campusService.retriveCampusList();
////    }
//
//    @ModelAttribute("selfIntro")
//    public SelfIntro selfIntro() {
//        SelfIntro selfIntro = new SelfIntro();
//        selfIntro.setTa1("저의 주요 경력사항은...");
//        selfIntro.setTa2("이 학교에 지원하게 된 동기는...");
//        return selfIntro;
//    }

}

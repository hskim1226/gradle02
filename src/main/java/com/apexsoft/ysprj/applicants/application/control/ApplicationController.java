package com.apexsoft.ysprj.applicants.application.control;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.ysprj.applicants.application.domain.*;
import com.apexsoft.ysprj.applicants.application.service.ApplicationService;
import com.apexsoft.ysprj.applicants.common.service.CommonService;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.*;

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
    public String myApplicationList(Principal principal) {
//        ParamForApplication p = new ParamForApplication();
//        p.setUserId(principal.getName());
//        List<CustomMyList> applications = applicationService.retrieveMyList(p);
        return "application/mylist";
    }

    @RequestMapping(value="/apply-work")
    public String displayApplyWork(Model model) {
        model.addAttribute("application", applicationService.retrieveApplication(1));
        return "application/appinfo-work";
    }

    @RequestMapping(value="/apply-file")
    public String displayApplyFile(Model model) {
        return "application/appinfo-fileupload";
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

        entireApplication.setUserId(principal.getName());
        entireApplication.setApplStsCode("00001");
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

    @RequestMapping(value="/in/{applNo}/{examCode}")
    public EntireApplication createEntireApplication(@PathVariable("applNo") int applNo,
                                                     @PathVariable("examCode") String examCode) {

        EntireApplication ea = new EntireApplication();
        ApplicationGeneral applGene = new ApplicationGeneral();
        ApplicationETCWithBLOBs applEtc = new ApplicationETCWithBLOBs();
        ApplicationAcademy highschool = new ApplicationAcademy();
        List<ApplicationAcademy> collegeList = new ArrayList<ApplicationAcademy>();
        List<ApplicationAcademy> graduateList = new ArrayList<ApplicationAcademy>();
        List<ApplicationExperience> experienceListList = new ArrayList<ApplicationExperience>();
        List<ApplicationLanguage> languageListList = new ArrayList<ApplicationLanguage>();

        String id = RandomUtils.nextInt(100000)+"";
        Timestamp timestamp = new Timestamp(new Date().getTime());

        ea.setApplNo(applNo);
        ea.setUserId(id);
        ea.setAdmsNo("15A");
        ea.setApplStsCode("00001");
        ea.setCreDate(timestamp);
        ea.setCreId(id);

        applGene.setApplNo(applNo);
        applGene.setCurrWrkpName("에이펙스");
        applGene.setCreDate(timestamp);
        applGene.setCreId(id);
        ea.setApplicationGeneral(applGene);


        applEtc.setCovLett("자기 소개 입니다.");
        applEtc.setStudPlan("연구계획 입니다.");
        applEtc.setCreDate(timestamp);
        applEtc.setCreId(id);
        ea.setApplicationETCWithBLOBs(applEtc);


        highschool.setAcadTypeCode("00001");
        highschool.setSchlName("깨똥고등학교");
        highschool.setCreDate(timestamp);
        highschool.setCreId(id);
        ea.setHighSchool(highschool);

        ApplicationAcademy aa0 = new ApplicationAcademy();
        aa0.setAcadSeq(1);
        aa0.setAcadTypeCode("00002");
        aa0.setSchlName("연세대학교");
        aa0.setCreDate(timestamp);
        aa0.setCreId(id);
        collegeList.add(aa0);

        ApplicationAcademy aa1 = new ApplicationAcademy();
        aa1.setAcadSeq(2);
        aa1.setAcadTypeCode("00002");
        aa1.setSchlName("면세대학교");
        aa1.setCreDate(timestamp);
        aa1.setCreId(id);
        collegeList.add(aa1);

        ApplicationAcademy aa2 = new ApplicationAcademy();
        aa2.setAcadSeq(3);
        aa2.setAcadTypeCode("00002");
        aa2.setSchlName("면제대학교");
        aa2.setCreDate(timestamp);
        aa2.setCreId(id);
        collegeList.add(aa2);
        ea.setCollegeList(collegeList);

        ApplicationExperience ae0 = new ApplicationExperience();
        aa0.setAcadSeq(1);
        ae0.setCorpName("보국전자");
        ae0.setCreDate(timestamp);
        ae0.setCreId(id);
        experienceListList.add(ae0);

        ApplicationExperience ae1 = new ApplicationExperience();
        aa1.setAcadSeq(2);
        ae1.setCorpName("가우스전자");
        ae1.setCreDate(timestamp);
        ae1.setCreId(id);
        experienceListList.add(ae1);
        ea.setApplicationExperienceList(experienceListList);

        ApplicationLanguage al0 = new ApplicationLanguage();
        al0.setLangSeq(1);
        al0.setLangExamCode("ToefL");
        al0.setCreDate(timestamp);
        al0.setCreId(id);
        languageListList.add(al0);

        ApplicationLanguage al1 = new ApplicationLanguage();
        al1.setLangSeq(2);
        al1.setLangExamCode(examCode);
        al1.setCreDate(timestamp);
        al1.setCreId(id);
        languageListList.add(al1);
        ea.setApplicationLanguageList(languageListList);

        applicationService.createEntireApplication(ea);

        return ea;
    }
}

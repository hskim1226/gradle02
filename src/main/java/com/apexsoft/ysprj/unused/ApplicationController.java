package com.apexsoft.ysprj.unused;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.persistence.file.handler.FileHandler;
import com.apexsoft.ysprj.applicants.application.domain.*;
import com.apexsoft.ysprj.applicants.common.domain.*;
import com.apexsoft.ysprj.applicants.common.domain.Campus;
import com.apexsoft.ysprj.applicants.common.service.CommonService;
import com.apexsoft.ysprj.applicants.payment.domain.ApplicationPayment;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.*;

/**
 * Created by hanmomhanda on 14. 8. 6.
 */
//@Controller
@SessionAttributes("entireApplication")
@RequestMapping(value="/applicationXXX")
@Deprecated
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private CommonService commonService;

    @Autowired
    private ObjectMapper objectMapper;

    private final String APP_INFO_SAVED = "00001";
    private final String ACAD_SAVED = "00002";
    private final String LANG_CAREER_SAVED = "00003";
    private final String FILEUPLOADE_SAVED = "00004";

    /**
     * 원서 작성 동의 화면
     * SimpleForwardingController에서 이전
     * @return
     */
    @RequestMapping(value = "/agreement", method = RequestMethod.POST)
    public ModelAndView checkAgreement(@RequestParam(value = "admsNo") String admsNo,
                                 @RequestParam(value = "entrYear") String entrYear,
                                 @RequestParam(value = "admsTypeCode") String admsTypeCode) {
        ModelAndView model = new ModelAndView("application/agreement");
        model.addObject("admsNo", admsNo);
        model.addObject("entrYear", entrYear);
        model.addObject("admsTypeCode", admsTypeCode);
        return model;
    }

    /**
     * 내원서 화면
     * @param principal
     * @param model
     * @return
     */
    @RequestMapping(value="/mylist")
    public String myApplicationList(Principal principal, Model model) {

        ParamForApplication p = new ParamForApplication();
        p.setUserId(principal.getName());

        List<CustomMyList> myList = applicationService.retrieveInfoListByParamObj(p, "CustomApplicationMapper.selectApplByUserId", CustomMyList.class);

        model.addAttribute("myList", myList);
        return "application/mylist";
    }

    /**
     * applNo로 EntireApplication 조회
     *
     * @param applNo
     * @return
     * @throws JsonProcessingException
     */
    @RequestMapping(value="/showEA/{applNo}", produces="text/plain;charset=UTF-8")
    @ResponseBody
    public String showEA(@PathVariable("applNo") int applNo)
            throws JsonProcessingException {

        EntireApplication ea = applicationService.retrieveEntireApplication(applNo);
        String r = null;
        r = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(ea);
        return r;
    }

//    @RequestMapping(value = "/preview")
    public String previewAppInfo(@RequestParam(value = "applNo", required = false) Integer applNo,
                                 @RequestParam(value = "admsNo", required = false) String admsNo,
                                 @RequestParam(value = "entrYear", required = false) String entrYear,
                                 @RequestParam(value = "admsTypeCode", required = false) String admsTypeCode,
                                 Model model) {

        if( applNo != null ) {
            EntireApplication entireApplication = applicationService.retrieveEntireApplication( applNo );
            model.addAttribute(entireApplication);
        }

        String result = "application";
        if( "A".equals(admsTypeCode) || "B".equals(admsTypeCode) ) {
            result += "/preview";
        } else if( "C".equals(admsTypeCode) ) {
            result += "/preview";
        }
        return result;
    }

    /**
     * 전체 정보를 모델에 담는 메서드
     *
     * @param applNo
     * @param admsNo
     * @param entrYear
     * @param admsTypeCode
     * @param targetView
     * @return
     */
    private ModelAndView getEntireInfo(Integer applNo, String admsNo,
                                String entrYear, String admsTypeCode, String targetView,
                                @ModelAttribute("entireApplication") EntireApplication entireApplication) {
        ModelAndView model = new ModelAndView(targetView);

//        Map<String, Object> commonCodeMap = new HashMap<String, Object>();
//
//        if( applNo != null ) {
////            if( applNo != entireApplication.getApplication().getApplNo() ) {
//            entireApplication = applicationService.retrieveEntireApplication(applNo);
////            }
//            CampusCollege campusCollege = applicationService.retrieveInfoByApplNo(applNo,
//                    "EntireApplicationMapper.selectCampusCollegeCode",
//                    CampusCollege.class);
//
//            // 지원사항 select 초기값 설정
//            List<com.apexsoft.ysprj.applicants.common.domain.Campus> campList = null;
//            List<AcademyResearchIndustryInstitution> ariInstList = null;
//            List<College> collList = null;
//            List<CodeNameDepartment> deptList = null;
//            List<CodeNameCourse> corsTypeList = null;
//            List<CodeNameDetailMajor> detlMajList = null;
//
//            ParamForSetupCourses param = new ParamForSetupCourses();
//            param.setAdmsNo( entireApplication.getApplication().getAdmsNo() );
//            param.setCollCode( entireApplication.getApplication().getCollCode() );
//            param.setDeptCode( entireApplication.getApplication().getDeptCode() );
//            param.setCorsTypeCode( entireApplication.getApplication().getCorsTypeCode() );
//            param.setAriInstCode( entireApplication.getApplication().getAriInstCode() );
//
//            String applAttrCode = entireApplication.getApplication().getApplAttrCode();
//            if( "00001".equals( applAttrCode ) ) {
//                campList = commonService.retrieveCampus();
//                collList = commonService.retrieveCollegeByCampus( entireApplication.getApplication().getCampCode() );
//                deptList = commonService.retrieveGeneralDepartmentByAdmsColl(param);
//                corsTypeList = commonService.retrieveGeneralCourseByAdmsDept(param);
//                detlMajList = commonService.retrieveGeneralDetailMajorByAdmsDeptCors(param);
//            } else if( "00002".equals( applAttrCode ) ) {
//                ariInstList = commonService.retrieveAriInst();
//                deptList = commonService.retrieveAriInstDepartmentByAdmsAriInst(param);
//                corsTypeList = commonService.retrieveAriInstCourseByAdmsDeptAriInst(param);
//                detlMajList = commonService.retrieveAriInstDetailMajorByAdmsDeptAriInst(param);
//            } else if( "00003".equals( applAttrCode ) ) {
//                campList = commonService.retrieveCampus();
//                collList = commonService.retrieveCollegeByCampus( entireApplication.getApplication().getCampCode() );
//                deptList = commonService.retrieveGeneralDepartmentByAdmsColl(param);
//                corsTypeList = commonService.retrieveCommissionCourseByAdmsDept(param);
//                detlMajList = commonService.retrieveGeneralDetailMajorByAdmsDeptCors(param);
//            }
//
//            if( campList != null )      commonCodeMap.put( "campList", campList );
//            if( collList != null )      commonCodeMap.put( "collList", collList );
//            if( ariInstList != null)    commonCodeMap.put( "ariInstList", ariInstList );
//            if( deptList != null )      commonCodeMap.put( "deptList", deptList );
//            if( corsTypeList != null )  commonCodeMap.put( "corsTypeList", corsTypeList );
//            if( detlMajList != null )   commonCodeMap.put( "detlMajList", detlMajList );
//
//            entireApplication.setDocGroupList( applicationService.retrieveManApplDocListByApplNo(applNo ) );
//        } else {
//            entireApplication = entireApplication();
//            entireApplication.getApplication().setAdmsNo(admsNo);
//            entireApplication.getApplication().setEntrYear(entrYear);
//            entireApplication.getApplication().setAdmsTypeCode(admsTypeCode);
//            entireApplication.getApplication().setApplAttrCode("00001");
////            entireApplication.getHighSchool().setAcadTypeCode("00001");
//
//            List<Campus> campList = commonService.retrieveCampus();
//            List<AcademyResearchIndustryInstitution> ariInstList = commonService.retrieveAriInst();
//            if( campList != null )      commonCodeMap.put( "campList", campList );
//            if( ariInstList != null)    commonCodeMap.put( "ariInstList", ariInstList );
//        }
//
//        model.addObject("entireApplication", entireApplication);
////        model.addAttribute("fromCareerLang", "fromCareerLang"); jsp에서 사용 안 함. 필요없는 것으로 추측
//
//        List<LanguageExam> langExamList = new ArrayList<LanguageExam>();
//        String result = "application/";
//        if( "A".equals(admsTypeCode) || "B".equals(admsTypeCode) ) {
//            result += "appinfo";
//
//            commonCodeMap.put( "applAttrList", commonService.retrieveCommonCodeByCodeGroup("APPL_ATTR") );
//            commonCodeMap.put( "mltrServList", commonService.retrieveCommonCodeByCodeGroup("MLTR_SERV") );
//            commonCodeMap.put( "mltrTypeList", commonService.retrieveCommonCodeByCodeGroup("MLTR_TYPE") );
//            commonCodeMap.put( "mltrRankList", commonService.retrieveCommonCodeByCodeGroup("MLTR_RANK") );
//            langExamList.addAll( commonService.retrieveLangExamByLangCode("ENG") );
//        } else if( "C".equals(admsTypeCode) ) {
//            result += "appinfo-fore";
//
//            commonCodeMap.put( "fornTypeList", commonService.retrieveCommonCodeByCodeGroup("FORN_TYPE") );
//            langExamList.addAll( commonService.retrieveLangExamByLangCode("KOR"));
//            for (LanguageExam languageExam : commonService.retrieveLangExamByLangCode("ENG")) {
//                if (!"GRE".equals(languageExam.getExamName())) {
//                    langExamList.add(languageExam);
//                }
//            }
//        }
//
//        commonCodeMap.put( "country", commonService.retrieveCountryByCode(entireApplication.getApplication().getCitzCntrCode()));
//        commonCodeMap.put( "emerContList", commonService.retrieveCommonCodeByCodeGroup("EMER_CONT") );
//        commonCodeMap.put( "toflTypeList", commonService.retrieveCommonCodeByCodeGroup("TOFL_TYPE") );
//        commonCodeMap.put( "fornExmpList", commonService.retrieveCommonCodeByCodeGroup("FORN_EXMP") );
//        commonCodeMap.put( "qualAreaList", commonService.retrieveCommonCodeByCodeGroup("QUAL_AREA") );
//        commonCodeMap.put( "langExamList", langExamList);
//
//
//        model.addObject( "common", commonCodeMap );
//
//        model.addObject( "msgRgstNo", MessageResolver.getMessage("U304"));
//        model.addObject( "msgPhoneNo", MessageResolver.getMessage("U305"));
//        model.addObject( "msgImageOnly", MessageResolver.getMessage("U308"));
//        model.addObject( "msgPDFOnly", MessageResolver.getMessage("U309"));
//        model.addObject( "msgGrad", MessageResolver.getMessage("U324"));

        return model;
    }

    /**
     * 입학원서 화면 표시(최초면 빈란, 아니면 내용 채워짐)
     *
     * @param applNo
     * @param admsNo
     * @param entrYear
     * @param admsTypeCode
     * @param model
     * @return
     */
    @RequestMapping(value = "/apply"/*, /method = RequestMethod.POST(*/)
    public ModelAndView displayAppInfo(@RequestParam(value = "applNo", required = false) Integer applNo,
                                 @RequestParam(value = "admsNo", required = false) String admsNo,
                                 @RequestParam(value = "entrYear", required = false) String entrYear,
                                 @RequestParam(value = "admsTypeCode", required = false) String admsTypeCode,
                                 @ModelAttribute("entireApplication") EntireApplication entireApplication,
                                 Model model, HttpServletRequest request) {
        return getEntireInfo(applNo, admsNo,
                entrYear, admsTypeCode, "application/appinfo",
                entireApplication);
//        Map<String, Object> commonCodeMap = new HashMap<String, Object>();
//
//        if( applNo != null ) {
////            if( applNo != entireApplication.getApplication().getApplNo() ) {
//                entireApplication = applicationService.retrieveEntireApplication(applNo);
////            }
//            CampusCollege campusCollege = applicationService.retrieveInfoByApplNo(applNo,
//                    "EntireApplicationMapper.selectCampusCollegeCode",
//                    CampusCollege.class);
//
//            // 지원사항 select 초기값 설정
//            List<Campus> campList = null;
//            List<AcademyResearchIndustryInstitution> ariInstList = null;
//            List<College> collList = null;
//            List<CodeNameDepartment> deptList = null;
//            List<CodeNameCourse> corsTypeList = null;
//            List<CodeNameDetailMajor> detlMajList = null;
//
//            ParamForSetupCourses param = new ParamForSetupCourses();
//            param.setAdmsNo( entireApplication.getApplication().getAdmsNo() );
//            param.setCollCode( entireApplication.getApplication().getCollCode() );
//            param.setDeptCode( entireApplication.getApplication().getDeptCode() );
//            param.setCorsTypeCode( entireApplication.getApplication().getCorsTypeCode() );
//            param.setAriInstCode( entireApplication.getApplication().getAriInstCode() );
//
//            String applAttrCode = entireApplication.getApplication().getApplAttrCode();
//            if( "00001".equals( applAttrCode ) ) {
//                campList = commonService.retrieveCampus();
//                collList = commonService.retrieveCollegeByCampus( entireApplication.getApplication().getCampCode() );
//                deptList = commonService.retrieveGeneralDepartmentByAdmsColl(param);
//                corsTypeList = commonService.retrieveGeneralCourseByAdmsDept(param);
//                detlMajList = commonService.retrieveGeneralDetailMajorByAdmsDeptCors(param);
//            } else if( "00002".equals( applAttrCode ) ) {
//                ariInstList = commonService.retrieveAriInst();
//                deptList = commonService.retrieveAriInstDepartmentByAdmsAriInst(param);
//                corsTypeList = commonService.retrieveAriInstCourseByAdmsDeptAriInst(param);
//                detlMajList = commonService.retrieveAriInstDetailMajorByAdmsDeptAriInst(param);
//            } else if( "00003".equals( applAttrCode ) ) {
//                campList = commonService.retrieveCampus();
//                collList = commonService.retrieveCollegeByCampus( entireApplication.getApplication().getCampCode() );
//                deptList = commonService.retrieveGeneralDepartmentByAdmsColl(param);
//                corsTypeList = commonService.retrieveCommissionCourseByAdmsDept(param);
//                detlMajList = commonService.retrieveGeneralDetailMajorByAdmsDeptCors(param);
//            }
//
//            if( campList != null )      commonCodeMap.put( "campList", campList );
//            if( collList != null )      commonCodeMap.put( "collList", collList );
//            if( ariInstList != null)    commonCodeMap.put( "ariInstList", ariInstList );
//            if( deptList != null )      commonCodeMap.put( "deptList", deptList );
//            if( corsTypeList != null )  commonCodeMap.put( "corsTypeList", corsTypeList );
//            if( detlMajList != null )   commonCodeMap.put( "detlMajList", detlMajList );
//
//            entireApplication.setDocGroupList( applicationService.retrieveManApplDocListByApplNo(applNo ) );
//        } else {
//            entireApplication = entireApplication();
//            entireApplication.getApplication().setAdmsNo(admsNo);
//            entireApplication.getApplication().setEntrYear(entrYear);
//            entireApplication.getApplication().setAdmsTypeCode(admsTypeCode);
//            entireApplication.getApplication().setApplAttrCode("00001");
////            entireApplication.getHighSchool().setAcadTypeCode("00001");
//
//            List<Campus> campList = commonService.retrieveCampus();
//            List<AcademyResearchIndustryInstitution> ariInstList = commonService.retrieveAriInst();
//            if( campList != null )      commonCodeMap.put( "campList", campList );
//            if( ariInstList != null)    commonCodeMap.put( "ariInstList", ariInstList );
//        }
//
//        model.addAttribute("entireApplication", entireApplication);
////        model.addAttribute("fromCareerLang", "fromCareerLang"); jsp에서 사용 안 함. 필요없는 것으로 추측
//
//        List<LanguageExam> langExamList = new ArrayList<LanguageExam>();
//        String result = "application/";
//        if( "A".equals(admsTypeCode) || "B".equals(admsTypeCode) ) {
//            result += "appinfo";
//
//            commonCodeMap.put( "applAttrList", commonService.retrieveCommonCodeByCodeGroup("APPL_ATTR") );
//            commonCodeMap.put( "mltrServList", commonService.retrieveCommonCodeByCodeGroup("MLTR_SERV") );
//            commonCodeMap.put( "mltrTypeList", commonService.retrieveCommonCodeByCodeGroup("MLTR_TYPE") );
//            commonCodeMap.put( "mltrRankList", commonService.retrieveCommonCodeByCodeGroup("MLTR_RANK") );
//            langExamList.addAll( commonService.retrieveLangExamByLangCode("ENG") );
//        } else if( "C".equals(admsTypeCode) ) {
//            result += "appinfo-fore";
//
//            commonCodeMap.put( "fornTypeList", commonService.retrieveCommonCodeByCodeGroup("FORN_TYPE") );
//            langExamList.addAll( commonService.retrieveLangExamByLangCode("KOR"));
//            for (LanguageExam languageExam : commonService.retrieveLangExamByLangCode("ENG")) {
//                if (!"GRE".equals(languageExam.getExamName())) {
//                    langExamList.add(languageExam);
//                }
//            }
//        }
//
//        commonCodeMap.put( "country", commonService.retrieveCountryByCode(entireApplication.getApplication().getCitzCntrCode()));
//        commonCodeMap.put( "emerContList", commonService.retrieveCommonCodeByCodeGroup("EMER_CONT") );
//        commonCodeMap.put( "toflTypeList", commonService.retrieveCommonCodeByCodeGroup("TOFL_TYPE") );
//        commonCodeMap.put( "fornExmpList", commonService.retrieveCommonCodeByCodeGroup("FORN_EXMP") );
//        commonCodeMap.put( "qualAreaList", commonService.retrieveCommonCodeByCodeGroup("QUAL_AREA") );
//        commonCodeMap.put( "langExamList", langExamList);
//
//
//        model.addAttribute( "common", commonCodeMap );
//
//        model.addAttribute( "msgRgstNo", MessageResolver.getMessage("U304"));
//        model.addAttribute( "msgPhoneNo", MessageResolver.getMessage("U305"));
//        model.addAttribute( "msgImageOnly", MessageResolver.getMessage("U308"));
//        model.addAttribute( "msgPDFOnly", MessageResolver.getMessage("U309"));
//        model.addAttribute( "msgGrad", MessageResolver.getMessage("U324"));
//
//
////        model.addAttribute( "L311", MessageResolver.getMessage("L311")); jsp에서 사용안함. 필요없는 것으로 추측
//        return result;
    }

    /**
     * 기본 정보 탭 저장
     *
     * @param entireApplication
     * @param bindingResult
     * @param principal
     * @return
     */
    @RequestMapping(value="/save/appInfo", method = RequestMethod.POST)
//    @ResponseBody
    public ModelAndView saveAppInfo(@Valid @ModelAttribute EntireApplication entireApplication,
                                         BindingResult bindingResult,
                                         Principal principal) {

//        if( bindingResult.hasErrors() ) {
//            return new ExecutionContext(ExecutionContext.FAIL);
//        }
//
//        if( principal == null ) {
//            return new ExecutionContext(ExecutionContext.FAIL);
//        }



        ExecutionContext ec = null;
        String userId = principal.getName();
        Application application = entireApplication.getApplication();
        ApplicationGeneral applicationGeneral = entireApplication.getApplicationGeneral();
        application.setUserId(userId);

        if (application.getApplNo() == null) { //insert
            application.setCreId(userId);
            applicationGeneral.setCreId(userId);
            ec = applicationService.createAppInfo(application,
                                                  applicationGeneral,
                                                  entireApplication.getApplicationForeigner());
        } else { //update
            application.setModId(userId);
            applicationGeneral.setModId(userId);
            ec = applicationService.updateAppInfo(application,
                                                  applicationGeneral,
                                                  entireApplication.getApplicationForeigner());
        }

//        return ec;
        ApplicationIdentifier data = (ApplicationIdentifier)ec.getData();

        return getEntireInfo(data.getApplNo(), application.getAdmsNo(),
                application.getEntrYear(), application.getAdmsTypeCode(), "application/appinfo",
                entireApplication);
    }

    /**
     * 학력 탭 저장
     *
     * @param entireApplication
     * @param bindingResult
     * @param principal
     * @return
     */
    @RequestMapping(value="/save/academy", method = RequestMethod.POST)
//    @ResponseBody
    public ModelAndView saveAcademy(@Valid @ModelAttribute EntireApplication entireApplication,
                                         BindingResult bindingResult,
                                         Principal principal,
                                         HttpServletRequest request) {

//        if( bindingResult.hasErrors() ) {
//            return new ExecutionContext(ExecutionContext.FAIL);
//        }
//
//        if( principal == null ) {
//            return new ExecutionContext(ExecutionContext.FAIL);
//        }

        List<Object> acadSeqList = new ArrayList<Object>();

        Map map = request.getParameterMap();
        Set<Map.Entry> set = map.entrySet();

        for( Map.Entry entry : set) {

            String key = entry.getKey().toString();

            if ((key.startsWith("collegeList") || key.startsWith("graduateList")) && key.endsWith("acadSeq")) {
                Object strAcadSeq = request.getParameter(key);
                acadSeqList.add(strAcadSeq);
//System.out.println(key + " : " + strAcadSeq);
            }
        }

//        List<CustomApplicationAcademy> collegeListFromEntire = entireApplication.getCollegeList();
//        for ( int i = 0 ; i < collegeListFromEntire.size() ; i++ ) {
//            CustomApplicationAcademy aa = collegeListFromEntire.get(i);
//            boolean toBeRemoved = true;
//            for (Object acadSeq : acadSeqList) {
//                if ( aa.getAcadSeq() != null && aa.getAcadSeq().toString().equals(acadSeq) ) {
//                    toBeRemoved = false;
//                } else if (aa.getAcadSeq() == null && aa.getSchlCode() != null) {
//                    toBeRemoved = false;
//                }
//            }
//            if (toBeRemoved) collegeListFromEntire.remove(aa);
//        }
//
//        List<CustomApplicationAcademy> graduateListFromEntire = entireApplication.getGraduateList();
//        for ( int i = 0 ; i < graduateListFromEntire.size() ; i++ ) {
//            CustomApplicationAcademy aa = graduateListFromEntire.get(i);
//            boolean toBeRemoved = true;
//            for (Object acadSeq : acadSeqList) {
//                if ( aa.getAcadSeq() != null && aa.getAcadSeq().toString().equals(acadSeq) ) {
//                    toBeRemoved = false;
//                } else if (aa.getAcadSeq() == null && aa.getSchlCode() != null && aa.getSchlCode().length() > 0) {
//                    toBeRemoved = false;
//                }
//            }
//            if (toBeRemoved) graduateListFromEntire.remove(aa);
//        }
//
//        entireApplication.setCollegeList(collegeListFromEntire);
//        entireApplication.setGraduateList(graduateListFromEntire);

        ExecutionContext ec = null;
        String userId = principal.getName();
        Application application = entireApplication.getApplication();
        application.setUserId(userId);
        application.setModId(userId);

        entireApplication.setCollegeList(preProcessAcadList(entireApplication.getCollegeList(), acadSeqList, userId));
        entireApplication.setGraduateList(preProcessAcadList(entireApplication.getGraduateList(), acadSeqList, userId));

        if (entireApplication.getApplication().getApplStsCode().equals(APP_INFO_SAVED)) { //insert
            ec = applicationService.createAcademy(application,
                    entireApplication.getCollegeList(),
                    entireApplication.getGraduateList());
        } else { //update
            ec = applicationService.updateAcademy(application,
                    entireApplication.getCollegeList(),
                    entireApplication.getGraduateList());
        }

        ApplicationIdentifier data = (ApplicationIdentifier)ec.getData();

        return getEntireInfo(data.getApplNo(), application.getAdmsNo(),
                application.getEntrYear(), application.getAdmsTypeCode(), "application/appinfo",
                entireApplication);
    }

    /**
     * acadSeq를 통해 화면의 학력 내용에서 C,U,D 할 목록을 추려내서 서비스 계층에 전달
     *
     * @param academyList
     * @param acadSeqList
     * @return
     */
    private List<CustomApplicationAcademy> preProcessAcadList(List<CustomApplicationAcademy> academyList, List<Object> acadSeqList, String userId) {
        for ( int i = 0 ; i < academyList.size() ; i++ ) {
            CustomApplicationAcademy aa = academyList.get(i);
            boolean toBeRemoved = true;
            for (Object acadSeq : acadSeqList) {
                if ( aa.getAcadSeq() != null && aa.getAcadSeq().toString().equals(acadSeq) ) {
                    toBeRemoved = false;
                    aa.setModId(userId);
                } else if (aa.getAcadSeq() == null && aa.getSchlCode() != null && aa.getSchlCode().length() > 0) {
                    toBeRemoved = false;
                    aa.setCreId(userId);
                }
            }
            if (toBeRemoved) academyList.remove(aa);
        }
        return academyList;
    }

    /**
     * 어학 경력 탭 저장
     *
     * @param entireApplication
     * @param bindingResult
     * @param principal
     * @return
     */
    @RequestMapping(value="/save/langCareer", method = RequestMethod.POST)
//    @ResponseBody
    public ModelAndView saveLangCareer(@Valid @ModelAttribute EntireApplication entireApplication,
                                        BindingResult bindingResult,
                                        Principal principal) {

//        if( bindingResult.hasErrors() ) {
//            return new ExecutionContext(ExecutionContext.FAIL);
//        }
//
//        if( principal == null ) {
//            return new ExecutionContext(ExecutionContext.FAIL);
//        }

        ExecutionContext ec = null;
        String userId = principal.getName();
        Application application = entireApplication.getApplication();

        if (application.getApplStsCode().equals(ACAD_SAVED)) { //insert
            for(ApplicationLanguage al : entireApplication.getApplicationLanguageList()) {
                al.setCreId(userId);
            }
            for(ApplicationExperience ae : entireApplication.getApplicationExperienceList()) {
                ae.setCreId(userId);
            }
            ec = applicationService.createLangCareer(application,
                    entireApplication.getApplicationLanguageList(),
                    entireApplication.getApplicationExperienceList());
        } else { //update
            ec = applicationService.updateLangCareer(application,
                    entireApplication.getApplicationLanguageList(),
                    entireApplication.getApplicationExperienceList());
        }

        ApplicationIdentifier data = (ApplicationIdentifier)ec.getData();

        return getEntireInfo(data.getApplNo(), application.getAdmsNo(),
                application.getEntrYear(), application.getAdmsTypeCode(), "application/appinfo",
                entireApplication);
    }

    /**
     * 첨부파일 탭 저장
     *
     * @param entireApplication
     * @param bindingResult
     * @param principal
     * @return
     */
    @RequestMapping(value="/save/fileUpload", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView saveFileUpload(@Valid @ModelAttribute EntireApplication entireApplication,
                                           BindingResult bindingResult,
                                           Principal principal) {

//        if( bindingResult.hasErrors() ) {
//            return new ExecutionContext(ExecutionContext.FAIL);
//        }
//
//        if( principal == null ) {
//            return new ExecutionContext(ExecutionContext.FAIL);
//        }

        ExecutionContext ec = null;
        Application application = entireApplication.getApplication();

        List<DocGroupFile> docGroupFileList = entireApplication.getDocGroupList();
        if (application.getApplStsCode().equals(LANG_CAREER_SAVED)) { //insert
            ec = applicationService.createFileUpload(application, docGroupFileList);
        } else { //update
            ec = applicationService.updateFileUpload(application, docGroupFileList);
        }

        ApplicationIdentifier data = (ApplicationIdentifier)ec.getData();

        return getEntireInfo(data.getApplNo(), application.getAdmsNo(),
                application.getEntrYear(), application.getAdmsTypeCode(), "application/appinfo",
                entireApplication);
    }

    /**
     * 입학원서 저장
     *
     * @param entireApplication
     * @param binding
     * @param principal
     * @return
     */
// 안 쓰는 걸로 추정됨
//    @RequestMapping(value = "/apply/save", method = RequestMethod.POST)
//    @ResponseBody
//    public ExecutionContext saveApplication(@Valid @ModelAttribute EntireApplication entireApplication,
//                                            BindingResult binding,
//                                            Principal principal) {
//        if( binding.hasErrors() ) {
//            return new ExecutionContext(ExecutionContext.FAIL);
//        }
//
//        if( principal == null ) {
//            return new ExecutionContext(ExecutionContext.FAIL);
//        }
//
//        ExecutionContext ec = null;
//        String userId = principal.getName();
//        entireApplication.getApplication().setUserId(userId);
//        entireApplication.getApplication().setApplStsCode("00001");
//        entireApplication.getApplication().setCreId(userId);
//
//        if( entireApplication.getApplication().getApplNo() == null ) {   // insert
//            entireApplication.getApplication().setCreId(userId);
//            entireApplication.getApplicationGeneral().setCreId(userId);
//            entireApplication.getApplicationETCWithBLOBs().setCreId(userId);
////            entireApplication.getHighSchool().setCreId(userId);
//            List<CustomApplicationAcademy> collegeList = entireApplication.getCollegeList();
//            for(ApplicationAcademy item : collegeList) {
//                item.setCreId(userId);
//            }
//            List<CustomApplicationAcademy> graduateList = entireApplication.getGraduateList();
//            for(ApplicationAcademy item : graduateList) {
//                item.setCreId(userId);
//            }
//            List<ApplicationExperience> experienceList = entireApplication.getApplicationExperienceList();
//            for(ApplicationExperience item : experienceList) {
//                item.setCreId(userId);
//            }
//            List<ApplicationLanguage> languageList = entireApplication.getApplicationLanguageList();
//            for(ApplicationLanguage item : languageList) {
//                item.setCreId(userId);
//            }
//
//
//            ec = applicationService.createEntireApplication( entireApplication );
//        } else {    // update
//            entireApplication.getApplication().setModId(userId);
//            entireApplication.getApplicationGeneral().setModId(userId);
//            entireApplication.getApplicationETCWithBLOBs().setModId(userId);
////            entireApplication.getHighSchool().setModId(userId);
//            List<CustomApplicationAcademy> collegeList = entireApplication.getCollegeList();
//            for(ApplicationAcademy item : collegeList) {
//                item.setModId(userId);
//            }
//            List<CustomApplicationAcademy> graduateList = entireApplication.getGraduateList();
//            for(ApplicationAcademy item : graduateList) {
//                item.setModId(userId);
//            }
//            List<ApplicationExperience> experienceList = entireApplication.getApplicationExperienceList();
//            for(ApplicationExperience item : experienceList) {
//                item.setModId(userId);
//            }
//            List<ApplicationLanguage> languageList = entireApplication.getApplicationLanguageList();
//            for(ApplicationLanguage item : languageList) {
//                item.setModId(userId);
//            }
//
//            ec = applicationService.updateEntireApplication( entireApplication );
//        }
//
//        return ec;
//    }

    /**
     * 입학원서 저장
     *
     * @param entireApplication
     * @param binding
     * @param principal
     * @return
     */
    @RequestMapping(value = "/apply/apply", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView applyApplication(@Valid @ModelAttribute EntireApplication entireApplication,
                                             BindingResult binding,
                                             Principal principal) {

        ApplicationPayment ap = new ApplicationPayment();
        ap.setCreId(principal.getName());
        entireApplication.setApplicationPayment(ap);
        Application application = entireApplication.getApplication();

        ExecutionContext ec = applicationService.confirmEntireApplication(entireApplication);

        ApplicationIdentifier data = (ApplicationIdentifier)ec.getData();

        return getEntireInfo(data.getApplNo(), application.getAdmsNo(),
                application.getEntrYear(), application.getAdmsTypeCode(), "application/mylist",
                entireApplication);
    }

    /**
     * 파일 업로드
     * 개별 파일 단위로 물리적 업로드만 하고,
     * 파일 업로드 테이블은 건드리지 않는다.
     *
     * @param entireApplication
     * @param binding
     * @param principal
     * @param fileHandler
     * @return
     */
    @RequestMapping(value = "/apply/fileUpload", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView fileUpload(@Valid @ModelAttribute final EntireApplication entireApplication,
                                       BindingResult binding,
                                       final Principal principal,
                                       FileHandler fileHandler) {

//        if( binding.hasErrors() ) {
//            return new ExecutionContext(ExecutionContext.FAIL);
//        }
//
//        if( principal == null ) {
//            return new ExecutionContext(ExecutionContext.FAIL);
//        }

        ExecutionContext ec = new ExecutionContext();

        if ( ec.getResult() == ExecutionContext.SUCCESS ) {

//            String returnFileMetaForm = fileHandler.handleMultiPartRequest(new FileUploadEventCallbackHandler<String, FileMetaForm>() {
//                /**
//                 * target 폴더 반환
//                 *
//                 * @param fileMetaForm
//                 *
//                 * @returnattribute
//                 */
//                @Override
//                protected String getDirectory(FileMetaForm fileMetaForm) {
//
//                    String admsNo = fileMetaForm.getAdmsNo();
//                    String userId = principal.getName();
//                    String firstString = userId.substring(0, 1);
//                    String applNo = fileMetaForm.getApplNo();
//
//                    return admsNo + "/" + firstString + "/" + userId + "/" + applNo;
//                }
//
//                /**
//                 * 실제 저장될 파일 이름 반환
//                 *
//                 * @return
//                 */
//                @Override
//                protected String createFileName(FileMetaForm fileMetaForm, FileItem fileItem) {
//                    return fileMetaForm.getFieldName() + "-" + fileItem.getOriginalFileName();
//                }
//
//                /**
//                 * 실제 업로드 처리
//                 *
//                 * @param fileItems
//                 * @param fileMetaForm
//                 * @param persistence
//                 * @return
//                 */
//                @Override
//                public String handleEvent(List<FileItem> fileItems,
//                                          FileMetaForm fileMetaForm,
//                                          FilePersistenceManager persistence) {
//
//                    FileInfo fileInfo;
//                    FileVO fileVO = new FileVO();
//
//                    for ( FileItem fileItem : fileItems){
//                        FileInputStream fis = null;
//                        try{
//                            String uploadDir = getDirectory(fileMetaForm);
//                            String uploadFileName = createFileName(fileMetaForm, fileItem);
//                            fileInfo = persistence.save(uploadDir,
//                                                        uploadFileName,
//                                                        fileItem.getOriginalFileName(),
//                                                        fis = new FileInputStream(fileItem.getFile()));
//                            fileVO.setPath(fileInfo.getDirectory());
//                            fileVO.setFileName(fileInfo.getFileName());
//                            fileMetaForm.setPath(fileInfo.getDirectory());
//                            fileMetaForm.setFileName(fileInfo.getFileName());
//                            fileMetaForm.setOriginalFileName(fileItem.getOriginalFileName());
//                        }catch(FileNotFoundException fnfe){
//                            throw new FileUploadException("", fnfe);
//                        }finally {
//                            try {
//                                if (fis!= null) fis.close();
//                            } catch (IOException e) {}
//                            FileUtils.deleteQuietly(fileItem.getFile());
//                        }
//                    }
//
//                    String jsonFileMetaForm = null;
//                    try {
//                        jsonFileMetaForm = objectMapper.writeValueAsString(fileMetaForm);
//                    } catch (JsonProcessingException e) {
//                        e.printStackTrace();
//                    }
//
//                    return jsonFileMetaForm;
//                }
//            }, FileMetaForm.class);
//
//            ec.setData(returnFileMetaForm);
        }

        Application application = entireApplication.getApplication();

        ApplicationIdentifier data = (ApplicationIdentifier)ec.getData();

        return getEntireInfo(data.getApplNo(), application.getAdmsNo(),
                application.getEntrYear(), application.getAdmsTypeCode(), "application/appinfo",
                entireApplication);
    }

    @ModelAttribute("entireApplication")
    public EntireApplication entireApplication() {
System.out.println("@ModelAttribute entireApplication() invoked");
        EntireApplication entireApplication = new EntireApplication();
        entireApplication.setApplication(new Application());
        entireApplication.setApplicationGeneral(new ApplicationGeneral());
        entireApplication.setApplicationForeigner(new ApplicationForeigner());
        entireApplication.setApplicationETCWithBLOBs(new ApplicationETCWithBLOBs());
//        entireApplication.setHighSchool(new ApplicationAcademy());
        entireApplication.setCollegeList(new ArrayList<CustomApplicationAcademy>());
        entireApplication.setGraduateList(new ArrayList<CustomApplicationAcademy>());
        entireApplication.setApplicationExperienceList(new ArrayList<ApplicationExperience>());
        entireApplication.setApplicationLanguageList(new ArrayList<ApplicationLanguage>());

        return entireApplication;
    }

    /**
     * 테스트 데이터 입력
     *
     * @param userId
     * @param examCode
     * @param applStsCode
     * @return
     * @throws JsonProcessingException
     */
//    @RequestMapping(value="/in/{userId}/{examCode}/{applStsCode}", produces="text/plain;charset=UTF-8")
//    @ResponseBody
//    public String createEntireApplication(@PathVariable("userId") String userId,
//                                          @PathVariable("examCode") String examCode,
//                                          @PathVariable("applStsCode") String applStsCode)
//            throws JsonProcessingException {
//
//        EntireApplication ea = new EntireApplication();
//
//        Timestamp timestamp = new Timestamp(new Date().getTime());
//
//        Application application = new Application();
//        application.setUserId(userId);
//        application.setAdmsNo("15A");
//        application.setApplStsCode("00001");
//        application.setEntrYear("2015");
//        application.setAdmsTypeCode("A");
//        application.setApplAttrCode("");
//        application.setDeptCode("10101");
//        application.setCorsTypeCode("2");
//        application.setDetlMajCode("DM002");
//        application.setPartTimeYn("N");
//        application.setKorName("대조영");
//        application.setChnName("大조영");
//        application.setEngSur("DAE");
//        application.setEngName("JOYOUNG");
//        application.setRgstNo("9009091111118");
//        application.setZipCode("151742");
//        application.setAddr("인천 서구 경서동");
//        application.setAddr("88 활어회집");
//        application.setTelNum("01087451254");
//        application.setMobiNum("01085693214");
//        application.setMailAddr("hanmomhanda@naver.com");
//        application.setApplStsCode(applStsCode);
//        application.setPrivInfoYn("Y");
//        application.setCreId(userId);
//        application.setCreDate(timestamp);
//        ea.setApplication(application);
//
//        ApplicationGeneral applicationGeneral = new ApplicationGeneral();
//        applicationGeneral.setCurrWrkpName("에이펙스");
//        applicationGeneral.setCreDate(timestamp);
//        applicationGeneral.setCreId(userId);
//        ea.setApplicationGeneral(applicationGeneral);
//
//        ApplicationETCWithBLOBs applicationETCWithBLOBs = new ApplicationETCWithBLOBs();
//        applicationETCWithBLOBs.setCovLett("자기 소개 입니다.");
//        applicationETCWithBLOBs.setStudPlan("연구계획 입니다.");
//        applicationETCWithBLOBs.setCreDate(timestamp);
//        applicationETCWithBLOBs.setCreId(userId);
//        ea.setApplicationETCWithBLOBs(applicationETCWithBLOBs);
//
//        ApplicationAcademy highSchool = new ApplicationAcademy();
//        highSchool.setAcadTypeCode("00001");
//        highSchool.setSchlName("깨똥고등학교");
//        highSchool.setCreDate(timestamp);
//        highSchool.setCreId(userId);
//        ea.setHighSchool(highSchool);
//
//        List<ApplicationAcademy> collegeList = new ArrayList<ApplicationAcademy>();
//            ApplicationAcademy aa0 = new ApplicationAcademy();
//            aa0.setAcadSeq(1);
//            aa0.setAcadTypeCode("00002");
//            aa0.setSchlName("연세대학교");
//            aa0.setCreDate(timestamp);
//            aa0.setCreId(userId);
//            collegeList.add(aa0);
//            ApplicationAcademy aa1 = new ApplicationAcademy();
//            aa1.setAcadSeq(2);
//            aa1.setAcadTypeCode("00002");
//            aa1.setSchlName("면세대학교");
//            aa1.setCreDate(timestamp);
//            aa1.setCreId(userId);
//            collegeList.add(aa1);
//            ApplicationAcademy aa2 = new ApplicationAcademy();
//            aa2.setAcadSeq(3);
//            aa2.setAcadTypeCode("00002");
//            aa2.setSchlName("면제대학교");
//            aa2.setCreDate(timestamp);
//            aa2.setCreId(userId);
//            collegeList.add(aa2);
//        ea.setCollegeList(collegeList);
//
//        List<ApplicationExperience> experienceList = new ArrayList<ApplicationExperience>();
//            ApplicationExperience ae0 = new ApplicationExperience();
//            aa0.setAcadSeq(1);
//            ae0.setCorpName("보국전자");
//            ae0.setCreDate(timestamp);
//            ae0.setCreId(userId);
//            experienceList.add(ae0);
//            ApplicationExperience ae1 = new ApplicationExperience();
//            aa1.setAcadSeq(2);
//            ae1.setCorpName("가우스전자");
//            ae1.setCreDate(timestamp);
//            ae1.setCreId(userId);
//            experienceList.add(ae1);
//        ea.setApplicationExperienceList(experienceList);
//
//        List<ApplicationLanguage> applicationLanguageList = new ArrayList<ApplicationLanguage>();
//            ApplicationLanguage al0 = new ApplicationLanguage();
//            al0.setLangSeq(1);
//            al0.setLangExamCode("ToefL");
//            al0.setCreDate(timestamp);
//            al0.setCreId(userId);
//            applicationLanguageList.add(al0);
//
//            ApplicationLanguage al1 = new ApplicationLanguage();
//            al1.setLangSeq(2);
//            al1.setLangExamCode(examCode);
//            al1.setCreDate(timestamp);
//            al1.setCreId(userId);
//            applicationLanguageList.add(al1);
//        ea.setApplicationLanguageList(applicationLanguageList);
//
//        applicationService.createEntireApplication(ea);
//
//        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(ea);
//    }
}

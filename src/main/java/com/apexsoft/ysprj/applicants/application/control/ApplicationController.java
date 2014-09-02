package com.apexsoft.ysprj.applicants.application.control;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.web.file.FileHandler;
import com.apexsoft.ysprj.applicants.application.domain.*;
import com.apexsoft.ysprj.applicants.application.service.ApplicationService;
import com.apexsoft.ysprj.applicants.common.domain.*;
import com.apexsoft.ysprj.applicants.common.service.CommonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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

    @Autowired
    private ObjectMapper objectMapper;

    @Resource(name = "messageResolver")
    MessageResolver messageResolver;

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

        List<CustomMyList> myList = applicationService.retrieveMyList(p);
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

    /**
     * appinfo 분할 작업용
     * @param model
     * @return
     */
    @RequestMapping(value="/apply-work")
    public String displayApplyWork(Model model) {
        model.addAttribute("application", applicationService.retrieveApplication(1));
        return "application/appinfo-work";
    }

    /**
     * appinfo 파일업로 작업용
     * @param model
     * @return
     */
    @RequestMapping(value="/apply-file")
    public String displayApplyFile(Model model) {
        return "application/appinfo-fileupload";
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
    public String displayAppInfo(@RequestParam(value = "applNo", required = false) Integer applNo,
                                 @RequestParam(value = "admsNo", required = false) String admsNo,
                                 @RequestParam(value = "entrYear", required = false) String entrYear,
                                 @RequestParam(value = "admsTypeCode", required = false) String admsTypeCode,
                                 @ModelAttribute("entireApplication") EntireApplication entireApplication,
                                 Model model) {

        Map<String, Object> commonCodeMap = new HashMap<String, Object>();

        if( applNo != null ) {
//            if( applNo != entireApplication.getApplication().getApplNo() ) {
                entireApplication = applicationService.retrieveEntireApplication(applNo);
//            }
            CampusCollege campusCollege = applicationService.retriveCampusCollege(applNo);
            entireApplication.setCampCode(campusCollege.getCampCode());
            entireApplication.setCollCode(campusCollege.getCollCode());

            // 지원사항 select 초기값 설정
            List<Campus> campList = null;
            List<AcademyResearchIndustryInstitution> ariInstList = null;
            List<College> collList = null;
            List<CodeNameDepartment> deptList = null;
            List<CodeNameCourse> corsTypeList = null;
            List<CodeNameDetailMajor> detlMajList = null;

            ParamForSetupCourses param = new ParamForSetupCourses();
            param.setAdmsNo( entireApplication.getApplication().getAdmsNo() );
            param.setCollCode( entireApplication.getCollCode() );
            param.setDeptCode( entireApplication.getApplication().getDeptCode() );
            param.setCorsTypeCode( entireApplication.getApplication().getCorsTypeCode() );
            param.setAriInstCode( entireApplication.getApplication().getAriInstCode() );

            String applAttrCode = entireApplication.getApplication().getApplAttrCode();
            if( "00001".equals( applAttrCode ) ) {
                campList = commonService.retrieveCampus();
                collList = commonService.retrieveCollegeByCampus( entireApplication.getCampCode() );
                deptList = commonService.retrieveGeneralDepartmentByAdmsColl(param);
                corsTypeList = commonService.retrieveGeneralCourseByAdmsDept(param);
                detlMajList = commonService.retrieveGeneralDetailMajorByAdmsDeptCors(param);
            } else if( "00002".equals( applAttrCode ) ) {
                ariInstList = commonService.retrieveAriInst();
                deptList = commonService.retrieveAriInstDepartmentByAdmsAriInst(param);
                corsTypeList = commonService.retrieveAriInstCourseByAdmsDeptAriInst(param);
                detlMajList = commonService.retrieveAriInstDetailMajorByAdmsDeptAriInst(param);
            } else if( "00003".equals( applAttrCode ) ) {
                campList = commonService.retrieveCampus();
                collList = commonService.retrieveCollegeByCampus( entireApplication.getCampCode() );
                deptList = commonService.retrieveGeneralDepartmentByAdmsColl(param);
                corsTypeList = commonService.retrieveCommissionCourseByAdmsDept(param);
            }

            if( campList != null )      commonCodeMap.put( "campList", campList );
            if( collList != null )      commonCodeMap.put( "collList", collList );
            if( ariInstList != null)    commonCodeMap.put( "ariInstList", ariInstList );
            if( deptList != null )      commonCodeMap.put( "deptList", deptList );
            if( corsTypeList != null )  commonCodeMap.put( "corsTypeList", corsTypeList );
            if( detlMajList != null )   commonCodeMap.put( "detlMajList", detlMajList );
        } else {
//            if( entireApplication.getApplication().getApplNo() != null) {
//                entireApplication = entireApplication();
//            }
            entireApplication.getApplication().setAdmsNo(admsNo);
            entireApplication.getApplication().setEntrYear(entrYear);
            entireApplication.getApplication().setAdmsTypeCode(admsTypeCode);

            List<Campus> campList = commonService.retrieveCampus();
            List<AcademyResearchIndustryInstitution> ariInstList = commonService.retrieveAriInst();
            if( campList != null )      commonCodeMap.put( "campList", campList );
            if( ariInstList != null)    commonCodeMap.put( "ariInstList", ariInstList );
        }

        model.addAttribute("entireApplication", entireApplication);

        List<CommonCode> applAttrList = commonService.retrieveCommonCodeValueByCodeGroup("APPL_ATTR");
        List<CommonCode> mltrServList = commonService.retrieveCommonCodeValueByCodeGroup("MLTR_SERV");
        List<CommonCode> mltrTypeList = commonService.retrieveCommonCodeValueByCodeGroup("MLTR_TYPE");
        List<CommonCode> mltrRankList = commonService.retrieveCommonCodeValueByCodeGroup("MLTR_RANK");
        List<CommonCode> emerContList = commonService.retrieveCommonCodeValueByCodeGroup("EMER_CONT");
        List<CommonCode> toflTypeList = commonService.retrieveCommonCodeValueByCodeGroup("TOFL_TYPE");
        List<CommonCode> fornExmpList = commonService.retrieveCommonCodeValueByCodeGroup("FORN_EXMP");

        commonCodeMap.put( "applAttrList", applAttrList );
        commonCodeMap.put( "mltrServList", mltrServList );
        commonCodeMap.put( "mltrTypeList", mltrTypeList );
        commonCodeMap.put( "mltrRankList", mltrRankList );
        commonCodeMap.put( "emerContList", emerContList );
        commonCodeMap.put( "toflTypeList", toflTypeList );
        commonCodeMap.put( "fornExmpList", fornExmpList );

        model.addAttribute( "common", commonCodeMap );

        model.addAttribute( "msgRgstNo", messageResolver.getMessage("U304"));
        model.addAttribute( "msgPhoneNo", messageResolver.getMessage("U305"));

        return "application/appinfo";
    }

    /**
     * 입학원서 저장
     *
     * @param entireApplication
     * @param binding
     * @param principal
     * @return
     */
    @RequestMapping(value = "/apply/save", method = RequestMethod.POST)
    @ResponseBody
    public ExecutionContext saveApplication(@Valid @ModelAttribute EntireApplication entireApplication,
                                            BindingResult binding,
                                            Principal principal,
                                            FileHandler fileHandler) {
        if( binding.hasErrors() ) {
            return new ExecutionContext(ExecutionContext.FAIL);
        }

        if( principal == null ) {
            return new ExecutionContext(ExecutionContext.FAIL);
        }

        String userId = principal.getName();
        entireApplication.getApplication().setUserId(userId);
        entireApplication.getApplication().setCreId(userId);
        entireApplication.getApplicationGeneral().setCreId(userId);
        entireApplication.getApplicationETCWithBLOBs().setCreId(userId);
        entireApplication.getHighSchool().setCreId(userId);
        List<ApplicationAcademy> collegeList = entireApplication.getCollegeList();
        for(ApplicationAcademy item : collegeList) {
            item.setCreId(userId);
        }
        List<ApplicationAcademy> graduateList = entireApplication.getGraduateList();
        for(ApplicationAcademy item : graduateList) {
            item.setCreId(userId);
        }
        List<ApplicationExperience> experienceList = entireApplication.getApplicationExperienceList();
        for(ApplicationExperience item : experienceList) {
            item.setCreId(userId);
        }
        List<ApplicationLanguage> languageList = entireApplication.getApplicationLanguageList();
        for(ApplicationLanguage item : languageList) {
            item.setCreId(userId);
        }

        entireApplication.getApplication().setApplStsCode("00001");
        String message = messageResolver.getMessage("U302");
        if( entireApplication.getApplication().getApplNo() == null ) {   // insert
            applicationService.createEntireApplication( entireApplication );
            message = messageResolver.getMessage("U301");
        } else {    // update
            applicationService.updateEntireApplication( entireApplication );
            message = messageResolver.getMessage("U303");
        }
        return new ExecutionContext(ExecutionContext.SUCCESS, message);
    }

    @RequestMapping(value="/apply/update")
    public ExecutionContext updateEntireApplication(@ModelAttribute EntireApplication entireApplication,
                                          BindingResult binding,
                                          Principal principal) {

        if( binding.hasErrors() ) {
            return new ExecutionContext(ExecutionContext.FAIL);
        }

        if( principal == null ) {
            return new ExecutionContext(ExecutionContext.FAIL);
        }
        String userId = principal.getName();
        entireApplication.getApplication().setModId(userId);
        entireApplication.getApplicationGeneral().setModId(userId);
        entireApplication.getApplicationETCWithBLOBs().setModId(userId);
        entireApplication.getHighSchool().setModId(userId);
        List<ApplicationAcademy> collegeList = entireApplication.getCollegeList();
        for(ApplicationAcademy item : collegeList) {
            item.setModId(userId);
        }
        List<ApplicationAcademy> graduateList = entireApplication.getGraduateList();
        for(ApplicationAcademy item : graduateList) {
            item.setModId(userId);
        }
        List<ApplicationExperience> experienceList = entireApplication.getApplicationExperienceList();
        for(ApplicationExperience item : experienceList) {
            item.setModId(userId);
        }
        List<ApplicationLanguage> languageList = entireApplication.getApplicationLanguageList();
        for(ApplicationLanguage item : languageList) {
            item.setModId(userId);
        }

        //TODO Call impl.updateEntireApplication
        String message = messageResolver.getMessage("U303");

        return new ExecutionContext(ExecutionContext.SUCCESS, message);
    }

    @RequestMapping(value = "/modify")
    public String modifyAppinfo(@RequestParam(value = "applNo", required = false) Integer applNo,
                                Model model) {
        model.addAttribute("entireApplication", applicationService.retrieveEntireApplication(applNo));
        return "application/appinfo";
    }

    @ModelAttribute("entireApplication")
    public EntireApplication entireApplication() {
        EntireApplication entireApplication = new EntireApplication();
        entireApplication.setApplication(new Application());
        entireApplication.setApplicationGeneral(new ApplicationGeneral());
        entireApplication.setApplicationETCWithBLOBs(new ApplicationETCWithBLOBs());
        entireApplication.setHighSchool(new ApplicationAcademy());
        entireApplication.setCollegeList(new ArrayList<ApplicationAcademy>());
        entireApplication.setGraduateList(new ArrayList<ApplicationAcademy>());
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
    @RequestMapping(value="/in/{userId}/{examCode}/{applStsCode}", produces="text/plain;charset=UTF-8")
    @ResponseBody
    public String createEntireApplication(@PathVariable("userId") String userId,
                                          @PathVariable("examCode") String examCode,
                                          @PathVariable("applStsCode") String applStsCode)
            throws JsonProcessingException {

        EntireApplication ea = new EntireApplication();

        Timestamp timestamp = new Timestamp(new Date().getTime());

        Application application = new Application();
        application.setUserId(userId);
        application.setAdmsNo("15A");
        application.setApplStsCode("00001");
        application.setEntrYear("2015");
        application.setAdmsTypeCode("A");
        application.setApplAttrCode("");
        application.setDeptCode("10101");
        application.setCorsTypeCode("2");
        application.setDetlMajCode("DM002");
        application.setPartTimeYn("N");
        application.setKorName("대조영");
        application.setChnName("大조영");
        application.setEngSur("DAE");
        application.setEngName("JOYOUNG");
        application.setRgstNo("9009091111118");
        application.setZipCode("151742");
        application.setAddr("인천 서구 경서동");
        application.setAddr("88 활어회집");
        application.setTelNum("01087451254");
        application.setMobiNum("01085693214");
        application.setMailAddr("hanmomhanda@naver.com");
        application.setApplStsCode(applStsCode);
        application.setPrivInfoYn("Y");
        application.setCreId(userId);
        application.setCreDate(timestamp);
        ea.setApplication(application);

        ApplicationGeneral applicationGeneral = new ApplicationGeneral();
        applicationGeneral.setCurrWrkpName("에이펙스");
        applicationGeneral.setCreDate(timestamp);
        applicationGeneral.setCreId(userId);
        ea.setApplicationGeneral(applicationGeneral);

        ApplicationETCWithBLOBs applicationETCWithBLOBs = new ApplicationETCWithBLOBs();
        applicationETCWithBLOBs.setCovLett("자기 소개 입니다.");
        applicationETCWithBLOBs.setStudPlan("연구계획 입니다.");
        applicationETCWithBLOBs.setCreDate(timestamp);
        applicationETCWithBLOBs.setCreId(userId);
        ea.setApplicationETCWithBLOBs(applicationETCWithBLOBs);

        ApplicationAcademy highSchool = new ApplicationAcademy();
        highSchool.setAcadTypeCode("00001");
        highSchool.setSchlName("깨똥고등학교");
        highSchool.setCreDate(timestamp);
        highSchool.setCreId(userId);
        ea.setHighSchool(highSchool);

        List<ApplicationAcademy> collegeList = new ArrayList<ApplicationAcademy>();
            ApplicationAcademy aa0 = new ApplicationAcademy();
            aa0.setAcadSeq(1);
            aa0.setAcadTypeCode("00002");
            aa0.setSchlName("연세대학교");
            aa0.setCreDate(timestamp);
            aa0.setCreId(userId);
            collegeList.add(aa0);
            ApplicationAcademy aa1 = new ApplicationAcademy();
            aa1.setAcadSeq(2);
            aa1.setAcadTypeCode("00002");
            aa1.setSchlName("면세대학교");
            aa1.setCreDate(timestamp);
            aa1.setCreId(userId);
            collegeList.add(aa1);
            ApplicationAcademy aa2 = new ApplicationAcademy();
            aa2.setAcadSeq(3);
            aa2.setAcadTypeCode("00002");
            aa2.setSchlName("면제대학교");
            aa2.setCreDate(timestamp);
            aa2.setCreId(userId);
            collegeList.add(aa2);
        ea.setCollegeList(collegeList);

        List<ApplicationExperience> experienceList = new ArrayList<ApplicationExperience>();
            ApplicationExperience ae0 = new ApplicationExperience();
            aa0.setAcadSeq(1);
            ae0.setCorpName("보국전자");
            ae0.setCreDate(timestamp);
            ae0.setCreId(userId);
            experienceList.add(ae0);
            ApplicationExperience ae1 = new ApplicationExperience();
            aa1.setAcadSeq(2);
            ae1.setCorpName("가우스전자");
            ae1.setCreDate(timestamp);
            ae1.setCreId(userId);
            experienceList.add(ae1);
        ea.setApplicationExperienceList(experienceList);

        List<ApplicationLanguage> applicationLanguageList = new ArrayList<ApplicationLanguage>();
            ApplicationLanguage al0 = new ApplicationLanguage();
            al0.setLangSeq(1);
            al0.setLangExamCode("ToefL");
            al0.setCreDate(timestamp);
            al0.setCreId(userId);
            applicationLanguageList.add(al0);

            ApplicationLanguage al1 = new ApplicationLanguage();
            al1.setLangSeq(2);
            al1.setLangExamCode(examCode);
            al1.setCreDate(timestamp);
            al1.setCreId(userId);
            applicationLanguageList.add(al1);
        ea.setApplicationLanguageList(applicationLanguageList);

        applicationService.createEntireApplication(ea);

        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(ea);
    }
}

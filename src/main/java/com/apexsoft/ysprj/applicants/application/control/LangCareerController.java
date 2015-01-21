package com.apexsoft.ysprj.applicants.application.control;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.ysprj.applicants.admission.domain.AdmissionCourseMajorLanguage;
import com.apexsoft.ysprj.applicants.admission.domain.ParamForAdmissionCourseMajor;
import com.apexsoft.ysprj.applicants.admission.service.AdmissionService;
import com.apexsoft.ysprj.applicants.application.domain.*;
import com.apexsoft.ysprj.applicants.application.service.LangCareerService;
import com.apexsoft.ysprj.applicants.common.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.*;

/**
 * Created by hanmomhanda on 15. 1. 13.
 *
 * 원서 어학/경력 정보 컨트롤러
 */
@Controller
@RequestMapping(value="/application/langCareer")
public class LangCareerController {

    @Autowired
    private LangCareerService langCareerService;

    @Autowired
    private CommonService commonService;

    @Autowired
    private AdmissionService admissionService;

    @Resource(name = "messageResolver")
    MessageResolver messageResolver;

    private final String APP_INFO_SAVED = "00001";
    private final String ACAD_SAVED = "00002";
    private final String LANG_CAREER_SAVED = "00003";

    private final String TARGET_VIEW = "application/langCareer";

    /**
     * 어학/경력 정보를 조회해서 어학/경력 정보 화면에 뿌려질 데이터 구성
     *
     * @param applicationIdentifier
     * @return
     */
    private ExecutionContext setupLangCareer(ApplicationIdentifier applicationIdentifier) {
        ExecutionContext ec;

        Map<String, Object> ecDataMap = new HashMap<String, Object>();
        Map<String, Object> commonCodeMap = new HashMap<String, Object>();
        LangCareer langCareer;

        int applNo = applicationIdentifier.getApplNo();
        String admsNo = applicationIdentifier.getAdmsNo();
        String entrYear = applicationIdentifier.getEntrYear();
        String admsTypeCode = applicationIdentifier.getAdmsTypeCode();

        ec = langCareerService.retrieveLangCareer(applNo);
        langCareer = (LangCareer)ec.getData();
        Application application = langCareer.getApplication();

        ParamForAdmissionCourseMajor param = new ParamForAdmissionCourseMajor();
        param.setAdmsNo(admsNo);
        param.setDeptCode(application.getDeptCode());
        param.setCorsTypeCode(application.getCorsTypeCode());
        param.setDetlMajCode(application.getDetlMajCode());
        List<AdmissionCourseMajorLanguage> langExamList =
                admissionService.retrieveAvailableEngExamList(param);

        commonCodeMap.put( "toflTypeList", commonService.retrieveCommonCodeValueByCodeGroup("TOFL_TYPE") );
        commonCodeMap.put( "fornExmpList", commonService.retrieveCommonCodeValueByCodeGroup("FORN_EXMP") );
        commonCodeMap.put( "langExamList", langExamList);

        ecDataMap.put("langCareer", langCareer);
        ecDataMap.put("common", commonCodeMap);
        ec.setData(ecDataMap);

        return ec;
    }

    public ExecutionContext retrieveAvailableEngExamList(ParamForAdmissionCourseMajor param) {

        List<AdmissionCourseMajorLanguage> admissionCourseMajorLanguageList = admissionService.retrieveAvailableEngExamList(param);

        ExecutionContext executionContext = new ExecutionContext();
        if (!(admissionCourseMajorLanguageList.size() > 0)) {
            executionContext.setMessage(messageResolver.getMessage("U300"));
        }


        return executionContext;
    }

    /**
     * 어학/경력 정보 최초작성/수정 화면
     *
     * @param model
     * @return
     */
    @RequestMapping(value="/edit")
    public ModelAndView getLangCareer(@ModelAttribute LangCareer model) {
        ModelAndView mv = new ModelAndView(TARGET_VIEW);

        Application application = model.getApplication();
        int applNo = application.getApplNo();
        String admsNo = application.getAdmsNo();
        String entrYear = application.getEntrYear();
        String admsTypeCode = application.getAdmsTypeCode();

        ExecutionContext ec = setupLangCareer(new ApplicationIdentifier(applNo, admsNo, entrYear, admsTypeCode));
        Map<String, Object> map = (Map<String, Object>)ec.getData();
        addObjectToMV(mv, map, ec);

        return mv;
    }

    /**
     * 어학/경력 정보 저장
     *
     * @param langCareer
     * @param principal
     * @return
     */
    @RequestMapping(value="/save", method = RequestMethod.POST)
    public ModelAndView saveLangCareer(@ModelAttribute LangCareer langCareer,
                                       Principal principal) {
        ModelAndView mv = new ModelAndView(TARGET_VIEW);

        ExecutionContext ec = null;
        String userId = principal.getName();

        Application application = langCareer.getApplication();
        int applNo = application.getApplNo();
        application.setUserId(userId);
        application.setModId(userId);

//        if (application.getApplStsCode().equals(ACAD_SAVED)) { //insert
//            for(ApplicationLanguage al : langCareer.getLanguageGroupList()) {
//                al.setApplNo(applNo);
//                al.setCreId(userId);
//            }
//            for(ApplicationExperience ae : langCareer.getApplicationExperienceList()) {
//                ae.setApplNo(applNo);
//                ae.setCreId(userId);
//            }
//            ec = langCareerService.createLangCareer(application,
//                    langCareer.getLanguageGroupList(),
//                    langCareer.getApplicationExperienceList());
//        } else { //update
//            ec = langCareerService.updateLangCareer(application,
//                    langCareer.getLanguageGroupList(),
//                    langCareer.getApplicationExperienceList());
//        }

        if (ec.getResult().equals(ExecutionContext.SUCCESS)) {
            ApplicationIdentifier data = (ApplicationIdentifier)ec.getData();
            ExecutionContext ecSetupLangCareer = setupLangCareer(data);

            if (ecSetupLangCareer.getResult().equals(ExecutionContext.SUCCESS)) {
                Map<String, Object> setupMap = (Map<String, Object>)ecSetupLangCareer.getData();
                addObjectToMV(mv, setupMap, ec);
            } else {
                mv = getErrorMV("common/error", ecSetupLangCareer);
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
        mv.addObject("langCareer", map.get("langCareer"));
        mv.addObject("common", map.get("common"));
        mv.addObject("resultMsg", ec.getMessage());
    }
}

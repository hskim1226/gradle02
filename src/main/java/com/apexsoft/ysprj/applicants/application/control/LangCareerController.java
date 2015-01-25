package com.apexsoft.ysprj.applicants.application.control;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.ysprj.applicants.admission.domain.ParamForAdmissionCourseMajor;
import com.apexsoft.ysprj.applicants.application.domain.*;
import com.apexsoft.ysprj.applicants.application.service.LangCareerService;
import com.apexsoft.ysprj.applicants.common.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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

    @Resource(name = "messageResolver")
    MessageResolver messageResolver;

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

        commonCodeMap.put( "toflTypeList", commonService.retrieveCommonCodeValueByCodeGroup("TOFL_TYPE") );
        commonCodeMap.put( "fornExmpList", commonService.retrieveCommonCodeValueByCodeGroup("FORN_EXMP") );

        ecDataMap.put("langCareer", langCareer);
        ecDataMap.put("common", commonCodeMap);
        ec.setData(ecDataMap);

        return ec;
    }

    /**
     * 어학/경력 정보 최초작성/수정 화면
     *
     * @param formData
     * @return
     */
    @RequestMapping(value="/edit")
    public ModelAndView getLangCareer(@ModelAttribute LangCareer formData,
                                      BindingResult bindingResult,
                                      ModelAndView mv) {
        mv.setViewName(TARGET_VIEW);
        if (bindingResult.hasErrors()) return mv;

        Application application = formData.getApplication();
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
     * @param formData
     * @param principal
     * @return
     */
    @RequestMapping(value="/save", method = RequestMethod.POST)
    public ModelAndView saveLangCareer(@ModelAttribute LangCareer formData,
                                       Principal principal,
                                       BindingResult bindindResult,
                                       ModelAndView mv) {
        mv.setViewName(TARGET_VIEW);
        if (bindindResult.hasErrors()) return mv;

        ExecutionContext ec = null;
        String userId = principal.getName();

        Application application = formData.getApplication();
        int applNo = application.getApplNo();
        application.setUserId(userId);
        application.setModId(userId);

        List<CustomApplicationExperience> exprList = formData.getApplicationExperienceList();

        removeEmptyApplicatinoAcademy(exprList);

        langCareerService.saveLangCareer(formData);

        // TODO - omw - 로직은 서비스로 이동 예정
//        if (application.getApplStsCode().equals(ACAD_SAVED)) { //insert
//            for (LanguageGroup lGroup : formData.getLanguageGroupList()) {
//                List<TotalApplicationLanguage> langList = lGroup.getLangList();
//                for (TotalApplicationLanguage alang : langList) {
//                    alang.setApplNo(applNo);
//                    alang.setCreId(userId);
//                }
//            }
//            for (ApplicationExperience ae : formData.getApplicationExperienceList()) {
//                ae.setApplNo(applNo);
//                ae.setCreId(userId);
//            }
//            ec = langCareerService.createLangCareer(application,
//                    formData.getLanguageGroupList(),
//                    formData.getApplicationExperienceList());
//        } else { //update
//            ec = langCareerService.updateLangCareer(application,
//                    formData.getLanguageGroupList(),
//                    formData.getApplicationExperienceList());
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

    private List<CustomApplicationExperience> removeEmptyApplicatinoAcademy(List<CustomApplicationExperience> list) {
        int i, length;
        for (i = 0, length = list.size() ; i < length ; i++) {
            if (list.get(i).getCorpName() == null || list.get(i).getCorpName().equals("")) {
                list.remove(i);
                length--;
                i--;
            }
        }
        return list;
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

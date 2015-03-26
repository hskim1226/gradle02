package com.apexsoft.ysprj.applicants.application.control;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.ysprj.applicants.application.domain.Application;
import com.apexsoft.ysprj.applicants.application.domain.CustomApplicationExperience;
import com.apexsoft.ysprj.applicants.application.domain.LangCareer;
import com.apexsoft.ysprj.applicants.application.service.LangCareerService;
import com.apexsoft.ysprj.applicants.application.validator.LangCareerValidator;
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
    private LangCareerValidator langCareerValidator;

    @Autowired
    private CommonService commonService;

    @Resource(name = "messageResolver")
    MessageResolver messageResolver;

    private final String TARGET_VIEW = "application/langCareer";

    /**
     * 어학/경력 정보 최초 작성 및 수정 화면
     *
     * @param formData
     * @param bindingResult
     * @param mv
     * @return
     */
    @RequestMapping(value="/edit")
    public ModelAndView getLangCareer(@ModelAttribute LangCareer formData,
                                      BindingResult bindingResult,
                                      ModelAndView mv) {
        mv.setViewName(TARGET_VIEW);
        if (bindingResult.hasErrors()) return mv;

        ExecutionContext ec = langCareerService.retrieveLangCareer(formData);

        Map<String, Object> map = (Map<String, Object>)ec.getData();
        addObjectToMV(mv, map, ec);

        return mv;
    }

    /**
     * 어학/경력 정보 저장
     *
     * @param formData
     * @param principal
     * @param bindingResult
     * @param mv
     * @return
     */
    @RequestMapping(value="/save", method = RequestMethod.POST)
    public ModelAndView saveLangCareer(@ModelAttribute LangCareer formData,
                                       Principal principal,
                                       BindingResult bindingResult,
                                       ModelAndView mv) {
        langCareerValidator.validate(formData, bindingResult);
        ExecutionContext ec = null;
        mv.setViewName(TARGET_VIEW);
        if (bindingResult.hasErrors()) {
            mv.addObject("resultMsg", messageResolver.getMessage("U334"));
            /*
            ExecutionContext ecRetrieve = langCareerService.retrieveLangSubCode(formData);

            Map<String, Object> setupMap = (Map<String, Object>)ecRetrieve.getData();
            addObjectToMV(mv, setupMap, ec);
            mv.addObject("langCareer", setupMap.get("langCareer"));

            */
            langCareerService.retrieveLangSubCode(formData);
            HashMap<String, Object> commonCodeMap = new HashMap<String, Object>();

            commonCodeMap.put( "ieltsLevelList", commonService.retrieveCommonCodeByCodeGroup("IELT_LEVL") );
            commonCodeMap.put( "topikLevelList", commonService.retrieveCommonCodeByCodeGroup("TOPK_LEVL") );

            mv.addObject("common", commonCodeMap);

            return mv;
        }


        String userId = principal.getName();

        Application application = formData.getApplication();
        int applNo = application.getApplNo();
        application.setUserId(userId);
        application.setModId(userId);

        List<CustomApplicationExperience> exprList = formData.getApplicationExperienceList();

        removeEmptyExperienceAcademy(exprList);

        ec = langCareerService.saveLangCareer(formData);

        if (ExecutionContext.SUCCESS.equals(ec.getResult())) {
            ExecutionContext ecRetrieve = langCareerService.retrieveLangCareer(formData);

            if (ExecutionContext.SUCCESS.equals(ecRetrieve.getResult())) {
                Map<String, Object> setupMap = (Map<String, Object>)ecRetrieve.getData();
                addObjectToMV(mv, setupMap, ec);
            } else {
                mv = getErrorMV("common/error", ecRetrieve);
            }
        } else {
            mv = getErrorMV("common/error", ec);
        }

        return mv;
    }

    /**
     * 입력란만 추가하고 데이터는 없는 경력 정보 제거
     *
     * @param list
     * @return
     */
    private List<CustomApplicationExperience> removeEmptyExperienceAcademy(List<CustomApplicationExperience> list) {
        int i, length;
        for (i = 0, length = list.size() ; i < length ; i++) {
            if (list.get(i).getCorpName() == null || "".equals(list.get(i).getCorpName())) {
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
        mv.addAllObjects(map);
        mv.addObject("resultMsg", ec.getMessage());
    }
}

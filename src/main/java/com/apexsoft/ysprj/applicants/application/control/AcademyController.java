package com.apexsoft.ysprj.applicants.application.control;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.ysprj.applicants.application.domain.*;
import com.apexsoft.ysprj.applicants.application.service.AcademyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.*;

/**
 * Created by hanmomhanda on 15. 1. 12.
 *
 * 원서 학력 정보 컨트롤러
 */
@Controller
@RequestMapping(value="/application/academy")
public class AcademyController {

    @Autowired
    private AcademyService academyService;

    @Resource(name = "messageResolver")
    MessageResolver messageResolver;

    private final String TARGET_VIEW = "application/academy";

    /**
     * 학력 정보 최초 작성 및 수정 화면
     *
     * @param formData
     * @return
     */
    @RequestMapping(value="/edit")
    public ModelAndView getAcademy(@ModelAttribute Academy formData,
                                   BindingResult bindingResult,
                                   ModelAndView mv) {
        mv.setViewName(TARGET_VIEW);
        if (bindingResult.hasErrors()) return mv;

        ExecutionContext ec = academyService.retrieveAcademy(formData);

        Map<String, Object> map = (Map<String, Object>)ec.getData();
        addObjectToMV(mv, map, ec);

        return mv;
    }

    /**
     * 학력 정보 저장
     *
     * @param formData
     * @param principal
     * @return
     */
    @RequestMapping(value="/save", method = RequestMethod.POST)
    public ModelAndView saveAcademy(@ModelAttribute Academy formData,
                                    Principal principal,
                                    BindingResult bindingResult,
                                    ModelAndView mv) {
        mv.setViewName(TARGET_VIEW);
        if (bindingResult.hasErrors()) return mv;

        ExecutionContext ec;
        String userId = principal.getName();

        Application application = formData.getApplication();

        application.setUserId(userId);
        application.setModId(userId);

        List<CustomApplicationAcademy> collegeList = formData.getCollegeList();
        List<CustomApplicationAcademy> graduateList = formData.getGraduateList();

        removeEmptyApplicationAcademy(collegeList);
        removeEmptyApplicationAcademy(graduateList);

        ec = academyService.saveAcademy(formData);

        if (ec.getResult().equals(ExecutionContext.SUCCESS)) {
            ExecutionContext ecRetrieve = academyService.retrieveAcademy(formData);

            if (ecRetrieve.getResult().equals(ExecutionContext.SUCCESS)) {
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
     * 입력란만 추가하고 실제 데이터가 없는 학력 정보 제거
     *
     * @param list
     * @return
     */
    private List<CustomApplicationAcademy> removeEmptyApplicationAcademy(List<CustomApplicationAcademy> list) {
        int i, length;
        for (i = 0, length = list.size() ; i < length ; i++) {
            if (list.get(i).getSchlCntrCode() == null || list.get(i).getSchlCntrCode().equals("")) {
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
        mv.addObject("academy", map.get("academy"));
        mv.addObject("resultMsg", ec.getMessage());
    }
}

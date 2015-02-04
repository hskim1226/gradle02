package com.apexsoft.ysprj.applicants.application.control;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.ysprj.applicants.application.domain.*;
import com.apexsoft.ysprj.applicants.application.service.ApplicationService;
import com.apexsoft.ysprj.applicants.application.service.BasisService;
import com.apexsoft.ysprj.applicants.application.validator.BasisValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.security.Principal;
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
    private BasisValidator basisValidator;

    @Resource(name = "messageResolver")
    MessageResolver messageResolver;

    private final String TARGET_VIEW = "application/basis";

    /**
     * 기본정보 최초 작성 및 수정 화면
     *
     * @return
     */
    @RequestMapping(value="/edit")
    public ModelAndView getBasis(@ModelAttribute Basis basis,
                                 BindingResult bindingResult,
                                 ModelAndView mv) {
        mv.setViewName(TARGET_VIEW);
        if (bindingResult.hasErrors()) return mv;

        ExecutionContext ec = basisService.retrieveBasis(basis);

        Map<String, Object> map = (Map<String, Object>)ec.getData();
        addObjectToMV(mv, map, ec);

        return mv;
    }

    /**
     * 기본 정보 저장
     *
     * @param formData
     * @param principal
     * @return
     */
    @RequestMapping(value="/save", method = RequestMethod.POST)
    public ModelAndView saveBasis(@ModelAttribute Basis formData,
                                  Principal principal,
                                  BindingResult bindingResult,
                                  ModelAndView mv) {
        basisValidator.validate(formData, bindingResult);
        mv.setViewName(TARGET_VIEW);
        if (bindingResult.hasErrors()) {
            mv.addObject("resultMsg", messageResolver.getMessage("U334"));
            ExecutionContext ecRetrieve = basisService.retrieveSelectionMap(formData);
            if (ecRetrieve.getResult().equals(ExecutionContext.SUCCESS)) {
                Map<String, Object> map = (Map<String, Object>)ecRetrieve.getData();
                mv.addObject("selection", map.get("selection"));
                mv.addObject("country", map.get("country"));
            } else {
                mv = getErrorMV("common/error", ecRetrieve);
            }
            return mv;
        }

        ExecutionContext ec;
        String userId = principal.getName();
        Application application = formData.getApplication();
        application.setUserId(userId);

        ec = basisService.saveBasis(formData);

        if (ec.getResult().equals(ExecutionContext.SUCCESS)) {

            ExecutionContext ecRetrieve = basisService.retrieveBasis(formData);
            if (ecRetrieve.getResult().equals(ExecutionContext.SUCCESS)) {
                Map<String, Object> map = (Map<String, Object>)ecRetrieve.getData();
                addObjectToMV(mv, map, ec);
            } else {
                mv = getErrorMV("common/error", ecRetrieve);
            }
        } else {
            mv = getErrorMV("common/error", ec);
        }

        return mv;
    }

    /**
     * 기본 정보 저장
     *
     * @param formData
     * @param principal
     * @return
     */
    @RequestMapping(value="/cancel", method = RequestMethod.POST)
    public ModelAndView cancelBasis(@ModelAttribute Basis formData,
                                  Principal principal,
                                  BindingResult bindingResult,
                                  ModelAndView mv) {

        mv.setViewName("application/mylist");
        if (bindingResult.hasErrors()) {
            mv.addObject("resultMsg", messageResolver.getMessage("U334"));
            ExecutionContext ecRetrieve = basisService.retrieveBasis(formData);
            if (ecRetrieve.getResult().equals(ExecutionContext.SUCCESS)) {
                Map<String, Object> map = (Map<String, Object>)ecRetrieve.getData();
                mv.addObject("selection", map.get("selection"));
                mv.addObject("country", map.get("country"));
            } else {
                mv = getErrorMV("common/error", ecRetrieve);
            }
            return mv;
        }

        ExecutionContext ec;
        String userId = principal.getName();
        Application application = formData.getApplication();
        application.setUserId(userId);

        ec = basisService.saveBasis(formData);

        if (ec.getResult().equals(ExecutionContext.SUCCESS)) {

            ExecutionContext ecApplicationByUserId;
            ParamForApplication p = new ParamForApplication();
            p.setUserId(principal.getName());

            ecApplicationByUserId = basisService.retrieveInfoListByParamObj(p, "CustomApplicationMapper.selectApplByUserId", CustomMyList.class);

            if (ecApplicationByUserId.getResult().equals(ExecutionContext.SUCCESS)) {
                mv.addObject("myList", ecApplicationByUserId.getData());
            } else {
                mv = getErrorMV("common/error", ecApplicationByUserId);
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
        mv.addObject("basis", map.get("basis"));
        mv.addObject("selection", map.get("selection"));
        mv.addObject("country", map.get("country"));
        mv.addObject("resultMsg", ec.getMessage());
    }
}

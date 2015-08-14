package com.apexsoft.ysprj.applicants.application.control;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.exception.ErrorInfo;
import com.apexsoft.framework.exception.YSBizException;
import com.apexsoft.framework.exception.YSBizNoticeException;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.ysprj.applicants.admission.service.AdmissionService;
import com.apexsoft.ysprj.applicants.application.domain.*;
import com.apexsoft.ysprj.applicants.application.service.RecommendationService;
import com.apexsoft.ysprj.applicants.common.util.CryptoUtil;
import com.apexsoft.ysprj.applicants.common.util.StringUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hanmomhanda on 15. 2. 14.
 */
@Controller
@RequestMapping(value="/application")
public class PreApplicationController {

    @Autowired
    private ObjectMapper jacksonObjectMapper;

    @Autowired
    private AdmissionService admissionService;

    @Autowired
    private RecommendationService recommendationService;

    @Autowired
    private ServletContext context;

    @Autowired
    private CommonDAO commonDAO;

    @Value("#{app['adms.general']}")
    private String admsGeneral;

    @Value("#{app['adms.foreign']}")
    private String admsForeign;

    @Value("#{app['adms.early']}")
    private String admsEarly;

    /**
     * 공고 목록 화면
     *
     * @return
     */
    @RequestMapping(value = "/admsList", method = RequestMethod.GET)
    public ModelAndView showList() {
        ModelAndView mv = new ModelAndView("application/admsList");

        mv.addObject("admsGeneral", admissionService.retrieveAdmissionByAdmsNo(admsGeneral));
        mv.addObject("admsForeign", admissionService.retrieveAdmissionByAdmsNo(admsForeign));
        mv.addObject("admsEarly", admissionService.retrieveAdmissionByAdmsNo(admsEarly));

        return mv;
    }

    /**
     * 일반 전형
     *
     * @return
     */
    @RequestMapping(value = "/general", method = RequestMethod.POST)
    public ModelAndView showGeneral() {
        ModelAndView mv = new ModelAndView("application/general");

        mv.addObject("admsGeneral", admissionService.retrieveAdmissionByAdmsNo(admsGeneral));

        return mv;
    }

    /**
     * 외국인 전형
     *
     * @return
     */
    @RequestMapping(value = "/foreign", method = RequestMethod.POST)
    public ModelAndView showForeign() {
        ModelAndView mv = new ModelAndView("application/foreign");

        mv.addObject("admsForeign", admissionService.retrieveAdmissionByAdmsNo(admsForeign));

        return mv;
    }

    /**
     * 조기 전형
     *
     * @return
     */
    @RequestMapping(value = "/early", method = RequestMethod.POST)
    public ModelAndView showEarly() {
        ModelAndView mv = new ModelAndView("application/early");

        mv.addObject("admsEarly", admissionService.retrieveAdmissionByAdmsNo(admsEarly));

        return mv;
    }

    /**
     * 내원서 화면
     * @param principal
     * @param mv
     * @return
     */
    @RequestMapping(value="/mylist")
    public ModelAndView myApplicationList(Principal principal, ModelAndView mv) {
        mv.setViewName("application/mylist");
        ParamForApplication parameter = new ParamForApplication();
        parameter.setUserId(principal.getName());

        List<CustomMyList> myList =
                commonDAO.queryForList("com.apexsoft.ysprj.applicants.application.sqlmap.CustomApplicationMapper.selectApplByUserId",
                    parameter, CustomMyList.class);

        mv.addObject("myList", myList);
        return mv;
    }

    /**
     * 원서 작성 동의 화면
     * SimpleForwardingController에서 이전
     * @return
     */
    @RequestMapping(value = "/agreement", method = RequestMethod.POST)
    public ModelAndView checkAgreement(@RequestParam(value = "admsNo") String admsNo,
                                       @RequestParam(value = "entrYear") String entrYear,
                                       @RequestParam(value = "admsTypeCode") String admsTypeCode) {
        ModelAndView mv = new ModelAndView("application/agreement");
        mv.addObject("admsNo", admsNo);
        mv.addObject("entrYear", entrYear);
        mv.addObject("admsTypeCode", admsTypeCode);
        return mv;
    }

    @RequestMapping(value = "/recReq/list")
    public ModelAndView recommendationList(Application application, ModelAndView mv) {
        mv.setViewName("application/recReqList");

        // TODO : 추천 요청 리스트 가져와서 mv에 세팅

        return mv;
    }

    @RequestMapping(value = "/recReq/edit")
    public ModelAndView recommendationEdit(Recommendation recommendation, ModelAndView mv) {
        mv.setViewName("application/recReqEdit");

        // TODO : 추천 요청 조회 mv에 세팅

        return mv;
    }

    @RequestMapping(value = "/recReq/preview", method = RequestMethod.POST)
    @ResponseBody
    public ExecutionContext recommendationPreview(Recommendation recommendation,
                                              BindingResult bindingResult,
                                              ModelAndView mv) {
//        mv.setViewName("application/recReqPreview");
//        if (bindingResult.hasErrors()) return mv;

        ExecutionContext ec = new ExecutionContext();

        int applNo = recommendation.getApplNo();
        String encrypted = getEncryptedRecKey(recommendation);
        recommendation.setRecKey(encrypted);
        recommendation.setRecStsCode("00001");

        String json = null;
        try {
            json = jacksonObjectMapper.writeValueAsString(recommendation);
        } catch (JsonProcessingException e) {
            throw new YSBizException(e);
        }

        ec.setData(json);

        return ec;
    }

    @RequestMapping(value = "/recReq/save", method = RequestMethod.POST)
    public ModelAndView recommendationSave(Recommendation recommendation, BindingResult bindingResult, ModelAndView mv) {
        if (bindingResult.hasErrors()) {
            System.err.println(bindingResult);
        }
        mv.setViewName("application/recReqEdit");
        recommendation.setRecKey(getEncryptedRecKey(recommendation));
        ExecutionContext ec = recommendationService.saveRecommendationRequest(recommendation);

        // TODO : DB 저장 후 model에 DB 내용을 채워서 편집 화면으로 이동
        Recommendation result = (Recommendation)ec.getData();
        mv.addObject("recommendation", result);
        mv.addObject("resultMsg", ec.getMessage());

        return mv;
    }

    @RequestMapping(value = "/recReq/cancel", method = RequestMethod.POST)
    public ModelAndView recommendationCancel(Recommendation recommendation, BindingResult bindingResult, ModelAndView mv) {
        if (bindingResult.hasErrors()) {
            System.err.println(bindingResult);
        }
        // TODO : DB cancel
        mv.setViewName("application/recReqList");

        ExecutionContext ec = recommendationService.deleteRecommendationRequest(recommendation);

        Recommendation result = (Recommendation)ec.getData();
        mv.addObject("recommendation", result);
        mv.addObject("resultMsg", ec.getMessage());

        return mv;
    }

    private String getEncryptedRecKey(Recommendation recommendation) {
        int applNo = recommendation.getApplNo();
        String profName = recommendation.getProfName();
        String profMailAddr = recommendation.getProfMailAddr();
        String input = applNo + ";" + profName + ";" + profMailAddr;

        String encrypted = null;
        try {
            encrypted = CryptoUtil.getCryptedString(context, input, true);
        } catch (IOException e) {
            throw new YSBizException(e);
        }
        return encrypted;
    }
}

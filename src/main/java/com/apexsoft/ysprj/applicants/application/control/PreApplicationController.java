package com.apexsoft.ysprj.applicants.application.control;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.exception.YSBizException;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.ysprj.applicants.admission.service.AdmissionService;
import com.apexsoft.ysprj.applicants.application.domain.*;
import com.apexsoft.ysprj.applicants.application.service.RecommendationService;
import com.apexsoft.ysprj.applicants.application.validator.RecommendationValidator;
import com.apexsoft.ysprj.applicants.common.util.CryptoUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

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
    private RecommendationValidator recommendationValidator;

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

    @Value("#{app['site.url']}")
    private String SITE_URL;

    @Value("#{app['recommendation.duedate']}")
    private String REC_DUE_DATE;

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

    /**
     * 추천서 요청 목록
     *
     * @param basis
     * @param mv
     * @return
     */
    @RequestMapping(value = "/recReq/list")
    public ModelAndView recommendationList(Basis basis, ModelAndView mv) {
        mv.setViewName("application/recReqList");
        Application application = basis.getApplication();
        int applNo = application.getApplNo();

        ExecutionContext ec = recommendationService.retrieveRecommendationList(applNo);

        mv.addObject("recommendationList", ec.getData());
        mv.addObject("applNo", applNo);
        return mv;
    }

    /**
     * 추천서 요청 작성
     *
     * @param recommendation
     * @param mv
     * @return
     */
    @RequestMapping(value = "/recReq/edit")
    public ModelAndView recommendationEdit(Recommendation recommendation, ModelAndView mv) {
        mv.setViewName("application/recReqEdit");
        int recNo = recommendation.getRecNo();
        if (recNo > 0) {
            ExecutionContext ec = recommendationService.retrieveRecommendation(recommendation.getRecNo());
            mv.addObject("recommendation", ec.getData());
        } else {
            mv.addObject("recommendation", recommendation);
        }

        return mv;
    }

    /**
     * 작성 중인 추천서 미리보기
     *
     * @param recommendation
     * @return
     */
    @RequestMapping(value = "/recReq/preview", method = RequestMethod.POST)
    @ResponseBody
    public ExecutionContext recommendationPreview(Recommendation recommendation) {

        ExecutionContext ec = new ExecutionContext();

        String encrypted = getEncryptedRecKey(recommendation);
        recommendation.setRecKey(encrypted);
        recommendation.setRecStsCode("00001");
        recommendation.setDueDate(REC_DUE_DATE);
        recommendationService.fillEtcInfo(recommendation);
        recommendation.setMailContents(makeLinkText(recommendation, true));

        String json = null;
        try {
            json = jacksonObjectMapper.writeValueAsString(recommendation);
        } catch (JsonProcessingException e) {
            throw new YSBizException(e);
        }

        ec.setData(json);

        return ec;
    }

    /**
     * 추천서 요청 저장
     *
     * @param recommendation
     * @param bindingResult
     * @param mv
     * @return
     */
    @RequestMapping(value = "/recReq/save", method = RequestMethod.POST)
    public ModelAndView recommendationSave(Recommendation recommendation,
                                           BindingResult bindingResult,
                                           ModelAndView mv) {

        recommendationValidator.validate(recommendation, bindingResult);
        mv.setViewName("application/recReqEdit");
        if (bindingResult.hasErrors()) {
            mv.addObject("resultMsg", MessageResolver.getMessage("U334"));
            return mv;
        }

        recommendation.setRecKey(getEncryptedRecKey(recommendation));
        recommendation.setDueDate(REC_DUE_DATE);
        ExecutionContext ec = recommendationService.saveRecommendationRequest(recommendation);

        Recommendation result = (Recommendation)ec.getData();
        mv.addObject("recommendation", result);
        mv.addObject("resultMsg", ec.getMessage());

        return mv;
    }

    /**
     * 추천서 요청 취소 -> DB 에서 삭제
     *
     * @param recommendation
     * @param mv
     * @return
     */
    @RequestMapping(value = "/recReq/cancel", method = RequestMethod.POST)
    public ModelAndView recommendationCancel(Recommendation recommendation, ModelAndView mv) {
        mv.setViewName("application/recReqList");

        ExecutionContext ec = recommendationService.deleteRecommendationRequest(recommendation);
        ExecutionContext ec2 = recommendationService.retrieveRecommendationList(recommendation.getApplNo());

        mv.addObject("recommendationList", ec2.getData());
        mv.addObject("resultMsg", ec.getMessage());
        mv.addObject("applNo", recommendation.getApplNo());

        return mv;
    }

    @RequestMapping(value = "/recReq/send", method = RequestMethod.POST)
    public ModelAndView recommendationSend(Recommendation recommendation, BindingResult bindingResult, ModelAndView mv) {
        recommendationValidator.validate(recommendation, bindingResult);
        mv.setViewName("application/recReqList");
        if (bindingResult.hasErrors()) {
            mv.addObject("resultMsg", MessageResolver.getMessage("U334"));
            return mv;
        }

        String encrypted = getEncryptedRecKey(recommendation);
        recommendation.setRecKey(encrypted);
        recommendation.setDueDate(REC_DUE_DATE);
        recommendationService.fillEtcInfo(recommendation);
        recommendation.setMailContents(makeLinkText(recommendation, false));

        // DB 저장 후 메일 보내기
        ExecutionContext ec = recommendationService.sendRecommendationRequest(recommendation);
        ExecutionContext ec2 = recommendationService.retrieveRecommendationList(recommendation.getApplNo());

        mv.addObject("recommendationList", ec2.getData());
        mv.addObject("resultMsg", ec.getMessage());
        mv.addObject("applNo", recommendation.getApplNo());

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
//System.err.println("encrypted Length : " + encrypted.length());
        } catch (IOException e) {
            throw new YSBizException(e);
        }
        return encrypted;
    }

    private String makeLinkText(Recommendation recommendation, boolean isPreview) {
        String splitLiner = MessageResolver.getMessage("L06534"); // ------
        String whoAmI = MessageResolver.getMessage("U06701"); // 안녕하십니까. 연세대학교 대학원 입학 신청 시스템입니다.
        String linkInfoText = MessageResolver.getMessage("U06504"); // 아래의 링크를 ...
        String contextPath = context.getContextPath();
        String linkText = SITE_URL + contextPath + "/application/recommend?key=" + recommendation.getRecKey();
        String br = "<br/>";
        String NEW_LINE1 = "\n";
        String NEW_LINE2 = "\n\n";
        String linkAnchorText = new StringBuilder()
                                    .append("<a href='")
                                    .append(linkText)
                                    .append("' target='_blank'>")
                                    .append(linkText)
                                    .append("</a>")
                                    .toString();
        StringBuilder sb = null;

        if (isPreview) {
            sb = new StringBuilder()
                    .append(MessageResolver.getMessage("MAIL_REQUEST_RECOMMENDATION_HEADER01"))
                    .append(NEW_LINE2)
                    .append(MessageResolver.getMessage("MAIL_REQUEST_RECOMMENDATION_HEADER02"))
                    .append(NEW_LINE1)
                    .append(MessageResolver.getMessage("MAIL_REQUEST_RECOMMENDATION_HEADER03",
                            new Object[]{recommendation.getDueDate()}))
                    .append(NEW_LINE2)
                    .append(MessageResolver.getMessage("MAIL_REQUEST_RECOMMENDATION_BODY_INFO_TITLE"))
                    .append(NEW_LINE1)
                    .append(MessageResolver.getMessage("MAIL_REQUEST_RECOMMENDATION_BODY_INFO_NAME",
                            new Object[]{recommendation.getApplicantName()}))
                    .append(NEW_LINE1)
                    .append(MessageResolver.getMessage("MAIL_REQUEST_RECOMMENDATION_BODY_INFO_NATIONALITY",
                            new Object[]{recommendation.getApplicantNationality()}))
                    .append(NEW_LINE1)
                    .append(MessageResolver.getMessage("MAIL_REQUEST_RECOMMENDATION_BODY_INFO_DEGREE",
                            new Object[]{recommendation.getDegree()}))
                    .append(NEW_LINE1)
                    .append(MessageResolver.getMessage("MAIL_REQUEST_RECOMMENDATION_BODY_INFO_MAJOR",
                            new Object[]{recommendation.getMajor()}))
                    .append(NEW_LINE2)
                    .append(MessageResolver.getMessage("MAIL_REQUEST_RECOMMENDATION_BODY_LETTER_TITLE"))
                    .append(NEW_LINE1)
                    .append(MessageResolver.getMessage("MAIL_REQUEST_RECOMMENDATION_BODY_LETTER_CONTENTS",
                            new Object[]{recommendation.getReqText()}))
                    .append(NEW_LINE2)
                    .append(MessageResolver.getMessage("MAIL_REQUEST_RECOMMENDATION_BODY_LINK_NOTICE"))
                    .append(NEW_LINE2)
                    .append(MessageResolver.getMessage("MAIL_REQUEST_RECOMMENDATION_BODY_LINK",
                            new Object[]{linkAnchorText}));
        } else {
            sb = new StringBuilder()
                    .append(MessageResolver.getMessage("MAIL_REQUEST_RECOMMENDATION_HEADER01"))
                    .append(NEW_LINE2)
                    .append(MessageResolver.getMessage("MAIL_REQUEST_RECOMMENDATION_HEADER02"))
                    .append(NEW_LINE1)
                    .append(MessageResolver.getMessage("MAIL_REQUEST_RECOMMENDATION_HEADER03",
                            new Object[]{recommendation.getDueDate()}))
                    .append(NEW_LINE2)
                    .append(MessageResolver.getMessage("MAIL_REQUEST_RECOMMENDATION_BODY_INFO_TITLE"))
                    .append(NEW_LINE1)
                    .append(MessageResolver.getMessage("MAIL_REQUEST_RECOMMENDATION_BODY_INFO_NAME",
                            new Object[]{recommendation.getApplicantName()}))
                    .append(NEW_LINE1)
                    .append(MessageResolver.getMessage("MAIL_REQUEST_RECOMMENDATION_BODY_INFO_NATIONALITY",
                            new Object[]{recommendation.getApplicantNationality()}))
                    .append(NEW_LINE1)
                    .append(MessageResolver.getMessage("MAIL_REQUEST_RECOMMENDATION_BODY_INFO_DEGREE",
                            new Object[]{recommendation.getDegree()}))
                    .append(NEW_LINE1)
                    .append(MessageResolver.getMessage("MAIL_REQUEST_RECOMMENDATION_BODY_INFO_MAJOR",
                            new Object[]{recommendation.getMajor()}))
                    .append(NEW_LINE2)
                    .append(MessageResolver.getMessage("MAIL_REQUEST_RECOMMENDATION_BODY_LETTER_TITLE"))
                    .append(NEW_LINE1)
                    .append(MessageResolver.getMessage("MAIL_REQUEST_RECOMMENDATION_BODY_LETTER_CONTENTS",
                            new Object[]{recommendation.getReqText()}))
                    .append(NEW_LINE2)
                    .append(MessageResolver.getMessage("MAIL_REQUEST_RECOMMENDATION_BODY_LINK_NOTICE"))
                    .append(NEW_LINE2)
                    .append(MessageResolver.getMessage("MAIL_REQUEST_RECOMMENDATION_BODY_LINK",
                            new Object[]{linkText}));
        }

        return sb.toString();
    }
}

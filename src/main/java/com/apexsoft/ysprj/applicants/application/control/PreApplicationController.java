package com.apexsoft.ysprj.applicants.application.control;

import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.ysprj.applicants.admission.service.AdmissionService;
import com.apexsoft.ysprj.applicants.application.domain.CustomMyList;
import com.apexsoft.ysprj.applicants.application.domain.ParamForApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.List;

/**
 * Created by hanmomhanda on 15. 2. 14.
 */
@Controller
@RequestMapping(value="/application")
public class PreApplicationController {

    @Autowired
    private AdmissionService admissionService;

    @Autowired
    private CommonDAO commonDAO;

    @Resource(name = "messageResolver")
    MessageResolver messageResolver;

    @Value("#{app['adms.general']}")
    private String admsGeneral;

    @Value("#{app['adms.foreign']}")
    private String admsForeign;

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
}

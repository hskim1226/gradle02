package com.apexsoft.ysprj.applicants.notice.control;

import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.ysprj.applicants.admission.service.AdmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by hanmomhanda on 15. 1. 6.
 */
@Controller
@RequestMapping(value="/notice")
public class NoticeController {

    @Autowired
    private AdmissionService admissionService;

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
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView showList() {
        ModelAndView model = new ModelAndView("notice/list");

        model.addObject("admsGeneral", admissionService.retrieveAdmissionByAdmsNo(admsGeneral));
        model.addObject("admsForeign", admissionService.retrieveAdmissionByAdmsNo(admsForeign));

        return model;
    }

    /**
     * 일반 전형
     *
     * @return
     */
    @RequestMapping(value = "/general", method = RequestMethod.POST)
    public ModelAndView showGeneral() {
        ModelAndView model = new ModelAndView("notice/general");

        model.addObject("admsGeneral", admissionService.retrieveAdmissionByAdmsNo(admsGeneral));

        return model;
    }

    /**
     * 외국인 전형
     *
     * @return
     */
    @RequestMapping(value = "/foreign", method = RequestMethod.POST)
    public ModelAndView showForeign() {
        ModelAndView model = new ModelAndView("notice/foreign");

        model.addObject("admsForeign", admissionService.retrieveAdmissionByAdmsNo(admsForeign));

        return model;
    }
}

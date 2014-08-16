package com.apexsoft.ysprj.application.web;

import com.apexsoft.ysprj.application.service.ApplicationService;
import com.apexsoft.ysprj.application.service.ApplicationVO;
import com.apexsoft.ysprj.application.service.SelfIntro;
import com.apexsoft.ysprj.application.service.StudyPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

/**
 * Created by hanmomhanda on 14. 8. 6.
 */
@Controller
@RequestMapping(value="/application")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @RequestMapping(value="/create", method = RequestMethod.POST)
    public String createApplication() {
        return "application/create";
    }

    @RequestMapping(value="/mylist")
    public String myApplicationList() {
        return "application/mylist";
    }

    @RequestMapping(value="/agreement")
    public String checkAgreement() {
        return "application/agreement";
    }

    @RequestMapping(value="/selfintro")
    public String selfIntro() {
        // TODO DB에 내용 있으면 가져와서 보여주고 없으면 공란
        return "application/selfintro";
    }

    @RequestMapping(value="/selfintro/save")
    public String saveSelfIntro() {
        // TODO DB에 저장, AJAX로 처리
        return "application/selfintro";
    }

    @RequestMapping(value="/studyplan")
    public String studyPlan() {
        // TODO DB에 내용 있으면 가져와서 보여주고 없으면 공란
        return "application/studyplan";
    }

    @RequestMapping(value="/studyplan/save")
    public String saveStudyPlan() {
        // TODO DB에 저장, AJAX로 처리
        return "application/studyplan";
    }

    @RequestMapping(value = "/appinfo", method = RequestMethod.GET)
    public String displayAppInfo(Model model) {
        ApplicationVO applicationVO = applicationService.retrieveApplication("");
        SelfIntro selfIntro = new SelfIntro();
        selfIntro.setTa1("첫번째 텍스트에어리어입니다.");
        StudyPlan studyPlan = new StudyPlan();
        studyPlan.setTa2("두번째 텍스트에어리어입니다.");
        model.addAttribute("applicationVO", applicationVO);
        model.addAttribute("selfIntro", selfIntro);
        model.addAttribute("studyPlan", studyPlan);
        return "application/appinfo";
    }

    @RequestMapping(value="/tabsample")
    public String tabSample() {
        return "application/tabsample";
    }

}

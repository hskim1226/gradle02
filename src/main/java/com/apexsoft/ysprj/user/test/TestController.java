package com.apexsoft.ysprj.user.test;

import com.apexsoft.ysprj.user.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by hanmomhanda on 14. 8. 20.
 */
@Controller
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    private TestService testService;

    @Autowired
    private ApplicationService applicationService;

    @RequestMapping(value="/tabsample")
    public String tabSample() {
        return "test/tabsample";
    }

    @RequestMapping(value = "/showAll")
    public String showEntireApplication() {
        return "test/showEntireApplication";
    }

    @RequestMapping(value = "/showApplication")
    public String showApplication(Model model) {

        model.addAttribute("application", applicationService.retrieveApplication(1));
        return "test/showApplication";
    }

    @RequestMapping(value = "/complexResultMapByOneQuery")
    public String showComplexResultMapByOneQuery(Model model) {

        model.addAttribute("application", testService.retrieveApplication());

        model.addAttribute("joinedApplication", testService.retrieveJoinedApplication());

        model.addAttribute("entireApplication", testService.retrieveEntireApplicationByOneQuery("a00001"));

        return "test/complexResultMap";
    }

    @RequestMapping(value = "/complexResultMapByNestedQuery")
    public String showComplexResultMapByNestedQuery(Model model) {

        model.addAttribute("application", testService.retrieveApplication());

        model.addAttribute("joinedApplication", testService.retrieveJoinedApplication());

        model.addAttribute("entireApplication", testService.retrieveEntireApplicationByNestedQuery("a00001"));

        return "test/complexResultMap";
    }

    @RequestMapping(value = "/checkNullValue")
    public String checkNullValue(@RequestParam String p1, @ModelAttribute String p2) {

        System.out.println("["+p1+"]");
        System.out.println("["+p2+"]");

        return "test/checkNullValue";
    }
}

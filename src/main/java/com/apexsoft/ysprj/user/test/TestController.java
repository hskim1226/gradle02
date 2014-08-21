package com.apexsoft.ysprj.user.test;

import com.apexsoft.ysprj.user.domain.Campus;
import com.apexsoft.ysprj.user.service.CampusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hanmomhanda on 14. 8. 20.
 */
@Controller
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    private TestService testService;

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

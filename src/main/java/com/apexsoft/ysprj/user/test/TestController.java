package com.apexsoft.ysprj.user.test;

import com.apexsoft.ysprj.user.domain.Campus;
import com.apexsoft.ysprj.user.service.CampusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
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
    private CampusService campusService;

    @Autowired
    private TestService testService;

    @RequestMapping(value = "/campusList")
    public ModelAndView campusList() {

        ModelAndView modelAndView = new ModelAndView("test/formTest");
        List<Campus> campusList = campusService.retriveCampusList();
        Map<String, String> campusMap = new HashMap<String, String>();
        for( Campus campus : campusList ) {
            campusMap.put(campus.getCampCode(), campus.getCampName());
        }

        modelAndView.addObject("campusList", campusList);
        modelAndView.addObject("campusMap", campusMap);
        modelAndView.addObject("campus", new Campus());

        return modelAndView;
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
}

package com.apexsoft.ysprj.user.web;

import com.apexsoft.ysprj.user.domain.Campus;
import com.apexsoft.ysprj.user.service.CampusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
}

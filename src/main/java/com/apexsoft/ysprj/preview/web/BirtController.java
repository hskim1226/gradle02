package com.apexsoft.ysprj.preview.web;

import com.apexsoft.ysprj.preview.service.BirtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2014-08-01.
 */
@Controller
@RequestMapping("/preview")
public class BirtController {
    @Autowired
    BirtService birtService;

    @RequestMapping(value = "/application")
    public ModelAndView getApplications(Model model) {
        try {
            Object applications = birtService.getApplications();
            model.addAttribute("applicationList", applications);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView("birtView");
    }

    @RequestMapping(value = "/application/{id}")
    public String getApplication(@PathVariable("id") String id) {
        return "birt/preview";
    }

}

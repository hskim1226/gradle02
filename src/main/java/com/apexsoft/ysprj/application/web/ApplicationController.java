package com.apexsoft.ysprj.application.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by hanmomhanda on 14. 8. 6.
 */
@Controller
@RequestMapping(value="/application")
public class ApplicationController {

    @RequestMapping(value="/create", method = RequestMethod.GET)
    public String createApplication() {
        return "application/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String completeApplication() {
        return "";
    }

    @RequestMapping(value="/mylist")
    public String myApplicationList() {
        return "application/mylist";
    }

}

package com.apexsoft.ysprj.application.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hanmomhanda on 14. 8. 6.
 */
@Controller
@RequestMapping(value="/application")
public class ApplicationController {

    @RequestMapping(value="/create")
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

}

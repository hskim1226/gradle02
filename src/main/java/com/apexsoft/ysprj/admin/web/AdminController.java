package com.apexsoft.ysprj.admin.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by hanmomhanda on 14. 8. 6.
 */
@Controller
@RequestMapping(value="/admin")
public class AdminController {

    @RequestMapping(value="/stats/daily")
    public String statsDaily() {
        return "admin/stats/daily";
    }

    @RequestMapping(value="/stats/category")
    public String statsCategory() {
        return "admin/stats/category";
    }

    @RequestMapping(value="/search/applicants")
    public String searchApplicants() {
        return "admin/search/applicants";
    }

    @RequestMapping(value="/stats/unpaid")
    public String statsUnpaid() {
        return "admin/stats/unpaid";
    }

    @RequestMapping(value="/modification/list")
    public String modificationList() {
        return "admin/modification/list";
    }

    @RequestMapping(value="/modification/request")
    public String modificationRequest() {
        return "admin/modification/request";
    }

    @RequestMapping(value="/modification/unit")
    public String modificationUnit() {
        return "admin/modification/unit";
    }

    @RequestMapping(value="/cancel/application")
    public String cancelApplication() {
        return "admin/cancel/application";
    }


}

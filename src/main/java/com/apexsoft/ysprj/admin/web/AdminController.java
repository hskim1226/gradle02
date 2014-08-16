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

    @RequestMapping(value="/dailystat")
    public String myApplicationList() {
        return "admin/dailystat";
    }

}

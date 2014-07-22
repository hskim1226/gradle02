package com.apexsoft.ysprj.user.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created with IntelliJ IDEA.
 * User: zbum
 * Date: 2014. 7. 22.
 * Time: 오전 10:41
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/admin/user")
public class UserManageController {

    @RequestMapping(method= RequestMethod.GET)
    public String displayUserManageList(){

        return "user/list";
    }
}

package com.apexsoft.ysprj.user.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created with IntelliJ IDEA.
 * User: zbum
 * Date: 2014. 7. 20.
 * Time: 오후 6:20
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(value="/user")
public class LoginController {

    @RequestMapping(value="/login", method= RequestMethod.GET)
    public String displayLoginForm( ) {
        return"user/login";
    }
}

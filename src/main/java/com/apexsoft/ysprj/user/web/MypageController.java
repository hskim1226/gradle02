package com.apexsoft.ysprj.user.web;

import com.apexsoft.ysprj.user.service.UsersService;
import com.apexsoft.ysprj.user.service.UsersVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by go2zo on 2014. 8. 10..
 */
@Controller
@RequestMapping(value = "/mypage")
public class MypageController {

    @Autowired
    private UsersService usersService;

    @RequestMapping(method = RequestMethod.GET)
    public String displayMypage() {
        return "user/detail";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String editAccount(UsersVO usersVO, HttpSession session) {
        UsersVO loginUsersVO = (UsersVO) session.getAttribute("user");

        return "mypage";
    }
}

package com.apexsoft.ysprj.user.web;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.ysprj.user.domain.Users;
import com.apexsoft.ysprj.user.service.UsersAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

/**
 * Created by go2zo on 2014. 8. 10..
 */
@Controller
@SessionAttributes("usersVO")
public class MypageController {

    @Autowired
    private UsersAccountService usersAccountService;

    @RequestMapping(value = "/mypage", method = RequestMethod.GET)
    public String displayMypage(ModelMap model, Principal principal) {
        String name = principal.getName();
        model.addAttribute("usersVO", usersAccountService.retrieveUser(name));
        return "user/detail";
    }

    @RequestMapping(value = "/mypage", method = RequestMethod.POST)
    @ResponseBody
    public ExecutionContext editAccount(@ModelAttribute @Valid Users users, BindingResult bindingResult) {
        if( bindingResult.hasErrors() ) {
            return new ExecutionContext( ExecutionContext.FAIL );
        }
        if( usersAccountService.modifyUsers(users) != 1 ) {
            String message = bindingResult.toString();
            return new ExecutionContext( ExecutionContext.FAIL, message );
        }
        return new ExecutionContext();
    }
}

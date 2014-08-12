package com.apexsoft.ysprj.user.web;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.ysprj.user.service.SignUpService;
import com.apexsoft.ysprj.user.service.UsersVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;

/**
 * Created with IntelliJ IDEA.
 * User: zbum
 * Date: 2014. 7. 21.
 * Time: 오후 3:13
 * To change this template use File | Settings | File Templates.
 */
@Controller
@SessionAttributes("usersVO")
@RequestMapping(value="/user")
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @RequestMapping(value="/signup", method= RequestMethod.GET)
    public String displaySignUpForm( ) {
        return"user/signup";
    }

    @RequestMapping(value="/signup", method= RequestMethod.POST)
    @ResponseBody
    public ExecutionContext signUp( @Valid UsersVO usersVO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return new ExecutionContext(ExecutionContext.FAIL);
        }
        return signUpService.registerUser(usersVO);
    }

    @RequestMapping(value="/available", method= RequestMethod.GET)
    @ResponseBody
    public ExecutionContext checkAvailable(UsersVO usersVO) {
        return signUpService.checkAvailable(usersVO);
    }
}

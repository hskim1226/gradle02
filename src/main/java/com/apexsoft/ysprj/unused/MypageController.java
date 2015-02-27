package com.apexsoft.ysprj.unused;

import com.apexsoft.ysprj.user.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * Created by go2zo on 2014. 8. 10..
 */
//@Controller
@SessionAttributes("usersVO")
@Deprecated
public class MypageController {

    @Autowired
    private UserAccountService userAccountService;

    @RequestMapping(value = "/mypageXXX", method = RequestMethod.GET)
    public String displayMypage(ModelMap model, Principal principal) {
        String name = principal.getName();
        model.addAttribute("usersVO", userAccountService.retrieveUser(name));
        return "user/detail";
    }

//    @RequestMapping(value = "/mypageXXX", method = RequestMethod.POST)
//    @ResponseBody
//    public ExecutionContext editAccount(@ModelAttribute @Valid User users, BindingResult bindingResult) {
//        if( bindingResult.hasErrors() ) {
//            return new ExecutionContext( ExecutionContext.FAIL );
//        }
//        if( userAccountService.modifyUser(users) != 1 ) {
//            String message = bindingResult.toString();
//            return new ExecutionContext( ExecutionContext.FAIL, message );
//        }
//        return new ExecutionContext();
//    }
}

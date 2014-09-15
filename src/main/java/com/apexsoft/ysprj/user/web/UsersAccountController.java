package com.apexsoft.ysprj.user.web;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.ysprj.user.domain.Users;
import com.apexsoft.ysprj.user.service.UsersAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: go2zo
 * Date: 2014-08-21
 * Time: 오후 4:32
 */
@Controller
@RequestMapping(value = "/user")
public class UsersAccountController {

    @Autowired
    private ServletContext context;

    @Autowired
    private UsersAccountService usersAccountService;

    @Autowired
    private MessageResolver messageResolver;

    @RequestMapping(value="/login", method= RequestMethod.GET)
    public String displayLoginForm() {
        return "user/login";
    }

    @RequestMapping(value = "/agreement", method = RequestMethod.GET)
    public String displaySignupAgreementForm(Model model, HttpServletResponse response) {
        Map<String, String> contentFiles = new HashMap<String, String>();
        contentFiles.put("terms-of-service", "/WEB-INF/terms-of-service.txt");
        contentFiles.put("privacy-policy1", "/WEB-INF/privacy-policy1.txt");
        contentFiles.put("privacy-policy2", "/WEB-INF/privacy-policy2.txt");
        contentFiles.put("privacy-policy3", "/WEB-INF/privacy-policy3.txt");

        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        StringBuffer buffer = new StringBuffer();
        String content = "";
        try {
            for(String key : contentFiles.keySet() ) {
                InputStream inputStream = context.getResourceAsStream(contentFiles.get(key));
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
                while( (content = bufferedReader.readLine()) != null ) {
                    content = content.trim();
                    if( content != "" ) {
                        buffer.append(content).append("\n");
                    }
                }
                model.addAttribute(key, buffer.toString());
                buffer.setLength(0);
                bufferedReader.close();
                inputStream.close();
            }
        }
        catch (Exception ignored) {
            // ignore
        }

        model.addAttribute("msg1", messageResolver.getMessage("U101"));
        model.addAttribute("msg2", messageResolver.getMessage("U102"));

        return "user/agreement";
    }

    @RequestMapping(value="/signup"/*, method= RequestMethod.POST*/)
    public String displaySignUpForm(@RequestParam("privInfoYn") String privInfoYn,
                                    @RequestParam("userAgreYn") String userAgreYn,
                                    @ModelAttribute("users") Users users,
                                    Model model) {
        users.setPrivInfoYn(privInfoYn);
        users.setUserAgreYn(userAgreYn);
        model.addAttribute("users", users);
        return"user/signup";
    }

    @RequestMapping(value="/signup/save", method= RequestMethod.POST)
    @ResponseBody
    public ExecutionContext signUp(@Valid Users users, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return new ExecutionContext(ExecutionContext.FAIL);
        }
        return usersAccountService.registerUser(users);
    }

    @RequestMapping(value="/idCheck", method= RequestMethod.GET)
    @ResponseBody
    public ExecutionContext idCheck(Users users) {
        return usersAccountService.isUserIdAvailable(users);
    }

    @RequestMapping(value = "/detail")
    public String detail(Model model, Principal principal) {
        Users users = usersAccountService.retrieveUser(principal.getName());
        model.addAttribute(users);
        return "user/detail";
    }

    @ModelAttribute("users")
    public Users initUsers() {
        return new Users();
    }

}

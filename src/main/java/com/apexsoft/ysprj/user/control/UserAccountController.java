package com.apexsoft.ysprj.user.control;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.ysprj.user.domain.Users;
import com.apexsoft.ysprj.user.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
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
public class UserAccountController {

    @Autowired
    private ServletContext context;

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private MessageResolver messageResolver;

    @RequestMapping(value="/login", method= RequestMethod.GET)
    public ModelAndView displayLoginForm(Users users,
                                   BindingResult bindingResult,
                                   ModelAndView mv,
                                   HttpServletRequest request) {
        mv.setViewName("user/login");
        if (bindingResult.hasErrors()) return mv;

        if (request.getAttribute("LOGIN_FAILURE") == Boolean.TRUE)
            mv.addObject("loginMessage", messageResolver.getMessage("U330"));

        return mv;
    }

    @RequestMapping(value = "/agreement", method = RequestMethod.GET)
    public String displaySignupAgreementForm(Model model, HttpServletResponse response) {
        Map<String, String> contentFiles = new HashMap<String, String>();
        contentFiles.put("terms-of-service", "/WEB-INF/terms-of-service.txt");
        contentFiles.put("privacy-policy1", "/WEB-INF/privacy-policy1.txt");
        contentFiles.put("privacy-policy2", "/WEB-INF/privacy-policy2.txt");
        contentFiles.put("privacy-policy3", "/WEB-INF/privacy-policy3.txt");

        BufferedReader bufferedReader;
        StringBuffer buffer = new StringBuffer();
        String content;
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
        return "user/signup";
    }

    @RequestMapping(value="/signup/save", method= RequestMethod.POST)
    public ModelAndView signUp(Users users, BindingResult bindingResult, ModelAndView mv) {
        mv.setViewName("user/login");
        ExecutionContext ec;
        if (bindingResult.hasErrors()){

        }
        int r = 0;
        ec = userAccountService.registerUserAndAuthority(users);
        mv.addObject("resultMsg", ec.getMessage());
        return mv;
    }

    @RequestMapping(value="/idCheck", method= RequestMethod.GET)
    @ResponseBody
    public ExecutionContext idCheck(Users users) {
        return userAccountService.isUserIdAvailable(users);
    }

    @RequestMapping(value = "/detail")
    public String detail(Model model, Principal principal) {
        Users users = userAccountService.retrieveUser(principal.getName());
        model.addAttribute(users);
        return "user/detail";
    }

    /**
     * 아이디 찾기 정보 입력 화면
     *
     * @param formData
     * @param mv
     * @return
     */
    @RequestMapping(value = "/findId")
    public ModelAndView showFindId(Users formData, ModelAndView mv) {
        mv.setViewName("user/findId");
        return mv;
    }

    /**
     * 아이디 찾기 처리
     * 아이디 있으면 아이디를 보여주는 화면으로
     * 아이디 없으면 아이디 찾기 정보 입력화면으로
     *
     * @param formData
     * @param bindingResult
     * @param mv
     * @return
     */
    @RequestMapping(value = "/getId", method = RequestMethod.POST)
    public ModelAndView findID(Users formData, BindingResult bindingResult, ModelAndView mv) {

//        findIdValidator.validate(formData, bindingResult);
        if (bindingResult.hasErrors()) {
            mv.addObject("resultMsg", messageResolver.getMessage("U334"));
            return mv;
        }

        ExecutionContext ec;
        ec = userAccountService.retrieveUserId(formData);

        if (ExecutionContext.SUCCESS.equals(ec.getResult())) {
            mv.setViewName("user/showId");
            Map<String, Object> map = (Map<String, Object>)ec.getData();
            mv.addAllObjects(map);
        } else {
            mv.setViewName("user/findId");
            mv.addObject("resultMsg", ec.getMessage());
        }

        return mv;
    }

    /**
     * 비밀번호 찾기 화면
     *
     * @param users
     * @param mv
     * @return
     */
    @RequestMapping(value = "/findPwd")
    public ModelAndView showFindPwd(Users users, ModelAndView mv) {
        mv.setViewName("user/findPwd");
        return mv;
    }

    /**
     * 비밀번호 찾기 처리
     * 해당 계정 있으면 비밀번호 재입력화면으로
     * 해당 계정 없으면 비밀번호 찾기 정보 입력화면으로
     *
     * @param formData
     * @param bindingResult
     * @param mv
     * @return
     */
    @RequestMapping(value = "/resetPwd", method = RequestMethod.POST)
    public ModelAndView findPwd(Users formData, BindingResult bindingResult, ModelAndView mv) {
        //        findIdValidator.validate(formData, bindingResult);
        if (bindingResult.hasErrors()) {
            mv.addObject("resultMsg", messageResolver.getMessage("U334"));
            return mv;
        }

        ExecutionContext ec;
        ec = userAccountService.retrievePwdLink(formData);

        if (ExecutionContext.SUCCESS.equals(ec.getResult())) {
            mv.setViewName("user/confirmPwd");
            Map<String, Object> map = (Map<String, Object>)ec.getData();
            mv.addAllObjects(map);
        } else {
            mv.setViewName("user/findPwd");
            mv.addObject("resultMsg", ec.getMessage());
        }

        return mv;
    }

    /**
     * 비밀번호 재설정
     *
     * @param formData
     * @param bindingResult
     * @param mv
     * @return
     */
    @RequestMapping(value = "/savePwd", method = RequestMethod.POST)
    public ModelAndView savePwd(Users formData, BindingResult bindingResult, ModelAndView mv) {
        //        findIdValidator.validate(formData, bindingResult);
        if (bindingResult.hasErrors()) {
            mv.addObject("resultMsg", messageResolver.getMessage("U334"));
            return mv;
        }

        int r1 = userAccountService.changePassword(formData);

        if (r1 == 1) {
            mv.setViewName("user/confirmPwd");
            mv.addObject("resultMsg", messageResolver.getMessage("U504"));
            mv.addObject("result", ExecutionContext.SUCCESS);
        } else {
            mv.setViewName("user/confirmPwd");
            mv.addObject("resultMsg", messageResolver.getMessage("U503"));
        }

        return mv;
    }

//    @ModelAttribute("users")
//    public Users initUsers() {
//        return new Users();
//    }

}

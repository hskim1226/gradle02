package com.apexsoft.ysprj.user.control;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.ysprj.applicants.common.util.WebUtil;
import com.apexsoft.ysprj.user.domain.User;
import com.apexsoft.ysprj.user.service.UserAccountService;
import com.apexsoft.ysprj.user.validator.UserModValidator;
import com.apexsoft.ysprj.user.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private UserValidator userValidator;

    @Autowired
    private UserModValidator userModValidator;

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private MessageResolver messageResolver;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private WebUtil webUtil;

    @RequestMapping(value="/login", method= RequestMethod.GET)
    public ModelAndView displayLoginForm(User user,
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

    @RequestMapping(value="/signup", method= RequestMethod.POST)
    public ModelAndView displaySignUpForm(@RequestParam("privInfoYn") String privInfoYn,
                                    @RequestParam("userAgreYn") String userAgreYn,
                                    @ModelAttribute("user") User user,
                                    HttpServletRequest request,
                                    BindingResult bindingResult,
                                    ModelAndView mv) {

        if (bindingResult.hasErrors()) {
            mv.setViewName("common/error");
            ExecutionContext ec = new ExecutionContext(ExecutionContext.FAIL);
            ec.setMessage(messageResolver.getMessage("U901"));
            ec.setErrCode("ERR9950");
            mv.addObject("ec", ec);

            return mv;
        }
        mv.setViewName("user/signup");
        webUtil.blockGetMethod(request, user.getUserId());
        user.setPrivInfoYn(privInfoYn);
        user.setUserAgreYn(userAgreYn);
        mv.addObject("user", user);
        return mv;
    }

    @RequestMapping(value="/signup/save", method= RequestMethod.POST)
    public ModelAndView signUp(User user, BindingResult bindingResult, ModelAndView mv) {
        userValidator.validate(user, bindingResult);
        mv.setViewName("user/signupok");
        ExecutionContext ec;
        if (bindingResult.hasErrors()){
            mv.setViewName("user/signup");
            mv.addObject("resultMsg", messageResolver.getMessage("U334"));
            return mv;
        }
        String encryptedPswd = encoder.encode(user.getPswd());
        user.setPswd(encryptedPswd);
        ec = userAccountService.registerUserAndAuthority(user);
        mv.addObject("resultMsg", ec.getMessage());
        return mv;
    }

    @RequestMapping(value="/idCheck", method= RequestMethod.GET)
    @ResponseBody
    public ExecutionContext idCheck(User user) {
        return userAccountService.isUserIdAvailable(user);
    }

//    @RequestMapping(value = "/detail")
//    public String detail(Model model, Principal principal) {
//        User user = userAccountService.retrieveUser(principal.getName());
//        model.addAttribute(user);
//        return "unused/showDetail";
//    }

    /**
     * 아이디 찾기 정보 입력 화면
     *
     * @param formData
     * @param mv
     * @return
     */
    @RequestMapping(value = "/findId")
    public ModelAndView showFindId(User formData, ModelAndView mv) {
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
    public ModelAndView findID(User formData,
                               BindingResult bindingResult,
                               HttpServletRequest request,
                               ModelAndView mv) {
        webUtil.blockGetMethod(request, formData.getUserId());
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
     * @param user
     * @param mv
     * @return
     */
    @RequestMapping(value = "/findPwd")
    public ModelAndView showFindPwd(User user, ModelAndView mv) {
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
    public ModelAndView findPwd(User formData, BindingResult bindingResult, ModelAndView mv) {
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
    public ModelAndView savePwd(User formData, BindingResult bindingResult, ModelAndView mv) {
        //        findIdValidator.validate(formData, bindingResult);
        if (bindingResult.hasErrors()) {
            mv.addObject("resultMsg", messageResolver.getMessage("U334"));
            return mv;
        }
        String encryptedPswd = encoder.encode(formData.getPswd());
        formData.setPswd(encryptedPswd);
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

    /**
     * 개인 정보 확인
     *
     * @return
     */
    @RequestMapping(value = "/info")
    public String showCheckPassword() {
        return "user/checkPwd";
    }

    /**
     * 개인 정보 수정을 위한 비밀번호 입력 화면
     *
     * @param user
     * @param mv
     * @return
     */
    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public ModelAndView checkPassword(User user,
                                      Principal principal,
                                      ModelAndView mv) {
        user.setUserId(principal.getName());
        ExecutionContext ec = userAccountService.checkPwd(user);
        if (ExecutionContext.SUCCESS.equals(ec.getResult())) {
            mv.setViewName("user/showDetail");
            mv.addObject("user", ec.getData());
        } else {
            mv.setViewName("user/checkPwd");
            mv.addObject("resultMsg", ec.getMessage());
        }

        return mv;
    }

    /**
     * 개인 정보 수정
     *
     * @param user
     * @param mv
     * @return
     */
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public ModelAndView modifyUserInfo(User user,
                                       BindingResult bindingResult,
                                       ModelAndView mv) {
        userModValidator.validate(user, bindingResult);
        mv.setViewName("user/showDetail");
        if (bindingResult.hasErrors()){
            mv.addObject("resultMsg", messageResolver.getMessage("U334"));
            return mv;
        }
        user.setEnabled(true);
        ExecutionContext ec = userAccountService.modifyUser(user);
        mv.addObject("resultMsg", ec.getMessage());
        return mv;
    }

    /**
     * 비밀 번호 변경 요청
     *
     * @return
     */
    @RequestMapping(value = "/changePwd", method = RequestMethod.POST)
    public String showPasswdView() {
        return "user/modifyPwd";
    }

    /**
     * 현재 비밀 번호 체크
     *
     * @return
     */
    @RequestMapping(value = "/checkCurrPwd", method = RequestMethod.POST)
    public ModelAndView checkCurrPwd(User user, Principal principal, ModelAndView mv) {

        user.setUserId(principal.getName());
        ExecutionContext ec = userAccountService.checkPwd(user);
        if (ExecutionContext.SUCCESS.equals(ec.getResult())) {
            mv.setViewName("user/confirmPwd");
            mv.addObject("user", ec.getData());
        } else {
            mv.setViewName("user/modifyPwd");
            mv.addObject("resultMsg", ec.getMessage());
        }

        return mv;
    }

    @RequestMapping(value="/adminLogin", method= RequestMethod.GET)
    public ModelAndView displayAdminLoginForm(User user,
                                         BindingResult bindingResult,
                                         ModelAndView mv,
                                         HttpServletRequest request) {
        webUtil.blockGetMethod(request, user.getUserId());
        mv.setViewName("user/adminLogin");
        if (bindingResult.hasErrors()) return mv;

        if (request.getAttribute("LOGIN_FAILURE") == Boolean.TRUE)
            mv.addObject("loginMessage", messageResolver.getMessage("U330"));

        return mv;
    }

}

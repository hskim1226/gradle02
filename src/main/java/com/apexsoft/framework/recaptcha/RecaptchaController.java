package com.apexsoft.framework.recaptcha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by hanmomhanda on 14. 7. 28.
 */
@Controller
@RequestMapping("/recaptcha")
public class RecaptchaController {

    @Autowired
    private RecaptchaService recaptchaService;

    @RequestMapping("/show")
    public String showRecaptcha() {
        return "recaptcha/show";
    }

    @RequestMapping("/verify")
    @ResponseBody
    public String verifyRecaptcha(HttpServletRequest request) {
        return recaptchaService.verify(request);
    }
}

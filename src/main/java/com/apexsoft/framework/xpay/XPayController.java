package com.apexsoft.framework.xpay;

import com.apexsoft.framework.recaptcha.RecaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by hanmomhanda on 14. 7. 28.
 */
@Controller
@RequestMapping("/pay")
public class XPayController {

    //@Autowired
    //private RecaptchaService recaptchaService;

    @RequestMapping("/request")
    public String forwardPayment() {
        return "xpay/payreq";
    }
}

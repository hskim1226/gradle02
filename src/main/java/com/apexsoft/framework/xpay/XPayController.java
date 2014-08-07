package com.apexsoft.framework.xpay;

import com.apexsoft.framework.recaptcha.RecaptchaService;
import com.springcryptoutils.core.digest.Digester;
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

    @Autowired
    private Digester digester;

    String LGD_MID;
    String LGD_MERTKEY;
    String LGD_CUSTOM_SKIN;
    String LGD_WINDOW_VER;
    String LGD_CUSTOM_PROCESSTYPE;
    String LGD_VERSION;

    @RequestMapping("/request")
    public String processXPay(HttpServletRequest request) {

        String LGD_OID;
        String LGD_AMOUNT;
        String LGD_BUYER;
        String LGD_PRODUCTINFO;
        String LGD_BUYEREMAIL;
        String LGD_TIMESTAMP;
        String LGD_BUYERID;
        String LGD_BUYERIP;
        String LGD_HASHDATA;

        String digest = digester.digest("eat me!");

        return "xpay/payreq";
    }
}

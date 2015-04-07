package com.apexsoft.ysprj.applicants.payment.control;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.exception.ErrorInfo;
import com.apexsoft.framework.exception.YSBizException;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.ysprj.applicants.application.domain.CustomMyList;
import com.apexsoft.ysprj.applicants.application.domain.ParamForApplication;
import com.apexsoft.ysprj.applicants.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cosb071 on 15. 4. 7.
 */
@Controller
@RequestMapping(value = "/payment/admin")
public class PaymentAdminController {

    @Resource(name = "messageResolver")
    MessageResolver messageResolver;

    @Autowired
    private PaymentService paymentService;

    @RequestMapping(value="/paymanual")
    public ModelAndView paymanual(Principal principal, ModelAndView mv) {

        mv.setViewName("xpay/paymanual");

        String adminID = principal.getName();
        if (!adminID.equals("Apex1234")) {
            ExecutionContext ec = new ExecutionContext(ExecutionContext.FAIL);
            ec.setMessage(messageResolver.getMessage("U902"));
            ec.setErrCode("ERR0801");
            Map<String, String> errorInfo = new HashMap<String, String>();
            errorInfo.put("adminID", adminID);
            ec.setErrorInfo(new ErrorInfo(errorInfo));
            throw new YSBizException(ec);
        }

        return mv;
    }

    @RequestMapping(value="/exepaymanual")
    public ModelAndView exepaymanually(Principal principal, ModelAndView mv) {

        mv.setViewName("xpay/paymanual");

        System.out.println("djkadkfakl");



        return mv;
    }



}

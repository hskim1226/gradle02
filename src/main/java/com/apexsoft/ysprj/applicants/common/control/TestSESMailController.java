package com.apexsoft.ysprj.applicants.common.control;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.mail.Mail;
import com.apexsoft.framework.mail.MailType;
import com.apexsoft.framework.mail.SESMailService;
import com.apexsoft.ysprj.applicants.common.domain.MailApplicationCompleted;
import com.apexsoft.ysprj.applicants.common.domain.MailContentsParamKey;
import com.apexsoft.ysprj.applicants.common.domain.MailDueNotification;
import com.apexsoft.ysprj.applicants.common.util.MailFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2014-08-01.
 */
@Controller
@RequestMapping("/application")
public class TestSESMailController {

    @Autowired
    MailFactory mailFactory;

    @Autowired
    SESMailService sesMailService;

    private static final Logger logger = LoggerFactory.getLogger(TestSESMailController.class);

    @RequestMapping(value = "/mail/sendApplicationCompleted/{to:.*}/{userName}/{userId}/{institute}/{major}/{applId}")
    @ResponseBody
    public ExecutionContext sendApplicationCompleted(@PathVariable("to") String mailAddr,
                                                     @PathVariable("userName") String userName,
                                                     @PathVariable("userId") String userId,
                                                     @PathVariable("institute") String instituteName,
                                                     @PathVariable("major") String major,
                                                     @PathVariable("applId") String applId
                                                     ) throws Exception {

        ExecutionContext ec = new ExecutionContext();
        String[] to = mailAddr.split(";");

        Mail mail = mailFactory.create(MailType.COMPLETE_NOTI);
        mail.setTo(to);
        mail.withContentsParam(MailContentsParamKey.USER_NAME, userName)
            .withContentsParam(MailContentsParamKey.USER_ID, userId)
            .withContentsParam(MailContentsParamKey.INSTITUTE_NAME, instituteName)
            .withContentsParam(MailContentsParamKey.MAJOR, major)
            .withContentsParam(MailContentsParamKey.APPL_ID, applId)
            .makeContents();

        sesMailService.sendMail(mail);
//        ec.setData(mailAddr);
        return ec;
    }


    @RequestMapping(value = "/mail/sendDueNoti")
    @ResponseBody
    public ExecutionContext sendDueNoti() throws Exception {

        ExecutionContext ec = new ExecutionContext();
        String toStr = "hanmomhanda@naver.com;dhoonkim@apexsoft.co.kr;cosb071@apexsoft.co.kr";
        String[] to = toStr.split(";");
//        String ccStr = "hanmomhanda@gmail.com";
//        String[] cc = ccStr.split(";");
//        String bccStr = "onetouch@apexsoft.co.kr";
//        String[] bcc = bccStr.split(";");

        Mail mail = mailFactory.create(MailType.DUE_NOTI);
        mail.setTo(to);
        mail.makeContents();
//        mail.setCc(cc);
//        mail.setBcc(bcc);

        sesMailService.sendMail(mail);
        return ec;
    }
}

package com.apexsoft.framework.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hanmomhanda on 14. 7. 25.
 */
@Controller
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private MailService mailService;

    @RequestMapping("/compose")
    public String composeMail() {
        return "mail/compose";
    }

    @RequestMapping("/send")
    public String sendMail(Mail mail) {
        // compose.jsp에서 메일 정보 가져와서 MailService 실행

        try {
            mailService.sendMail(mail);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "mail/result";
    }
}

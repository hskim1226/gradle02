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
    MailSender mailSender;

    @RequestMapping("/compose")
    public String composeMail() {
        return "/";
    }

    @RequestMapping("/send")
    public String sendMail() {
        return "/";
    }
}

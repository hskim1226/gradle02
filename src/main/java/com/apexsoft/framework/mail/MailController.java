package com.apexsoft.framework.mail;

import com.apexsoft.framework.common.vo.ExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @ResponseBody
    public ExecutionContext sendMail(Mail mail) {
        ExecutionContext ec = new ExecutionContext();

        // compose.jsp에서 메일 정보 가져와서 MailService 실행

        try {
            mailService.sendMail(mail);
        } catch (Exception e) {
            e.printStackTrace();
            ec.setResult(ExecutionContext.FAIL);
        }

        if (ExecutionContext.SUCCESS.equals(ec.getResult()))
            ec.setMessage("메일 발송 성공");
        else
            ec.setMessage("메일 발송 실패");
        return ec;
    }
}

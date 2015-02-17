package com.apexsoft.framework.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by hanmomhanda on 14. 7. 25.
 */
@Service("mailService")
public class MailServiceImpl implements MailService {

    @Autowired
    private MailSender mailSender;

    @Autowired
    private SimpleMailMessage preConfiguredMessage;

    @Override
    public void sendMail(Mail mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mail.getFrom());
        message.setTo(getArray(mail.getTo()));
        message.setCc(getArray(mail.getCc()));
        message.setBcc(getArray(mail.getBc()));
        message.setSubject(mail.getSubject());
        message.setText(mail.getContents());
        mailSender.send(message);
    }

    private String[] getArray(String addresses) {
        return getArray(addresses, ",");
    }

    private String[] getArray(String addresses, String delimiter) {
        List<String> addrArray = new ArrayList<String>();
        StringTokenizer stkn = new StringTokenizer(addresses, delimiter);
        while(stkn.hasMoreTokens()) {
            addrArray.add(stkn.nextToken());
        }
        return (String[])addrArray.toArray(new String[0]);
    }
}

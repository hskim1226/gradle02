package com.apexsoft.framework.mail;

/**
 * Created by hanmomhanda on 14. 7. 25.
 */
public interface MailService {
    public void sendMail(String to, String subject, String body);
}

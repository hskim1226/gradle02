package com.apexsoft.framework.mail;

/**
 * Created by hanmomhanda on 15. 5. 11.
 */
public interface SESMailService {

    void sendMail(Mail mail) throws Exception;
}

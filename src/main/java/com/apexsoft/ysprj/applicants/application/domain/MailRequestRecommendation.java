package com.apexsoft.ysprj.applicants.application.domain;

import com.apexsoft.framework.mail.Mail;

/**
 * Created by hanmomhanda on 15. 5. 12.
 */
public class MailRequestRecommendation extends Mail {

    @Override
    public void makeContents() {
        setContents(((Recommendation) getInfo()).getMailContents());
    }
}

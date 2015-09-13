package com.apexsoft.ysprj.applicants.application.domain;

import com.apexsoft.framework.mail.Mail;
import com.apexsoft.framework.message.MessageResolver;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;

/**
 * Created by hanmomhanda on 15. 9. 13.
 */
public class MailUrgeRecommendation extends Mail {

    @Value("#{app['institution.name.en']}")
    private String INST_NAME_EN;

    private final String NEW_LINE1 = "\n";
    private final String NEW_LINE2 = "\n\n";

    @Override
    public void makeContents() {
        Recommendation recommendation = (Recommendation) getInfo();
        Map<Object, String> contentsParam = getContentsParam();
        String contextPath = contentsParam.get("contextPath");
        String siteURL = contentsParam.get("siteURL");
        setContents(new StringBuilder()
                .append(MessageResolver.getMessage("MAIL_URGENCY_RECOMMENDATION_SUBJECT",
                        new Object[]{INST_NAME_EN}))
                .append(NEW_LINE1)
                .append(MessageResolver.getMessage("MAIL_REQUEST_RECOMMENDATION_HEADER01"))
                .toString());
    }
}

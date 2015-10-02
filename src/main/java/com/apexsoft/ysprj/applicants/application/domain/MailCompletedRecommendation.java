package com.apexsoft.ysprj.applicants.application.domain;

import com.apexsoft.framework.mail.Mail;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.ysprj.applicants.common.domain.MailContentsParamKey;
import org.springframework.beans.factory.annotation.Value;

import java.util.Locale;

/**
 * Created by hanmomhanda on 15. 5. 12.
 */
public class MailCompletedRecommendation extends Mail {

    private final String NEW_LINE1 = "\n";
    private final String NEW_LINE2 = "\n\n";
    private Locale locale = null;

    public MailCompletedRecommendation() {
    }

    public MailCompletedRecommendation(Locale locale) {
        this.locale = locale;
    }

    @Override
    public void makeContents() {
        setContents(new StringBuilder()
                .append(MessageResolver.getMessage("MAIL_COMPLETED_RECOMMENDATION_HEADER01"))
                .append(NEW_LINE1)
                .append(MessageResolver.getMessage("MAIL_COMPLETED_RECOMMENDATION_HEADER02"))
                .append(NEW_LINE2)
                .append(MessageResolver.getMessage("MAIL_COMPLETED_RECOMMENDATION_HEADER03",
                        new Object[]{getContentsParam().get(MailContentsParamKey.USER_NAME)}))
                .append(NEW_LINE2)
                .append(MessageResolver.getMessage("MAIL_COMPLETED_RECOMMENDATION_BODY01",
                        new Object[]{getContentsParam().get(MailContentsParamKey.PROF_NAME)}))
                .append(NEW_LINE2)
                .append(MessageResolver.getMessage("MAIL_COMPLETED_RECOMMENDATION_BODY02", new Object[]{MessageResolver.getMessage("MAIL_COMMON_SITE_URL")}))
                .append(NEW_LINE2)
                .append(MessageResolver.getMessage("MAIL_COMPLETED_RECOMMENDATION_FOOTER_01"))
                .append(NEW_LINE2)
                .append(MessageResolver.getMessage("MAIL_COMMON_SITE_URL"))
                .append(NEW_LINE2)
                .append(MessageResolver.getMessage("MAIL_COMMON_FOOTER_01"))
                .append(NEW_LINE2)
                .append(MessageResolver.getMessage("MAIL_COMMON_FOOTER_02"))
                .toString());
    }
}

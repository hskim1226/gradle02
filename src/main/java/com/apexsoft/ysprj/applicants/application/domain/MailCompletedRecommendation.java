package com.apexsoft.ysprj.applicants.application.domain;

import com.apexsoft.framework.mail.Mail;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.ysprj.applicants.common.domain.MailContentsParamKey;

/**
 * Created by hanmomhanda on 15. 5. 12.
 */
public class MailCompletedRecommendation extends Mail {

    private final String NEW_LINE1 = "\n";
    private final String NEW_LINE2 = "\n\n";

    @Override
    public void makeContents() {
        setContents(new StringBuilder()
                .append(MessageResolver.getMessage("MAIL_COMPLETED_RECOMMENDATION_HEADER01",
                        new Object[]{getContentsParam().get(MailContentsParamKey.USER_NAME)}))
                .append(NEW_LINE2)
                .append(MessageResolver.getMessage("MAIL_COMPLETED_RECOMMENDATION_BODY01",
                        new Object[]{getContentsParam().get(MailContentsParamKey.PROF_NAME)}))
                .append(NEW_LINE2)
                .append(MessageResolver.getMessage("MAIL_COMPLETED_RECOMMENDATION_BODY02"))
                .append(NEW_LINE2)
                .append(MessageResolver.getMessage("MAIL_COMMON_SITE_URL"))
                .append(NEW_LINE2)
                .append(MessageResolver.getMessage("MAIL_COMMON_FOOTER_01"))
                .append(NEW_LINE2)
                .append(MessageResolver.getMessage("MAIL_COMMON_FOOTER_02"))
                .toString());
    }
}

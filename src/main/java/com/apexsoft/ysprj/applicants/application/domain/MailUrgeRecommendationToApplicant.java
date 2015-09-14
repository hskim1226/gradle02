package com.apexsoft.ysprj.applicants.application.domain;

import com.apexsoft.framework.exception.YSBizException;
import com.apexsoft.framework.mail.Mail;
import com.apexsoft.framework.message.MessageResolver;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by hanmomhanda on 15. 9. 13.
 */
public class MailUrgeRecommendationToApplicant extends Mail {

    private final String NEW_LINE1 = "\n";
    private final String NEW_LINE2 = "\n\n";

    @Override
    public void makeContents() {
        Recommendation recommendation = (Recommendation) getInfo();
        Map<Object, String> contentsParam = getContentsParam();
        String dueTimeString = contentsParam.get("dueTime");
        String applicantName = contentsParam.get("applicantName");
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        Date dueTime = null;
//        try {
//            dueTime = df.parse(dueTimeString.split(" ")[0]);
//        } catch ( ParseException e ) {
//            throw new YSBizException(e);
//        }
//        Long daysLeft = (dueTime.getTime() - System.currentTimeMillis())/86400000;
        setContents(new StringBuilder()
                .append(MessageResolver.getMessage("MAIL_URGENCY_RECOMMENDATION_NOTICE_HEADER01",
                        new Object[]{applicantName}))
                .append(NEW_LINE1)
                .append(MessageResolver.getMessage("MAIL_URGENCY_RECOMMENDATION_NOTICE_HEADER02"))
                .append(NEW_LINE2)
                .append(MessageResolver.getMessage("MAIL_URGENCY_RECOMMENDATION_NOTICE_BODY_PROF_NAME",
                        new Object[]{recommendation.getProfName()}))
                .append(NEW_LINE1)
                .append(MessageResolver.getMessage("MAIL_URGENCY_RECOMMENDATION_NOTICE_BODY_PROF_INST",
                        new Object[]{recommendation.getProfInst()}))
                .append(NEW_LINE2)
                .append(MessageResolver.getMessage("MAIL_URGENCY_RECOMMENDATION_NOTICE_BODY01",
                        new Object[]{recommendation.getProfName()}))
                .append(NEW_LINE1)
                .append(MessageResolver.getMessage("MAIL_URGENCY_RECOMMENDATION_NOTICE_BODY02",
                        new Object[]{applicantName, dueTimeString}))
                .append(NEW_LINE2)
                .append(MessageResolver.getMessage("MAIL_COMMON_FOOTER_01"))
                .append(NEW_LINE1)
                .append(MessageResolver.getMessage("MAIL_COMMON_FOOTER_02"))
                .append(NEW_LINE1)
                .append(MessageResolver.getMessage("MAIL_COMMON_SITE_URL"))
                .toString());
    }
}

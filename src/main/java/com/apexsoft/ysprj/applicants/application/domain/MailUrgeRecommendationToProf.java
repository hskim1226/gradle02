package com.apexsoft.ysprj.applicants.application.domain;

import com.apexsoft.framework.exception.YSBizException;
import com.apexsoft.framework.mail.Mail;
import com.apexsoft.framework.message.MessageResolver;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * Created by hanmomhanda on 15. 9. 13.
 */
public class MailUrgeRecommendationToProf extends Mail {

    private final String NEW_LINE1 = "\n";
    private final String NEW_LINE2 = "\n\n";
    private Locale locale = null;

    public MailUrgeRecommendationToProf() {

    }

    public MailUrgeRecommendationToProf(Locale locale) {
        this.locale = locale;
    }

    @Override
    public void makeContents() {
        Recommendation recommendation = (Recommendation) getInfo();
        Map<Object, String> contentsParam = getContentsParam();
        String contextPath = contentsParam.get("contextPath");
        String siteURL = contentsParam.get("siteURL");
        String linkText = siteURL + contextPath +
                "/application/recommend?key=" + recommendation.getRecKey() +
                "&lang=en";
        String dueTimeString = contentsParam.get("dueTime");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dueTime = null;
        try {
            dueTime = df.parse(dueTimeString.split(" ")[0]);
        } catch ( ParseException e ) {
            throw new YSBizException(e);
        }
        Long daysLeft = (dueTime.getTime() - System.currentTimeMillis())/86400000;
        setContents(new StringBuilder()
                .append(MessageResolver.getMessage("MAIL_REQUEST_RECOMMENDATION_HEADER01"))
                .append(NEW_LINE1)
                .append(MessageResolver.getMessage("MAIL_REQUEST_RECOMMENDATION_HEADER02"))
                .append(NEW_LINE2)
                .append(MessageResolver.getMessage("MAIL_URGENCY_RECOMMENDATION_HEADER03"))
                .append(NEW_LINE2)
                .append(MessageResolver.getMessage("MAIL_URGENCY_RECOMMENDATION_HEADER04",
                        new Object[]{dueTimeString}))
//                .append(MessageResolver.getMessage("MAIL_REQUEST_RECOMMENDATION_BODY_INFO_TITLE"))
//                .append(NEW_LINE1)
//                .append(MessageResolver.getMessage("MAIL_REQUEST_RECOMMENDATION_BODY_INFO_NAME",
//                        new Object[]{recommendation.getApplicantName()}))
//                .append(NEW_LINE1)
//                .append(MessageResolver.getMessage("MAIL_REQUEST_RECOMMENDATION_BODY_INFO_NATIONALITY",
//                        new Object[]{recommendation.getApplicantNationality()}))
//                .append(NEW_LINE1)
//                .append(MessageResolver.getMessage("MAIL_REQUEST_RECOMMENDATION_BODY_INFO_DEGREE",
//                        new Object[]{recommendation.getDegree()}))
//                .append(NEW_LINE1)
//                .append(MessageResolver.getMessage("MAIL_REQUEST_RECOMMENDATION_BODY_INFO_MAJOR",
//                        new Object[]{recommendation.getMajor()}))
                .append(NEW_LINE2)
                .append(MessageResolver.getMessage("MAIL_REQUEST_RECOMMENDATION_BODY_LINK_NOTICE"))
                .append(NEW_LINE2)
                .append(MessageResolver.getMessage("MAIL_REQUEST_RECOMMENDATION_BODY_LINK",
                        new Object[]{linkText}))
                .append(NEW_LINE2)
                .append(MessageResolver.getMessage("MAIL_URGENCY_RECOMMENDATION_FOOTER_01"))
                .append(NEW_LINE2)
                .append(MessageResolver.getMessage("MAIL_URGENCY_RECOMMENDATION_FOOTER_02"))
                .append(NEW_LINE2)
                .append(MessageResolver.getMessage("MAIL_COMMON_FOOTER_01"))
                .append(NEW_LINE2)
                .append(MessageResolver.getMessage("MAIL_COMMON_FOOTER_02"))
                .append(NEW_LINE2)
                .append(MessageResolver.getMessage("MAIL_COMMON_SITE_URL"))
                .toString());
    }
}

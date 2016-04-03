package com.apexsoft.ysprj.applicants.application.domain;

import com.apexsoft.framework.mail.Mail;
import com.apexsoft.framework.message.MessageResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.ServletContext;
import java.util.Locale;
import java.util.Map;

/**
 * Created by hanmomhanda on 15. 5. 12.
 */
public class MailRequestRecommendation extends Mail {

    private Locale locale = null;

    public MailRequestRecommendation() {
        this.locale = Locale.KOREAN;
    }

    public MailRequestRecommendation(Locale locale) {
        this.locale = locale;
    }

    @Override
    public void makeContents() {
        Recommendation recommendation = (Recommendation) getInfo();
        Map<Object, String> contentsParam = getContentsParam();
//        String contextPath = contentsParam.get("contextPath");
        String siteURL = contentsParam.get("siteURL");
//        String linkText = siteURL + contextPath +
//                "/application/recommend?key=" + recommendation.getRecKey() +
//                "&lang=en";
        String linkText = getLinkText(siteURL, contentsParam, recommendation);
        String alternativeURL = contentsParam.get("alternativeURL");
//        String alternativeLinkText = alternativeURL + contextPath +
//                "/application/recommend?key=" + recommendation.getRecKey() +
//                "&lang=en";
        String alternativeLinkText = getLinkText(alternativeURL, contentsParam, recommendation);
        String NEW_LINE1 = "\n";
        String NEW_LINE2 = "\n\n";

        StringBuilder sb = new StringBuilder()
                .append(MessageResolver.getMessage("MAIL_REQUEST_RECOMMENDATION_HEADER01"))
                .append(NEW_LINE1)
                .append(MessageResolver.getMessage("MAIL_REQUEST_RECOMMENDATION_HEADER02"))
                .append(NEW_LINE2)
                .append(MessageResolver.getMessage("MAIL_REQUEST_RECOMMENDATION_HEADER03",
                        new Object[]{recommendation.getProfName()}))
                .append(NEW_LINE2)
                .append(MessageResolver.getMessage("MAIL_REQUEST_RECOMMENDATION_HEADER04"))
                .append(NEW_LINE2)
                .append(MessageResolver.getMessage("MAIL_REQUEST_RECOMMENDATION_BODY_INFO_TITLE"))
                .append(NEW_LINE1)
                .append(MessageResolver.getMessage("MAIL_REQUEST_RECOMMENDATION_BODY_INFO_NAME",
                        new Object[]{recommendation.getApplicantName()}))
                .append(NEW_LINE1)
                .append(MessageResolver.getMessage("MAIL_REQUEST_RECOMMENDATION_BODY_INFO_NATIONALITY",
                        new Object[]{recommendation.getApplicantNationality()}))
                .append(NEW_LINE1)
                .append(MessageResolver.getMessage("MAIL_REQUEST_RECOMMENDATION_BODY_INFO_DEGREE",
                        new Object[]{recommendation.getDegree()}))
                .append(NEW_LINE1)
                .append(MessageResolver.getMessage("MAIL_REQUEST_RECOMMENDATION_BODY_INFO_MAJOR",
                        new Object[]{recommendation.getMajor()}))
                .append(NEW_LINE2)
                .append(MessageResolver.getMessage("MAIL_REQUEST_RECOMMENDATION_BODY_DEADLINE",
                        new Object[]{recommendation.getDueDate()}))
                .append(NEW_LINE2)
                .append(MessageResolver.getMessage("MAIL_REQUEST_RECOMMENDATION_BODY_LINK_NOTICE"))
                .append(NEW_LINE2)
                .append(MessageResolver.getMessage("MAIL_REQUEST_RECOMMENDATION_BODY_LINK",
                        new Object[]{ linkText }))
                .append(NEW_LINE2)
                .append(MessageResolver.getMessage("MAIL_REQUEST_RECOMMENDATION_BODY_ALTERNATIVE_LINK",
                        new Object[]{ alternativeLinkText }))
                .append(NEW_LINE2)
                .append(MessageResolver.getMessage("MAIL_REQUEST_RECOMMENDATION_FOOTER_01"))
                .append(NEW_LINE2)
                .append(MessageResolver.getMessage("MAIL_REQUEST_RECOMMENDATION_FOOTER_02"))
                .append(NEW_LINE2)
                .append(MessageResolver.getMessage("MAIL_COMMON_FOOTER_01"))
                .append(NEW_LINE1)
                .append(MessageResolver.getMessage("MAIL_COMMON_FOOTER_02"))
                .append(NEW_LINE1)
                .append(MessageResolver.getMessage("MAIL_COMMON_SITE_URL"));
        setContents(sb.toString());
    }

    private String getLinkText(String url, Map<Object, String> contentsParam, Recommendation recommendation) {
        String contextPath = contentsParam.get("contextPath");
        return url + contextPath +
                "/application/recommend?key=" + recommendation.getRecKey() +
                "&lang=en";
    }
}

package com.apexsoft.ysprj.applicants.common.domain;

import com.apexsoft.framework.mail.Mail;
import com.apexsoft.framework.message.MessageResolver;

import java.util.Locale;

/**
 * Created by hanmomhanda on 15. 5. 12.
 */
public class MailApplicationCompleted extends Mail {

    private final String NEW_LINE1 = "\n";
    private final String NEW_LINE2 = "\n\n";
    private Locale locale = null;

    public MailApplicationCompleted() {
        this.locale = Locale.KOREAN;
        setSubject(MessageResolver.getMessage("MAIL_APPLICATION_COMPLETED_SUBJECT"));
    }

    public MailApplicationCompleted(Locale locale) {
        this.locale = locale;
        setSubject(MessageResolver.getMessage("MAIL_APPLICATION_COMPLETED_SUBJECT"));
    }

    @Override
    public void makeContents() {
        setContents(new StringBuilder()
                .append(MessageResolver.getMessage("MAIL_APPLICATION_COMPLETED_HEADER_01",
                        new Object[]{getContentsParam().get(MailContentsParamKey.USER_NAME)}, locale))
                .append(NEW_LINE1)
                .append(MessageResolver.getMessage("MAIL_COMMON_HEADER_02", locale))
                .append(NEW_LINE2)
                .append(MessageResolver.getMessage("MAIL_APPLICATION_COMPLETED_BODY_01",
                        new Object[]{getContentsParam().get(MailContentsParamKey.USER_NAME)}, locale))
                .append(NEW_LINE2)
                .append(MessageResolver.getMessage("MAIL_APPLICATION_COMPLETED_BODY_USERNAME",
                        new Object[]{getContentsParam().get(MailContentsParamKey.USER_NAME)}, locale))
                .append(NEW_LINE1)
                .append(MessageResolver.getMessage("MAIL_APPLICATION_COMPLETED_BODY_USERID",
                        new Object[]{getContentsParam().get(MailContentsParamKey.USER_ID)}, locale))
                .append(NEW_LINE1)
                .append(MessageResolver.getMessage("MAIL_APPLICATION_COMPLETED_BODY_INISTITUTE",
                        new Object[]{getContentsParam().get(MailContentsParamKey.INSTITUTE_NAME)}, locale))
                .append(NEW_LINE1)
                .append(MessageResolver.getMessage("MAIL_APPLICATION_COMPLETED_BODY_MAJOR",
                        new Object[]{getContentsParam().get(MailContentsParamKey.MAJOR)}, locale))
                .append(NEW_LINE1)
                .append(MessageResolver.getMessage("MAIL_APPLICATION_COMPLETED_BODY_APPLID",
                        new Object[]{getContentsParam().get(MailContentsParamKey.APPL_ID)}, locale))
                .append(NEW_LINE2)
                .append(MessageResolver.getMessage("MAIL_APPLICATION_COMPLETED_BODY_REMEMBER", locale))
                .append(NEW_LINE2)
                .append(MessageResolver.getMessage("MAIL_APPLICATION_COMPLETED_BODY_GOODLUCK", locale))
                .append(NEW_LINE2)
                .append(MessageResolver.getMessage("MAIL_COMMON_FOOTER_01", locale))
                .append(NEW_LINE2)
                .append(MessageResolver.getMessage("MAIL_COMMON_FOOTER_02", locale))
                .toString());
    }
}

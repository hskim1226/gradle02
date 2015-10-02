package com.apexsoft.ysprj.applicants.common.domain;

import com.apexsoft.framework.mail.Mail;
import com.apexsoft.framework.message.MessageResolver;

import java.util.Locale;

/**
 * Created by hanmomhanda on 15. 5. 12.
 */
public class MailDueNotification extends Mail {

    private final String NEW_LINE = "\n\n";
    private Locale locale = null;

    public MailDueNotification() {
        // TODO 호스트 코드에서 appl_sts 가 0001~0010 사이의 미완료 상태 원서의 메일 주소 가져와서
        // setTo(to)만 메일 주소 수 만큼 반복 실행해서 수신자 설정 후 sendMail()로 발송
//        setTo(new String[]{"hanmomhanda@naver.com"});
        setSubject(MessageResolver.getMessage("MAIL_DUE_SUBJECT"));
    }

    public MailDueNotification(Locale locale) {
        this.locale = locale;
        setSubject(MessageResolver.getMessage("MAIL_DUE_SUBJECT"));
    }

    @Override
    public void makeContents() {
        setContents(new StringBuilder()
                .append(MessageResolver.getMessage("MAIL_COMMON_HEADER_01"))
                .append(NEW_LINE)
                .append(MessageResolver.getMessage("MAIL_DUE_CONTENTS_BODY_01"))
                .append(NEW_LINE)
                .append(MessageResolver.getMessage("MAIL_DUE_CONTENTS_BODY_02"))
                .append(NEW_LINE)
                .append(MessageResolver.getMessage("MAIL_COMMON_SITE_URL"))
                .append(NEW_LINE)
                .append(MessageResolver.getMessage("MAIL_COMMON_FOOTER_01"))
                .append(NEW_LINE)
                .append(MessageResolver.getMessage("MAIL_COMMON_FOOTER_02"))
                .toString());
    }
}

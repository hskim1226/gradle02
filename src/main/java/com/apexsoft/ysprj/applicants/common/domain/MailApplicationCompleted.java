package com.apexsoft.ysprj.applicants.common.domain;

import com.apexsoft.framework.mail.Mail;
import com.apexsoft.framework.message.MessageResolver;

/**
 * Created by hanmomhanda on 15. 5. 12.
 */
public class MailApplicationCompleted extends Mail {

    private final String NEW_LINE1 = "\n";
    private final String NEW_LINE2 = "\n\n";

    public MailApplicationCompleted() {
        // TODO 호스트 코드에서 해당 사용자의 정보를 조회해서 appl_sts 가 0001~0010 사이의 미완료 상태 원서의 메일 주소 가져와서
        // setTo(to)만 메일 주소 수 만큼 반복 실행해서 수신자 설정 후 sendMail()로 발송
        setTo(new String[]{"hanmomhanda@naver.com"});
        setSubject(MessageResolver.getMessage("MAIL_APPLICATION_COMPLETED_SUBJECT"));
    }

    public void makeContents() {
        setContents(new StringBuilder()
                .append(MessageResolver.getMessage("MAIL_APPLICATION_COMPLETED_HEADER_01",
                        new Object[]{getContentsParam().get(MailContentsParamKey.USER_NAME)}))
                .append(NEW_LINE1)
                .append(MessageResolver.getMessage("MAIL_COMMON_HEADER_02"))
                .append(NEW_LINE2)
                .append(MessageResolver.getMessage("MAIL_APPLICATION_COMPLETED_BODY_01",
                        new Object[]{getContentsParam().get(MailContentsParamKey.USER_NAME)}))
                .append(NEW_LINE2)
                .append(MessageResolver.getMessage("MAIL_APPLICATION_COMPLETED_BODY_USERNAME",
                        new Object[]{getContentsParam().get(MailContentsParamKey.USER_NAME)}))
                .append(NEW_LINE1)
                .append(MessageResolver.getMessage("MAIL_APPLICATION_COMPLETED_BODY_USERID",
                        new Object[]{getContentsParam().get(MailContentsParamKey.USER_ID)}))
                .append(NEW_LINE1)
                .append(MessageResolver.getMessage("MAIL_APPLICATION_COMPLETED_BODY_INISTITUTE",
                        new Object[]{getContentsParam().get(MailContentsParamKey.INSTITUTE_NAME)}))
                .append(NEW_LINE1)
                .append(MessageResolver.getMessage("MAIL_APPLICATION_COMPLETED_BODY_MAJOR",
                        new Object[]{getContentsParam().get(MailContentsParamKey.MAJOR)}))
                .append(NEW_LINE1)
                .append(MessageResolver.getMessage("MAIL_APPLICATION_COMPLETED_BODY_APPLID",
                        new Object[]{getContentsParam().get(MailContentsParamKey.APPL_ID)}))
                .append(NEW_LINE2)
                .append(MessageResolver.getMessage("MAIL_APPLICATION_COMPLETED_BODY_REMEMBER"))
                .append(NEW_LINE2)
                .append(MessageResolver.getMessage("MAIL_APPLICATION_COMPLETED_BODY_GOODLUCK"))
                .append(NEW_LINE2)
                .append(MessageResolver.getMessage("MAIL_COMMON_FOOTER_01"))
                .append(NEW_LINE2)
                .append(MessageResolver.getMessage("MAIL_COMMON_FOOTER_02"))
                .toString());
    }
}

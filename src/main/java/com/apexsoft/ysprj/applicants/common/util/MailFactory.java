package com.apexsoft.ysprj.applicants.common.util;

import com.apexsoft.framework.exception.YSBizException;
import com.apexsoft.framework.mail.Mail;
import com.apexsoft.framework.mail.MailType;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.ysprj.applicants.common.domain.MailApplicationCompleted;
import com.apexsoft.ysprj.applicants.common.domain.MailDueNotification;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by hanmomhanda on 15. 5. 12.
 */
@Component
public class MailFactory {

    @Resource
    MessageResolver messageResolver;

    public Mail create(MailType mailType) {
        switch (mailType) {
            case DUE_NOTI:
                return new MailDueNotification();

            case COMPLETE_NOTI:
                return new MailApplicationCompleted();

            default:
                throw new YSBizException();
        }
    }
}

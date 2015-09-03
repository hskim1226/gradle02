package com.apexsoft.ysprj.applicants.common.util;

import com.apexsoft.framework.exception.YSBizException;
import com.apexsoft.framework.mail.Mail;
import com.apexsoft.framework.mail.MailType;
import com.apexsoft.ysprj.applicants.application.domain.MailCompletedRecommendation;
import com.apexsoft.ysprj.applicants.application.domain.MailRequestRecommendation;
import com.apexsoft.ysprj.applicants.common.domain.MailApplicationCompleted;
import com.apexsoft.ysprj.applicants.common.domain.MailDueNotification;
import org.springframework.stereotype.Component;

/**
 * Created by hanmomhanda on 15. 5. 12.
 */
@Component
public class MailFactory {

    public Mail create(MailType mailType) {
        switch (mailType) {
            case DUE_NOTI:
                return new MailDueNotification();

            case COMPLETE_NOTI:
                return new MailApplicationCompleted();

            case RECOMMENDATION_REQUEST:
                return new MailRequestRecommendation();

            case RECOMMENDATION_COMPLETED:
                return new MailCompletedRecommendation();

            default:
                throw new YSBizException();
        }
    }
}

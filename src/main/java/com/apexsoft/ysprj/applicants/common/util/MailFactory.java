package com.apexsoft.ysprj.applicants.common.util;

import com.apexsoft.framework.exception.YSBizException;
import com.apexsoft.framework.mail.Mail;
import com.apexsoft.framework.mail.MailType;
import com.apexsoft.ysprj.applicants.application.domain.MailCompletedRecommendation;
import com.apexsoft.ysprj.applicants.application.domain.MailRequestRecommendation;
import com.apexsoft.ysprj.applicants.application.domain.MailUrgeRecommendationToApplicant;
import com.apexsoft.ysprj.applicants.application.domain.MailUrgeRecommendationToProf;
import com.apexsoft.ysprj.applicants.common.domain.MailApplicationCompleted;
import com.apexsoft.ysprj.applicants.common.domain.MailDueNotification;
import org.springframework.stereotype.Component;

/**
 * Created by hanmomhanda on 15. 5. 12.
 */
@Component
public class MailFactory {

    public static Mail create(MailType mailType) {
        switch (mailType) {
            case DUE_NOTI:
                return new MailDueNotification();

            case COMPLETE_NOTI:
                return new MailApplicationCompleted();

            case RECOMMENDATION_REQUEST:
                return new MailRequestRecommendation();

            case RECOMMENDATION_COMPLETED:
                return new MailCompletedRecommendation();

            case RECOMMENDATION_URGE:
                return new MailUrgeRecommendationToProf();

            case RECOMMENDATION_URGE_NOTICE:
                return new MailUrgeRecommendationToApplicant();

            default:
                throw new YSBizException();
        }
    }
}

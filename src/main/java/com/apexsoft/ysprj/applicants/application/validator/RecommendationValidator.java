package com.apexsoft.ysprj.applicants.application.validator;

import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.ysprj.applicants.application.domain.*;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by hanmomhanda on 15. 2. 1.
 */
@Component
public class RecommendationValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Application.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        int i = 0;
        Recommendation recommendation = (Recommendation)o;

        if (recommendation.getProfName() == null || recommendation.getProfName().length() == 0) {
            errors.rejectValue("profName", "U331",
                    new Object[]{MessageResolver.getMessage("L06502")}, MessageResolver.getMessage("U332"));  /*"교수 이름"*/
        }
        if (recommendation.getProfMailAddr() == null || recommendation.getProfMailAddr().length() == 0) {
            errors.rejectValue("profMailAddr", "U331",
                    new Object[]{MessageResolver.getMessage("L06503")}, MessageResolver.getMessage("U332"));  /*"교수 e-mail"*/
        }
        if (recommendation.getProfInst() == null || recommendation.getProfInst().length() == 0) {
            errors.rejectValue("profInstitution", "U331",
                    new Object[]{MessageResolver.getMessage("L06506")}, MessageResolver.getMessage("U332"));  /*"교수 소속 학교"*/
        }
        if (recommendation.getProfPhone() == null || recommendation.getProfPhone().length() == 0) {
            errors.rejectValue("profPhone", "U331",
                    new Object[]{MessageResolver.getMessage("L06508")}, MessageResolver.getMessage("U332"));  /*"연락처"*/
        }
//        if (recommendation.getReqSubject() == null || recommendation.getReqSubject().length() == 0) {
//            errors.rejectValue("reqSubject", "U331",
//                    new Object[]{MessageResolver.getMessage("L06536")}, MessageResolver.getMessage("U332"));  /*"메일 제목"*/
//        }
//        if (recommendation.getReqText() == null || recommendation.getReqText().length() == 0) {
//            errors.rejectValue("reqText", "U331",
//                    new Object[]{MessageResolver.getMessage("L06504")}, MessageResolver.getMessage("U332"));  /*"요청 내용"*/
//        }
    }
}

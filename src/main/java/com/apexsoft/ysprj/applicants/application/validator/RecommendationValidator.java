package com.apexsoft.ysprj.applicants.application.validator;

import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.web.validation.NamedValidator;
import com.apexsoft.ysprj.applicants.application.domain.*;
import com.apexsoft.ysprj.applicants.common.util.ValidationUtil;
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
                    new Object[]{MessageResolver.getMessage("L06503")}, MessageResolver.getMessage("U332"));  /*"교수 이름"*/
        }
        if (recommendation.getReqText() == null || recommendation.getReqText().length() == 0) {
            errors.rejectValue("reqText", "U331",
                    new Object[]{MessageResolver.getMessage("L06504")}, MessageResolver.getMessage("U332"));  /*"교수 이름"*/
        }
    }
}

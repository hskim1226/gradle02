package com.apexsoft.ysprj.applicants.application.validator;

import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.web.validation.NamedListValidator;
import com.apexsoft.ysprj.applicants.application.domain.CustomApplicationExperience;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.util.List;

/**
 * Created by hanmomhanda on 15. 2. 5.
 */
@Component
public class ApplicationExperienceValidator extends NamedListValidator {

    @Autowired
    MessageResolver messageResolver;

    @Override
    public void validate(Object o, Errors errors, String className) {
        List<CustomApplicationExperience> customApplicationExperienceList = (List<CustomApplicationExperience>) o;
        int l = customApplicationExperienceList.size();
        String prefix;

        for ( int i = 0 ; i < l ; i++ ) {
            CustomApplicationExperience item = customApplicationExperienceList.get(i);
            prefix = className + "[" + i + "].";

            if (item.getJoinDay() == null || item.getJoinDay().length() == 0) {
                errors.rejectValue(prefix + "joinDay", "U331",
                        new Object[]{"입사일"}, messageResolver.getMessage("U332"));
            }
            if ("N".equals(item.getCurrYn())) {
                if (item.getRetrDay() == null || item.getRetrDay().length() == 0) {
                    errors.rejectValue(prefix + "retrDay", "U331",
                            new Object[]{"퇴사일"}, messageResolver.getMessage("U332"));
                }
            }
            if (item.getCorpName() == null || item.getCorpName().length() == 0) {
                errors.rejectValue(prefix + "corpName", "U331",
                        new Object[]{"기관명"}, messageResolver.getMessage("U332"));
            }
            if (item.getExprDesc() == null || item.getExprDesc().length() == 0) {
                errors.rejectValue(prefix + "exprDesc", "U331",
                        new Object[]{"경력 내용"}, messageResolver.getMessage("U332"));
            }
        }
    }
}

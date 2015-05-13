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
                        new Object[]{MessageResolver.getMessage("L03203")}, MessageResolver.getMessage("U332"));  /*"입사일"*/
            }
            if ("N".equals(item.getCurrYn())) {
                if (item.getRetrDay() == null || item.getRetrDay().length() == 0) {
                    errors.rejectValue(prefix + "retrDay", "U331",
                            new Object[]{MessageResolver.getMessage("L03204")}, MessageResolver.getMessage("U332"));  /*"퇴사일"*/
                }
            }
            if (item.getCorpName() == null || item.getCorpName().length() == 0) {
                errors.rejectValue(prefix + "corpName", "U331",
                        new Object[]{MessageResolver.getMessage("L03206")}, MessageResolver.getMessage("U332"));  /*"기관명"*/
            }
            if (item.getExprDesc() == null || item.getExprDesc().length() == 0) {
                errors.rejectValue(prefix + "exprDesc", "U331",
                        new Object[]{MessageResolver.getMessage("L03207")}, MessageResolver.getMessage("U332"));  /*"경력 내용"*/
            }
        }
    }
}

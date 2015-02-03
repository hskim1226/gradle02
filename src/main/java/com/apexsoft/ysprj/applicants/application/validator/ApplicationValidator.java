package com.apexsoft.ysprj.applicants.application.validator;

import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.ysprj.applicants.application.domain.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by hanmomhanda on 15. 2. 1.
 */
@Component
public class ApplicationValidator implements Validator {

    @Autowired
    MessageResolver messageResolver;

    @Override
    public boolean supports(Class<?> aClass) {
        return Application.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Application application = (Application) o;
        String bindingResultTargetClassName = errors.getObjectName();
        String className = application.getClass().getSimpleName();
        String prefix = (!bindingResultTargetClassName.equals(className)) ? className + "." : "";

        if (application.getKorName() == null || application.getKorName().length() == 0) {
            errors.rejectValue(prefix + "korName", "U331",
                    new Object[]{"지원자 정보 > 한글 이름"}, messageResolver.getMessage("U332"));
        }
        if (application.getEngSur() == null || application.getEngSur().length() == 0) {
            errors.rejectValue(prefix + "engSur", "U331",
                    new Object[]{"지원자 정보 > 영문 이름"}, messageResolver.getMessage("U332"));
        }
        if (application.getEngName() == null || application.getEngName().length() == 0) {
            errors.rejectValue(prefix + "engName", "U331",
                    new Object[]{"지원자 정보 > 영문 이름"}, messageResolver.getMessage("U332"));
        }
        if (application.getRgstNo() == null || application.getRgstNo().length() == 0) {
            errors.rejectValue(prefix + "engName", "U331",
                    new Object[]{"지원자 정보 > 주민등록번호"}, messageResolver.getMessage("U332"));
        }
    }
}

package com.apexsoft.ysprj.applicants.application.validator;

import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.web.validation.NamedValidator;
import com.apexsoft.ysprj.applicants.application.domain.ApplicationGeneral;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by hanmomhanda on 15. 2. 1.
 */
@Component
public class ApplicationGeneralValidator implements NamedValidator {

    @Autowired
    MessageResolver messageResolver;

    @Override
    public boolean supports(Class<?> aClass) {
        return ApplicationGeneral.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

    }

    @Override
    public void validate(Object o, Errors errors, String className) {
        ApplicationGeneral applicationGeneral = (ApplicationGeneral) o;

        String prefix = className + ".";

        if (applicationGeneral.getEmerContName() == null || applicationGeneral.getEmerContName().length() == 0) {
            errors.rejectValue(prefix + "emerContName", "U331",
                    new Object[]{"비상연락처 이름"}, messageResolver.getMessage("U332"));
        }
        if (applicationGeneral.getEmerContCode() == null || applicationGeneral.getEmerContCode().length() == 0) {
            errors.rejectValue(prefix + "emerContCode", "U331",
                    new Object[]{"비상연락처 관계"}, messageResolver.getMessage("U332"));
        }
        if (applicationGeneral.getEmerContTel() == null || applicationGeneral.getEmerContTel().length() == 0) {
            errors.rejectValue(prefix + "emerContTel", "U331",
                    new Object[]{"비상연락처 전화번호"}, messageResolver.getMessage("U332"));
        }
    }
}

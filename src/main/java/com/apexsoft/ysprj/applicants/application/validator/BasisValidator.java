package com.apexsoft.ysprj.applicants.application.validator;

import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.ysprj.applicants.application.domain.Application;
import com.apexsoft.ysprj.applicants.application.domain.ApplicationForeigner;
import com.apexsoft.ysprj.applicants.application.domain.ApplicationGeneral;
import com.apexsoft.ysprj.applicants.application.domain.Basis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by hanmomhanda on 15. 2. 1.
 */
@Component
public class BasisValidator implements Validator {

    @Autowired
    MessageResolver messageResolver;

    @Autowired
    ApplicationValidator applicationValidator;

    @Autowired
    ApplicationGeneralValidator applicationGeneralValidator;

    @Autowired
    ApplicationForeignerValidator applicationForeignerValidator;

    @Override
    public boolean supports(Class<?> clazz) {
        return Basis.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        int i = 0;
        Basis basis = (Basis)o;

        Application application = basis.getApplication();
        boolean isKorean = "118".equals(application.getCitzCntrCode());
        applicationValidator.validate(application, errors, "application");

        if ("C".equals(application.getAdmsTypeCode())) {
            ApplicationForeigner applicationForeigner = basis.getApplicationForeigner();
            applicationForeignerValidator.validate(applicationForeigner, errors, "applicationForeigner", isKorean);
        } else {
            ApplicationGeneral applicationGeneral = basis.getApplicationGeneral();
            applicationGeneralValidator.validate(applicationGeneral, errors, "applicationGeneral");
        }
    }
}

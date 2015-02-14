package com.apexsoft.ysprj.applicants.application.validator;

import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.ysprj.applicants.application.domain.*;
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
        ApplicationGeneral applicationGeneral = basis.getApplicationGeneral();
        ApplicationForeigner applicationForeigner = basis.getApplicationForeigner();

        applicationValidator.validate(application, errors, "application");
        applicationGeneralValidator.validate(applicationGeneral, errors, "applicationGeneral");
        applicationForeignerValidator.validate(applicationForeigner, errors, "applicationForeigner");

    }
}

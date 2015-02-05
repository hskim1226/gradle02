package com.apexsoft.ysprj.applicants.application.validator;

import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.ysprj.applicants.application.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

/**
 * Created by hanmomhanda on 15. 2. 1.
 */
@Component
public class LangCareerValidator implements Validator {

    @Autowired
    MessageResolver messageResolver;

    @Autowired
    ApplicationExperienceValidator applicationExperienceValidator;

    @Override
    public boolean supports(Class<?> clazz) {
        return LangCareer.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        int i = 0;
        LangCareer langCareer = (LangCareer)o;
        Application application = langCareer.getApplication();
        ApplicationGeneral applicationGeneral= langCareer.getApplicationGeneral();

        List<CustomApplicationExperience> applicationExperienceList = langCareer.getApplicationExperienceList();

        applicationExperienceValidator.validate(applicationExperienceList, errors);

    }
}

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
    LanguageGroupValidator languageGroupValidator;

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
        List<LanguageGroup> languageGroupList = langCareer.getLanguageGroupList();
        List<CustomApplicationExperience> applicationExperienceList = langCareer.getApplicationExperienceList();

        languageGroupValidator.validate(languageGroupList, errors, "languageGroupList");
        // 경력 사항은 없을 수도 있으므로 validate 안 함
//        applicationExperienceValidator.validate(applicationExperienceList, errors, "applicationExperienceList");

    }
}

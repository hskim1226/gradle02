package com.apexsoft.ysprj.applicants.application.validator;

import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.web.validation.NamedListValidator;
import com.apexsoft.ysprj.applicants.application.domain.CustomApplicationExperience;
import com.apexsoft.ysprj.applicants.application.domain.LanguageGroup;
import com.apexsoft.ysprj.applicants.application.domain.TotalApplicationLanguage;
import com.apexsoft.ysprj.applicants.application.domain.TotalApplicationLanguageContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

/**
 * Created by hanmomhanda on 15. 2. 6.
 */
@Component
public class LanguageGroupValidator extends NamedListValidator {

    @Autowired
    MessageResolver messageResolver;

    @Autowired
    ApplicationLanguageValidator applicationLanguageValidator;

    @Override
    public void validate(Object o, Errors errors, String className) {
        List<LanguageGroup> languageGroupList = (List<LanguageGroup>) o;
        int l = languageGroupList.size();
        String prefix;

        for ( int i = 0 ; i < l ; i++ ) {
            LanguageGroup item = languageGroupList.get(i);
            prefix = className + "[" + i + "].langList";

            // LanguageGroup에는 화면에서 list 외에 validation 해야 할 property가 없음
            // 따라서 list 만 validation 수행

            List<TotalApplicationLanguageContainer> langList = item.getLangList();
            applicationLanguageValidator.validate(langList, errors, prefix);
        }
    }
}

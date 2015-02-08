package com.apexsoft.ysprj.applicants.application.validator;

import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.ysprj.applicants.application.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

/**
 * Created by hanmomhanda on 15. 2. 6.
 */
@Component
public class DocumentValidator implements Validator {

    @Autowired
    MessageResolver messageResolver;

    @Autowired
    TotalApplicationDocumentValidator totalApplicationDocumentValidator;

    @Autowired
    ApplicationExperienceValidator applicationExperienceValidator;

    @Override
    public boolean supports(Class<?> clazz) {
        return Document.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        int i = 0;
        Document document = (Document)o;

        List<TotalApplicationDocumentContainer> documentContainerList = document.getDocumentContainerList();

        totalApplicationDocumentValidator.validate(documentContainerList, errors, "documentContainerList");

    }
}

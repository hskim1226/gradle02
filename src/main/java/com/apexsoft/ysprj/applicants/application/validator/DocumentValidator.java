package com.apexsoft.ysprj.applicants.application.validator;

import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.ysprj.applicants.application.domain.Application;
import com.apexsoft.ysprj.applicants.application.domain.Document;
import com.apexsoft.ysprj.applicants.application.domain.TotalApplicationDocumentContainer;
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

        Document document = (Document)o;

        Application application = document.getApplication();

        if (application.getDocChckYn() == null || application.getDocChckYn().length() == 0 || "N".equals(application.getDocChckYn())) {
            errors.rejectValue("application.docChckYn", "U331",
                    new Object[]{messageResolver.getMessage("L04103")}, messageResolver.getMessage("U332"));  /*"첨부 파일 안내 확인"*/
        }

        List<TotalApplicationDocumentContainer> documentContainerList = document.getDocumentContainerList();

        totalApplicationDocumentValidator.validate(documentContainerList, errors, "documentContainerList");

    }
}

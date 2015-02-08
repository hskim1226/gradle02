package com.apexsoft.ysprj.applicants.application.validator;

import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.web.validation.NamedListValidator;
import com.apexsoft.ysprj.applicants.application.domain.TotalApplicationDocumentContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.util.List;

/**
 * Created by hanmomhanda on 15. 2. 6.
 */
@Component
public class TotalApplicationDocumentValidator extends NamedListValidator {

    @Autowired
    MessageResolver messageResolver;

    @Override
    public void validate( Object o, Errors errors, String className ) {
        List<TotalApplicationDocumentContainer> documentContainerList = (List<TotalApplicationDocumentContainer>) o;
        int l = documentContainerList.size();
        String prefix;

        for ( int i = 0; i < l; i++ ) {
            TotalApplicationDocumentContainer item = documentContainerList.get(i);
            prefix = className + "[" + i + "].";

            if ( "Y".equals(item.getLastYn()) ) {
                if ( "Y".equals(item.getMdtYn()) ) {
                    if ( !item.isFileUploadFg() ) {
                        errors.rejectValue(prefix + "fileUploadFg", "U331",
                                new Object[]{item.getDocItemName()}, messageResolver.getMessage("U332"));
                    }
                }
            }
        }
    }
}

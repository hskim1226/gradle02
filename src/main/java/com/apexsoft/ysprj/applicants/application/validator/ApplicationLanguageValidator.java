package com.apexsoft.ysprj.applicants.application.validator;

import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.web.validation.NamedListValidator;
import com.apexsoft.framework.web.validation.NamedValidator;
import com.apexsoft.ysprj.applicants.application.domain.TotalApplicationLanguage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.util.List;

/**
 * Created by hanmomhanda on 15. 2. 5.
 */
@Component
public class ApplicationLanguageValidator extends NamedListValidator {

    @Autowired
    MessageResolver messageResolver;

    @Override
    public void validate(Object o, Errors errors, String name) {

        List<TotalApplicationLanguage> applicationLanguageList = (List<TotalApplicationLanguage>) o;
        int l = applicationLanguageList.size();
        String prefix;

        for ( int i = 0 ; i < l ; i++ ) {
            TotalApplicationLanguage item = applicationLanguageList.get(i);
            prefix = name + "[" + i + "].";

            if (item.isCheckedFg()) {
                String itemCode = item.getItemCode();
                if (itemCode.equals("00001")) { // 시험이 TOEFL 일 경우 시험 종류 선택 필수
                    if (item.getToflTypeCode() == null || item.getToflTypeCode().length() == 0) {
                        errors.rejectValue(prefix + "toflTypeCode", "U331",
                                new Object[]{"TOEFL 시험 종류"}, messageResolver.getMessage("U332"));
                    }
                }
                if (item.getExamDay() == null || item.getExamDay().length() == 0) {
                    errors.rejectValue(prefix + "examDay", "U331",
                            new Object[]{"시험일"}, messageResolver.getMessage("U332"));
                }
                if (item.getLangGrad() == null || item.getLangGrad().length() == 0) {
                    errors.rejectValue(prefix + "langGrad", "U331",
                            new Object[]{"점수"}, messageResolver.getMessage("U332"));
                }
            }
        }
    }
}

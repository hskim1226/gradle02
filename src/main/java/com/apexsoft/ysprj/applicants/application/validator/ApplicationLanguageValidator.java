package com.apexsoft.ysprj.applicants.application.validator;

import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.web.validation.NamedListValidator;
import com.apexsoft.framework.web.validation.NamedValidator;
import com.apexsoft.ysprj.applicants.application.domain.TotalApplicationLanguage;
import com.apexsoft.ysprj.applicants.application.domain.TotalApplicationLanguageContainer;
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

        List<TotalApplicationLanguageContainer> applicationLanguageList = (List<TotalApplicationLanguageContainer>) o;
        int l = applicationLanguageList.size();
        String prefix;

        for ( int i = 0 ; i < l ; i++ ) {
            TotalApplicationLanguageContainer langList = applicationLanguageList.get(i);
            prefix = name + "[" + i + "].";

            List<TotalApplicationLanguageContainer> aLangOrExemptList = langList.getSubContainer();
            int aLangOrExemptL = aLangOrExemptList.size();

            for ( int j = 0 ; j < aLangOrExemptL ; j++ ) {
                prefix += "subContainer[" + j + "].";
                TotalApplicationLanguageContainer aLangOrExempt = aLangOrExemptList.get(j);

                if (aLangOrExempt.isCheckedFg()) {

                    if ("ENG_EXMP1".equals(langList.getSelGrpCode())) {
                        String selGrpCode = aLangOrExempt.getSelGrpCode();
                        if (selGrpCode == null || selGrpCode.length() == 0 || Integer.parseInt(selGrpCode) <= 0) {
                            errors.rejectValue(prefix + "selGrpCode", "U331",
                                    new Object[]{"외국어 시험 면제 사유"}, messageResolver.getMessage("U332"));
                        }
                    } else {
                        String itemCode = aLangOrExempt.getItemCode();
                        if ("00001".equals(itemCode)) { // 시험이 TOEFL 일 경우 시험 종류 선택 필수
                            if (aLangOrExempt.getToflTypeCode() == null || aLangOrExempt.getToflTypeCode().length() == 0) {
                                errors.rejectValue(prefix + "toflTypeCode", "U331",
                                        new Object[]{"TOEFL 시험 종류"}, messageResolver.getMessage("U332"));
                            }
                        }
                        if (aLangOrExempt.getExamDay() == null || aLangOrExempt.getExamDay().length() == 0) {
                            errors.rejectValue(prefix + "examDay", "U331",
                                    new Object[]{"시험일"}, messageResolver.getMessage("U332"));
                        }
                        if (aLangOrExempt.getLangGrad() == null || aLangOrExempt.getLangGrad().length() == 0) {
                            errors.rejectValue(prefix + "langGrad", "U331",
                                    new Object[]{"점수"}, messageResolver.getMessage("U332"));
                        }
                    }
                }
            }
        }
    }
}

package com.apexsoft.ysprj.applicants.application.validator;

import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.web.validation.NamedListValidator;
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
        int checkedCnt = 0; // 시험성적정보, 시험면제정보를 통털어 checked된게 하나도 없으면 invalid

        for ( int i = 0 ; i < l ; i++ ) { // 시험성적정보, 시험면제정보
            TotalApplicationLanguageContainer langList = applicationLanguageList.get(i);
            prefix = name + "[" + i + "].";

            List<TotalApplicationLanguageContainer> aLangOrExemptList = langList.getSubContainer();
            int aLangOrExemptL = aLangOrExemptList.size();


            for ( int j = 0 ; j < aLangOrExemptL ; j++ ) {
                prefix += "subContainer[" + j + "].";
                TotalApplicationLanguageContainer aLangOrExempt = aLangOrExemptList.get(j);

                if (aLangOrExempt.isCheckedFg()) { // TOEFL, TOEIC, TEPS, ..., 면제사유
                    checkedCnt++;
//                    if ("ENG_EXMP1".equals(langList.getSelGrpCode())) {
//                        String selGrpCode = aLangOrExempt.getSelGrpCode();
//                        if (selGrpCode == null || selGrpCode.length() == 0 || Integer.parseInt(selGrpCode) <= 0) {
//                            errors.rejectValue(prefix + "selGrpCode", "U331",
//                                    new Object[]{"외국어 시험 면제 사유"}, messageResolver.getMessage("U332"));
//                        }
//                    } else if ("KOR_EXMP1".equals(langList.getSelGrpCode())) {
//                        String selGrpCode = aLangOrExempt.getSelGrpCode();
//                        if (selGrpCode == null || selGrpCode.length() == 0 || Integer.parseInt(selGrpCode) <= 0) {
//                            errors.rejectValue(prefix + "selGrpCode", "U331",
//                                    new Object[]{"한국어 시험 면제 사유"}, messageResolver.getMessage("U332"));
//                        }
//                    }
                    if ("00002".equals(langList.getItemCode())) {
                        String subCode = aLangOrExempt.getSubCode();
                        if (subCode == null || subCode.length() == 0 || Integer.parseInt(subCode) <= 0) {
                            errors.rejectValue(prefix + "subCode", "U331",
                                    new Object[]{messageResolver.getMessage("L03107")}, messageResolver.getMessage("U332"));  /*"시험 면제 사유"*/
                        }
                    }

                    else {
                        String itemGrpCode = aLangOrExempt.getItemGrpCode();
                        String itemCode = aLangOrExempt.getItemCode();
                        if ("LANG_EXAM".equals(itemGrpCode) && "00001".equals(itemCode)) { // 시험이 TOEFL 일 경우 시험 종류 선택 필수
//                        if ("00001".equals(itemCode)) { // 시험이 TOEFL 일 경우 시험 종류 선택 필수
//                            if (aLangOrExempt.getToflTypeCode() == null || aLangOrExempt.getToflTypeCode().length() == 0) {
                            if (aLangOrExempt.getSubCode() == null || aLangOrExempt.getSubCode().length() == 0) {
                                errors.rejectValue(prefix + "subCode", "U331",
                                        new Object[]{messageResolver.getMessage("L03108")}, messageResolver.getMessage("U332"));  /*"TOEFL 시험 종류"*/
                            }
                        }
                        if (aLangOrExempt.getExamDay() == null || aLangOrExempt.getExamDay().length() == 0) {
                            errors.rejectValue(prefix + "examDay", "U331",
                                    new Object[]{messageResolver.getMessage("L03103")}, messageResolver.getMessage("U332"));  /*"시험일"*/
                        }
                        if (aLangOrExempt.getLangGrad() == null || aLangOrExempt.getLangGrad().length() == 0) {
                            errors.rejectValue(prefix + "langGrad", "U331",
                                    new Object[]{messageResolver.getMessage("L03104")}, messageResolver.getMessage("U332"));  /*"점수"*/
                        }
                    }
                }
                prefix = name + "[" + i + "].";
            }
        }
        if (checkedCnt == 0) {
            errors.rejectValue(name.substring(0, name.indexOf('.')), "U331",
                    new Object[]{messageResolver.getMessage("L03109")}, messageResolver.getMessage("U332"));  /*"시험 성적 또는 면제 정보 중 최소한 하나는 입력해야 합니다."*/
        }
    }
}

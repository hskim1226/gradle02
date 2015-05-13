package com.apexsoft.ysprj.applicants.application.validator;

import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.web.validation.NamedListValidator;
import com.apexsoft.ysprj.applicants.application.domain.LanguageGroup;
import com.apexsoft.ysprj.applicants.application.domain.TotalApplicationLanguageContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.util.List;

/**
 * Created by hanmomhanda on 15. 2. 6.
 */
@Component
public class LanguageGroupValidator extends NamedListValidator {

    @Autowired
    ApplicationLanguageValidator applicationLanguageValidator;

    @Override
    public void validate(Object o, Errors errors, String className) {
        List<LanguageGroup> languageGroupList = (List<LanguageGroup>) o;
        int M = languageGroupList.size();
        String gPrefix;
        boolean passFg = false;
        int checkedCnt = 0; // 시험성적정보, 시험면제정보를 통털어 checked된게 하나도 없으면 invalid
        for ( int inx = 0 ; inx < M ; inx++ ) { // 영어, 한국어, ...
            LanguageGroup item = languageGroupList.get(inx);
            gPrefix = className + "[" + inx + "].langList";

            // LanguageGroup에는 화면에서 list 외에 validation 해야 할 property가 없음
            // 따라서 list 만 validation 수행

            List<TotalApplicationLanguageContainer> langSendList = item.getLangList();
            /*
            applicationLanguageValidator.validate(langList, errors, prefix);

            */
            String name = gPrefix;
            String prefix;
            int l = langSendList.size();

            //영어, 한국어 별도로 각각 체크하려면 아래 주석 해제, 막으면 통합하여 1개만 제출
            //checkedCnt = 0; // 시험성적정보, 시험면제정보를 통털어 checked된게 하나도 없으면 invalid

            for ( int i = 0 ; i < l ; i++ ) { // 시험성적정보, 시험면제정보
                TotalApplicationLanguageContainer langList = langSendList.get(i);
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
//                                    new Object[]{"외국어 시험 면제 사유"}, MessageResolver.getMessage("U332"));
//                        }
//                    } else if ("KOR_EXMP1".equals(langList.getSelGrpCode())) {
//                        String selGrpCode = aLangOrExempt.getSelGrpCode();
//                        if (selGrpCode == null || selGrpCode.length() == 0 || Integer.parseInt(selGrpCode) <= 0) {
//                            errors.rejectValue(prefix + "selGrpCode", "U331",
//                                    new Object[]{"한국어 시험 면제 사유"}, MessageResolver.getMessage("U332"));
//                        }
//                    }
                        if ("00002".equals(langList.getItemCode())) { // 성적 미제출
                            String subCode = aLangOrExempt.getSubCode();
                            if (subCode == null || subCode.length() == 0 || Integer.parseInt(subCode) <= 0) {
                                errors.rejectValue(prefix + "subCode", "U331",
                                        new Object[]{MessageResolver.getMessage("L03107")}, MessageResolver.getMessage("U332"));  /*"시험 면제 사유"*/
                            }
                        } else { // 성적 제출
                            String itemGrpCode = aLangOrExempt.getItemGrpCode();
                            String itemCode = aLangOrExempt.getItemCode();
                            if ("LANG_EXAM".equals(itemGrpCode) && "00001".equals(itemCode)) { // 시험이 TOEFL 일 경우 시험 종류 선택 필수
//                        if ("00001".equals(itemCode)) { // 시험이 TOEFL 일 경우 시험 종류 선택 필수
//                            if (aLangOrExempt.getToflTypeCode() == null || aLangOrExempt.getToflTypeCode().length() == 0) {
                                if (aLangOrExempt.getSubCode() == null || aLangOrExempt.getSubCode().length() == 0) {
                                    errors.rejectValue(prefix + "subCode", "U331",
                                            new Object[]{MessageResolver.getMessage("L03108")}, MessageResolver.getMessage("U332"));  /*"TOEFL 시험 종류"*/
                                }
                            }
                            if (aLangOrExempt.getExamDay() == null || aLangOrExempt.getExamDay().length() == 0) {
                                errors.rejectValue(prefix + "examDay", "U331",
                                        new Object[]{MessageResolver.getMessage("L03103")}, MessageResolver.getMessage("U332"));  /*"시험일"*/
                            }
                            if (aLangOrExempt.getLangGrad() == null || aLangOrExempt.getLangGrad().length() == 0) {
                                errors.rejectValue(prefix + "langGrad", "U331",
                                        new Object[]{MessageResolver.getMessage("L03104")}, MessageResolver.getMessage("U332"));  /*"점수"*/
                            }
                        }
                    }
                    prefix = name + "[" + i + "].";
                }
            }
//            언어별로 제출, 미제출 중 최소 하나는 선택해야 하는 경우
//            if (checkedCnt == 0) {
//                errors.rejectValue(name.substring(0, name.indexOf('.')), "U331",
//                        new Object[]{MessageResolver.getMessage("U03105")}, MessageResolver.getMessage("U332"));  /*"시험 성적 또는 면제 정보 중 최소한 하나는 입력해야 합니다."*/
//            }
        }
        // 전체 언어에서 제출, 미제출 중 최소 하나는 선택해야 하는 경우
        if (checkedCnt == 0) {
            errors.rejectValue(className, "U331",
                    new Object[]{MessageResolver.getMessage("U03106")}, MessageResolver.getMessage("U332"));  /*"시험 성적 또는 면제 정보 중 최소한 하나는 입력해야 합니다."*/
        }
    }
}

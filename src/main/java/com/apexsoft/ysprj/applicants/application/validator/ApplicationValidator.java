package com.apexsoft.ysprj.applicants.application.validator;

import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.web.validation.NamedValidator;
import com.apexsoft.ysprj.applicants.application.domain.Application;
import com.apexsoft.ysprj.applicants.common.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

/**
 * Created by hanmomhanda on 15. 2. 1.
 */
@Component
public class ApplicationValidator implements NamedValidator {

    @Autowired
    MessageResolver messageResolver;

    @Override
    public boolean supports(Class<?> aClass) {
        return Application.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

    }

    @Override
    public void validate(Object o, Errors errors, String className) {
        Application application = (Application) o;
//        String bindingResultTargetClassName = errors.getObjectName();
//        String instanceName = className.substring(0, 1).toLowerCase() + className.substring(1);
//        String prefix = (!bindingResultTargetClassName.equals(className)) ? instanceName + "." : "";
        String prefix = className + ".";

        if (!"C".equals(application.getAdmsTypeCode()) && !"D".equals(application.getAdmsTypeCode())) {
            // 외국인 전형 아닐 때만 필수인 것들
            if (application.getKorName() == null || application.getKorName().length() == 0) {
                errors.rejectValue(prefix + "korName", "U331",
                        new Object[]{"한글 이름"}, messageResolver.getMessage("U332"));
            }
            if (application.getZipCode() == null || application.getZipCode().length() == 0) {
                errors.rejectValue(prefix + "zipCode", "U331",
                        new Object[]{"우편번호"}, messageResolver.getMessage("U332"));
            }
            if (application.getAddr() == null || application.getAddr().length() == 0) {
                errors.rejectValue(prefix + "addr", "U331",
                        new Object[]{"주소"}, messageResolver.getMessage("U332"));
            }
            if (application.getTelNum() == null || application.getTelNum().length() == 0) {
                errors.rejectValue(prefix + "telNum", "U331",
                        new Object[]{"전화번호"}, messageResolver.getMessage("U332"));
            }
            if (application.getMobiNum() == null || application.getMobiNum().length() == 0) {
                errors.rejectValue(prefix + "mobiNum", "U331",
                        new Object[]{"휴대폰"}, messageResolver.getMessage("U332"));
            }
        } else {
            // 외국인 전형일 때만 필수인 것들
            if (application.getFornTypeCode() == null || application.getFornTypeCode().length() == 0) {
                errors.rejectValue(prefix + "fornTypeCode", "U331",
                        new Object[]{"외국인 구분"}, messageResolver.getMessage("U332"));
            }
        }

        if (application.getEngSur() == null || application.getEngSur().length() == 0) {
            errors.rejectValue(prefix + "engSur", "U331",
                    new Object[]{"영문 성"}, messageResolver.getMessage("U332"));
        }
        if (application.getEngName() == null || application.getEngName().length() == 0) {
            errors.rejectValue(prefix + "engName", "U331",
                    new Object[]{"영문 이름"}, messageResolver.getMessage("U332"));
        }
        if ( application.getRgstBornDate() == null || application.getRgstBornDate().length() == 0 ) {
            String itemName = "118".equals(application.getCitzCntrCode()) ? "주민등록 상 생년월일" : "생년월일";
            errors.rejectValue(prefix + "rgstBornDate", "U331",
                    new Object[]{itemName}, messageResolver.getMessage("U332"));
        }
        if ("118".equals(application.getCitzCntrCode())) {
//            if ( application.getRgstNo() == null || application.getRgstNo().length() == 0 ) {
//                errors.rejectValue(prefix + "rgstNo", "U331",
//                        new Object[]{"주민등록번호"}, messageResolver.getMessage("U332"));
//            }
            // 국적이 대한민국 일 때만 필수

            if (application.getApplStsCode() == null || application.getApplStsCode().length() == 0) {
                if ( application.getRgstEncr() == null || application.getRgstEncr().length() == 0 ) {
                    errors.rejectValue(prefix + "rgstEncr", "U331",
                            new Object[]{"주민등록번호 뒷자리"}, messageResolver.getMessage("U332"));
                }
                String rgstNo = application.getRgstBornDate() + application.getRgstEncr();
                if (!ValidationUtil.checkKorSSN(rgstNo)) {
                    errors.rejectValue(prefix + "rgstEncr", "U345", messageResolver.getMessage("U332"));
                }
            }

        }
        if (application.getCitzCntrCode() == null || application.getCitzCntrCode().length() == 0) {
            errors.rejectValue(prefix + "citzCntrCode", "U331",
                    new Object[]{"국적"}, messageResolver.getMessage("U332"));
        }

        if (application.getMailAddr() == null || application.getMailAddr().length() == 0) {
            errors.rejectValue(prefix + "mailAddr", "U331",
                    new Object[]{"E-mail"}, messageResolver.getMessage("U332"));
        }

    }
}

package com.apexsoft.ysprj.applicants.application.validator;

import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.web.validation.NamedValidator;
import com.apexsoft.ysprj.applicants.application.domain.ApplicationForeigner;
import com.apexsoft.ysprj.applicants.application.domain.ApplicationGeneral;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

/**
 * Created by hanmomhanda on 15. 2.14.
 */
@Component
public class ApplicationForeignerValidator implements NamedValidator {

    @Autowired
    MessageResolver messageResolver;

    @Override
    public boolean supports(Class<?> aClass) {
        return ApplicationForeigner.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

    }

    @Override
    public void validate(Object o, Errors errors, String className) {
        ApplicationForeigner applicationForeigner = (ApplicationForeigner) o;

        String prefix = className + ".";

        if (applicationForeigner.getBornCntrCode() == null || applicationForeigner.getBornCntrCode().length() == 0) {
            errors.rejectValue(prefix + "bornCntrCode", "U331",
                    new Object[]{"출생국"}, messageResolver.getMessage("U332"));
        }
        if (applicationForeigner.getHomeAddr() == null || applicationForeigner.getHomeAddr().length() == 0) {
            errors.rejectValue(prefix + "homeAddr", "U331",
                    new Object[]{"본국 주소"}, messageResolver.getMessage("U332"));
        }
        if (applicationForeigner.getHomeTel() == null || applicationForeigner.getHomeTel().length() == 0) {
            errors.rejectValue(prefix + "homeTel", "U331",
                    new Object[]{"본국 연락처"}, messageResolver.getMessage("U332"));
        }
        if (applicationForeigner.getVisaNo() == null || applicationForeigner.getVisaNo().length() == 0) {
            errors.rejectValue(prefix + "visaNo", "U331",
                    new Object[]{"비자 번호"}, messageResolver.getMessage("U332"));
        }
//        if (applicationForeigner.getVisaTypeCode() == null || applicationForeigner.getVisaTypeCode().length() == 0) {
//            errors.rejectValue(prefix + "visaTypeCode", "U331",
//                    new Object[]{"비자 종류"}, messageResolver.getMessage("U332"));
//        }
        if (applicationForeigner.getVisaExprDay() == null || applicationForeigner.getVisaExprDay().length() == 0) {
            errors.rejectValue(prefix + "visaExprDay", "U331",
                    new Object[]{"비자 만료일"}, messageResolver.getMessage("U332"));
        }
        if (applicationForeigner.getKorEmrgName() == null || applicationForeigner.getKorEmrgName().length() == 0) {
            errors.rejectValue(prefix + "korEmrgName", "U331",
                    new Object[]{"국내 비상연락처 이름"}, messageResolver.getMessage("U332"));
        }
        if (applicationForeigner.getKorEmrgTel() == null || applicationForeigner.getKorEmrgTel().length() == 0) {
            errors.rejectValue(prefix + "korEmrgTel", "U331",
                    new Object[]{"국내 비상연락처 전화번호"}, messageResolver.getMessage("U332"));
        }
        if (applicationForeigner.getHomeEmrgName() == null || applicationForeigner.getHomeEmrgName().length() == 0) {
            errors.rejectValue(prefix + "homeEmrgName", "U331",
                    new Object[]{"본국 비상연락처 이름"}, messageResolver.getMessage("U332"));
        }
        if (applicationForeigner.getHomeEmrgTel() == null || applicationForeigner.getHomeEmrgTel().length() == 0) {
            errors.rejectValue(prefix + "homeEmrgTel", "U331",
                    new Object[]{"본국 비상연락처 전화번호"}, messageResolver.getMessage("U332"));
        }
    }
}

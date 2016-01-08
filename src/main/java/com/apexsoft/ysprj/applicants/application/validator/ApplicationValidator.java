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

//        if (!"C".equals(application.getAdmsTypeCode()) && !"D".equals(application.getAdmsTypeCode())) {
        if (!application.isForeignAppl()) {
            // 외국인 전형 아닐 때만 필수인 것들
            if (application.getKorName() == null || application.getKorName().length() == 0) {
                errors.rejectValue(prefix + "korName", "U331",
                        new Object[]{MessageResolver.getMessage("L02122")}, MessageResolver.getMessage("U332"));  /*"한글 이름"*/
            }
            if (application.getZipCode() == null || application.getZipCode().length() == 0) {
                errors.rejectValue(prefix + "zipCode", "U331",
                        new Object[]{MessageResolver.getMessage("L01503")}, MessageResolver.getMessage("U332"));  /*"우편번호"*/
            }
            if (application.getAddr() == null || application.getAddr().length() == 0) {
                errors.rejectValue(prefix + "addr", "U331",
                        new Object[]{MessageResolver.getMessage("L01502")}, MessageResolver.getMessage("U332"));  /*"주소"*/
            }
            if (application.getTelNum() == null || application.getTelNum().length() == 0) {
                errors.rejectValue(prefix + "telNum", "U331",
                        new Object[]{MessageResolver.getMessage("L01504")}, MessageResolver.getMessage("U332"));  /*"전화번호"*/
            }
            if (application.getMobiNum() == null || application.getMobiNum().length() == 0) {
                errors.rejectValue(prefix + "mobiNum", "U331",
                        new Object[]{MessageResolver.getMessage("L01505")}, MessageResolver.getMessage("U332"));  /*"휴대폰"*/
            }
        } else {
            // 외국인 전형일 때만 필수인 것들
            if (application.getFornTypeCode() == null || application.getFornTypeCode().length() == 0) {
                errors.rejectValue(prefix + "fornTypeCode", "U331",
                        new Object[]{MessageResolver.getMessage("L01306")}, MessageResolver.getMessage("U332"));  /*"외국인 구분"*/
            }
        }

        // 4/8 학교 측에서 외국인 전형 전화 응대를 위해 휴대폰 번호 vali 묶으라 함
        if (application.getMobiNum() == null || application.getMobiNum().length() == 0) {
            errors.rejectValue(prefix + "mobiNum", "U331",
                    new Object[]{MessageResolver.getMessage("L01505")}, MessageResolver.getMessage("U332"));  /*"휴대폰"*/
        }

        if (application.getEngSur() == null || application.getEngSur().length() == 0) {
            errors.rejectValue(prefix + "engSur", "U331",
                    new Object[]{MessageResolver.getMessage("L01204")}, MessageResolver.getMessage("U332"));  /*"영문 성"*/
        }
        if (application.getEngName() == null || application.getEngName().length() == 0) {
            errors.rejectValue(prefix + "engName", "U331",
                    new Object[]{MessageResolver.getMessage("L01205")}, MessageResolver.getMessage("U332"));  /*"영문 이름"*/
        }
        if ( application.getRgstBornDate() == null || application.getRgstBornDate().length() == 0 ) {
            String itemName = application.isKorean() ? MessageResolver.getMessage("L01217") : MessageResolver.getMessage("L01216");  /*"주민등록 상 생년월일" : "생년월일"*/
            errors.rejectValue(prefix + "rgstBornDate", "U331",
                    new Object[]{itemName}, MessageResolver.getMessage("U332"));
        }
        if (application.isKorean()) {
//            if ( application.getRgstNo() == null || application.getRgstNo().length() == 0 ) {
//                errors.rejectValue(prefix + "rgstNo", "U331",
//                        new Object[]{"주민등록번호"}, MessageResolver.getMessage("U332"));
//            }

            // 주민번호 뒷자리는 국적 대한민국, 외국인 전형 아니고 최초 저장할 때만 validation
            if (application.getApplStsCode() == null || application.getApplStsCode().length() == 0) {
//                if (!"C".equals(application.getAdmsTypeCode()) && !"D".equals(application.getAdmsTypeCode())) {
                if (!application.isForeignAppl()) {
                    if ( application.getRgstEncr() == null || application.getRgstEncr().length() == 0 ) {
                        errors.rejectValue(prefix + "rgstEncr", "U331",
                                new Object[]{MessageResolver.getMessage("U01208")}, MessageResolver.getMessage("U332"));  /*"주민등록번호 뒷자리"*/
                    }
                    String rgstNo = application.getRgstBornDate() + application.getRgstEncr();
                    if (!ValidationUtil.checkKorSSN(rgstNo)) {
                        errors.rejectValue(prefix + "rgstEncr", "U345", MessageResolver.getMessage("U332"));
                    }
                }
            }

        }
        if (application.getCitzCntrCode() == null || application.getCitzCntrCode().length() == 0) {
            errors.rejectValue(prefix + "citzCntrCode", "U331",
                    new Object[]{MessageResolver.getMessage("L01206")}, MessageResolver.getMessage("U332"));  /*"국적"*/
        }
        if (application.getGend() == null || application.getGend().length() == 0) {
            errors.rejectValue(prefix + "gend", "U331",
                    new Object[]{MessageResolver.getMessage("L01218")}, MessageResolver.getMessage("U332"));  /*"성별"*/
        }

        if (application.getMailAddr() == null || application.getMailAddr().length() == 0) {
            errors.rejectValue(prefix + "mailAddr", "U331",
                    new Object[]{MessageResolver.getMessage("L01506")}, MessageResolver.getMessage("U332"));  /*"E-mail"*/
        }

    }
}

package com.apexsoft.ysprj.applicants.application.validator;

import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.web.validation.NamedListValidator;
import com.apexsoft.ysprj.applicants.application.domain.CustomApplicationAcademy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.util.List;

/**
 * Created by hanmomhanda on 15. 2. 6.
 */
@Component
public class ApplicationAcademyValidator extends NamedListValidator {

    @Autowired
    MessageResolver messageResolver;

    @Override
    public void validate(Object o, Errors errors, String className) {
        List<CustomApplicationAcademy> applicationAcademyList = (List<CustomApplicationAcademy>) o;
        int l = applicationAcademyList.size();
        String prefix;

        for (int i = 0 ; i < l ; i++) {
            CustomApplicationAcademy caa = applicationAcademyList.get(i);
            prefix = className + "[" + i + "].";

            if (caa.getSchlCntrCode() == null || caa.getSchlCntrCode().length() == 0) {
                errors.rejectValue(prefix + "schlCntrCode", "U331",
                        new Object[]{messageResolver.getMessage("L02103")}, messageResolver.getMessage("U332"));  /*학교 소재 국가*/
            }
            if (caa.getEntrDay() == null || caa.getEntrDay().length() == 0) {
                errors.rejectValue(prefix + "entrDay", "U331",
                        new Object[]{messageResolver.getMessage("L02106")}, messageResolver.getMessage("U332"));  /*"시작일"*/
            }
            if (caa.getGrdaDay() == null || caa.getGrdaDay().length() == 0) {
                errors.rejectValue(prefix + "grdaDay", "U331",
                        new Object[]{messageResolver.getMessage("L02107")}, messageResolver.getMessage("U332"));  /*"종료일"*/
            }
            if (caa.getGrdaTypeCode() == null || caa.getGrdaTypeCode().length() == 0) {
                errors.rejectValue(prefix + "grdaTypeCode", "U331",
                        new Object[]{messageResolver.getMessage("L02108")}, messageResolver.getMessage("U332"));  /*"졸업 구분"*/
            } else if ("00001".equals(caa.getGrdaTypeCode())) {
                if (caa.getDegrNo() == null || caa.getDegrNo().length() == 0) {
                    errors.rejectValue(prefix + "degrNo", "U331",
                            new Object[]{messageResolver.getMessage("L02128")}, messageResolver.getMessage("U332"));  /*"학위 번호"*/
                }
            }
            if (caa.getSchlName() == null || caa.getSchlName().length() == 0) {
                errors.rejectValue(prefix + "schlName", "U331",
                        new Object[]{messageResolver.getMessage("L02127")}, messageResolver.getMessage("U332"));  /*"학교 이름"*/
            }
            if (caa.getCollName() == null || caa.getCollName().length() == 0) {
                errors.rejectValue(prefix + "collName", "U331",
                        new Object[]{messageResolver.getMessage("L02115")}, messageResolver.getMessage("U332"));  /*"단과 대학"*/
            }
            if (caa.getMajName() == null || caa.getMajName().length() == 0) {
                errors.rejectValue(prefix + "majName", "U331",
                        new Object[]{messageResolver.getMessage("L02116")}, messageResolver.getMessage("U332"));  /*"학과 이름"*/
            }
            if (caa.getGradFormCode() == null || caa.getGradFormCode().length() == 0) {
                errors.rejectValue(prefix + "gradFormCode", "U331",
                        new Object[]{messageResolver.getMessage("L02129")}, messageResolver.getMessage("U332"));  /*"학점 구분"*/
            }
            if (caa.getGradAvr() == null || caa.getGradAvr().length() == 0) {
                errors.rejectValue(prefix + "gradAvr", "U331",
                        new Object[]{messageResolver.getMessage("L02118")}, messageResolver.getMessage("U332"));  /*"평점"*/
            }
            if (caa.getGradFull() == null || caa.getGradFull().length() == 0 ) {
                errors.rejectValue(prefix + "gradFull", "U331",
                        new Object[]{messageResolver.getMessage("L02119")}, messageResolver.getMessage("U332"));  /*"만점"*/
            }
        }
    }
}

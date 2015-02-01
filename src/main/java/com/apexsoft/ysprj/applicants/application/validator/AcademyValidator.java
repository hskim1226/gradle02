package com.apexsoft.ysprj.applicants.application.validator;

import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.ysprj.applicants.application.domain.Academy;
import com.apexsoft.ysprj.applicants.application.domain.CustomApplicationAcademy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

/**
 * Created by hanmomhanda on 15. 2. 1.
 */
@Component
public class AcademyValidator implements Validator {

    @Autowired
    MessageResolver messageResolver;

    @Override
    public boolean supports(Class<?> clazz) {
        return Academy.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        int i = 0;
        Academy academy = (Academy)o;
        List<CustomApplicationAcademy> collegeList = academy.getCollegeList();
        List<CustomApplicationAcademy> graduateList = academy.getGraduateList();

        validateList(collegeList, errors, "collegeList");
        validateList(graduateList, errors, "graduateList");
    }

    private void validateList(List<CustomApplicationAcademy> list, Errors errors, String listName) {
        for (int i = 0, length = list.size() ; i < length ; i++) {
            CustomApplicationAcademy caa = list.get(i);
            if (caa.getSchlCntrCode() == null || caa.getSchlCntrCode().length() == 0) {
                errors.rejectValue(listName + "[" + i + "].schlCntrCode", "U331",
                        new Object[]{"학교 소재 국가"}, messageResolver.getMessage("U332"));
            }
            if (caa.getEntrDay() == null || caa.getEntrDay().length() == 0) {
                errors.rejectValue(listName + "[" + i + "].entrDay", "U331",
                        new Object[]{"입학일"}, messageResolver.getMessage("U332"));
            }
            if (caa.getGrdaDay() == null || caa.getGrdaDay().length() == 0) {
                errors.rejectValue(listName + "[" + i + "].grdaDay", "U331",
                        new Object[]{"졸업(예정)일"}, messageResolver.getMessage("U332"));
            }
            if (caa.getGrdaTypeCode() == null || caa.getGrdaTypeCode().length() == 0) {
                errors.rejectValue(listName + "[" + i + "].grdaTypeCode", "U331",
                        new Object[]{"졸업 구분"}, messageResolver.getMessage("U332"));
            }
            if (caa.getSchlName() == null || caa.getSchlName().length() == 0) {
                errors.rejectValue(listName + "[" + i + "].schlName", "U331",
                        new Object[]{"학교 이름"}, messageResolver.getMessage("U332"));
            }
            if (caa.getCollName() == null || caa.getCollName().length() == 0) {
                errors.rejectValue(listName + "[" + i + "].collName", "U331",
                        new Object[]{"단과 대학"}, messageResolver.getMessage("U332"));
            }
            if (caa.getMajName() == null || caa.getMajName().length() == 0) {
                errors.rejectValue(listName + "[" + i + "].majName", "U331",
                        new Object[]{"학과 이름"}, messageResolver.getMessage("U332"));
            }
            if (caa.getGradAvr() == null || caa.getGradAvr().length() == 0) {
                errors.rejectValue(listName + "[" + i + "].gradAvr", "U331",
                        new Object[]{"평량 평균"}, messageResolver.getMessage("U332"));
            }
            if (caa.getGradFull() == null || caa.getGradFull().length() == 0 ) {
                errors.rejectValue(listName + "[" + i + "].gradFull", "U331",
                        new Object[]{"평량 평균"}, messageResolver.getMessage("U332"));
            }
        }
    }
}

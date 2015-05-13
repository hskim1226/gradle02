package com.apexsoft.ysprj.applicants.application.validator;

import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.ysprj.applicants.application.domain.Academy;
import com.apexsoft.ysprj.applicants.application.domain.Application;
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
    ApplicationAcademyValidator applicationAcademyValidator;

    @Override
    public boolean supports(Class<?> clazz) {
        return Academy.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        int i = 0;
        Academy academy = (Academy)o;
        Application application = academy.getApplication();
        List<CustomApplicationAcademy> collegeList = academy.getCollegeList();
        List<CustomApplicationAcademy> graduateList = academy.getGraduateList();

        String corsTypeCode = application.getCorsTypeCode();

        applicationAcademyValidator.validate(collegeList, errors, "collegeList");
        if ("02".equals(corsTypeCode) || "06".equals(corsTypeCode) || "08".equals(corsTypeCode) || "11".equals(corsTypeCode)) // 박사 과정일 때만 석사 학력 validation
            applicationAcademyValidator.validate(graduateList, errors, "graduateList");
    }
}

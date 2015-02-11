package com.apexsoft.framework.web.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

/**
 * Created by hanmomhanda on 15. 2. 6.
 */
public abstract class NamedListValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return List.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

    }

    public abstract void validate(Object o, Errors errors, String className);
}

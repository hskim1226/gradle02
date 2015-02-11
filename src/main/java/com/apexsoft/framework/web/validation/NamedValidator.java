package com.apexsoft.framework.web.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by hanmomhanda on 15. 2. 6.
 */
public interface NamedValidator extends Validator {

    void validate(Object o, Errors errors, String className);
}

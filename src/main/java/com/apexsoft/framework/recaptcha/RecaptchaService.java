package com.apexsoft.framework.recaptcha;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by hanmomhanda on 14. 7. 28.
 */
public interface RecaptchaService {
    public String verify(HttpServletRequest request);
}

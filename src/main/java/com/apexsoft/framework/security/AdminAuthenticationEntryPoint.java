package com.apexsoft.framework.security;

import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

/**
 * Created by hanmomhanda on 16. 3. 25.
 */
public class AdminAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {

    public AdminAuthenticationEntryPoint(String loginFormUrl) {
        super(loginFormUrl);
    }
}

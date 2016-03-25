package com.apexsoft.framework.security;

import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

/**
 * Created by hanmomhanda on 16. 3. 25.
 */
public class UserAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {

    public UserAuthenticationEntryPoint(String loginFormUrl) {
        super(loginFormUrl);
    }
}

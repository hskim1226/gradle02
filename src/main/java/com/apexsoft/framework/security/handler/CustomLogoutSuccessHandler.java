package com.apexsoft.framework.security.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * Created by hanmomhanda on 16. 3. 25.
 */
public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

    private String userTargetUrl;
    private String adminTargetUrl;
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    public void setUserTargetUrl(String userTargetUrl) {
        this.userTargetUrl = userTargetUrl;
    }

    public void setAdminTargetUrl(String adminTargetUrl) {
        this.adminTargetUrl = adminTargetUrl;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        String targetUrl = determinTargetUrl(authentication);
        if(response.isCommitted()) {
            this.logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
        } else {
            this.redirectStrategy.sendRedirect(request, response, targetUrl);
        }
    }

    private String determinTargetUrl(Authentication authentication) {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if (roles.contains("ROLE_ADMIN")) {
            return adminTargetUrl;
        } else {
            return userTargetUrl;
        }
    }
}

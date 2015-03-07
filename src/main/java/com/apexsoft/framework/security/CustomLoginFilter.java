package com.apexsoft.framework.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.Locale;

/**
 * Created by hanmomhanda on 15. 3. 7.
 */
public class CustomLoginFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication( HttpServletRequest request, HttpServletResponse response ) throws AuthenticationException {

        Enumeration parameterNames = request.getParameterNames();
        System.err.println("in CustomLoginFilter");
        while (parameterNames.hasMoreElements()) {
            String paramName = (String)parameterNames.nextElement();
            System.err.println(paramName + " : " + request.getParameter(paramName));
        }

//        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
//        localeResolver.setLocale(request, response, new Locale(request.getParameter("lang")));
        WebUtils.setSessionAttribute(request, SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, new Locale(request.getParameter("lang")));

        return super.attemptAuthentication(request, response);
    }
}

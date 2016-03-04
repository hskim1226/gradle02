package com.apexsoft.framework.filter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.util.WebUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;

/**
 * Created by hanmomhanda on 15. 10. 4.
 */
public class LocaleFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String lang = request.getParameter("lang");
        if (lang != null && !StringUtils.isEmpty(lang))
            WebUtils.setSessionAttribute((HttpServletRequest) request, SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, new Locale(lang));
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}

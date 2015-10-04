package com.apexsoft.framework.unused;

import com.apexsoft.framework.security.UserSessionVO;
import com.apexsoft.ysprj.applicants.common.service.CommonService;
import com.apexsoft.ysprj.code.AuthorityType;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.util.WebUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by hanmomhanda on 15. 3. 2.
 */
@Deprecated
public class LocaleInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    CommonService commonService;

    /**
     * Locale 처리
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String lang = request.getParameter("lang");
        if (lang != null && !StringUtils.isEmpty(lang))
            WebUtils.setSessionAttribute(request, SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, new Locale(lang));
        return true;
    }
}

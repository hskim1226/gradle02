package com.apexsoft.framework.interceptor;

import com.apexsoft.framework.security.UserSessionVO;
import com.apexsoft.ysprj.applicants.common.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by hanmomhanda on 15. 3. 2.
 */
public class KeyInfoBlockInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    CommonService commonService;

    /**
     * 로그인 한 사용자가 다른 사람의 applNo로 정보를 요청할 때 blocking
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        List<String> requestedApplNoList = new ArrayList<String>();
        Enumeration parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = (String)parameterNames.nextElement();
            if (paramName.indexOf("applNo") >= 0) {
                requestedApplNoList.add(request.getParameter(paramName));
            }
        }

        SecurityContext sc = (SecurityContext)request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        Authentication auth = sc.getAuthentication();
        UserSessionVO userSessionVO = (UserSessionVO)auth.getPrincipal();
        String userId = userSessionVO.getUsername();

        List<Integer> availableApplNos = commonService.retrieveAvailableApplNos(userId);
        Set<Integer> availableApplNoSet = new HashSet<Integer>(availableApplNos);

        for(String requestedApplNo : requestedApplNoList) {
            if (!availableApplNoSet.contains(Integer.parseInt(requestedApplNo))) {
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/common/illegalAccess.jsp");
                rd.forward(request, response);
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }
}

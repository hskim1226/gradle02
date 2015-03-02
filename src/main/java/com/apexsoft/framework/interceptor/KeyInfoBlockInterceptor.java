package com.apexsoft.framework.interceptor;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by hanmomhanda on 15. 3. 2.
 */
public class KeyInfoBlockInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("######### KeyInfoBlockInterceptor.preHandle ###########");
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/common/illegalAccess.jsp");
        rd.forward(request, response);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("######### KeyInfoBlockInterceptor.postHandle ###########");
    }
}

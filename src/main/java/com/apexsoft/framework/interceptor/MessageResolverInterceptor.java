package com.apexsoft.framework.interceptor;

import com.apexsoft.framework.message.MessageResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by hanmomhanda on 15. 3. 15.
 */
public class MessageResolverInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    MessageResolver messageResolver;

    @Override
    public void postHandle( HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView ) throws Exception {

        modelAndView.addObject("msg", messageResolver);
    }
}

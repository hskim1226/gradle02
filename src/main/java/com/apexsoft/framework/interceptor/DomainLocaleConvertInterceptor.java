package com.apexsoft.framework.interceptor;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map.Entry;

/**
 * Created with IntelliJ IDEA.
 * User: go2zo
 * Date: 2014-09-15
 * Time: 오후 5:58
 */
public class DomainLocaleConvertInterceptor extends HandlerInterceptorAdapter {

    private Converter converter;

    public Converter getConverter() {
        return converter;
    }

    public void setConverter(Converter converter) {
        this.converter = converter;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        if (localeResolver == null) {
            throw new IllegalStateException("No LocaleResolver found: not in a DispatcherServlet request?");
        }

        Locale locale = localeResolver.resolveLocale(request);
        if (locale != null) {
            if (modelAndView != null) {
                ModelMap modelMap = modelAndView.getModelMap();
                Iterator<Entry<String, Object>> iter = modelMap.entrySet().iterator();
                while (iter.hasNext()) {
                    Entry<String, Object> entry = iter.next();
                    converter.convert(entry.getValue(), locale, request);
                }
            }
        }

        super.postHandle(request, response, handler, modelAndView);
    }

}

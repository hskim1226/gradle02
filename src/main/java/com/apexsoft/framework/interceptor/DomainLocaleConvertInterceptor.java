package com.apexsoft.framework.interceptor;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Created with IntelliJ IDEA.
 * User: go2zo
 * Date: 2014-09-15
 * Time: 오후 5:58
 */
public class DomainLocaleConvertInterceptor extends HandlerInterceptorAdapter {

    private DomainLocaleConverter converter = new DomainLocaleConverter();

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
                    convertValueLocale(entry.getValue(), locale);
                }
            }
        }

        super.postHandle(request, response, handler, modelAndView);
    }

    private void convertValueLocale(Object value, Locale locale) throws Exception {
        if (value instanceof Collection) {
            Collection collection = (Collection) value;
            for (Iterator iter = collection.iterator(); iter.hasNext();) {
                convertValueLocale (iter.next(), locale);
            }
        } else if (value instanceof Map) {
            Map map = (Map) value;
            for (Iterator<Entry> iter = map.entrySet().iterator(); iter.hasNext();) {
                Entry entry = iter.next();
                convertValueLocale (entry.getValue(), locale);
            }
        } else if (value != null) {
            Class clazz = value.getClass();
            Package pack = clazz.getPackage();

            if (clazz.getClassLoader() != null &&
                    pack.getName().startsWith("com.apexsoft") &&
                    pack.getName().endsWith("domain")) {
                converter.convert(value, locale);
            }
        }
    }
}

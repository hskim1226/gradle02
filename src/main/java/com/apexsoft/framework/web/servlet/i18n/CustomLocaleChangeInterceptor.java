package com.apexsoft.framework.web.servlet.i18n;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Created with IntelliJ IDEA.
 * User: go2zo
 * Date: 2014-09-15
 * Time: 오후 5:58
 */
public class CustomLocaleChangeInterceptor extends LocaleChangeInterceptor {

    private DomainLocaleConverter converter = new DomainLocaleConverter();

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String newLocale = request.getParameter( getParamName() );

        if( modelAndView != null ) {
            ModelMap modelMap = modelAndView.getModelMap();
            Iterator<Entry<String, Object>> iter = modelMap.entrySet().iterator();
            while (iter.hasNext()) {
                Entry<String, Object> entry = iter.next();
                convertValueLocale(entry.getValue(), newLocale);
            }
        }

        super.postHandle(request, response, handler, modelAndView);
    }

    private void convertValueLocale(Object value, String locale) throws Exception {
        if( value instanceof Collection) {
            Collection collection = (Collection) value;
            for( Iterator iter = collection.iterator(); iter.hasNext(); ) {
                convertValueLocale( iter.next(), locale );
            }
        } else if( value instanceof Map) {
            Map map = (Map) value;
            for( Iterator<Entry> iter = map.entrySet().iterator(); iter.hasNext(); ) {
                Entry entry = iter.next();
                convertValueLocale( entry.getValue(), locale );
            }
        } else {
            converter.convert(value, locale);
        }
    }
}

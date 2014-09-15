package com.apexsoft.framework.web.servlet.i18n;

import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: go2zo
 * Date: 2014-09-15
 * Time: 오후 6:47
 */
public class DomainLocaleConverter {

    private final static String PREFIX_GET = "get";
    private final static String PREFIX_SET = "set";
    private final static String SUFFIX = "Xx";

    public Object convert(Object domain, String newLocale) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class clazz = domain.getClass();
        Method[] methods = clazz.getMethods();

        newLocale = newLocale == null ? "" : newLocale;
        if( Locale.KOREA.equals(new Locale(newLocale)) ) {
            newLocale = "";
        };

//        if( StringUtils.hasText(newLocale) ) {
            String newSuffix = StringUtils.hasText(newLocale) ? SUFFIX + newLocale.toLowerCase() : "";

            for( int i = 0, len = methods.length; i < len; i++ ) {
                String getMethodName = methods[i].getName();
                Method setMethod = null, getMethod = methods[i];
                String setMethodName = null;

                if( getMethodName.startsWith(PREFIX_GET) && getMethodName.endsWith(newSuffix) ) {
                    setMethodName = PREFIX_SET + getMethodName.substring( PREFIX_GET.length(), getMethodName.length() - newSuffix.length() );
                    Object value = getMethod.invoke(domain);

                    for( int j = 0; j < methods.length; j++ ) {
                        if( setMethodName.equals( methods[j].getName() ) ) {
                            setMethod = methods[j];
                        }
                    }
                    if (setMethod != null && value != null) {
                        setMethod.invoke(domain, value);
                    }
                }
            }
//        }

        return domain;
    }
}

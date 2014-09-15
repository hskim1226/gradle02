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
        Method[] methods = clazz.getDeclaredMethods();

        newLocale = newLocale == null ? "" : newLocale;
        if( Locale.KOREA.equals(new Locale(newLocale)) ) {
            newLocale = "";
        };

        String newSuffix = StringUtils.hasText(newLocale) ? SUFFIX + newLocale.toLowerCase() : "";

        for (int i = 0, len = methods.length; i < len; i++) {
            String getterName = methods[i].getName();
            Method setter = null, getter = methods[i];
            String setterName = null;

            if (getterName.startsWith(PREFIX_GET) && getterName.endsWith(newSuffix)) {
                setterName = PREFIX_SET + getterName.substring(PREFIX_GET.length(), getterName.length() - newSuffix.length());
                Object value = getter.invoke(domain);

                for (int j = 0; j < methods.length; j++) {
                    if (setterName.equals(methods[j].getName())) {
                        setter = methods[j];
                    }
                }
                if (setter != null && value != null) {
                    setter.invoke(domain, value);
                }
            }
        }

        return domain;
    }
}

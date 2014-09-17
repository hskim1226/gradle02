package com.apexsoft.framework.interceptor;

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

    /**
     * locale에 따라 domain 객체의 멤버변수를 변환
     * ex) locale이 'en'인 경우,
     *     name <- nameXxen 으로 대체
     *
     * @param domain
     * @param locale
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public Object convert(Object domain, Locale locale) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class clazz = domain.getClass();
        Method[] methods = clazz.getDeclaredMethods();

        String newLocale = locale == null ? "" : locale.getLanguage();
        if (Locale.KOREA.equals(locale)) {
            newLocale = "";
        };
        if (!StringUtils.hasText(newLocale)) {
            return domain;
        }

        String newSuffix = StringUtils.hasText(newLocale) ? SUFFIX + newLocale.toLowerCase() : "";

        for (int i = 0, len = methods.length; i < len; i++) {
            String getterName = methods[i].getName();
            Method setter = null, getter = methods[i];
            String setterName = null;

            if (getterName.startsWith(PREFIX_GET) && getterName.endsWith(newSuffix) &&
                    String.class.isAssignableFrom(getter.getReturnType())) {
                setterName = PREFIX_SET + getterName.substring(PREFIX_GET.length(), getterName.length() - newSuffix.length());
                Object value = getter.invoke(domain);

                if (value instanceof String) {
                    // setter 검색
                    for (int j = 0; j < methods.length; j++) {
                        if (setterName.equals(methods[j].getName())) {
                            setter = methods[j];
                            break;
                        }
                    }
                    // setter 에 값 입력
                    if (setter != null) {
                        setter.invoke(domain, value);
                    }
                }
            }
        }

        return domain;
    }
}

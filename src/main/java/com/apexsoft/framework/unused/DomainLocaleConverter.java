package com.apexsoft.framework.unused;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: go2zo
 * Date: 2014-09-15
 * Time: 오후 6:47
 */
@Deprecated
public class DomainLocaleConverter implements Converter {

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
    private void doConvert(Object domain, Locale locale) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class clazz = domain.getClass();
        Method[] methods = clazz.getDeclaredMethods();

        String newLocale = locale == null ? "" : locale.getLanguage();
        if (Locale.KOREA.equals(locale)) {
            newLocale = "";
        };
        if (!StringUtils.hasText(newLocale)) {
            return;
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
    }

    private Locale getCurrentLocale(HttpServletRequest request) {
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        if (localeResolver == null) {
            throw new IllegalStateException("No LocaleResolver found: not in a DispatcherServlet request?");
        }

        return localeResolver.resolveLocale(request);
    }

    public void convert(Object domain, HttpServletRequest request) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        convert(domain, null, request);
    }

    public void convert(Object domain, Locale locale, HttpServletRequest request) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (locale == null) {
            locale = getCurrentLocale(request);
        }
        if (domain instanceof Collection) {
            Collection collection = (Collection) domain;
            for (Iterator iter = collection.iterator(); iter.hasNext();) {
                convert(iter.next(), locale, request);
            }
        } else if (domain instanceof Map) {
            Map map = (Map) domain;
            for (Iterator<Map.Entry> iter = map.entrySet().iterator(); iter.hasNext();) {
                Map.Entry entry = iter.next();
                convert(entry.getValue(), locale, request);
            }
        } else if (domain != null) {
            Class clazz = domain.getClass();
            Package pack = clazz.getPackage();

            if (clazz.getClassLoader() != null &&
                    pack.getName().startsWith("com.apexsoft") &&
                    pack.getName().endsWith("domain")) {
                doConvert(domain, locale);
            }
        }
    }
}

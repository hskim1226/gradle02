package com.apexsoft.framework.interceptor;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: go2zo
 * Date: 2014-09-18
 * Time: 오전 1:19
 */
public interface Converter {
    void convert(Object object, HttpServletRequest request) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException;
    void convert(Object object, Locale locale, HttpServletRequest request) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;
}

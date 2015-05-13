package com.apexsoft.framework.message;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.MessageSourceAccessor;

import java.util.Locale;

public class MessageResolver {

    private static MessageSourceAccessor messageSourceAccessor;

    public void setMessageSourceAccessor(MessageSourceAccessor messageSourceAccessor) {
        this.messageSourceAccessor = messageSourceAccessor;
    }

    public static String getMessage(String code) {
        return messageSourceAccessor.getMessage(code, LocaleContextHolder.getLocale());
    }

    public static String getMessage(String code, Locale locale) {
        return messageSourceAccessor.getMessage(code, locale);
    }

    public static String getMessage(String code, Object[] args) {
        return messageSourceAccessor.getMessage(code, args, LocaleContextHolder.getLocale());
    }

    public static String getMessage(String code, Object[] args, Locale locale) {
        return messageSourceAccessor.getMessage(code, args, locale);
    }

    // 아래는 jsp에서 ${msg.getMsg()} 형태로 호출하는 부분
    public String getMsg(String code) {
        return getMessage(code);
    }

    public String getMsg(String code, Locale locale) {
        return getMessage(code, locale);
    }
}

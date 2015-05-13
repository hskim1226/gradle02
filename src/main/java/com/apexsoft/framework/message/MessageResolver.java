package com.apexsoft.framework.message;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.MessageSourceAccessor;

import java.util.Locale;

public class MessageResolver {

    private static MessageSourceAccessor messageSourceAccessor;

    private Locale defaultLocale = Locale.KOREAN;

    public void setMessageSourceAccessor(MessageSourceAccessor messageSourceAccessor) {
        this.messageSourceAccessor = messageSourceAccessor;
    }

    public String getMessage(String code) {
        return messageSourceAccessor.getMessage(code, LocaleContextHolder.getLocale());
    }

    public String getMessage(String code, Locale locale) {
        return messageSourceAccessor.getMessage(code, locale);
    }

    public static String getMessageS(String code) {
        return messageSourceAccessor.getMessage(code, LocaleContextHolder.getLocale());
    }

    public static String getMessageS(String code, Object[] args) {
        return messageSourceAccessor.getMessage(code, args, LocaleContextHolder.getLocale());
    }

    public static String getMessageS(String code, Object[] args, Locale locale) {
        return messageSourceAccessor.getMessage(code, args, locale);
    }
}

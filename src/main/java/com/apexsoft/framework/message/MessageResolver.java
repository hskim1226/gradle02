package com.apexsoft.framework.message;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.support.MessageSourceAccessor;

import java.util.Locale;

public class MessageResolver {

    private MessageSourceAccessor messageSourceAccessor;

    private Locale defaultLocale = Locale.KOREAN;

    public void setMessageSourceAccessor(MessageSourceAccessor messageSourceAccessor) {
        this.messageSourceAccessor = messageSourceAccessor;
    }

    public String getMessage(String code) {
        return messageSourceAccessor.getMessage(code, defaultLocale);
    }

    public String getMessage(String code, Locale locale) {
        return messageSourceAccessor.getMessage(code, locale);
    }

}

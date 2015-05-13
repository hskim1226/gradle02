package com.apexsoft.framework.mail;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hanmomhanda on 14. 7. 25.
 */
public abstract class Mail {

    @NotNull
    String from;

    @NotNull
    String[] to;

    String subject;

    String[] cc;

    String[] bcc;

    String contents;

    Map<Object, String> contentsParam = new HashMap<Object, String>();

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String[] getTo() {
        return to;
    }

    public void setTo(String[] to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String[] getCc() {
        return cc;
    }

    public void setCc(String[] cc) {
        this.cc = cc;
    }

    public String[] getBcc() {
        return bcc;
    }

    public void setBcc(String[] bcc) {
        this.bcc = bcc;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Map<Object, String> getContentsParam() {
        return contentsParam;
    }

    public Mail withContentsParam(Object key, String value) {
        contentsParam.put(key, value);
        return this;
    }

    public abstract void makeContents();
}

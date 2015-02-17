package com.apexsoft.framework.mail;

import javax.validation.constraints.NotNull;

/**
 * Created by hanmomhanda on 14. 7. 25.
 */
public class Mail {

    @NotNull
    String from;

    @NotNull
    String to;

    String subject;

    String cc;

    String bc;

    String contents;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getBc() {
        return bc;
    }

    public void setBc(String bc) {
        this.bc = bc;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}

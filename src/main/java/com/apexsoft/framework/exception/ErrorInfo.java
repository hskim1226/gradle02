package com.apexsoft.framework.exception;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by hanmomhanda on 15. 2. 21.
 */
public class ErrorInfo {
    Map<String, String> info;
    Throwable error;
    List<StackTraceElement> stackTraceElementList;

    public ErrorInfo() {

    }

    public ErrorInfo(Map info) {
        this.info = info;
    }

    public ErrorInfo(Map info, List<StackTraceElement> stackTrace) {
        this.info = info;
        this.stackTraceElementList = stackTrace;
    }

    public ErrorInfo(Map info, Throwable error) {
        this.info= info;
        this.error = error;
    }

    public Map<String, String> getInfo() {
        return info;
    }

    public void setInfo( Map<String, String> info ) {
        this.info = info;
    }

    public Throwable getError() {
        return error;
    }

    public void setError( Throwable error ) {
        this.error = error;
    }

    public List<StackTraceElement> getStackTraceElementList() {
        return stackTraceElementList;
    }

    public void setStackTraceElementList( List<StackTraceElement> stackTraceElementList ) {
        this.stackTraceElementList = stackTraceElementList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : info.entrySet()) {
            sb.append("{").append(entry.getKey()).append(" : [").append(entry.getValue()).append("]} ");
        }
        return sb.toString();
    }
}
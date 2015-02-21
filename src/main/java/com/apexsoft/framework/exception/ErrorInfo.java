package com.apexsoft.framework.exception;

import java.util.List;

/**
 * Created by hanmomhanda on 15. 2. 21.
 */
public class ErrorInfo {
    int applNo;
    String userId;
    Throwable error;
    List<StackTraceElement> stackTraceElementList;

    public ErrorInfo() {

    }

    public ErrorInfo(int applNo, String userId) {
        this.applNo = applNo;
        this.userId = userId;
    }

    public ErrorInfo(int applNo, String userId, List<StackTraceElement> stackTrace) {
        this.applNo = applNo;
        this.userId = userId;
        this.stackTraceElementList = stackTrace;
    }

    public ErrorInfo(int applNo, String userId, Throwable error) {
        this.applNo = applNo;
        this.userId = userId;
        this.error = error;
    }

    public int getApplNo() {
        return applNo;
    }

    public void setApplNo(int applNo) {
        this.applNo = applNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("applNo : [").append(this.applNo)
          .append("], userId : [").append(this.userId).append("]");
        return sb.toString();
    }
}
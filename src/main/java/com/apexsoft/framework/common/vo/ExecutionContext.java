package com.apexsoft.framework.common.vo;

import com.apexsoft.framework.exception.ErrorInfo;

/**
 * Created with IntelliJ IDEA.
 * User: zbum
 * Date: 2014. 7. 21.
 * Time: 오후 10:19
 * To change this template use File | Settings | File Templates.
 */
public class ExecutionContext <T>{

    public static String SUCCESS = "SUCCESS";
    public static String FAIL = "FAIL";

    private String result;
    private T data;
    private String message;
    private String errCode;
    private ErrorInfo errorInfo;

    public ExecutionContext() {
        this.result = SUCCESS;
    }

    public ExecutionContext(String result) {
        this.result = result;
    }

    public ExecutionContext(String result, String message) {
        this.result = result;
        this.message = message;
    }

    public ExecutionContext(String result, String message, String errCode) {
        this.result = result;
        this.message = message;
        this.errCode = errCode;
    }

    public ExecutionContext(String result, String message, T data) {
        this.result = result;
        this.message = message;
        this.data = data;
    }

    public ExecutionContext(String result, String message, T data, String errCode) {
        this.result = result;
        this.message = message;
        this.data = data;
        this.errCode = errCode;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public ErrorInfo getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(ErrorInfo errorInfo) {
        this.errorInfo = errorInfo;
    }

    @Override
    public String toString() {
        return "ExecutionContext{" +
                "result='" + result + '\'' +
                ", message='" + message + '\'' +
                '}';
    }


}

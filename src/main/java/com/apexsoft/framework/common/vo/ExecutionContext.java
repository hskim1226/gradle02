package com.apexsoft.framework.common.vo;

/**
 * Created with IntelliJ IDEA.
 * User: zbum
 * Date: 2014. 7. 21.
 * Time: 오후 10:19
 * To change this template use File | Settings | File Templates.
 */
public class ExecutionContext {

    public static String SUCCESS = "SUCCESS";
    public static String FAIL = "FAIL";

    private String result;

    private String message;

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

    @Override
    public String toString() {
        return "ExecutionContext{" +
                "result='" + result + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}

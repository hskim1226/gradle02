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

    private String result = SUCCESS;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}

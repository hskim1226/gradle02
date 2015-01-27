package com.apexsoft.framework.exception;

import com.apexsoft.framework.common.vo.ExecutionContext;

/**
 * Created by hanmomhanda on 15. 1. 27.
 */
public class YSBizException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String errorMessage;

    private ExecutionContext ec;

    public YSBizException() {
    }

    public YSBizException(String userMessage) {
        super(userMessage);
    }

    public YSBizException(String userMessage, Throwable cause) {
        super(userMessage, cause);
    }

    public YSBizException(Throwable cause) {
        super(cause);
    }

    public YSBizException(String userMessage, String errorMessage) {
        super(userMessage);
        this.errorMessage = errorMessage;
    }

    public YSBizException(String userMessage, Throwable cause, String errorMessage) {
        super(errorMessage, cause);
        this.errorMessage = errorMessage;
    }

    public YSBizException(ExecutionContext ec) {
        super(ec.getMessage());
        this.ec = ec;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

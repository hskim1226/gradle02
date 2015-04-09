package com.apexsoft.framework.exception;

import com.apexsoft.framework.common.vo.ExecutionContext;

/**
 * Created by hanmomhanda on 15. 1. 27.
 */
public class YSBizNoticeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String errorMessage;

    private ExecutionContext ec;

    public YSBizNoticeException() {
    }

    public YSBizNoticeException(String userMessage) {
        super(userMessage);
    }

    public YSBizNoticeException(String userMessage, Throwable cause) {
        super(userMessage, cause);
    }

    public YSBizNoticeException(Throwable cause) {
        super(cause);
    }

    public YSBizNoticeException(String userMessage, String errorMessage) {
        super(userMessage);
        this.errorMessage = errorMessage;
    }

    public YSBizNoticeException(String userMessage, Throwable cause, String errorMessage) {
        super(errorMessage, cause);
        this.errorMessage = errorMessage;
    }

    public YSBizNoticeException(ExecutionContext ec) {
        super(ec.getMessage());
        this.ec = ec;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ExecutionContext getExecutionContext() {
        return ec;
    }

    public void setExecutionContext(ExecutionContext ec) {
        this.ec = ec;
    }
}

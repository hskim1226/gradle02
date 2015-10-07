package com.apexsoft.framework.persistence.file.exception;

import com.apexsoft.framework.common.vo.ExecutionContext;
import org.springframework.core.NestedRuntimeException;

/**
 * Created by hanmomhanda on 15. 10. 8.
 */
public class NotFoundInS3Exception extends NestedRuntimeException {

    private final ExecutionContext ec;
    private final String userMessageCode;
    private final String errorCode;

    public NotFoundInS3Exception(ExecutionContext ec, String userMessageCode, String errorCode) {
        super(ec.getMessage());
        this.ec = ec;
        this.userMessageCode = userMessageCode;
        this.errorCode = errorCode;
    }

    public ExecutionContext getEc() {
        return ec;
    }

    public String getUserMessageCode() {
        return userMessageCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}

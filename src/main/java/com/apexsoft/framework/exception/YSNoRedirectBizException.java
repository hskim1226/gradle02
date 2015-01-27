package com.apexsoft.framework.exception;

import com.apexsoft.framework.common.vo.ExecutionContext;

/**
 * Created by hanmomhanda on 15. 1. 27.
 */
public class YSNoRedirectBizException extends YSBizException {

    private String targetView;

    public YSNoRedirectBizException(ExecutionContext ec) {
        super(ec);
    }

    public String getTargetView() {
        return targetView;
    }

    public void setTargetView(String targetView) {
        this.targetView = targetView;
    }
}

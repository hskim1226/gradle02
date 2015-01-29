package com.apexsoft.framework.exception;

import com.apexsoft.framework.common.vo.ExecutionContext;
import org.osgi.framework.namespace.ExecutionEnvironmentNamespace;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hanmomhanda on 15. 1. 27.
 */
public class YSNoRedirectBizException extends YSBizException {

    private String targetView;

    private Map<String, Object> previousDataMap = new HashMap<String, Object>();

    public YSNoRedirectBizException(ExecutionContext ec) {
        super(ec);
    }

    public String getTargetView() {
        return targetView;
    }

    public void setTargetView(String targetView) {
        this.targetView = targetView;
    }

    public Map<String, Object> getPreviousDataMap() {
        return previousDataMap;
    }

    public void setPreviousDataMap(Map<String, Object> previousDataMap) {
        this.previousDataMap = previousDataMap;
    }
}

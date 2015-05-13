package com.apexsoft.ysprj.applicants.common.util;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.exception.YSBizNoticeException;
import com.apexsoft.framework.message.MessageResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by hanmomhanda on 15. 2. 28.
 */
@Component
public class WebUtil {

    public void blockGetMethod(HttpServletRequest request, Object distinguisher) {
        if ("GET".equals(request.getMethod()) && distinguisher == null) {
            ExecutionContext ec = new ExecutionContext(ExecutionContext.FAIL);
            ec.setMessage(MessageResolver.getMessage("U00011"));
            ec.setErrCode("ERR2011");
            throw new YSBizNoticeException(ec);
        }
    }

}

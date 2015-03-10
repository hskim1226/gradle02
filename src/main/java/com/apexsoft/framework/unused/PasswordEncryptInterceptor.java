package com.apexsoft.framework.unused;

import com.apexsoft.framework.security.UserSessionVO;
import com.apexsoft.ysprj.applicants.common.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by hanmomhanda on 15. 3. 5.
 *
 * 비밀번호 암호화를 인터셉터에서 하려 했으나
 * 아래와 같이 구현해도
 * 컨트롤러에는 암호화되지 않은 값이 전달되어 폐기
 */
@Deprecated
public class PasswordEncryptInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    CommonService commonService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 사용자 비밀번호 암호화
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        RequestWrapper req = new RequestWrapper(request);
        Enumeration parameterNames = req.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = (String)parameterNames.nextElement();
            if (paramName.indexOf("pswd") >= 0) {
                String paramVal = req.getParameter(paramName);
                if (paramVal !=null && paramVal.length() > 0)
                    req.setParameter(paramName, passwordEncoder.encode(req.getParameter(paramName)));
            }
        }
        request = req;

        return true;
    }

    private class RequestWrapper extends SecurityContextHolderAwareRequestWrapper {
//    private class RequestWrapper extends HttpServletRequestWrapper {

        Map<String, Object> params;
        public RequestWrapper(HttpServletRequest request) {
            super(request, null);
            this.params = new HashMap<String, Object>(request.getParameterMap());
        }

        @Override
        public Map getParameterMap() {
            return Collections.unmodifiableMap(params);
        }

        @Override
        public Enumeration getParameterNames() {
            return Collections.enumeration(params.keySet());
        }

        @Override
        public String getParameter(String name) {
            return getParameterValues(name)[0];
        }

        @Override
        public String[] getParameterValues(String name) {
            String[] result = null;
            String[] temp = (String[])params.get(name);
            if (temp != null) {
                result = new String[temp.length];
                System.arraycopy(temp, 0, result, 0, temp.length);
            }
            return result;
        }

        public void setParameter(String name, String value) {
            String[] aValue = {value};
            setParameter(name, aValue);
        }

        public void setParameter(String name, String[] values) {
            params.put(name, values);
        }
    }
}

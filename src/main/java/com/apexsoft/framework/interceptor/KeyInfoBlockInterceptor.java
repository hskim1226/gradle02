package com.apexsoft.framework.interceptor;

import com.apexsoft.framework.security.UserSessionVO;
import com.apexsoft.ysprj.applicants.common.service.CommonService;
import com.apexsoft.ysprj.code.AuthorityType;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by hanmomhanda on 15. 3. 2.
 */
public class KeyInfoBlockInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    CommonService commonService;

    /**
     * 로그인 한 사용자가 다른 사람의 applNo로 정보를 요청할 때 blocking
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        List<String> requestedApplNoList = new ArrayList<String>();
        Enumeration parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = (String)parameterNames.nextElement();
            if (paramName.indexOf("applNo") >= 0) {
                String paramVal = request.getParameter(paramName);
                if (paramVal !=null && paramVal.length() > 0)
                    requestedApplNoList.add(request.getParameter(paramName));
            }
        }
        // *download/{applNo}, *delete/{applNo}/{docSeq} 같은 형식인 경우
        String url = request.getRequestURI();
        String lowerUrl = url.toLowerCase();
        int indexOfDownload = lowerUrl.indexOf("download");
        if (indexOfDownload > 0) {
            String tmpUrl = url.substring(indexOfDownload);
            String applNo = extractApplNo(tmpUrl);
            if (applNo.length() > 0)
                requestedApplNoList.add(applNo);
        }
        int indexOfDelete = lowerUrl.indexOf("delete");
        if (indexOfDelete > 0) {
            String tmpUrl = url.substring(indexOfDelete);
            String applNo = extractApplNo(tmpUrl);
            if (applNo.length() > 0)
                requestedApplNoList.add(applNo);
        }
        int indexOfRecommend = lowerUrl.indexOf("application/recommend");
        if (indexOfRecommend > 0) {
            return true;
        }

        SecurityContext sc = (SecurityContext)request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        Authentication auth = sc.getAuthentication();
        UserSessionVO userSessionVO = (UserSessionVO)auth.getPrincipal();
        String userId = userSessionVO.getUsername();
        Collection<? extends GrantedAuthority> userRoles = userSessionVO.getAuthorities();
        boolean isValidAccess = false;

        List<Integer> availableApplNos = commonService.retrieveAvailableApplNos(userId);
        Set<Integer> availableApplNoSet = new HashSet<Integer>(availableApplNos);

        for(String requestedApplNo : requestedApplNoList) {
            if (!availableApplNoSet.contains(Integer.parseInt(requestedApplNo))) {
                for (GrantedAuthority authority : userRoles) {
                    if (authority.getAuthority().equals(AuthorityType.ROLE_SYSADMIN.getValue())) {
                        isValidAccess = true;
                    }
                }
                if (!isValidAccess) {
                    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/common/illegalAccess.jsp");
                    rd.forward(request, response);
                    return false;
                }
            }
        }

        return true;
    }

    private String extractApplNo(String substring) {
        int indexOfSlash = substring.indexOf('/');
        if (indexOfSlash > 0) {
            String tmpUrl2 = substring.substring(indexOfSlash+1);
            if (NumberUtils.isDigits(tmpUrl2)) {
                return tmpUrl2;
            } else {
                int indexOfSlash2 = tmpUrl2.indexOf('/');
                if (indexOfSlash2 > 0) {
                    String no = tmpUrl2.substring(0, indexOfSlash2);
                    if (NumberUtils.isNumber(no)) {
                        return no;
                    }
                }
            }
        }
        return "";
    }
}

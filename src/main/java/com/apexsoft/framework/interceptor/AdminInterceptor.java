package com.apexsoft.framework.interceptor;

import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.security.UserSessionVO;
import com.apexsoft.ysprj.admin.domain.CommonAdminInfo;
import com.apexsoft.ysprj.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by hanmomhanda on 15. 3. 15.
 */
public class AdminInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    AdminService adminService;

    @Override
    public void postHandle(  HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView ) throws Exception {


        //adminService.getApplicantDetail
        if (modelAndView != null) {
            ServletContext o = request.getSession().getServletContext();
            Object sqc = request.getSession();
            SecurityContext sc = (SecurityContext)request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
            Authentication auth = sc.getAuthentication();
            UserSessionVO userSessionVO = (UserSessionVO)auth.getPrincipal();

//            CommonAdminInfo info = adminService.retrieveCommonAdminInfo();
            Map<String, Object> eMap = adminService.retrieveInterceptorInfo();
//            info.setAdminName(userSessionVO.getUsername());
            CommonAdminInfo adminInfo = (CommonAdminInfo)eMap.get("adminInfo");
            adminInfo.setAdminName(userSessionVO.getUsername());
            modelAndView.addObject("applCntList",eMap.get("applCntList"));
            modelAndView.addObject("adminInfo",eMap.get("adminInfo"));


        }
    }
}

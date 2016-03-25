package com.apexsoft.framework.security.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationFailureHandler implements org.springframework.security.web.authentication.AuthenticationFailureHandler {
	
    static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationFailureHandler.class);

	private String userTargetUrl;
	private String adminTargetUrl;

	public void setUserTargetUrl(String userTargetUrl) {
		this.userTargetUrl = userTargetUrl;
	}

	public void setAdminTargetUrl(String adminTargetUrl) {
		this.adminTargetUrl = adminTargetUrl;
	}

	@Override
	public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse res, AuthenticationException exception) throws IOException,
            ServletException {

		logger.debug(exception.getMessage() + "로그인 실패");

		String targetUrl;

		String referer = req.getHeader("referer");
		if (referer.contains("admin")) {
			req.setAttribute("ADMIN_LOGIN_FAILURE", true);
			targetUrl = adminTargetUrl;
		} else {
			req.setAttribute("LOGIN_FAILURE", true);
			targetUrl = userTargetUrl;
		}
		res.sendRedirect(req.getContextPath() + targetUrl);

// sendRedirect 방식을 쓰면 일단 요청이 사용자에게 반환되고, 사용자가 다시 /user/login?auth=fail 로 GET 요청을 날린 것과 같게 된다.
// 장점 : 사용자에게 보여지는 주소 창에 j_spring_security_check.do 와 같은 내부 내용을 보여주지 않는다.
// 단점 : req.setAttribute() 한 것이 무효화 되어 ?auth=fail 로 다시 서버에 요청을 보내서 로그인 실패임을 알린다.
//        res.sendRedirect(req.getContextPath() + "/user/login?auth=fail");

// forward 방식을 쓰면 요청을 사용자에게 반환하지 않고 req, res 객체를 다른 서블릿으로 보낸다.
// 장점 : req.setAttribute("LOGIN_FAILURE", true); 처럼 req, res에 수행한 내용이 무효화 되지 않고 유지된다.
// 단점 : 사용자에게 보여지는 주소 창에 보여지면 안되는 내부 주소인 j_spring_security_check.do 가 그대로 노출된다.
//		RequestDispatcher dispatcher = req.getRequestDispatcher("/user/login");
//		dispatcher.forward(req, res);
	}
}

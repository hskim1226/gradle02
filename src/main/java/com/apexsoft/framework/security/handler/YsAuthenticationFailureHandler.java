package com.apexsoft.framework.security.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class YsAuthenticationFailureHandler implements org.springframework.security.web.authentication.AuthenticationFailureHandler {
	
    static final Logger logger = LoggerFactory.getLogger(YsAuthenticationFailureHandler.class);


	@Override
	public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse res, AuthenticationException exception) throws IOException,
            ServletException {
		logger.error("", exception);
		
		req.setAttribute("LOGIN_FAILURE", true);

        res.sendRedirect(req.getContextPath()+"/user/login");
//		RequestDispatcher dispatcher = req.getRequestDispatcher("/user/login");
//
//		dispatcher.forward(req, res);
	}
}

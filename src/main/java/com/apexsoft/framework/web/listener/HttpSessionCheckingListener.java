package com.apexsoft.framework.web.listener;

import java.util.Date;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpSessionCheckingListener implements HttpSessionListener {

	private static final Logger logger = LoggerFactory.getLogger(HttpSessionCheckingListener.class);

	public HttpSessionCheckingListener() {
		logger.info("HttpSessionCheckingListener");
	}

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		if (logger.isDebugEnabled()) {
			logger.debug(new StringBuffer("Session ID")
					.append(se.getSession().getId())
					.append(" created at ")
					.append(new Date().toString())
					.toString());
		}
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		if (logger.isDebugEnabled()) {
			logger.debug(new StringBuffer("Session ID")
					.append(se.getSession().getId())
					.append(" destroyed at ")
					.append(new Date().toString())
					.toString());
		}
	}

}

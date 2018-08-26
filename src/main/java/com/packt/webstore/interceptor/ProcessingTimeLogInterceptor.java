package com.packt.webstore.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class ProcessingTimeLogInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(ProcessingTimeLogInterceptor.class);

	/**
	 * This implementation always returns {@code true}.
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		logger.info("preHandle " + request.getRequestURL());

		long startTime = System.currentTimeMillis();
		request.setAttribute("startTime", startTime);

		return true;
	}

	/**
	 * This implementation is empty.
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {

		logger.info("postHandle " + request.getRequestURL());

		String queryString = request.getQueryString() == null ? "" : "?" + request.getQueryString();
		String path = request.getRequestURL() + queryString;

		long startTime = (Long) request.getAttribute("startTime");
		long endTime = System.currentTimeMillis();

		logger.info(String.format("%s millisecond taken to process the request %s", (endTime - startTime), path));

	}

	/**
	 * This implementation is empty.
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable Exception ex) throws Exception {
	}
}

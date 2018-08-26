package com.packt.webstore.interceptor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

public class RequestMappingInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	@Lazy
	private RequestMappingHandlerMapping handlerMapping;

	private String errorRedirect;

	public RequestMappingInterceptor() {
		super();
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws IOException {

		String requestMapping = request.getRequestURI().substring(0, request.getRequestURI().length());
		List<String> requestMappingList = new ArrayList<>();

		handlerMapping.getHandlerMethods().forEach((k, v) -> {
			requestMappingList.add(k.toString());
		});

		for (String req : requestMappingList) {
			if (req.equals(requestMapping)) {
				return true;
			}
		}

		response.sendRedirect(errorRedirect);

		return false;
	}

	public String getErrorRedirect() {
		return errorRedirect;
	}

	public void setErrorRedirect(String errorRedirect) {
		this.errorRedirect = errorRedirect;
	}

}

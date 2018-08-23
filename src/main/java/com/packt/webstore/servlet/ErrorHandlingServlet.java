package com.packt.webstore.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "ErrorHandlingServlet", description = "Example Servlet Using Annotations", urlPatterns = {"/error" })
public class ErrorHandlingServlet extends HttpServlet {

	private static final Logger logger = LoggerFactory.getLogger(ErrorHandlingServlet.class);

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("doGet");

		response.sendRedirect(request.getContextPath() + "/errorPage");
	}
}

package com.packt.webstore.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class CustomControllerAdvice {

	private static final Logger logger = LoggerFactory.getLogger(CustomControllerAdvice.class);

	@ExceptionHandler(NoHandlerFoundException.class)
	public String handle(Exception ex) {
		logger.info("handle");
		return "redirect:/404";
	}

	@RequestMapping(value = { "/404" }, method = RequestMethod.GET)
	public String NotFoudPage() {
		logger.info("NotFoudPage");
		return "errorPage";

	}
}

package com.packt.webstore.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.NoHandlerFoundException;

@Controller
public class ErrorController {

	private static final Logger logger = LoggerFactory.getLogger(ErrorController.class);

	@RequestMapping("/errorPage")
	public String errorPage(Model model) throws NoHandlerFoundException {
		logger.info("errorPage");

		return "errorPage";
	}
}

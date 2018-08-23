package com.packt.webstore.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.NoHandlerFoundException;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping("/")
	public String welcome(Model model) throws NoHandlerFoundException {
		logger.info("welcome");

		model.addAttribute("greeting", "Welcome to Web Store !");
		model.addAttribute("tagline", "The one and only amazing web store");

		return "welcome";
	}

}

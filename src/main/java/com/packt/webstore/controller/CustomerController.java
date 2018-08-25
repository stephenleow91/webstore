package com.packt.webstore.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.packt.webstore.domain.Customer;
import com.packt.webstore.service.CustomerService;

@Controller
public class CustomerController {

	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private CustomerService customerService;

	@InitBinder
	public void initialiseBinder(WebDataBinder binder) {
		binder.setAllowedFields("customerId", "name", "address");
	}

	@RequestMapping("/customers")
	public String list(Model model) {
		logger.info("list");

		model.addAttribute("customers", customerService.getAllCustomers());

		return "customers";
	}

	@RequestMapping(value = "/customers/add", method = RequestMethod.GET)
	public String getAddNewCustomerForm(@ModelAttribute("newCustomer") Customer newCustomer, Model model) {
		logger.info("getAddNewCustomerForm");

		model.addAttribute("newCustomer", newCustomer);

		return "addCustomer";
	}

	@RequestMapping(value = "/customers/add", method = RequestMethod.POST)
	public String processAddNewCustomerForm(@ModelAttribute("newCustomer") Customer newCustomer, BindingResult result) {
		logger.info("processAddNewCustomerForm");

		String[] suppressedFields = result.getSuppressedFields();

		if (suppressedFields.length > 0) {
			throw new RuntimeException("Attemting to bind disallowrd fields : "
					+ StringUtils.arrayToCommaDelimitedString(suppressedFields));
		}

		customerService.addCustomer(newCustomer);

		return "redirect:/customers";
	}

}

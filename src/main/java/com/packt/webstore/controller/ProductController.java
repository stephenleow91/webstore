package com.packt.webstore.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.packt.webstore.domain.repository.ProductRepository;
import com.packt.webstore.service.ProductService;

@Controller
public class ProductController {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductService productService;

	@RequestMapping("/products")
	public String list(Model model) {
		logger.info("list");

		// Product iphone = new Product("P1234", "iPhone 6s", new BigDecimal(500));
		// iphone.setDescription("Apple iPhone 6s smartphone with 4.00-inch 640x1136
		// display and 8-megapixel rear camera");
		// iphone.setCategory("Smartphone");
		// iphone.setManufacturer("Apple");
		// iphone.setUnitsInStock(1000);

		model.addAttribute("products", productRepository.getAllProducts());

		return "products";
	}

	@RequestMapping("/update/stock")
	public String updateStock(Model model) {
		logger.info("list");

		productService.updateAllStock();

		return "redirect:/products";
	}

}

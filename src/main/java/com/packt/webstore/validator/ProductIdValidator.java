package com.packt.webstore.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.packt.webstore.domain.Product;
import com.packt.webstore.exception.ProductNotFoundException;
import com.packt.webstore.service.ProductService;

public class ProductIdValidator implements ConstraintValidator<ProductId, String> {

	private static final Logger logger = LoggerFactory.getLogger(ProductIdValidator.class);

	@Autowired
	private ProductService productService;

	@Override
	public void initialize(ProductId constraintAnnotation) {
		// intentionally left blank; this is the place to initialize the constraint annotation for any sensible default values.
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		logger.info("isValid");

		Product product;

		try {
			product = productService.getProductById(value);
		}catch(ProductNotFoundException e) {
			return true;
		}

		if(product != null) {
			return false;
		}
		return true;
	}

}

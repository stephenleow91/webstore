package com.packt.webstore.validator;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CategoryValidator implements ConstraintValidator<Category, String> {

	private static final Logger logger = LoggerFactory.getLogger(CategoryValidator.class);

	private List<String> allowedCategories;

	/**
	 * @param allowedCategories
	 */
	public CategoryValidator() {
		super();
		allowedCategories = new ArrayList<>();
		allowedCategories.add("Laptop");
		allowedCategories.add("Smartphone");
		allowedCategories.add("Tablet");
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		logger.info("isValid");

		for (String category : allowedCategories) {
			if (category.equalsIgnoreCase(value)) {
				return true;
			}
		}

		return false;
	}

}

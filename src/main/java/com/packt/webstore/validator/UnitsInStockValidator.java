package com.packt.webstore.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnitsInStockValidator implements ConstraintValidator<UnitsInStock, Long> {

	private static final Logger logger = LoggerFactory.getLogger(UnitsInStockValidator.class);

	@Override
	public boolean isValid(Long value, ConstraintValidatorContext context) {
		logger.info("isValid");

		if (value == null) {
			return false;
		}

		if (value.compareTo(-1L) <= 0) {
			return false;
		}

		return true;
	}

}

package com.packt.webstore.validator;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.packt.webstore.domain.Product;

public class ProductValidator implements Validator {

	private static final Logger logger = LoggerFactory.getLogger(ProductValidator.class);

	@Autowired
	private javax.validation.Validator beanValidator;

	private Set<Validator> springValidators;

	public Set<Validator> getSpringValidators() {
		return springValidators;
	}

	public void setSpringValidators(Set<Validator> springValidators) {
		this.springValidators = springValidators;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Product.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		logger.info("validate");

		Set<ConstraintViolation<Object>> constraintViolations = beanValidator.validate(target);

		for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
			String propertPath = constraintViolation.getPropertyPath().toString();
			String message = constraintViolation.getMessage();

			errors.rejectValue(propertPath, "", message);
		}

		for (Validator validator : springValidators) {
			validator.validate(target, errors);
		}

	}

}

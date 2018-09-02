package com.packt.webstore.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.packt.webstore.domain.Product;

@Component
public class ProductImageValidator implements Validator {

	private Long allowedSize;

	public ProductImageValidator() {
		super();
		this.allowedSize = 10000000L;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Product.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Product product = (Product) target;

		if (product.getProductImage() != null && allowedSize.compareTo(product.getProductImage().getSize()) < 0) {
			errors.rejectValue("productImage", "com.packt.webstore.validator.ProductImageValidator.message");
		}
	}

}

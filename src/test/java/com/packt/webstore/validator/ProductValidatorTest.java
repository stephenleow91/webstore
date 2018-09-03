package com.packt.webstore.validator;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.validation.BindException;
import org.springframework.validation.ValidationUtils;

import com.packt.webstore.configuration.WebApplicationContextConfig;
import com.packt.webstore.domain.Product;

@ExtendWith(SpringExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
@ContextConfiguration(classes = WebApplicationContextConfig.class)
@WebAppConfiguration
public class ProductValidatorTest {

	@Autowired
	private ProductValidator productValidator;

	@Test
	public void product_without_UnitPrice_should_be_invalid() {
		// Arrange
		Product product = new Product();
		BindException bindException = new BindException(product, "product");

		// Act
		ValidationUtils.invokeValidator(productValidator, product, bindException);

		// Assert
		Assertions.assertEquals(3, bindException.getErrorCount());
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 999 " + bindException.getMessage());
		Assertions.assertTrue(bindException.getLocalizedMessage().contains("Category Invalid. It cannot be empty."));

	}

	@Test
	public void product_with_existing_productId_invalid() {
		// Arrange
		Product product = new Product("P1234", "iPhone 5s", new BigDecimal(500));
		product.setCategory("Tablet");
		BindException bindException = new BindException(product, "product");

		// Act
		ValidationUtils.invokeValidator(productValidator, product, bindException);

		// Assert
		Assertions.assertEquals(1, bindException.getErrorCount());
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 666 " + bindException.getMessage());
		Assertions.assertTrue(bindException.getLocalizedMessage().contains("A product already exists with this product id."));

	}

	@Test
	public void a_valid_product_should_not_get_any_error_during_validation() {
		// Arrange
		Product product = new Product("P9876", "iPhone 5s", new BigDecimal(500));
		product.setCategory("Tablet");
		BindException bindException = new BindException(product, "product");

		// Act
		ValidationUtils.invokeValidator(productValidator, product, bindException);

		// Assert
		Assertions.assertEquals(0, bindException.getErrorCount());

	}
}

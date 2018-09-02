package com.packt.webstore.domain;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class CartItemTest {

	private CartItem cartItem;

	@BeforeAll
	public void setup() {
		cartItem = new CartItem("1");
	}

	@Test
	public void cartItem_total_price_should_be_equal_to_product_unit_price_in_case_of_single_quantity() {
		// Arrange
		Product iphone = new Product("P1234", "iPhone 5s", new BigDecimal(500));
		cartItem.setProduct(iphone);
		cartItem.setQuantity(1);
		cartItem.updateTotalPrice();

		// Act
		BigDecimal totalPrice = cartItem.getTotalPrice();

		// Assert
		Assertions.assertEquals(iphone.getUnitPrice(), totalPrice);
	}

}

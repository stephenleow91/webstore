package com.packt.webstore.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.packt.webstore.configuration.WebApplicationContextConfig;
import com.packt.webstore.domain.Product;

@ExtendWith(SpringExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
@ContextConfiguration(classes = WebApplicationContextConfig.class)
@WebAppConfiguration
public class ProductControllerTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@BeforeAll
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void testGetProducts() throws Exception {
		this.mockMvc.perform(get("/market/products")).andExpect(model().attributeExists("products"));
	}

	@Test
	public void testGetProductById() throws Exception {
		// Arrange
		Product product = new Product("P1234", "iPhone 5s", new BigDecimal(500));

		// Act & Assert
		this.mockMvc.perform(get("/market/product").param("id", "P1234"))
			.andExpect(model().attribute("product", product))
			.andExpect(model().attributeExists("product"));
	}

}

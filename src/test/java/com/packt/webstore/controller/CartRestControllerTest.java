package com.packt.webstore.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.packt.webstore.configuration.WebApplicationContextConfig;

@ExtendWith(SpringExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
@ContextConfiguration(classes = WebApplicationContextConfig.class)
@WebAppConfiguration
public class CartRestControllerTest {

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	MockHttpSession session;

	private MockMvc mockMvc;

	@BeforeAll
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void read_method_should_return_correct_cart_Json_object() throws Exception {
		// Arrange
		this.mockMvc.perform(put("/rest/cart/add/P1234") //
				.session(session)) //
				.andExpect(status().is(200));

		// Act
		this.mockMvc.perform(get("/rest/cart/" + session.getId()) //
				.session(session)) //
				.andExpect(status().isOk()) //
				.andExpect(jsonPath("$.cartItems[0].product.productId").value("P1234"));
	}

}

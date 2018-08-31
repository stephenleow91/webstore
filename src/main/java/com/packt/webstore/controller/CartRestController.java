package com.packt.webstore.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.packt.webstore.domain.Cart;
import com.packt.webstore.dto.CartDTO;
import com.packt.webstore.service.CartService;

@RestController
@RequestMapping(value = "rest/cart")
public class CartRestController {

	private static final Logger logger = LoggerFactory.getLogger(CartRestController.class);

	@Autowired
	private CartService cartService;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public void create(@RequestBody CartDTO cartDto) {
		logger.info("create");

		cartService.create(cartDto);
	}

	@RequestMapping(value = "/{cartId}", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public Cart read(@PathVariable(value = "cartId") String cartId) {
		logger.info("read");

		return cartService.read(cartId);
	}

	@RequestMapping(value = "/{cartId}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public void update(@PathVariable(value = "cartId") String cartId, @RequestBody CartDTO cartDto) {
		logger.info("update");

		cartService.update(cartId, cartDto);
	}

	@RequestMapping(value = "/{cartId}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public void delete(@PathVariable(value = "cartId") String cartId) {
		logger.info("delete");

		cartService.delete(cartId);
	}

	@RequestMapping(value = "/add/{productId}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public void addItem(@PathVariable(value = "productId") String productId, HttpSession session) {
		logger.info("addItem");

		cartService.addItem(session.getId(), productId);
	}

	@RequestMapping(value = "/remove/{productId}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public void removeItem(@PathVariable(value = "productId") String productId, HttpSession session) {
		logger.info("removeItem");

		cartService.removeItem(session.getId(), productId);
	}

}

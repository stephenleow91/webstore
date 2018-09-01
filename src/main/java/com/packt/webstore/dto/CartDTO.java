package com.packt.webstore.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CartDTO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	private List<CartItemDTO> cartItems;

	public CartDTO() {
		super();
		cartItems = new ArrayList<>();
	}

	public CartDTO(String id) {
		super();
		this.id = id;
		cartItems = new ArrayList<>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<CartItemDTO> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItemDTO> cartItems) {
		this.cartItems = cartItems;
	}

	public void addCartItem(CartItemDTO cartItemDto) {
		cartItems.add(cartItemDto);
	}

}

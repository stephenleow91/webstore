package com.packt.webstore.service;

import com.packt.webstore.domain.Cart;
import com.packt.webstore.dto.CartDTO;

public interface CartService {

	void create(CartDTO cartDto);

	Cart read(String cartId);

	void update(String cartId, CartDTO cartDto);

	void delete(String id);

	void addItem(String cartId, String productId);

	void removeItem(String cartId, String productId);

	Cart validate(String cartId);

	void clearCart(String cartId);

}

package com.packt.webstore.domain.repository;

import com.packt.webstore.domain.Cart;
import com.packt.webstore.dto.CartDTO;

public interface CartRepository {

	void create(CartDTO cartDto);

	Cart read(String id);

	void update(String cartId, CartDTO cartDto);

	void delete(String id);

	void addItem(String cartId, String productId);

	void removeItem(String cartId, String productId);

	void clearCart(String cartId);

}

package com.cart.service;

import java.util.List;

import com.cart.entity.CartItemEntity;

public interface CartService {

	List<CartItemEntity> getCartItems(Long userId);

	void addOrUpdateItem(Long userId, Long productId, int quantity);

	void removeItem(Long userId, Long productId);

	void clearCart(Long userId);

}

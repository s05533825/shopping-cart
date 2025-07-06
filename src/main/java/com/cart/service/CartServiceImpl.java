package com.cart.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cart.entity.CartItemEntity;
import com.cart.repository.CartItemRepository;

@Service
public class CartServiceImpl implements CartService {

	private final CartItemRepository cartRepo;

	public CartServiceImpl(CartItemRepository cartRepo) {
		this.cartRepo = cartRepo;
	}

	@Override
	public List<CartItemEntity> getCartItems(Long userId) {
		// TODO Auto-generated method stub
		return cartRepo.findByUserId(userId);
	}

	@Override
	public void addOrUpdateItem(Long userId, Long productId, int quantity) {
		Optional<CartItemEntity> existing = cartRepo.findByUserIdAndProductId(userId, productId);
		// TODO Auto-generated method stub
		if (existing.isPresent()) {
			CartItemEntity item = existing.get();
			item.setQuantity(item.getQuantity() + quantity);
			cartRepo.save(item);
		} else {
			CartItemEntity item = new CartItemEntity();
			item.setUserId(userId);
			item.setProductId(productId);
			item.setQuantity(quantity);
			cartRepo.save(item);
		}
	}

	@Override
	public void removeItem(Long userId, Long productId) {
		// TODO Auto-generated method stub
		cartRepo.findByUserIdAndProductId(userId, productId).ifPresent(item -> cartRepo.delete(item));
	}

	@Override
	public void clearCart(Long userId) {
		// TODO Auto-generated method stub
		cartRepo.deleteByUserId(userId);
	}

}

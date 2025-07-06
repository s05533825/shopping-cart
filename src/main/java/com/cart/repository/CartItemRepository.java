package com.cart.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cart.entity.CartItemEntity;

public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {

	List<CartItemEntity> findByUserId(Long userId);

	Optional<CartItemEntity> findByUserIdAndProductId(Long userId, Long productId);

	void deleteByUserId(Long userId);

}

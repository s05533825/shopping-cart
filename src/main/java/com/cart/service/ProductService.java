package com.cart.service;

import java.util.List;
import java.util.Optional;

import com.cart.model.Product;

public interface ProductService {

	List<Product> getAllProducts();

	Optional<Product> getProductById(Long id);
}

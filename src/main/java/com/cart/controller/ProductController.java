package com.cart.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cart.model.Product;
import com.cart.model.ShoppingCart;
import com.cart.service.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/products")
	public String listProduct(Model model) {
		List<Product> products = productService.getAllProducts();
		model.addAttribute("products", products); // 把商品列表丟給前端
		return "products"; // 對應 templates/products.html
	}

}

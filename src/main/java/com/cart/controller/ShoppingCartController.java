package com.cart.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cart.model.Product;
import com.cart.model.ShoppingCart;
import com.cart.service.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ShoppingCartController {

	@Autowired
	private ProductService productService;

	@PostMapping("/cart/add-multiple")
	public String addMultipleToCart(@RequestParam("selectedIds") List<Long> selectedIds,
			@RequestParam Map<String, String> quantityMap, HttpSession session) {
		ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
		if (cart == null) {
			cart = new ShoppingCart();
			session.setAttribute("cart", cart);
		}

		for (Long productId : selectedIds) {
			Optional<Product> productOpt = productService.getProductById(productId);
			if (productOpt.isPresent()) {
				Product product = productOpt.get();
				String qtyStr = quantityMap.get("quantityMap[" + productId + "]");
				int qty = 1;
				try {
					qty = Integer.parseInt(qtyStr);
				} catch (NumberFormatException e) {
					// fallback to 1 if error
				}
				cart.addItem(product.getId(), product.getName(), product.getPrice(), qty);
			}
		}

		return "redirect:/cart";
	}

	@PostMapping("/cart/update/{id}")
	public String updateQuantity(@PathVariable("id") Long productId, @RequestParam("quantity") int quantity,
			HttpSession session) {
		ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
		if (cart != null) {
			cart.updateItem(productId, quantity);
		}
		return "redirect:/cart";
	}

	// 單一加入購物車
//	@PostMapping("/cart/add/{id}")
//	public String addToCart(@PathVariable("id") Long productId, @RequestParam("quantity") int quantity,
//			HttpSession session) {
//
//		ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
//		if (cart == null) {
//			cart = new ShoppingCart();
//			session.setAttribute("cart", cart);
//		}
//
//		Optional<Product> productOpt = productService.getProductById(productId);
//		if (productOpt.isPresent()) {
//			Product p = productOpt.get();
//			cart.addItem(p.getId(), p.getName(), p.getPrice(), quantity);
//		}
//
//		return "redirect:/products";
//	}

	// 查看購物車
	@GetMapping("/cart")
	public String viewCart(Model model, HttpSession session) {
		ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
		if (cart == null) {
			cart = new ShoppingCart();
		}
		model.addAttribute("cart", cart);
		return "cart"; // 對應 templates/cart.html
	}

	// 刪除單一商品
	@GetMapping("/cart/remove/{id}")
	public String removeItem(@PathVariable("id") Long productId, HttpSession session) {
		ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
		if (cart != null) {
			cart.removeItem(productId);
		}
		return "redirect:/cart";
	}

	// 清空購物車
	@GetMapping("/cart/clear")
	public String clearCart(HttpSession session) {
		session.removeAttribute("cart");
		return "redirect:/cart";
	}

	// 模擬結帳（實際專案可導向付款頁或生成訂單）
	@GetMapping("/cart/checkout")
	public String checkout(HttpSession session, Model model) {
		ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
		if (cart != null && !cart.getItems().isEmpty()) {
			// 模擬結帳邏輯：顯示總金額、清空購物車
			model.addAttribute("total", cart.getTotal());
			session.removeAttribute("cart"); // 結帳完清空購物車
			return "checkout";
		}
		return "redirect:/cart";
	}

}

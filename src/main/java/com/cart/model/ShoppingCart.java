package com.cart.model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

	private List<CartItem> items = new ArrayList<>();
	private double total = 0;

//	加入商品
	public void addItem(Long productId, String name, double price, int quantity) {
		for (CartItem item : items) {
			if (item.getProductId().equals(productId)) {
				item.setQuantity(item.getQuantity() + quantity);
				return;
			}
		}
		items.add(new CartItem(productId, name, price, quantity));
		updateTotal();
	}

//	刪除商品
	public void removeItem(Long productId) {
		items.removeIf(item -> item.getProductId().equals(productId));
		updateTotal();
	}

//	清空購物車
	public void clear() {
		items.clear();
		total = 0;
	}

//	更新金額
	public void updateTotal() {
		// TODO Auto-generated method stub
		total = 0;
		for (CartItem item : items) {
			total += item.getTotalPrice();
		}
	}

	public void updateItem(Long productId, int quantity) {
		for (CartItem item : items) {
			if (item.getProductId().equals(productId)) {
				if (quantity <= 0) {
					items.remove(item);
				} else {
					item.setQuantity(quantity);
				}
				break; // 找到後就結束迴圈
			}
		}
		updateTotal(); // 記得更新總金額
	}

	public List<CartItem> getItems() {
		return items;
	}

	public double getTotal() {
		return total;
	}

}

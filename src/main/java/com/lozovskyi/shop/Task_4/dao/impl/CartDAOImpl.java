package com.lozovskyi.shop.Task_4.dao.impl;

import com.lozovskyi.shop.Task_4.dao.CartDAO;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/*
  Class CartDAOImpl is the CartDAO implementation
 */
public class CartDAOImpl implements CartDAO {
	private Map<Integer, Integer> cart = new HashMap<>();
	private Map<LocalDateTime, Integer> history = new LinkedHashMap<LocalDateTime, Integer>(5 * 10 / 7, 0.7f, true) {
		protected boolean removeEldestEntry(Map.Entry<LocalDateTime, Integer> eldest) {
			return history.size() > 5;
		}
	};

	@Override
	public Map<Integer, Integer> getCart() {
		return cart;
	}

	@Override
	public Map<LocalDateTime, Integer> getHistory() {
		return history;
	}

	@Override
	public void addToCart(int productId, int quantity) {
		if (cart.containsKey(productId)) {
			cart.put(productId, cart.get(productId)+quantity);
		} else {
			cart.put(productId, quantity);
		}
		history.put(LocalDateTime.now(), productId);
	}

	@Override
	public boolean removeFromCart(int productId, int quantity) {
		if (cart.get(productId) - quantity > 0) {
			cart.put(productId, cart.get(productId)-quantity);
		} else {
			return removeAllFromCart(productId);
		}
		return true;
	}

	@Override
	public boolean removeAllFromCart(int productId) {
		cart.remove(productId);
		return true;
	}

	@Override
	public void clearCart() {
		cart.clear();
	}
}


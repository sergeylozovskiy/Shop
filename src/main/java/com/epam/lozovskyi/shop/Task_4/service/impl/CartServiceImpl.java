package com.epam.lozovskyi.shop.Task_4.service.impl;

import com.epam.lozovskyi.shop.Task_4.dao.CartDAO;
import com.epam.lozovskyi.shop.Task_4.dao.ProductDAO;
import com.epam.lozovskyi.shop.Task_4.service.CartService;

import java.time.LocalDateTime;
import java.util.Map;

public class CartServiceImpl implements CartService {
	private CartDAO cartDAO;
	private ProductDAO productDAO;

	public CartServiceImpl(CartDAO cartDAO, ProductDAO productDAO) {
		this.cartDAO = cartDAO;
		this.productDAO = productDAO;
	}

	@Override
	public boolean addToCart(int productId, int quantity) {
		if (productDAO.getProducts().containsKey(productId)) {
			cartDAO.addToCart(productId, quantity);
		}
		return cartDAO.getCart().containsKey(productId);
	}

	@Override
	public void clearCart() {
		cartDAO.clearCart();
	}

	@Override
	public Map<Integer, Integer> getCart() {
		return cartDAO.getCart();
	}

	@Override
	public Map<LocalDateTime, Integer> getHistory() {
		return cartDAO.getHistory();
	}

	@Override
	public boolean removeFromCart(int productId, int quantity) {
		return cartDAO.removeFromCart(productId, quantity);
	}

	@Override
	public boolean removeAllProductsByIdFromCart(int productId) {
		return cartDAO.removeAllFromCart(productId);
	}

	@Override
	public int countTotalPrice() {
		Map<Integer, Integer> cart = cartDAO.getCart();
		int totalPrice = 0;
		for (Map.Entry<Integer, Integer> entry : cart.entrySet()) {
			for (int i = 0; i < entry.getValue(); i++) {
				totalPrice += productDAO.getPriceById(entry.getKey());
			}
		}
		return totalPrice;
	}
}


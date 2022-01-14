package com.epam.lozovskyi.shop.Task_4.service;

import java.time.LocalDateTime;
import java.util.Map;

public interface CartService {

	boolean addToCart(int productId, int quantity);

	void clearCart();

	Map<Integer, Integer> getCart();

	Map<LocalDateTime, Integer> getHistory();

	boolean removeFromCart(int productId, int quantity);

	boolean removeAllProductsByIdFromCart(int productId);

	int countTotalPrice();
}


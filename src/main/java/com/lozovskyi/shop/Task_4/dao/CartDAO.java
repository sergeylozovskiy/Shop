package com.lozovskyi.shop.Task_4.dao;

import java.time.LocalDateTime;
import java.util.Map;

/*
  Interface CartDAO is used to generalize the work of the data access object, that contains info about the cart
 */
public interface CartDAO {
	Map<Integer, Integer> getCart();
	
	Map<LocalDateTime, Integer> getHistory();

	void addToCart(int productId, int quantity);

	boolean removeFromCart(int productId, int quantity);

	boolean removeAllFromCart(int productId);


	void clearCart();
}


package com.lozovskyi.shop.Task_4.dao.impl;

import com.lozovskyi.shop.Task_1.entity.Product;
import com.lozovskyi.shop.Task_4.dao.OrderDAO;

import java.util.List;
import java.util.TreeMap;

/*
  Class OrderDAOImpl is the OrderDAO implementation
 */
public class OrderDAOImpl implements OrderDAO {
	private TreeMap<Long, List<Product>> orders = new TreeMap<>();

	@Override
	public void addOrder(Long date, List<Product> cart) {
		orders.put(date, cart);
	}

	@Override
	public TreeMap<Long, List<Product>> getOrders() {
		return orders;
	}
}

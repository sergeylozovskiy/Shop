package com.epam.lozovskyi.shop.Task_4.dao;

import com.epam.lozovskyi.shop.Task_1.entity.Product;

import java.util.List;
import java.util.TreeMap;

public interface OrderDAO {

	void addOrder(Long date, List<Product> cart);

	TreeMap<Long, List<Product>> getOrders();
}
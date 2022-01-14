package com.lozovskyi.shop.Task_4.service;

import com.lozovskyi.shop.Task_1.entity.Product;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public interface OrderService {
	Map.Entry<Long, List<Product>> getNearestOrderToDate(LocalDate date);

	boolean completeOrder(Long date);

	TreeMap<Long, List<Product>> getOrders();
}


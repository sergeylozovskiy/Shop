package com.epam.lozovskyi.shop.Task_4.service.impl;

import com.epam.lozovskyi.shop.Task_1.entity.Product;
import com.epam.lozovskyi.shop.Task_4.dao.CartDAO;
import com.epam.lozovskyi.shop.Task_4.dao.OrderDAO;
import com.epam.lozovskyi.shop.Task_4.dao.ProductDAO;
import com.epam.lozovskyi.shop.Task_4.service.OrderService;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class OrderServiceImpl implements OrderService {
	private OrderDAO orderDAO;
	private CartDAO cartDAO;
	private ProductDAO productDAO;

	public OrderServiceImpl(CartDAO cartDAO, ProductDAO productDAO, OrderDAO orderDAO) {
		this.cartDAO = cartDAO;
		this.productDAO = productDAO;
		this.orderDAO = orderDAO;
	}

	@Override
	public Map.Entry<Long, List<Product>> getNearestOrderToDate(LocalDate date) {
		long toFind = date.atStartOfDay().toEpochSecond(ZoneOffset.UTC);

		return getNearestOrder(toFind);
	}

	@Override
	public boolean completeOrder(Long date) {
		int orders = orderDAO.getOrders().size();
		orderDAO.addOrder(date, productDAO.getProducts(cartDAO.getCart()));
		cartDAO.clearCart();
		return orderDAO.getOrders().size() - orders == 1;
	}

	@Override
	public TreeMap<Long, List<Product>> getOrders() {
		return orderDAO.getOrders();
	}

	private Map.Entry<Long, List<Product>> getNearestOrder(Long date) {
		if (getOrders().isEmpty()) {
			return null;
		}
		Map.Entry<Long, List<Product>> left = getOrders().lowerEntry(date);
		Map.Entry<Long, List<Product>> right = getOrders().ceilingEntry(date);
		if (left == null) {
			return right;
		}
		if (right == null || ((right.getKey() - date) > (date - left.getKey()))) {
			return left;
		}
		return right;
	}
}


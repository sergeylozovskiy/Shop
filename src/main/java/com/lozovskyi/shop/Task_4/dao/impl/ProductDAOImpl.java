package com.lozovskyi.shop.Task_4.dao.impl;

import com.lozovskyi.shop.Task_1.entity.Product;
import com.lozovskyi.shop.Task_4.dao.ProductDAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
  Class ProductDAOImpl is the ProductDAO implementation
 */

public class ProductDAOImpl implements ProductDAO {
	private Map<Integer, Product> products = new HashMap<>();

	@Override
	public void addProduct(Product toAdd) {
		products.put(toAdd.getId(), toAdd);
	}

	@Override
	public void removeProduct(Product toRemove) {
		products.remove(toRemove.getId());
	}

	@Override
	public Map<Integer, Product> getProducts() {
		return products;
	}

	@Override
	public List<Product> getProducts(Map<Integer, Integer> cart) {
		List<Product> toGet = new ArrayList<>();

		cart.entrySet().forEach(entry ->{
			toGet.add(products.get(entry.getKey()));
		});
//
//		for (Map.Entry<Integer, Integer> entry : cart.entrySet()) {
//			for (int i = 0; i < entry.getValue(); i++) {
//				toGet.add(products.get(entry.getKey()));
//			}
//		}
		return toGet;
	}

	@Override
	public void setProducts(Map<Integer, Product> toSet) {

		toSet.entrySet().forEach(entry -> {
			addProduct(entry.getValue());
		});

//		for (Map.Entry<Integer, Product> entry : toSet.entrySet()) {
//			addProduct(entry.getValue());
//		}
	}

	@Override
	public int getPriceById(int productId) {
		for (Map.Entry<Integer, Product> entry : products.entrySet()) {
			if (entry.getKey() == productId) {
				return entry.getValue().getPrice();
			}
		}
		return -1;
	}

	@Override
	public Product getProductById(int productId) {
		return products.get(productId);
	}
}
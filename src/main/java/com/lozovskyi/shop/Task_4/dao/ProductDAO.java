package com.lozovskyi.shop.Task_4.dao;

import com.lozovskyi.shop.Task_1.entity.Product;

import java.util.List;
import java.util.Map;

public interface ProductDAO {

	void addProduct(Product toAdd);

	void removeProduct(Product toRemove);

	Map<Integer, Product> getProducts();

	List<Product> getProducts(Map<Integer, Integer> cart);

	void setProducts(Map<Integer, Product> toSet);

	int getPriceById(int productId);

	Product getProductById(int productId);
}


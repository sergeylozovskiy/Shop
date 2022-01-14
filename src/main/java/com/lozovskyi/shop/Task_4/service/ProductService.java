package com.lozovskyi.shop.Task_4.service;

import com.lozovskyi.shop.Task_1.entity.Product;

import java.util.Map;

public interface ProductService {

	Product getProductById(int productId);

	Map<Integer, Product> getProducts();

	void addProduct(Product product);
}

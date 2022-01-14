package com.lozovskyi.shop.Task_4.service.impl;

import com.lozovskyi.shop.Task_1.entity.Product;
import com.lozovskyi.shop.Task_4.dao.ProductDAO;
import com.lozovskyi.shop.Task_4.service.ProductService;

import java.util.Map;


public class ProductServiceImpl implements ProductService {
	private ProductDAO productDAO;

	public ProductServiceImpl(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	@Override
	public Product getProductById(int productId) {
		return productDAO.getProductById(productId);
	}

	@Override
	public Map<Integer, Product> getProducts() {
		return productDAO.getProducts();
	}

	@Override
	public void addProduct(Product product) {
		if (product != null) {
			productDAO.addProduct(product);
		}
	}
}


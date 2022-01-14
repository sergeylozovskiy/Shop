package com.epam.lozovskyi.shop.Task_4.strategy;

import com.epam.lozovskyi.shop.Task_1.entity.Product;
import com.epam.lozovskyi.shop.Task_4.input.InputHelper;

public class StrategyContext {
	private CreationStrategy creationStrategy;

	public StrategyContext(CreationStrategy creationStrategy) {
		this.creationStrategy = creationStrategy;
	}

	public Product executeStrategy(InputHelper inputHelper) {
		return creationStrategy.createProduct(inputHelper);
	}
}

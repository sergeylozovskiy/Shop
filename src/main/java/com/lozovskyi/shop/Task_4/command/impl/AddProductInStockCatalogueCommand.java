package com.lozovskyi.shop.Task_4.command.impl;

import com.lozovskyi.shop.Task_1.entity.Product;
import com.lozovskyi.shop.Task_4.command.Command;
import com.lozovskyi.shop.Task_4.context.Context;
import com.lozovskyi.shop.Task_4.strategy.CreationStrategy;
import com.lozovskyi.shop.Task_4.strategy.StrategyContext;
import com.lozovskyi.shop.Task_4.strategy.impl.JewelryEarringsCreationStrategy;
import com.lozovskyi.shop.Task_4.strategy.impl.JewelryRingCreationStrategy;
import com.lozovskyi.shop.Task_4.utility.InputUtil;

import java.util.HashMap;
import java.util.Map;

public class AddProductInStockCatalogueCommand extends Command {
	private Map<Integer, CreationStrategy> strategyMap = new HashMap<>();

	@Override
	public void execute(Context context) {
		strategyMap.put(1, new JewelryRingCreationStrategy());
		strategyMap.put(2, new JewelryEarringsCreationStrategy());

		System.out.println("Choose type of product to add:\n1. JewelryRing \n2. JewelryEarrings\n");
		int choice = 0;
		while (!strategyMap.containsKey(choice)) {
			choice = InputUtil.getInteger();
		}
		CreationStrategy chosenStrategy = strategyMap.get(choice);
		StrategyContext strategyContext = new StrategyContext(chosenStrategy);

		Product product = strategyContext.executeStrategy(context.getInputHelper());
		context.getProductService().addProduct(product);
	}

	@Override
	public String about() {
		return "to add product in stock catalogue";
	}
}

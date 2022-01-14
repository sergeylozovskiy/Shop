package com.epam.lozovskyi.shop.Task_4.command.impl;

import com.epam.lozovskyi.shop.Task_1.entity.Product;
import com.epam.lozovskyi.shop.Task_4.command.Command;
import com.epam.lozovskyi.shop.Task_4.context.Context;

import java.util.Map;

public class ViewProductsInStockCommand extends Command {
	@Override
	public void execute(Context context) {
		System.out.format("----< Products in stock\n");
		Map<Integer, Product> toView = context.getProductService().getProducts();
		if (!toView.isEmpty()) {
			System.out.format("%-5s | %-20s | %s\n", "ID", "NAME", "DESCRIPTION");
			System.out.format("----------------------------------------------\n");
//
//			toView.entrySet()
//					.stream()
//					//.sorted()
//					.forEach(entry -> {
//						System.out.format("%-5d | %-20s | %s\n", entry.getKey(), entry.getValue().getName(), entry.getValue().toString());
//					});

			for(Map.Entry<Integer, Product> entry: toView.entrySet()){
				System.out.format("%-5d | %-20s | %s\n", entry.getKey(), entry.getValue().getName(), entry.getValue().toString());
			}
			System.out.format("----------------------------------------------\n");
		} else {
			System.out.format("There are no products in stock!\n");
		}
	}

	@Override
	public String about() {
		return "to view the list of products in stock";
	}
}

package com.epam.lozovskyi.shop.Task_4.command.impl;

import com.epam.lozovskyi.shop.Task_4.command.Command;
import com.epam.lozovskyi.shop.Task_4.context.Context;

import java.time.LocalDateTime;
import java.util.Map;

public class ViewLastFiveAddedProductsCommand extends Command {
	@Override
	public void execute(Context context) {
		System.out.println("----< Products in the cart\n");
		Map<LocalDateTime, Integer> history = context.getCartService().getHistory();
		if (!history.isEmpty()) {
			System.out.format("%-30s | %-10s | %s\n", "DATE", "NAME", "ID");
			System.out.format("----------------------------------------------\n");
			for(Map.Entry<LocalDateTime, Integer> entry: history.entrySet()){
				int productId = entry.getValue();
				String productName = context.getProductService().getProductById(productId).getName();
				System.out.format("%-30s | %-10s | %d\n", entry.getKey(), productName, productId);
			}
			System.out.format("----------------------------------------------\n");
		} else {
			System.out.println("Cart is empty!\n");
		}
	}

	@Override
	public String about() {
		return "to view last five added products to te cart";
	}
}


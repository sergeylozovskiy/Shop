package com.lozovskyi.shop.Task_4.command.impl;

import com.lozovskyi.shop.Task_4.command.Command;
import com.lozovskyi.shop.Task_4.context.Context;

import java.util.Map;

public class ViewCartCommand extends Command {
	@Override
	public void execute(Context context) {
		System.out.println("----< Products in the cart\n");
		Map<Integer, Integer> cart = context.getCartService().getCart();
		if (!cart.isEmpty()) {
			System.out.format("%-5s | %-10s | %s\n", "ID", "NAME", "QUANTITY");
			System.out.format("----------------------------------------------\n");
			for(Map.Entry<Integer, Integer> entry: cart.entrySet()){
				System.out.format("%-5d | %-10s | %d\n", entry.getKey(), context.getProductService().getProductById(entry.getKey()).getName(), entry.getValue());
			}
			System.out.println("----------------------------------------------\n");
		} else {
			System.out.println("Cart is empty!\n");
		}
	}

	@Override
	public String about() {
		return "to view the cart content";
	}
}

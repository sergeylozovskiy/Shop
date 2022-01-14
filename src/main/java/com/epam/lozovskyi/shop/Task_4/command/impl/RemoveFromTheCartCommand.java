package com.epam.lozovskyi.shop.Task_4.command.impl;

import com.epam.lozovskyi.shop.Task_4.command.Command;
import com.epam.lozovskyi.shop.Task_4.context.Context;
import com.epam.lozovskyi.shop.Task_4.utility.InputUtil;

public class RemoveFromTheCartCommand extends Command {

	@Override
	public void execute(Context context) {
		boolean ifRemoved;
		System.out.println("----< Removing product from the cart . . .\n");
		System.out.println("----< Input product id: ");
		int id = InputUtil.getInteger();
		System.out.println("----< Do you want to remove all products with such an id?(y/n): ");
		String input = InputUtil.getYesOrNo();
		if (input.equals("y")) {
			ifRemoved = context.getCartService().removeAllProductsByIdFromCart(id);
			System.out.format("----< All the products %d were removed: %s\n", id, ifRemoved);
		} else {
			System.out.println("----< Input quantity: ");
			int quantity = InputUtil.getInteger();
			ifRemoved = context.getCartService().removeFromCart(id, quantity);
			System.out.format("----< Product %d (quantity: %d) was removed: %s\n", id, quantity, ifRemoved);
		}
	}

	@Override
	public String about() {
		return "to remove a product from the cart";
	}
}

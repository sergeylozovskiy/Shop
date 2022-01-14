package com.epam.lozovskyi.shop.Task_4.command.impl;

import com.epam.lozovskyi.shop.Task_4.command.Command;
import com.epam.lozovskyi.shop.Task_4.context.Context;
import com.epam.lozovskyi.shop.Task_4.utility.InputUtil;

public class AddToCartCommand extends Command {
	@Override
	public void execute(Context context) {
		System.out.println("----< Adding product to the cart . . .\n");
		System.out.println("----< Input product id: ");
		int id = InputUtil.getInteger();
		System.out.println("----< Input quantity: ");
		int quantity = InputUtil.getInteger();
		boolean ifAdded = context.getCartService().addToCart(id, quantity);
		System.out.format("----< Product %d (quantity: %d) was added: %s\n", id, quantity, ifAdded);
	}

	@Override
	public String about() {
		return "to add product to the cart";
	}
}

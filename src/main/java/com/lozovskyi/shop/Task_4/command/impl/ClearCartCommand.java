package com.lozovskyi.shop.Task_4.command.impl;

import com.lozovskyi.shop.Task_4.command.Command;
import com.lozovskyi.shop.Task_4.context.Context;

public class ClearCartCommand extends Command {
	@Override
	public void execute(Context context) {
		System.out.println("----< Clearing cart . . .\n");
		context.getCartService().clearCart();
		System.out.println("----< Cart was cleared!\n");
	}

	@Override
	public String about() {
		return "to clear the cart";
	}
}

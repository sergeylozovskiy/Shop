package com.epam.lozovskyi.shop.Task_4.command.impl;

import com.epam.lozovskyi.shop.Task_4.command.Command;
import com.epam.lozovskyi.shop.Task_4.context.Context;
import com.epam.lozovskyi.shop.Task_4.utility.InputUtil;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class CompleteOrderCommand extends Command {
	@Override
	public void execute(Context context) {
		System.out.format("----< Completing order . . .\n");
		int totalPrice = context.getCartService().countTotalPrice();
		if (totalPrice > 0) {
			System.out.format("----< Total price of your your order is: %d \n", totalPrice);
			System.out.println("----< Do you want to complete order?(y/n) \n");
			String input = InputUtil.getYesOrNo();
			if (input.equals("y")) {
				context.getOrderService().completeOrder(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
				System.out.println("----< Order was completed!\n");
			} else {
				System.out.println("----< You can keep buying products\n!");
			}
		}
	}

	@Override
	public String about() {
		return "to complete order";
	}
}
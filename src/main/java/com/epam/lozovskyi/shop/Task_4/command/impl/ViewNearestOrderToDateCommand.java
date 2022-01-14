package com.epam.lozovskyi.shop.Task_4.command.impl;

import com.epam.lozovskyi.shop.Task_1.entity.Product;
import com.epam.lozovskyi.shop.Task_4.command.Command;
import com.epam.lozovskyi.shop.Task_4.context.Context;
import com.epam.lozovskyi.shop.Task_4.utility.InputUtil;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;

public class ViewNearestOrderToDateCommand extends Command {
	@Override
	public void execute(Context context) {
		if (context.getOrderService().getOrders().isEmpty()) {
			System.out.println("----< There are no any orders!\n");
		} else {
			System.out.format("----< Input date (e.g. %s\n", LocalDate.now());
			LocalDate date = InputUtil.getLocalDate();
			Map.Entry<Long, List<Product>> nearestOrderToDate = context.getOrderService().getNearestOrderToDate(date);
			System.out.format("%-10s | %-10s | %s\n", "DATE", "ID", "NAME");
			System.out.format("----------------------------------------------\n");
			long orderDate = nearestOrderToDate.getKey();
			List<Product> orderContent = nearestOrderToDate.getValue();
			System.out.format("%-10s | %-10s | %s\n", Instant.ofEpochSecond(orderDate).atZone(ZoneOffset.UTC).toLocalDate(), "", "");
			for (Product product : orderContent) {
				System.out.format("%-10s | %-10s | %s\n", "", product.getId(), product.getName());
			}
		}
	}

	@Override
	public String about() {
		return "to view the nearest to the input date order";
	}
}

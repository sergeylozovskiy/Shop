package com.epam.lozovskyi.shop.Task_4.strategy.impl;

import com.epam.lozovskyi.shop.Task_1.entity.Product;
import com.epam.lozovskyi.shop.Task_1.entity.impl.JewelryRing;
import com.epam.lozovskyi.shop.Task_4.input.InputHelper;
import com.epam.lozovskyi.shop.Task_4.strategy.CreationStrategy;

public class JewelryRingCreationStrategy implements CreationStrategy {
	@Override
	public Product createProduct(InputHelper inputHelper) {
		JewelryRing product = new JewelryRing();
		System.out.println(" ----input Ring id (number):");
		product.setId(inputHelper.getInteger());
		System.out.println(" ----input Ring price (number):");
		product.setPrice(inputHelper.getInteger());
		System.out.println(" ----input Ring name (string):");
		product.setName(inputHelper.getString());
		System.out.println(" ----input Ring material (string):");
		product.setMaterial(inputHelper.getString());
		System.out.println(" ----input Ring color (string):");
		product.setColor(inputHelper.getString());
		System.out.println(" ----input Ring karat (number):");
		product.setKarat(inputHelper.getInteger());
		System.out.println(" ----input Ring hallmark (number):");
		product.setHallmark(inputHelper.getInteger());
		System.out.println(" ----input Ring shape (string):");
		product.setShape(inputHelper.getString());
		System.out.print("\n");
		return product;
	}
}

package com.epam.lozovskyi.shop.Task_4.strategy.impl;

import com.epam.lozovskyi.shop.Task_1.entity.Product;
import com.epam.lozovskyi.shop.Task_1.entity.impl.JewelryEarrings;
import com.epam.lozovskyi.shop.Task_4.input.InputHelper;
import com.epam.lozovskyi.shop.Task_4.strategy.CreationStrategy;

public class JewelryEarringsCreationStrategy implements CreationStrategy {
	@Override
	public Product createProduct(InputHelper inputHelper) {
		JewelryEarrings product = new JewelryEarrings();
		System.out.println(" ---- input Earring id (number):");
		product.setId(inputHelper.getInteger());
		System.out.println(" ---- input Earring price (number):");
		product.setPrice(inputHelper.getInteger());
		System.out.println(" ---- input Earring name (string):");
		product.setName(inputHelper.getString());
		System.out.println(" ---- input Earring material (string):");
		product.setMaterial(inputHelper.getString());
		System.out.println(" ---- input Earring color (string):");
		product.setColor(inputHelper.getString());
		System.out.println(" ---- input Earring karat (number):");
		product.setKarat(inputHelper.getInteger());
		System.out.println(" ---- input Earring hallmark (number):");
		product.setHallmark(inputHelper.getInteger());
		System.out.println(" ----input Earring style (number):");
		product.setStyle(inputHelper.getString());
		System.out.print("\n");
		return product;
	}
}

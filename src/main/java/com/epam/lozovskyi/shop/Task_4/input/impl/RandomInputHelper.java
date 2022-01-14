package com.epam.lozovskyi.shop.Task_4.input.impl;

import com.epam.lozovskyi.shop.Task_4.input.InputHelper;

import java.security.SecureRandom;

/*
Random filling
 */
public class RandomInputHelper implements InputHelper {
	private static final String TEMPLATE = "value";
	private static final int BOUND = 999;
	private SecureRandom rand = new SecureRandom();

	@Override
	public int getInteger() {
		return (rand.nextInt(BOUND) + 1);
	}

	@Override
	public String getString() {
		return TEMPLATE + (rand.nextInt(BOUND) + 1);
	}
}

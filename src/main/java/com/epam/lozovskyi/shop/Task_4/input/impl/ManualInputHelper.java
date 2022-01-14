package com.epam.lozovskyi.shop.Task_4.input.impl;

import com.epam.lozovskyi.shop.Task_4.input.InputHelper;

import java.util.Scanner;
/*
Manual filling
 */
public class ManualInputHelper implements InputHelper {
	private Scanner scanner = new Scanner(System.in);

	@Override
	public int getInteger() {
		return scanner.nextInt();
	}

	@Override
	public String getString() {
		return scanner.next();
	}
}

package com.epam.lozovskyi.shop.Task_4.input;

import com.epam.lozovskyi.shop.Task_4.input.impl.ManualInputHelper;
import com.epam.lozovskyi.shop.Task_4.input.impl.RandomInputHelper;
import com.epam.lozovskyi.shop.Task_4.utility.InputUtil;

import java.util.HashMap;
import java.util.Map;

public class InputHelperResolver {
	private Map<Integer, InputHelper> inputHelpers = new HashMap<>();

	public InputHelper chooseInputHelper() {
		inputHelpers.put(1, new ManualInputHelper());
		inputHelpers.put(2, new RandomInputHelper());

		System.out.format("Mode for adding products:\n1. Manual \n2. Random\n");
		int choice = 0;
		while(!inputHelpers.containsKey(choice)){
			choice = InputUtil.getInteger();
		}
		return inputHelpers.get(choice);
	}
}
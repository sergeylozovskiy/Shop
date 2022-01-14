package com.epam.lozovskyi.shop.Task_4.command;

import java.util.Map;

public class CommandContainer {
	private Map<Integer, Command> actions;

	public CommandContainer(Map<Integer, Command> actions) {
		this.actions = actions;
	}

	public Command get(Integer action) {
		if (action == null || !actions.containsKey(action)) {
			return actions.get(404);
		}
		return actions.get(action);
	}
}

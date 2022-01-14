package com.lozovskyi.shop.Task_4.Controller;

import com.lozovskyi.shop.Task_4.command.Command;
import com.lozovskyi.shop.Task_4.command.CommandContainer;
import com.lozovskyi.shop.Task_4.context.Context;

import java.util.Map;

public class Controller {
	private Context context;
	private CommandContainer commands;

	public void setCommands(Map<Integer, Command> commands) {
		this.commands = new CommandContainer(commands);
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public final void start(Integer actionNum) {
		Command command = commands.get(actionNum);
		command.execute(context);
		if (actionNum != 0) {
			command = commands.get(0);
			command.execute(context);
		}
	}
}

package com.epam.lozovskyi.shop.Task_4.command.impl;

import com.epam.lozovskyi.shop.Task_4.command.Command;
import com.epam.lozovskyi.shop.Task_4.context.Context;

import java.util.Map;

public class IndexCommand extends Command {

	@Override
	public void execute(Context context) {
		Map<Integer, Command> commands = context.getCommands();
		System.out.println("");
		for(Map.Entry<Integer, Command> entry : commands.entrySet()){
			System.out.format("%-5d  | %s\n", entry.getKey(), entry.getValue().about());
		}
	}

	@Override
	public String about() {
		return "to view commands' description";
	}
}


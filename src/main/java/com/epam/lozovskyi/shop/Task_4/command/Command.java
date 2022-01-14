package com.epam.lozovskyi.shop.Task_4.command;

import com.epam.lozovskyi.shop.Task_4.context.Context;

public abstract class Command {

	public abstract void execute(Context context);

	public abstract String about();

}
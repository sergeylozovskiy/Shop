package com.lozovskyi.shop.Task_4.command.impl;

import com.lozovskyi.shop.Task_4.command.Command;
import com.lozovskyi.shop.Task_4.context.Context;
import com.lozovskyi.shop.Task_4.utility.BackupUtil;

public class ExitCommand extends Command {
	@Override
	public void execute(Context context) {

		BackupUtil.save(context.getProductService().getProducts(), Context.BACK_UP_FILE);
		System.out.println(" The file has been saved!\n");
		System.out.println(" ------- Exit!\n");
		System.exit(0);
	}

	@Override
	public String about() {
		return "to exit";
	}
}

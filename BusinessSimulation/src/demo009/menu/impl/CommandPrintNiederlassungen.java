package demo009.menu.impl;

import java.util.List;

import demo009.ApplicationContext;
import demo009.menu.Command;
import demo009.misc.DisplayService;
import demo009.model.Niederlassung;

public class CommandPrintNiederlassungen implements Command {

	@Override
	public String menuItemName() {
		return "Niederlassungen ausgeben";
	}

	@Override
	public void execute(ApplicationContext context) {
		
		System.out.println("========== "+menuItemName());

		DisplayService.anzeigenNiederlassung(context);
	}

}

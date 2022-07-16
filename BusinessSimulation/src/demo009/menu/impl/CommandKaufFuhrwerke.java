package demo009.menu.impl;

import java.util.List;
import java.util.Scanner;

import demo009.ApplicationContext;
import demo009.menu.Command;
import demo009.model.Niederlassung;
import demo009.model.Fuhrpark;

public class CommandKaufFuhrwerke implements Command {

	@Override
	public String menuItemName() {
		return "Fuhrwerke kaufen";
	}

	@Override
	public void execute(ApplicationContext context) {
		
		// TODO Auto-generated method stub
		System.out.println("========== "+menuItemName());
		int auswahlIndex =0;
		
		boolean scannerErfolg=false;
		
		System.out.printf("======================%n");
		System.out.println("Anzahl vorhandener Fuhrwerke: "+context.getVerfuegbareFuhrwerke());
		System.out.printf("======================%n");
			
		
		Scanner myScanner = new Scanner(System.in);
	

		if(auswahlIndex==1) {
			// Verlassen des Menus
			return;
		}
		if (context.getGuthaben()<(context.getFuhrpark().getPreisFuhrwerk())){
			System.out.println("\nSie können sich kein neues Fuhrwerk leisten.\n");
			return;
		}
		
		scannerErfolg=false;
		int kaufenFuhrwerke;
		while(!scannerErfolg) {
			try {
				System.out.println("Wie viele Fuhrwerke wollen Sie kaufen? ");
				kaufenFuhrwerke = myScanner.nextInt();
				if(context.getGuthaben()>=(context.getFuhrpark().getPreisFuhrwerk()*kaufenFuhrwerke)) {
					context.getFuhrpark().setAnzahlFuhrwerke(kaufenFuhrwerke+context.getVerfuegbareFuhrwerke());
					context.guthabenMinus(context.getFuhrpark().getPreisFuhrwerk()*kaufenFuhrwerke);
					System.out.println("Neue Anzahl an Fuhrwerken: "+context.getVerfuegbareFuhrwerke()+"\n"+"Neues Guthaben: "
					+context.getGuthaben()+" Taler\n");
					return;
				}else {
					System.out.println("Sie besitzen nur: "+context.getGuthaben()+" Taler");
					System.out.println("Der Preis für ein Fuhrwerk beträgt: "+context.getFuhrpark().getPreisFuhrwerk()+" Taler");
					scannerErfolg=false;
				}
				
				myScanner.nextLine();
				
			
			} catch (Exception e) {
				e.printStackTrace();
				scannerErfolg=false;
				myScanner.nextLine();
			}
		
		
	}
	}

}

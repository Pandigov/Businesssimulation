package demo009.menu.impl;

import java.util.List;
import java.util.Scanner;

import demo009.ApplicationContext;
import demo009.menu.Command;
import demo009.model.Niederlassung;
import demo009.model.Fuhrpark;

public class CommandVerkaufFuhrwerke implements Command {

	@Override
	public String menuItemName() {
		return "Fuhrwerke verkaufen";
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
		
		if (context.getVerfuegbareFuhrwerke()==0) {
			System.out.println("Sie besitzen keine Fuhrwerke");
			return;
		}
		scannerErfolg=false;
		int verkaufenFuhrwerke;
		while(!scannerErfolg) {
			try {
				System.out.println("Wie viele Fuhrwerke wollen Sie verkaufen? ");
				verkaufenFuhrwerke = myScanner.nextInt();
				if(context.getVerfuegbareFuhrwerke()>=verkaufenFuhrwerke) {
					context.getFuhrpark().setAnzahlFuhrwerke(context.getVerfuegbareFuhrwerke()-verkaufenFuhrwerke);
					context.guthabenPlus(verkaufenFuhrwerke*context.getFuhrpark().getPreisFuhrwerk());
					System.out.println("Neue Anzahl an Fuhrwerken: "+context.getVerfuegbareFuhrwerke()+"\n"+"Neues Guthaben: "
					+context.getGuthaben()+" Taler\n");
					return;
					
				}else {
					System.out.println("Es können nur maximal "+context.getVerfuegbareFuhrwerke()+" Fuhrwerke verkauft werden\n");
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

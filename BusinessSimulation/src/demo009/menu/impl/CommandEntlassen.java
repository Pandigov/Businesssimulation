package demo009.menu.impl;

import java.util.List;
import java.util.Scanner;

import demo009.ApplicationContext;
import demo009.menu.Command;
import demo009.model.Niederlassung;

public class CommandEntlassen implements Command {

	@Override
	public String menuItemName() {
		return "Arbeitende entlassen";
	}

	@Override
	public void execute(ApplicationContext context) {
		// TODO Auto-generated method stub
		System.out.println("========== "+menuItemName());
		int auswahlNiederlassungIndex=0;
		
		boolean scannerErfolg=false;
		System.out.println("Wählen sie die Niederlassung aus, in der Arbeitende entlassen werden sollen");
		
		List<Niederlassung> listeNiederlassung = context.getNiederlassungen();
		int indexNdls;
		System.out.printf("%s %15s\n","Niederlassung","Arbeitende");
		
		for (indexNdls = 0; indexNdls < listeNiederlassung.size(); indexNdls++) {
			String ausgabeString = indexNdls+": %s %15s\n";
			
			
		
			System.out.printf(ausgabeString,listeNiederlassung.get(indexNdls).getOrt(),listeNiederlassung.get(indexNdls).getArbeiter());
			
			
			
		}
		System.out.println(indexNdls + ": Zurück");
		
		Scanner myScanner = new Scanner(System.in);
		while(!scannerErfolg) {
			try {
				
				System.out.print("Auswahl:");
				auswahlNiederlassungIndex = myScanner.nextInt();
				scannerErfolg=true;
				myScanner.nextLine();
			} catch (Exception e) {
				e.printStackTrace();
				scannerErfolg=false;
				myScanner.nextLine();
			}
		}

		if(auswahlNiederlassungIndex==indexNdls) {
			// Verlassen des Menus
			return;
		}

		int einstellenMitarbeiter;
		
		scannerErfolg=false;
		while(!scannerErfolg) {
			try {
				System.out.printf("Wie viele Mitarbeiter sollen in "+listeNiederlassung.get(auswahlNiederlassungIndex).getOrt()+" entlassen werden?%n");
				System.out.println("Anzahl Mitarbeiter): ");
				einstellenMitarbeiter = myScanner.nextInt();
				myScanner.nextLine();
				if(einstellenMitarbeiter <= listeNiederlassung.get(auswahlNiederlassungIndex).getArbeiter()) {
					listeNiederlassung.get(auswahlNiederlassungIndex).entlassen(einstellenMitarbeiter);
					return;
				}else {
					System.out.println("Es koennen nur maximal "+listeNiederlassung.get(auswahlNiederlassungIndex).getArbeiter()+ " entlassen werden.");
					scannerErfolg=false;
				}
				
			
				
			} catch (Exception e) {
				e.printStackTrace();
				scannerErfolg=false;
				myScanner.nextLine();
			}
		
	}
	}
}

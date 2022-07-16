package demo009.menu.impl;

import java.util.List;
import java.util.Scanner;

import demo009.ApplicationContext;
import demo009.menu.Command;
import demo009.model.Niederlassung;

public class CommandLoehneFestlegen implements Command {

	@Override
	public String menuItemName() {
		return "Löhne festlegen";
	}

	@Override
	public void execute(ApplicationContext context) {

		while (true) {
			int auswahlNiederlassungIndex = 0;

			System.out.println("========== " + menuItemName());

			boolean scannerErfolg = false;
			System.out.println("Wählen sie die Niederlassung aus, für die der Lohn festgelegt werden soll");

			List<Niederlassung> listeNiederlassung = context.getNiederlassungen();
			int indexNdls;
			System.out.printf("%s %10s\n", "Niederlassung", "Lohn");

			for (indexNdls = 0; indexNdls < listeNiederlassung.size(); indexNdls++) {
				String ausgabeString = indexNdls + ": %s %10s\n";

				System.out.printf(ausgabeString, listeNiederlassung.get(indexNdls).getOrt(),
						listeNiederlassung.get(indexNdls).getLohn());
			}
			System.out.println(indexNdls + ": Zurück");

			Scanner myScanner = new Scanner(System.in);
			while (!scannerErfolg) {
				try {

					System.out.print("Auswahl:");
					auswahlNiederlassungIndex = myScanner.nextInt();
					scannerErfolg = true;
					myScanner.nextLine();
				} catch (Exception e) {
					e.printStackTrace();
					scannerErfolg = false;
					myScanner.nextLine();
				}
			}

			if (auswahlNiederlassungIndex == indexNdls) {
				// Verlassen des Menus
				return;
			}

			int lohnhoehe;

			scannerErfolg = false;
			while (!scannerErfolg) {
				try {
					System.out.printf("Wie hoch soll der Lohn für "
							+ listeNiederlassung.get(auswahlNiederlassungIndex).getOrt() + " sein?%n");
					System.out.println("Lohn (in Taler): ");
					lohnhoehe = myScanner.nextInt();
					myScanner.nextLine();
					listeNiederlassung.get(auswahlNiederlassungIndex).setLohn(lohnhoehe);
					break;

				} catch (Exception e) {
					e.printStackTrace();
					scannerErfolg = false;
					myScanner.nextLine();
				}
				continue;
			}
		}

	}

}

package Businesssimulation;

import java.util.Iterator;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class BusinessSimulation001 {

	public static void printNiederlassung(ArrayList<Niederlassung> niederlassungListe) {
		for (int i = 0; i < 3; i++) {
			System.out.println("------------------------------------------");
			System.out.println("Niederlassung: " + niederlassungListe.get(i).getOrt());
			System.out.println("------------------------------------------");
			System.out.println("Warenart: " + niederlassungListe.get(i).getWarenartString());
			System.out.println("------------------------------------------");
			System.out.println("Anzahl Arbeitende: " + niederlassungListe.get(i).getArbeitende());
			System.out.printf("------------------------------------------%n");
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Wirtschaftssimulation Programmierung II");

		ArrayList<Niederlassung> niederlassungListe = new ArrayList<Niederlassung>();
		
		System.out.println("Verfügbare Niederlassungen: ");

		niederlassungListe.add(new Niederlassung("Dresden", Warenart.GOLD, 2));
		niederlassungListe.add(new Niederlassung("Einbeck", Warenart.BIER, 1));
		niederlassungListe.add(new Niederlassung("Wien", Warenart.WEIN, 2));
		
		Collections.sort(niederlassungListe);
		printNiederlassung(niederlassungListe);
		
		Lager lagerAugsburg = new Lager("Augsburg");
		int erloes = 0;
		int guthaben = 0;
		int runde = 0;

		System.out.printf("Anfangsbestand: %n");
		for (Iterator<Warenart> warenartIterator = lagerAugsburg.getEingelagerteWaren().iterator(); warenartIterator.hasNext();) {
			Warenart warenart = (Warenart) warenartIterator.next();
			System.out.println(Util.convertWarenartToString(warenart) + ": " + lagerAugsburg.getBestand(warenart));
		}

		Scanner myScanner = new Scanner(System.in);

		while (runde < 2) {

			runde++;

			System.out.println("=================Spielrunde " + runde);
			System.out.println("Aktueller Bestand im Lager:");
			
			for (Iterator<Warenart> warenartIterator = lagerAugsburg.getEingelagerteWaren().iterator(); warenartIterator.hasNext();) {
				Warenart warenart = (Warenart) warenartIterator.next();
				System.out.println(Util.convertWarenartToString(warenart) + ": " + lagerAugsburg.getBestand(warenart));
			}

			System.out.println("Guthaben:" + guthaben);
			System.out.println("Wie viele Fuhrwerke sollen gekauft werden");
			
			int antwortKaufFuhrwerk = myScanner.nextInt();
			Fuhrpark.kaufeFuhrwerk(antwortKaufFuhrwerk);
			guthaben=guthaben - (Fuhrpark.getPreisFuhrwerk()*antwortKaufFuhrwerk);
			
			System.out.println("Wie viele Fuhrwerke sollen verkauft werden?");
			int antwortVerkaufFuhrwerk = myScanner.nextInt();
			Fuhrpark.verkaufeFuhrwerk(antwortVerkaufFuhrwerk);
			guthaben = guthaben +(Fuhrpark.getPreisFuhrwerk()*antwortVerkaufFuhrwerk);
			
			
			for (int k = 0; k < niederlassungListe.size(); k++) {
				System.out.println("Gebe den Lohn für " + niederlassungListe.get(k).getOrt()+ " ein.");
				int lohnAnfang = myScanner.nextInt();
				niederlassungListe.get(k).setLohn(lohnAnfang);
			}

			for (int i = 0; i < 3; i++) {
				
				String standort=niederlassungListe.get(i).getOrt();
				String warenartNiederlassung=niederlassungListe.get(i).getWarenartString();
				int lohn=niederlassungListe.get(i).getLohn();

				System.out.println("=====================================");
				System.out.println("Lohn " + standort + " " + lohn);

				System.out.println("Wollen sie den Lohn für " + standort + " ändern(j/n)");
				String antwortAendern = myScanner.next();
				if (antwortAendern.equals("j")) {
					System.out.println("Auf was soll der Lohn geändert werden?");
					int antwortLohn = myScanner.nextInt();
					lohn = antwortLohn;
				}

				System.out.println("Wie viele neue Arbeiter sollen in Niederlassung "+standort+" eingestellt werden?");
				int antwortEinstellen = myScanner.nextInt();
				niederlassungListe.get(i).einstellen(antwortEinstellen);

				System.out.println("Wie viele Arbeitskräfte sollen " + standort + " entlassen werden?");
				int antwortEntlassen = myScanner.nextInt();

				niederlassungListe.get(i).entlassen(antwortEntlassen);

				System.out.println("Soll ein Fuhrwerk geschickt werden? (j/n)");

				while (true) {
					String antwortProd = myScanner.next();
					if (antwortProd.equals("j")) {
						if (Fuhrpark.getAnzahl() > 0){
							niederlassungListe.get(i).anfordern();
							niederlassungListe.get(i).produzieren();
							int produzierteMenge = niederlassungListe.get(i).abholen();
							lagerAugsburg.einlagern(niederlassungListe.get(i).getWarenart(), produzierteMenge);
							Fuhrpark.lowerAnzahl();
							break;
						}else if (Fuhrpark.getAnzahl() <= 0) {
							System.out.println("Sie haben nicht genug Fuhrwerke, bitte versuchen Sie es erneut");
							continue;
					}else if (antwortProd.equals("n")) {
						break;
					} else {
						System.out.println("Ungültige Eingabe, bitte versuche es erneut");
						continue;
						}
					}
				}
				
				System.out.println("Aktueller Bestand im Lager:");
				for (Iterator<Warenart> warenartIterator = lagerAugsburg.getEingelagerteWaren()
						.iterator(); warenartIterator.hasNext();) {
					Warenart warenart = (Warenart) warenartIterator.next();
					System.out.println(Util.convertWarenartToString(warenart) + ": " + lagerAugsburg.getBestand(warenart));
				}
				while (true) {

					System.out.println("Wie viel " + warenartNiederlassung+ " soll verkauft werden");

					int antwortVerkauf = myScanner.nextInt();

					if (lagerAugsburg.getBestand(niederlassungListe.get(i).getWarenart()) < antwortVerkauf) {

						System.out.println("Sie besitzen nicht genug Waren. Versuchen Sie es erneut");
						continue;

					} 
					int preis = (int) (Math.random() * 10 + 5);
					int lohnkosten = (niederlassungListe.get(i).getArbeitende() * lohn);
						
					erloes = lagerAugsburg.verkaufen(niederlassungListe.get(i).getWarenart(), antwortVerkauf,
								preis);
						
					int gewinn = erloes - lohnkosten;

					System.out.println("Erlös durch Verkauf in " + niederlassungListe.get(i).getOrt() + ": " + erloes);
					System.out.println("Lohnkosten: "+lohnkosten);
					System.out.println("Gesamterlös: " +gewinn);
					System.out.println("Guthaben: "+(gewinn + guthaben));
					guthaben = gewinn + guthaben;
					break;

				}
				
			}
			System.out.println("=======================");
			System.out.println("Auswertung nach Ende der"+runde+"-ten Runde:");
			
			for(int i=0; i < niederlassungListe.size(); i++) {
				System.out.println( niederlassungListe.get(i).getOrt()+" "+(lagerAugsburg.getBestand(niederlassungListe.get(i).getWarenart())));
			}
			System.out.println("Gesamtguthaben: " + guthaben);
			
			
			
		}

	}
}

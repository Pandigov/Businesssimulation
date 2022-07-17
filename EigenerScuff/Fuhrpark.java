package Businesssimulation;

public class Fuhrpark {
	public static int anzahl = 1;
	private static int preisFuhrwerk = 10;
	
	public static int getAnzahl(){
		return anzahl;
	}
	
	
	public static int getPreisFuhrwerk() {
		return preisFuhrwerk;
	}


	public static void lowerAnzahl() {
		anzahl--;
	}
	
	public static void kaufeFuhrwerk(int antwKaufFuhrwerk) {
		anzahl=anzahl+antwKaufFuhrwerk;
	}
	
	public static void verkaufeFuhrwerk(int antwortVerkaufFuhrwerk) {
		anzahl=anzahl-antwortVerkaufFuhrwerk;
	}
}

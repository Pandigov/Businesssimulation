package demo009.misc;


import java.util.Iterator;
import demo009.model.Warenart;
import demo009.ApplicationContext;
import demo009.model.Niederlassung;

public class DisplayService {
	
	public static int anzeigenNiederlassung(ApplicationContext appContext) {
		String format = "%-10s %-10s %-20s %-20s %-13s %n ";
		System.out.println("===============Niederlassungen");
		System.out.printf(format, "Ort","Warenart","Status Anforderung","gesendete Fuhrwerke","Arbeiter");
		
		for (Niederlassung niederlassung : appContext.getNiederlassungen()) {
			System.out.printf(format, niederlassung.getOrt(), niederlassung.getWarenartString(),
		    niederlassung.wurdeAngefordert() ? "angefordert" : "nicht angefordert", appContext.geplanteFuhrwerke.get(niederlassung) == null ? "0" : appContext.geplanteFuhrwerke.get(niederlassung)
		    ,niederlassung.getArbeiter()+"\n");
		
		}
		return 0;
	}

	public static int anzeigenLager(ApplicationContext appContext) {
		System.out.println("===============Lager");	
		
		for (Iterator<Warenart> warenartIterator = appContext.getLager().getEingelagerteWaren().iterator(); warenartIterator.hasNext();) {
			Warenart warenart = (Warenart) warenartIterator.next();
			System.out.println(Util.convertWarenartToString(warenart) + ": " +appContext.getLager().getBestand(warenart));
	
		}
		System.out.println("====================================");
		 return 0;
	}
	
	public static int anzeigenGuthaben(ApplicationContext appContext) {
		System.out.println("===============Finanzen");	
		System.out.println("Guthaben(in Taler): "+appContext.getGuthaben());
		return 0;
	}
}

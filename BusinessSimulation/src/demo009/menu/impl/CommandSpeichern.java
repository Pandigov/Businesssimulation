package demo009.menu.impl;

import java.io.File;

import demo009.ApplicationContext;
import demo009.menu.Command;
import demo009.misc.Util;
import demo009.model.Warenart;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.io.BufferedWriter;

public class CommandSpeichern implements Command {

	@Override
	public String menuItemName() {
		return "Speichern";
	}

	@Override
	public void execute(ApplicationContext context) {
		FileWriter fw, fw2, fw3, fw4 = null;
		
		try {
			fw = new FileWriter (new File ("z:/Savegame/fuhrpark.txt"));
			fw.write(context.getVerfuegbareFuhrwerke()+";"+context.getFuhrpark().getPreisFuhrwerk());
			fw.close();
			
			fw2 = new FileWriter (new File ("z:/Savegame/guthaben.txt"));
			fw2.write(""+context.getGuthaben());
			fw2.close();
			
			fw3 = new FileWriter (new File ("z:/Savegame/lager.txt"));
			BufferedWriter bw = new BufferedWriter(fw3);
			
			bw.write(context.getLager().getOrt());
			bw.newLine();
			
			for (Iterator<Warenart> warenartIterator = context.getLager().getEingelagerteWaren().iterator(); warenartIterator.hasNext();) {
				Warenart warenart = (Warenart) warenartIterator.next();
				bw.write(Util.convertWarenartToString(warenart) + ";" + context.getLager().getBestand(warenart));
				bw.newLine();
			}
			bw.close();
			
			fw4 = new FileWriter (new File("z:/Savegame/niederlassungen.txt"));
			BufferedWriter bw2 = new BufferedWriter(fw4);
			
			for (int i=0; i < context.getNiederlassungen().size(); i++) {
				
				bw2.write(context.getNiederlassungen().get(i).getOrt()+";"+context.getNiederlassungen().get(i).getWarenartString()+";"
				+context.getNiederlassungen().get(i).getArbeiter()+";"+context.getNiederlassungen().get(i).getLohn());
				bw2.newLine();
			}
			bw2.close();
			
			}
		
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
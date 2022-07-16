package demo009.menu.impl;

import java.io.File;

import demo009.ApplicationContext;
import demo009.menu.Command;
import demo009.misc.Util;
import demo009.model.Warenart;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.io.BufferedReader;
import java.util.StringTokenizer;

public class CommandLesen implements Command {

	@Override
	public String menuItemName() {
		return "Spielstand laden";
	}

	@Override
	public void execute(ApplicationContext context) {
		FileReader fr, fr2, fr3;
		try {
			fr = new FileReader("g:/Savegame/fuhrpark.txt");
			BufferedReader br = new BufferedReader(fr);
			
			String eineZeile= br.readLine();
			StringTokenizer st = new StringTokenizer(eineZeile,";");
			
		    context.getFuhrpark().setAnzahlFuhrwerke(Integer.parseInt(st.nextToken().trim()));
		    context.getFuhrpark().setPreisFuhrwerk(Integer.parseInt(st.nextToken().trim()));
		    
		    System.out.println(context.getFuhrpark().getPreisFuhrwerk());
		    
		    
		    fr2= new FileReader("g:/Savegame/guthaben.txt");
		    BufferedReader br2 = new BufferedReader(fr2);
		    context.setGuthaben(Integer.parseInt(br2.readLine()));
		    System.out.println(context.getGuthaben());
			
		    
		    fr3=new FileReader("g:/Savegame/lager.txt");
		    BufferedReader br3 = new BufferedReader(fr3);
		    
		    String eineZeile2 = br3.readLine();
		    
		    while(eineZeile2 !=null){
		    	
		    	StringTokenizer st2 = new StringTokenizer(eineZeile2, ";");
		    	
		    	for (Iterator<Warenart> warenartIterator = context.getLager().getEingelagerteWaren().iterator(); warenartIterator.hasNext();) {
					Warenart warenart = (Warenart) warenartIterator.next();
					context.getLager().lagerbestand.put(warenart, Integer.parseInt(st2.nextToken().trim()));
					
		    	}
		    	
	
		    	context.getLager().einlagern(null, Integer.parseInt(st2.nextToken().trim()));
		    }
		    
			br.close();
			br2.close();
			
			
			}
		
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}

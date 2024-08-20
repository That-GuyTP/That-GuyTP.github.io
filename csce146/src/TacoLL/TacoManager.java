package TacoLL;

import java.util.Scanner;
import java.io.*;

public class TacoManager {
	
	public static final String DELIM = "\t";
	public static final int BODY_FIELD_AMT = 3;
	private GenLL<Taco> tacos;
	
	//DEFAULT CONSTRUCTOR
	public TacoManager() {
		tacos = new GenLL<Taco>();
	}
	
	//ADD TACO
	public void addTaco(Taco xTaco) {
		tacos.add(xTaco);
	}
	
	//REMOVE TACO
	public void removeTaco(String xName) {
		tacos.reset();
		while(tacos.hasMore()) {
			if(tacos.getCurrent().getName().equalsIgnoreCase(xName)) {
				tacos.removeCurrent();
				break;
			}//IF
			tacos.gotoNext();
		}//EOC WHILE
	}
	
	//SORT TACO (BY PRICE)
	private void sortTacos() {
		boolean swapped = true;
		while(swapped) {
			swapped = false;
			for(int i = 0; i < tacos.getSize(); i++) {
				if(tacos.getAt(i + 1) == null)
					break;
				if(tacos.getAt(i).getPrice() > tacos.getAt(i + 1).getPrice()) {
					Taco temp = tacos.getAt(i);
					tacos.setAt(i, tacos.getAt(i+1));
					tacos.setAt(i+1, temp);
					swapped = true;
				}//EOC IF 2
			}//EOC FOR
		}//EOC WHILE
	}
	
	//WRITE TACO LL
	public void writeTacoFile(String xName) {
		try {
			PrintWriter fileWriter = new PrintWriter(new FileOutputStream(xName));
			tacos.reset(); //resets current reference back to start/head
			while(tacos.hasMore()) {
				Taco xTaco = tacos.getCurrent();
				fileWriter.println(xTaco.getName() + DELIM + xTaco.getLocation() + DELIM + xTaco.getPrice());
				tacos.gotoNext();
			}//EOC WHILE
			fileWriter.close();
		}catch (Exception e) {
			e.printStackTrace();
		}//EOC TRY-CATCH
	}
	
	public void readTacoFile(String xName) {
		try {
			Scanner fileScanner = new Scanner(new File(xName));
			while(fileScanner.hasNextLine()) {
				String fileLine = fileScanner.nextLine();
				String[] splitLines = fileLine.split(DELIM); //Splits each element of a line into 3 parts based off our added DELIM's we declared as a constant. If they're included in the file then they are split and created as elements.
				if(splitLines.length == BODY_FIELD_AMT) {
					String name = splitLines[0];
					String loc = splitLines[1];
					double price = Double.parseDouble(splitLines[2]);
					Taco xTaco = new Taco(name, loc, price);
					this.addTaco(xTaco);
				}//EOC IF
			}//EOC WHILE
			fileScanner.close();
		}catch(Exception e) {
			e.printStackTrace();
		}//EOC TRY-CATCH
	}
	
	public void printTacos() {
		tacos.print();
	}
	
}//EOC TacoManager

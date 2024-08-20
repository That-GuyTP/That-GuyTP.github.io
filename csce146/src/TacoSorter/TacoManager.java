package TacoSorter;

import java.util.Scanner;
import java.io.*;

public class TacoManager {

	//INSTANCE VARIABLES
	private Taco[] tacos;
	public static final int DEF_SIZE = 10;
	public static final String DELIM = "\t";
	public static final int BODY_FIELD_AMT = 3;
	public static final int HEADER_FIELD_AMT = 2;
	
	//DEFAULT CONSTRUCTOR
	public TacoManager() {
		init(DEF_SIZE);
	}
	
	//PARAMETER COSNTRUCTOR
	public TacoManager(int size) {
		init(size);
	}
	
	//DECLARE ARRAY SIZE
	public void init(int size) {
		if(size >= 1)
			tacos = new Taco[size];
		else
			tacos = new Taco[DEF_SIZE];
	}
	
	//ADD TACO TO ARRAY
	public void addTaco(Taco xTaco) {
		if(tacos[tacos.length -1] != null)
			return;
		for(int i = 0; i < tacos.length; i++) {
			if(tacos[i] == null) {
				tacos[i] = xTaco;
				break;
			}//EOC IF
		}//EOC FOR
	}
	
	//REMOVE TACO FROM ARRAY
	public void removeTaco(String xName) {
		int removeIndex = -1;
		for(int i = 0; i < tacos.length; i++) {
			if(tacos[i] != null && tacos[i].getName().equals(xName)) {
				removeIndex = i;
				break;
			}//EOC IF
		}//EOC FOR
		if(removeIndex == -1) {
			return;
		}else {
			for(int i = removeIndex; i < tacos.length - 1; i++) {
				tacos[i] = tacos[i + 1];
			}//EOC FOR
			tacos[tacos.length - 1] = null;
		}//EOC ELSE
	}//EOC removeTaco
	
	//SORT TACOS
	private void sortTacos() {
		boolean swapped = true;
		while(swapped == true) {
			swapped = false;
			for(int i = 0; i < tacos.length; i++) {
				if(tacos[i + 1] == null) {
					break;
				}//EOC IF 1
				if(tacos[i].getPrice() > tacos[i + 1].getPrice()) {
					Taco temp = tacos[i];
					tacos[i] = tacos[i + 1];
					tacos[i + 1] = temp;
					swapped = true;
				}//EOC IF 2
			}//EOC FOR
		}//EOC WHILE
	}//EOC sortTacos
	
	//WRITE TO FILE
	public void writeTacoFile(String xName) {
		try {
			PrintWriter  fileWriter = new PrintWriter(new FileOutputStream(xName));
			fileWriter.println("Taco Amt: " + DELIM + tacos.length);
			for(Taco taco : tacos) {
				if(taco == null) {
					break;
				}//EOC IF
				fileWriter.println(taco.getName() + DELIM + taco.getLocation() + DELIM + taco.getPrice());
			}//EOC FOR
			fileWriter.close();
		}catch(Exception e) {
			e.printStackTrace();
		}//EOC TRY-CATCH
	}//EOC writeTacoFile
	
	//READ TACO FILE
	public void readTacoFile(String xName) {
		try {
			Scanner fileScanner = new Scanner(new File(xName));
			String fileLine = fileScanner.nextLine();
			String[] splitLines = fileLine.split(DELIM);
			if(splitLines.length == HEADER_FIELD_AMT) {
				String name = splitLines[0];
				String location = splitLines[1];
				double price = Double.parseDouble(splitLines[2]);
				Taco xTaco = new Taco(name, location, price);
				this.addTaco(xTaco);
			}//EOC IF
			fileScanner.close();
		} catch (Exception e) {
			e.printStackTrace();
		}//EOC TRY-CATCH
	}//EPC readTacoFile
	
	//PRINT TACOS
	public void printTacos() {
		for(Taco taco : tacos) {
			if(taco == null)
				break;
			System.out.println(taco);
		}//EOC FOR
	}
	//EOC printTacos
}//EOC TacoManager

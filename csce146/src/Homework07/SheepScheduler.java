package Homework07;

//Thomas Peterson
//This is the backend class for my SheepScheduler.

import java.util.Scanner;
import java.io.*;

public class SheepScheduler {

	//CONSTANTS & DECLARATIONS
	private MinHeap<Sheep> sheep;
	public static final Scanner kb = new Scanner(System.in);
	public static final String DELIM = "\t";
	public static final int BODY_FIELD_AMT = 3;
	
	//CONSTRUCTOR
	public SheepScheduler() {
		sheep = new MinHeap<Sheep>();
	}//EOC DEF CON
	
	//READ SHEEP FILE
	public void readSheepFile(String xName) {
		try {
			Scanner fileScanner = new Scanner(new File(xName + ".txt"));
			while(fileScanner.hasNextLine()) {
				String fileLine = fileScanner.nextLine();
				String[] splitLines = fileLine.split(DELIM);
				if(splitLines.length >= BODY_FIELD_AMT) {
					String name = splitLines[0].trim();
					int sTime = Integer.parseInt(splitLines[1]);
					int aTime = Integer.parseInt(splitLines[2]);
					Sheep xSheep = new Sheep(name, sTime, aTime);
					sheep.add(xSheep);
				}//EOC IF
			}//EOC WHILE
			fileScanner.close();//DON'T FORGET
		}catch(Exception e) {
			System.out.println("Sorry, there was an error locating the file");
			e.printStackTrace();
		}//EOC TRY-CATCH
	}//EOC readSheepFile
	
	//PRINT SHEEP SCHEDULE
	public void scheduleSheep() {
		System.out.println("Sheep Sheering Schedule:");
		sheep.print();
	}//EOC scheduleSheep
	
	//ADD SHEEP AFTER SCHEDULE IS DONE
	public void addSheep(String xName, int xSTime, int xATime) {
		Sheep xSheep = new Sheep(xName, xSTime, xATime);
		sheep.add(xSheep);
	}//EOC addSheep
	
}//EOC SheepScheduler

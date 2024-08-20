package Lab07;

//Thomas Peterson
//This is the FruitTreeTester class of the lab. It contains all required methods and operations.

import java.util.Scanner;
import java.io.*;

public class FruitTreeTester {

	//CONSTANTS
	public static final Scanner kb = new Scanner(System.in);
	public static final String DELIM = "\t";
	public static final int BODY_FIELD_AMT = 2;
	public static final Fruit decoyBanana = new Fruit("Banana", 1.6365611326426943);
	public static LinkedBST<Fruit> fruitBST = new LinkedBST<Fruit>();
	
	public static void main(String[] args) {

		//GREETING & FILE READING
		System.out.println("Hello and welcome to the Fruit Tree sorter.");
		System.out.println("Please enter the name of the text file you would like to be read (without extension or file path).");
		String fileName = kb.nextLine();
		readFruitFile(fileName);
		
		//OPERATIONS REQUIRED
		System.out.println("Printing in Pre-Order format");
		fruitBST.printPreorder();
		System.out.println();
		System.out.println("Printing in In-Order format");
		fruitBST.printInorder();
		System.out.println();
		System.out.println("Printing in Post-Order format");
		fruitBST.printPostorder();
		System.out.println();
		System.out.println("Deleting a \"Banana\" from the list");
		fruitBST.remove(decoyBanana);
		fruitBST.printInorder();
		
		
	}//EOC MAIN METHOD

	//READ FRUIT FILE
	public static void readFruitFile(String xName) {
		try {
			Scanner fileScanner = new Scanner(new File(xName + ".txt"));
			while(fileScanner.hasNextLine()) {
				String fileLine = fileScanner.nextLine();
				String[] splitLines = fileLine.split(DELIM);
				if(splitLines.length >= BODY_FIELD_AMT) {
					String type = splitLines[0].trim();
					Double weight = Double.parseDouble(splitLines[1].trim());
					Fruit xFruit = new Fruit(type, weight);
					fruitBST.add(xFruit);
				}//EOC IF
			}//EOC WHILE
			fileScanner.close();//DON'T FORGET
		}catch(Exception e) {
			System.out.println("Sorry, there was an error locating the file");
			e.printStackTrace();
		}//EOC TRY-CATCH
	}//EOC readFruitFile
	
}//EOC FruitTreeTester

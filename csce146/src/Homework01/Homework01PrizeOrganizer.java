package Homework01;

import java.io.*;
import java.util.Scanner;
import java.util.Random;

public class Homework01PrizeOrganizer {

	//CONSTANTS
	private static final int MAX_PRIZES = 5;
	private static final int MAX_GUESS_RANGE = 1300;
	
	//DEFINING PRIZE OBJECT
	//Here I'm just defining an object "Prize" and it's values. For this homework I'll need a name and value.
	private class Prize {
		
		//INSTANCE VARIABLES
		String name;
		int price;
		
		//DEFAULT CONSTRUCTOR
		public Prize() {
			this.name = "N/A";
			this.price = 1;
		}//DEFAULT CONSTRUCTOR
		
		//PARAMETER CONSTRUCTOR
		public Prize(String xName, int xPrice) {
			this.name = xName;
			this.price = xPrice;
		}//EOC PARAMETER CONSTRUCTOR

		//ACCESSORS & MUTATORS
		public String getName() {
			return this.name;
		}//EOC getName

		public void setName(String xName) {
			if(xName != null)
				this.name = xName;
			this.name = "N/A";
		}//EOC setName

		public int getPrice() {
			return price;
		}//EOC getPrice

		public void setPrice(int xPrice) {
			if(xPrice > 0)
				this.price = xPrice;
			this.price = 1;
		}//EOC setPrice
		
	}//EOC PRIZE
	
	//INSTANCE VARIABLES
	private Prize[] prizes;
	private Prize[] allPrizes;
	
	//DEFAULT CONSTRUCTOR
	public Homework01PrizeOrganizer() {
		prizes = new Prize[MAX_PRIZES];
		allPrizes = new Prize[0];
	}//EOC DEFAULT CONSTRUCTOR
	
	//ReadPrizesFromFile METHOD
	//This method reads the file and then assigns each valid prize to an index in the array "allPrizes[]".
	public void readPrizesFromFile(String PrizeList) {
		try {
			Scanner fileScanner = new Scanner(new File("Prize List.txt"));
			
			//DEFINING AMOUNT OF PRIZES
			int numOfPrizes = 0;
			while (fileScanner.hasNextLine()) {
				numOfPrizes++;
				fileScanner.nextLine();
			}//EOC WHILE
			
			//POPULATING ALLPRIZE ARRAY
			while (fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine();
				String[] split = line.split("\t");
				for(int i = 0; i <= numOfPrizes; i++) {
					if (split.length == 2) {
						String name = split[0];
						int price = Integer.parseInt(split[1]);
						Prize prize = new Prize(name, price);
						allPrizes[i] = prize;
					}//EOC IF
				}//EOC FOR
			}//EOC WHILE
			fileScanner.close();//NEEDED CLOSE
		} catch (Exception e) {
			System.out.println("Sorry there was an issue reading the file");
			e.printStackTrace();
		}//EOC TRY-CATCH
	}//EOC readPrizesFromFile
	
	//pickRandomPrizes METHOD
	//This method picks random indexs from allPrizes[] and assigns it to the array "prizes[]".
	public void pickRandomPrizes() {
		Random rand = new Random();
		for(int i = 0; i <= prizes.length; i++) {
			Prize randPrize = allPrizes[rand.nextInt(allPrizes.length)];
			if (prizes[i].equals(randPrize)) {
				break;
			}else {
				prizes[i].equals(randPrize);
			}//EOC IF-ELSE
		}//EOC FOR
	}//EOC pickRandomPrizes
	
	//getTotalValue METHOD
	//This method just gets the value of each object's price in the prizes array and adds it together.
	public int getTotalValue() {
		int total = 0;
		for(int i = 0; i < 6 && prizes[i] != null; i++) {
			total += prizes[i].getPrice();
		}//EOC FOR
		return total;
	}//EOC getTotalValue
	
	//checkWin METHOD
	//This method simply checks the user's guess and sees if it's valid for a win.
	public boolean checkWin(int guess) {
		int total = getTotalValue();
		if ((total - guess) <= MAX_GUESS_RANGE && guess <= total) {
			return true;
		}else {
			return false;
		}//EPC IF
	}//EOC checkWin
	
	public String print() {
		for(int i = 0; i < allPrizes.length; i++) {
			System.out.println(allPrizes[i] + "");
		}//EOC FOR
		return "";
	}
	
}//EOC Homework01PrizeOrganizer

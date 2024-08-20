package TacoSorter;

import java.util.Scanner;

public class TacoManagerFE {

	private static Scanner kb = new Scanner(System.in);
	private static TacoManager tacoManager = new TacoManager();
	
	public static void main(String[] args) {
		printGreeting();
		boolean quit = false;
		while(!quit) {
			printChoices();
			int choice = kb.nextInt();
			kb.nextLine();
			switch(choice) {
			case 1:
				addTaco();
				break;
			case 2:
				removeTaco();
				break;
			case 3:
				readTacoFile();
				break;
			case 4:
				writeTacoFile();
				break;
			case 9:
				quit = true;
				break;
			default:
				System.out.println("Invalid Input");
			}//EOC SWITCH
			tacoManager.printTacos();
		}

	}//EOC MAIN METHOD

	//GREETING
	public static void printGreeting() {
		System.out.println("Welcome to the Taco Manager");
	}//EOC printGreeting
	
	//CHOICES
	public static void printChoices() {
		System.out.println("Enter 1 to add a taco\n"
				+ "Enter 2 to remove a taco\n"
				+ "Enter 3 to read a taco database file\n"
				+ "Enter 4 to write to a taco database file\n"
				+ "Enter 9 to quit");
	}//EOC printChoices
	
	//ADD TACO
	public static void addTaco() {
		System.out.println("Enter the name of the taco");
		String name = kb.nextLine();
		System.out.println("Enter the location of the taco");
		String location = kb.nextLine();
		System.out.println("Enter the price of the taco");
		double price = kb.nextInt();
		kb.nextLine();
		tacoManager.addTaco(new Taco(name, location, price));
	}//EOC addTaco
	
	//REMOVE TACO
	public static void removeTaco() {
		System.out.println("Enter the name of the taco to remove");
		String name = kb.nextLine();
		tacoManager.removeTaco(name);
	}//EOC removeTaco
	
	//READ TACO FILE
	public static void readTacoFile() {
		System.out.println("Enter the file name to read a TacoDB");
		String fileName = kb.nextLine();
		tacoManager.readTacoFile(fileName);
	}
	
	//WRITE TACO FILE
	public static void writeTacoFile() {
		System.out.println("Enter the file name to write a TacoDB File");
		String fileName = kb.nextLine();
		tacoManager.writeTacoFile(fileName);
	}
	
}//EOC TacoManagerFE

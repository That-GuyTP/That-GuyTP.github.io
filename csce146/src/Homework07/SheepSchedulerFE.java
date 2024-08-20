package Homework07;

//Thomas Peterson
//This is the front end of my SheepScheduler class for HW07.

import java.util.Scanner;

public class SheepSchedulerFE {

	//CONSTANTS, VARIABLES, & DECLARATIONS
	public static Scanner kb = new Scanner(System.in);
	public static SheepScheduler ss = new SheepScheduler();
	public static boolean quit = false;
	public static String answer = "";
	public static int choice = 0;
	
	//MAIN METHOD
	public static void main(String[] args) {
		
		//WELCOME & LOOP BEGINS
		System.out.println("Welcome to the Sheep Scheduler Program.");
		while(quit != true) {
			System.out.println("Please enter the name of your sheep list file (without extension or path):");
			answer = kb.nextLine();
			ss.readSheepFile(answer);
			ss.scheduleSheep();
			System.out.println("---Schedule Complete---");
			afterListCompletes();
		}//EOC WHILE
		
	}//EOC MAIN METHOD

	//AFTERLISTCOMPLETES METHOD
	public static void afterListCompletes() {
		System.out.println("Enter 1 to add another sheep. Enter 2 to quit the program.");
		choice = kb.nextInt();
		kb.nextLine();
		
		//ADD ANOTHER SHEEP
		if(choice == 1) {
			String xName;
			int xSTime;
			int xATime;
			System.out.println("Enter the name of the sheep:");
			xName = kb.nextLine();
			System.out.println("Enter the sheer time for the sheep (in whole minutes):");
			xSTime = kb.nextInt();
			kb.nextLine();
			System.out.println("Enter the arrival time for the sheep (in whole minutes):");
			xATime = kb.nextInt();
			kb.nextLine();
			ss.addSheep(xName, xSTime, xATime);
			System.out.println("---Creating New Schedule---");
			ss.scheduleSheep();
			System.out.println("---Schedule Complete---");
		//QUIT
		}else if(choice == 2) {
			System.out.println("Have a good day!");
			quit = true;
		}//EOC I-EI
	}//EOC afterListCompletes
	
}//EOC SheepSchedulerFE

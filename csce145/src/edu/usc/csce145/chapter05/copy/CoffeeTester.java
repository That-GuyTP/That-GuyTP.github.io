package edu.usc.csce145.chapter05.copy;

//Thomas Peterson

import java.util.Scanner;

public class CoffeeTester {

	public static void main (String args[]) {
		
		//SCANNER
		Scanner kb;
		kb = new Scanner(System.in);
		
		//VARIABLES
		String name;
		int caffeineContent;
		boolean reprompt = false;
		String answer;
		
		//INTRO
		System.out.println("Hello and welcome to the coffee ordering machine.");
		
		//REPROMPT START
		while(reprompt != true) {
			
		//COFFEE 1 / OBJECT 1
		System.out.println("Please enter the name of the first coffee:");
		name = kb.nextLine();
		System.out.println("Please enter the amount of caffeine in this coffee (50-300 mgs):");
		caffeineContent = kb.nextInt();
		kb.nextLine();//CONSUMING THE REST OF THIS LINE SO SCANNER WORKS AFTER.
		Coffee c1 = new Coffee(name, caffeineContent);
		
		//COFFEE 2 / OBJECT 2
		System.out.println("Please enter the name of the second coffee: ");
		name = kb.nextLine();
		System.out.println("Please enter the amount of caffeine in this coffee (50-300 mgs):");
		caffeineContent = kb.nextInt();
		kb.nextLine();//CONSUMING THE REST OF THIS LINE SO SCANNER WORKS AFTER.
		Coffee c2 = new Coffee(name, caffeineContent);
		
		//PRINTING
		System.out.println();
		System.out.println("Thank you. I am creating both coffees...");
		System.out.println(c1.toString());
		System.out.println("If you were to drink " + c1.RiskyAmount() + " cups in one hour, you would have a health risk.");
		System.out.println("");
		System.out.println(c2.toString());
		System.out.println("If you were to drink " + c2.RiskyAmount() + " cups in one hour, you would have a health risk.");
		System.out.println("Are these coffee's the same coffee? " + c1.equals(c2));
		
		//PROGRAM RESTART PROMPT
		System.out.println("Would you like to create more coffee objects? (Enter \"Yes\" or \"No\")");
		answer = kb.nextLine();
			if(answer.equalsIgnoreCase("No")) {
				System.out.println("Exiting the program. Have a good day!");
				reprompt = true;
			}else if(answer.equalsIgnoreCase("Yes")){
				reprompt = false;
			}else {
				System.out.println("Sorry, that was not a valid response. Exiting program.");
			}//EOC IF-ELSE CHAIN
			
		}//EOC WHILE
		
		
		kb.close();
		
	}//EOC MAIN METHOD
	
}//EOC CoffeeTester

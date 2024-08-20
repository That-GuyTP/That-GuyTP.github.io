package edu.usc.csce145.chapter02;

import java.util.Scanner;
//imports are required to be inside the package. Used to import needed, precreated tools.

public class WhatsForBreakfast {

	
	public static void main (String[] args) {
		
		Scanner kb = new Scanner(System.in);
		//Scanner used to create an object (kb) used to read the user's input from the keyboard.
		
		//Can also be declared as:
		//Scanner kb;
		//kb = new Scanner(System.in);
		
		System.out.println("what do you want for breakfast?");
		
		String breakfast;
		//Declared a variable. String is a type of command used to allow the user to input as many items as they need.
		
		breakfast = kb.nextLine();
		//.nextLine is used to read the entire line. This line here is being used to declare that the input of whatever the user
		//types is going to be declared as the String breakfast.
		
		System.out.println("Enter a beverage of your choice:");
		
		String drink =kb.next();
		//.next is used to get the next full value or word.
		
		System.out.println("Your breakfast for today is: " + breakfast + " with " + drink);
		// "+" is used to combine/join two segments/variables together.
		
		
		kb.close();
		//Used to stop the scanner program from running after the program is ran. Helps clear up cpu usuage and drain.
		
	}//EOC main
	
}//EOC class

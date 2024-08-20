package edu.mtc.egr281.chapter04;

//Thomas Peterson
//Project06
//Current Date: 10-06-2022
//Due Date: 10-06-2022
//Using scanners and the java utility random, this program is a game that will attempt to guess your number.

import java.util.Scanner;

public class Project06 {

	
	public static void main(String[] args) {
		
		//Algorithm Design:
		//Understand what he wants from me
		//Create a prompt that ask what the user wants the higher bound number to be
		//Begin to guess what the user's number is by dividing the higher bound number by 2
		//Output the guess and prompt the user for higher, lower, or the answer
		//Output accordingly by changing the new range.
		//Add a cheat detector
		
		//STORAGE
		Scanner kb = new Scanner(System.in);
		
		//Random NumberGenerator = new Random();
		
		int NumberOfGuesses = 0;
		int CompGuess = 0;
		int LowerBound = 1;
		String Input = "";
		
		//INPUT
		System.out.println("Welcome to can a computer guess your number!");
		System.out.println("You may pick any number above zero, so long as it isn't a decimal number.");
		System.out.println("To begin please enter the higher bound of all the numbers you wish for me to guess through: ");
		int HigherBound = kb.nextInt();
		
		//PROCESS
		do {
			++ NumberOfGuesses;
			CompGuess = ((HigherBound + LowerBound) / 2);
			
			System.out.println("Is your number " + CompGuess + "?");
			Input = kb.next();
			
				//ANSWER
			if(Input.equalsIgnoreCase("y")) {
				System.out.println("I win. I guessed your number in " + NumberOfGuesses + " guesses.");
				break;
				
				//HIGHER	
				} else if(Input.equalsIgnoreCase("h")) {
				++NumberOfGuesses;
				LowerBound = (CompGuess + 1);
				System.out.println("Higher it is!");
			
				//LOWER
				} else if(Input.equalsIgnoreCase("l")) {
				HigherBound = (CompGuess - 1);
				System.out.println("Lower it is!");
			
				//CHEATING (DOES NOT WORK CURRENTLY. CAN"T SEEM TO FIND OUT THE NEEDED EXPRESSION.
				} else if(HigherBound == LowerBound) {
				System.out.println("Hey no cheating!");
				System.out.println("Since you want to lie, the program is ending. I win by ruling of disqualification. Try again.");
				kb.close();
				
				//INVALID INPUT
				} else {
				System.out.println("Sorry not a valid input. Please try again");
				System.out.print("Please input 'y' if I guessed correctly, 'l' if it is lower, or 'h' if it is higher");
			}//EOC ElseIf
		
		} while (Input != "y");
		System.out.print("Thank you for playing!");
		
		kb.close();
	}//EOC main
	
}//EOC Project06

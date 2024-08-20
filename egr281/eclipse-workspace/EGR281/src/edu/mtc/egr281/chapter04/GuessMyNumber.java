package edu.mtc.egr281.chapter04;

import java.util.Random;
import java.util.Scanner;

public class GuessMyNumber {

	public static void main(String[] args) {
		
		//STORE
		Scanner kb = new Scanner(System.in);
			//RANDOM
			Random numberGenerator = new Random();
			
			//INTS
			int numberOfGuesses = 0;
			int usersGuess = -1;
			
		
		//INPUT
		System.out.println("Welcome to Guess My Number!");
		System.out.println("I will generate a random number for you to guess");
		System.out.println("Please enter the upper bound: ");
		int theNumber = numberGenerator.nextInt(kb.nextInt() + 1);
		//PROCESS
		do {
			++numberOfGuesses;
			
			System.out.print("Enter guess #" + numberOfGuesses + ": ");
			usersGuess = kb.nextInt();
			
			if(usersGuess < theNumber) {
				System.out.println("Guess again, this time go HIGHER");
			}else if(usersGuess > theNumber) {
				System.out.println("Guess again, this time go LOWER");
				
			}//EOC elseif
			
		} while(usersGuess != theNumber);
		
		//OUTPUT
		System.out.print("You guessed my number " + theNumber);;
		System.out.println(", in " + numberOfGuesses + " guesses");
		
		kb.close();
	}//EOC main
	
}//EOC GMN

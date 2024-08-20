package edu.usc.csce145.chapter03;

import java.util.Scanner;
import java.util.Random;

public class BranchingStatements_GuessTheValue {

	//Constants
	public static final int POOL = 100;
	
	public static void main(String[] args) {
		
	//Scanner
		Scanner kb;
		kb = new Scanner(System.in);
	
	//Random
		Random r;
		r = new Random();
		
	//Random Number Chooser
		int secretNumber = r.nextInt(POOL)+1;
		
	//Game Begin
		System.out.println("I will pick an integer between 1 and 100. Try to guess which number I choose. Enter number:");
		int guess = kb.nextInt();
		
	//While Loop
		boolean correctGuess = false;
		while(correctGuess == false) {
			if(guess < secretNumber) {
				System.out.println("Your choice was smaller than the right number. Pick a larger value!");
			}else if(guess < secretNumber) {
				System.out.println("Your choice was bigger than the right number. Pick a smaller value!");
	//Ending Loop		
			}else {
				System.out.println("You guessed the right number!");
				correctGuess = true;
			}//EOC if loop
			
		}//EOC while loop
		
	//Continuing Loop
		guess = kb.nextInt();
		
		kb.close();

	}//EOC main

}//EOC class

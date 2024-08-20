package Homework01;

//THOMAS PETERSON
/* PLANNED STEPS
 * Understand what the homework is asking me to do
 * Create a method that can take a txt file and read what's inside it
 * Then convert those names and values to a linked list
 * Create a back-end that calculates all of the values needed
 * Recieve the user's input
 * Compare atual input and user's input to see if there's a range of 1300
 * Tell the user if they won or not
 * Ask the user to quit or continue.
 */

/*NOTES
 * Price File Format:
    <<Prize’s Name 0>>\t<<Prize’s Price 0>>\n
	<<Prize’s Name 1>>\t<<Prize’s Price 1>>\n
 * You can use the example file, you're own file, OR prompt the user to enter one
 * Program picks 5 random works from the prize structure
 * No repeat files
 * User's guess must be within $1300 to win
 * Game must continue until user decides it should stop
 * Use linkedlists to create a list of objects that have mutliple values.
 * 
 */

import java.util.Scanner;

public class Homework01 {

	public static void main(String[] args) {
		
		//SCANNER AND VARIABLES
		Scanner kb = new Scanner(System.in);
		int guess = 0;
		int answer = 0;
		boolean isDone = false;
		
		//GAME LOOP
		while(isDone != true) {
			System.out.println("Hello and welcome to the Prize Amount Guessing Game!");
			System.out.println("I will present 5 items to you and you must guess within $1300 of their total value to win.");
			System.out.println("Ready? Lets go!");
			Homework01PrizeOrganizer game = new Homework01PrizeOrganizer();
			game.readPrizesFromFile("Prize List.txt");
			game.print();
			game.pickRandomPrizes();
			
			//USER INPUT
			System.out.println("Based off these 5 prizes, what do you think their total is? (Enter whole number)");
			guess = kb.nextInt();
			kb.nextLine();//CLEARING SCANNER
			
			//CHECK WIN
			if(game.checkWin(guess)) {
				System.out.println("Your guess is lower than the total and within $1300. You win!!!");
			}else {
				System.out.println("Sorry your guess wasn't correct. You lose!");
			}//EOC IF-ELSE
			
			//GAME REPROMPT
			System.out.println("Would you like to play again? (Enter 1 for yes. Enter 2 for no)");
			answer = kb.nextInt();
			kb.nextLine();//CLEARING SCANNER
			if(answer == 1) {
				isDone = false;
			}else {
				isDone = true;
			}//EOC IF-ELSE
		}//EOC WHILE
		
		System.out.println("Thanks for playing!");
		kb.close();
		
	}//EOC MAIN METHOD
	
}//EOC Homework01

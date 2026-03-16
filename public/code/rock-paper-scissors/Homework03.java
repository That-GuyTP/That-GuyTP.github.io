package edu.usc.csce145.chapter07;

//Thomas Peterson

import java.util.Scanner;
import java.util.Random;

public class Homework03 {

	public static final int THREE = 3;
	
	public static void main(String args[]) {
		
		//Scanner
		Scanner kb;
		kb = new Scanner(System.in);
		
		//Random
		Random r;
		r = new Random();
		
		//Variables
		String name;
		name = "";
		//I'm adding a name string just for fun. It'll allow a little more personality into the program.
		
		String playerChoice;
		playerChoice = "";
		
		String computerChoice;
		computerChoice = "";
		
		int playerScore = 0;
		int computerScore = 0;
		int playerRoll = 0;
		
		//Intro and Name Collection
		System.out.println("Hello and Welcome to Rock, Paper, Scissors! Please enter your name:");
		name = kb.next();
		System.out.println("Hello " + name + "! Let me introduce you to the rules. Like most rock paper scissors games, you have to choice the option of either rock, paper, or scissors. If you enter an inncorect input,");
		System.out.println("the computer will automatically gain a point. The game is best 2 out of 3 rounds. Alright lets begin.");
		
		//Game Start
		//I chose a do-while loop since I believed it was going to be easier overall to use. Run the game until the condition of "1 person wins" is met. It is set up so that the game keep going until either person earns 2 
		//points. This is so that there can always be a winner instead of the potiental for a draw.
		do {
			//Player Choice
			System.out.println("Plesae enter \"Rock\", \"Paper\", or \"Scissors\":");
			playerChoice = kb.next();
			
			//Computer Choice
			int computerRoll = r.nextInt(THREE + 1);
			
		//Comp and Player Roll Assigners
			//Player Roll Assigner
			if(playerChoice.equalsIgnoreCase("Rock")) {
				playerRoll = 1;
			}else if(playerChoice.equalsIgnoreCase("Paper")) {
				playerRoll = 2;
			}else if(playerChoice.equalsIgnoreCase("Scissor")) {
				playerRoll = 3;
			}else {
				playerRoll = 4;
			}//EOC if-else block
			
			//Computer Roll Assigner
			if(computerRoll == 1) {
				computerChoice = "Rock";
			}else if(computerRoll == 2) {
				computerChoice = "Paper";
			}else if(computerRoll == 3) {
				computerChoice = "Scissor";
			}//EOC if-else block
			
		//Roll Comparison & Point Awarding
			//Draw
			if(computerRoll == playerRoll) {
				System.out.println("The computer chose " + computerChoice + ". You chose " + playerChoice + ". Looks like it's a draw. No one gets any points.");
				
			//Invalid Input
			}else if(playerRoll == 4) {
				System.out.println("Sorry, that wasn't a correct input. The computer is automatically awarded a point.");
				++computerScore;
				
			//Player Wins
			}else if(computerRoll == 3 && playerRoll == 1) {
				System.out.println("The computer chose " + computerChoice + ". You chose " + playerChoice + ". Looks like you won! Here's a point.");
				++playerScore;
			}else if(computerRoll == 1 && playerRoll == 2) {
				System.out.println("The computer chose " + computerChoice + ". You chose " + playerChoice + ". Looks like you won! Here's a point.");
				++playerScore;
			}else if(computerRoll == 2 && playerRoll == 3) {
				System.out.println("The computer chose " + computerChoice + ". You chose " + playerChoice + ". Looks like you won! Here's a point.");
				++playerScore;
				
			//Computer Wins
			}else if(computerRoll == 1 && playerRoll == 3) {
				System.out.println("The computer chose " + computerChoice + ". You chose " + playerChoice + ". Looks like you lose. The computer has earned a point.");
				++computerScore;
			}else if(computerRoll == 2 && playerRoll == 1) {
				System.out.println("The computer chose " + computerChoice + ". You chose " + playerChoice + ". Looks like you lose. The computer has earned a point.");
				++computerScore;
			}else if(computerRoll == 3 && playerRoll == 2) {
				System.out.println("The computer chose " + computerChoice + ". You chose " + playerChoice + ". Looks like you lose. The computer has earned a point.");
				++computerScore;
			}//EOC Choice Comparison && Point Awarding
			
			//Point Keeper
			System.out.println("The score is " + computerScore + " points for the computer. And " + playerScore + " points for you.");
			
			//Choice Reset
			//I'm adding these two reset lines to ensure the program isn't accepting old inputs for either the player or the computer.
			playerChoice = "";
			computerRoll = 0;
			
			//Game Winning Condition Output
			if(playerScore == 2) {
				System.out.println("Congrats, " + name + " you won!");
				System.out.println("Game closing.");
				System.exit(0);
			}//EOC Player Win Condition
			if(computerScore == 2) {
				System.out.println("Sorry, " + name + " you lost.");
				System.out.println("Game closing.");
				System.exit(0);
			}//EOC Computer Win Condition
			
		}while(playerScore != 2 || computerScore != 2); 
	
		
		
		kb.close();
		
	}//EOC main
	
}

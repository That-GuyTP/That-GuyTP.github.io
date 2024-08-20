package edu.mtc.egr281.chapter07;

//Thomas Peterson
//Project08
//Current Date: 10-19-2022
//Due Date: 10-20-2022
//Create a program using strings to store the data of player's scores and display it out to the user.

import java.util.Scanner;
import java.util.Arrays;

public class Project08 {
	
	public static void main(String[] args) {
		
		//Algorithm Design
			//Figure out what the heck I need to do
			//Prompt for player name
			//Prompt for score
			//Design an output
			//Output histogram
		
		//SCANNER & INT'S
			Scanner kb = new Scanner(System.in);
			int b = 1;
			int d = 0;
			
		//PROMPT & ARRAYS/STRINGS
			System.out.print("Please enter amount of players: ");
			int amountOfPlayers = kb.nextInt();
		
			String[] playerName = new String[amountOfPlayers];
		
			int[] playerScore;
			playerScore = new int[amountOfPlayers];
			
		//PROCESS
			//PLAYER NAMES
			for(int i = 0; i < amountOfPlayers; ++i) {
				System.out.print("Please enter player #" + b + "'s name: ");
				playerName[i] = kb.next();
				++b;
			}//EOC playerName for
		
			//PLAYER SCORES
			for(int j = 0; j < playerScore.length; ++j) {
				System.out.print("Please enter score for " + playerName[d] + ": ");
				playerScore[j] = kb.nextInt();
				++d;
			}//EOC playerScore for	
			
		//OUTPUT
			System.out.println("");
			System.out.println("Player scores during the event:");
			for(int o = 0; o < amountOfPlayers; ++o) {
				System.out.print(playerName[o] + ": ");
			//ASTERISCS
				for(int ast = 0; ast < playerScore[o]; ++ast) {	
					System.out.print("*");	
				}//EOC INNER
				System.out.println("");
			}//EOC OUTER print
		kb.close();
	}//EOC main
	
}//EOC Project08

//ALLIGNMENT try 1
//int size;
//size = playerName.length / 2

//StringBuffer str = new StringBuffer("");
//str.setLength(size);



//ALLIGNMENT try 2
//for(int spaces = 10; spaces > playerName.length; -- spaces) {
	//System.out.print("");
//}
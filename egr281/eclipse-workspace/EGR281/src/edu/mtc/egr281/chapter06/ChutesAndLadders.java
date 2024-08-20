package edu.mtc.egr281.chapter06;

import java.util.Scanner;

public class ChutesAndLadders {

	private static Scanner kb;
	private static Spinner spinner;
	private static Player[] players;
	
	public static void main(String[] args) {
		
		//INSTA
		kb = new Scanner(System.in);
		spinner = new Spinner();
		
		//Prompt for the number of players
		System.out.print("Please enter number of players: ");
		players = new Player[kb.nextInt()];
		for(int i = 0; i < players.length; ++i) {
			System.out.print("Enter name of player $" + (i+1) + ": ");
			players[i] = new Player(kb.next());
		}//EOC for
		
		//Play the name
		int index = -1;
		Player player;
		
		do {
			++index;
			player = players[index % players.length];
			
			spinner.spin();
			System.out.println(player.getName() + " spins a " + spinner.getValue());
			System.out.println(player.updateCurrentSquareNumber(spinner.getValue()));
			
		} while(player.getCurrentSquareNumber() !=100);
		
		System.out.print(player.getName() + " is the winner!!");
		
		kb.close();
		
	}//EOC main
	
}//EOC ChutesAndLadders

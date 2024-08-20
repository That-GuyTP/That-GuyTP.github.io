package edu.mtc.egr281.chapter06;

import java.util.Scanner;

public class Project11 {

//Fields
	private static Scanner kb;
	private static Project11Snails color;
	private static Die cd1, cd2;
	
	public static void main(String[] args) {
		
	//INSTANTS
		kb = new Scanner(System.in);
		cd1 = new Die();
		cd2 = new Die();
		String playerName = new String();
		
	//WELCOME AND INSTRUCTIONS
		System.out.print("Welcome to the Snail Racing Board Game.");
		System.out.println("In this game you must guess between 1 of six colored snails to see if they cross the line first.");
		System.out.println("You options are, Orange, Green, Pink, Red, Blue, & Yellow");
		
		//PROMPT FOR CHOICE OF COLOR
		System.out.print("What is your name?");
		playerName = kb.nextLine();
		System.out.print("Please enter the color you believe is gonna win");
		color = new Project11Snails(kb.nextLine());
		
		//GAME
		int index = -1;
		Project11Snails player;
		
		do {
			++index;
			player.equalsIgnoreCase(color);
			cd1.roll();
			System.out.println("");
			System.out.println(Project11Snails.getColor() + " was rolled");
			cd2.roll();
			System.out.println(getColor() + "was rolled");
			cd1.roll();
			
		} while(color.getCurrentSpace() != 9);
		
		if(color.equals(color)) {
			System.out.print("Congrats " + playerName + ". You guessed right!");
		}else
			System.out.print("Sorry " + playerName + ". You didn't guess right, better luck next time.");
		
		kb.close();
		
	}//EOC method main
	
}//EOC Project11

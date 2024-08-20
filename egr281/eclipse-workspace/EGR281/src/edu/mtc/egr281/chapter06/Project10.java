package edu.mtc.egr281.chapter06;

//Thomas Peterson
//Project10
//Current Date: 11-4-2022
//Due Date: 11-3-2022
//Create a game based off the instructions that will allow kids to play it.

//Algorithm Design
//This is the file that the kids will be handed and use to play
//Understand what the assignment is asking of me
//Create a prompt that asks for amount of players
//Create a string that adds player name
//Use commands to play game
//Declare winner

import java.util.Scanner;

public class Project10 {

	private static Scanner kb;
	private static Project10Driver[] players;
	private static Die die;
	
	public static void main(String[] args) {
		
	//INSTANTS
		kb = new Scanner(System.in);
		die = new Die();
		
	//WELCOME AND INSTRUCTIONS
		System.out.print("Welcome to THE ULTIMATE ATV BUILDER game! ");
		System.out.println("In this game you and your friends will compete to see who can build their ATV the fastest. ");
		System.out.println("The rules are as follows: You all are handed a 6 sided die and must roll once, in order. Each number represents a part. ");
		System.out.println("A 1 is a body, 2 an engine, 3 is handlebars, 4 is headlights, 5 a seat, and 6 are your tires. ");
		System.out.println("First, you must roll a 1 for your body. Secondly you must roll a 2 for your engine. (If you do not roll these in order, you will keep rolling until you do). ");
		System.out.println("Then, you are allowed to get any other part you need. You need 1 of each part & 6 tires to win. The first to collect them all, wins! ");
		
		//PROMPT FOR AMOUNT OF PLAYERS
		System.out.print("Please enter the number of players: ");
		players = new Project10Driver[kb.nextInt()];
		for(int i = 0; i < players.length; ++i) {
			System.out.print("Enter name of player #" + (i+1) + ": ");
			players[i] = new Project10Driver(kb.next());
		}//EOC for names
		
	//GAME
		int index = -1;
		Project10Driver player;
		
		do {
			++index;
			player = players[index % players.length];
			die.roll();
			System.out.println("");
			System.out.println(player.getName() + " rolls a " + die.getValue());
			System.out.println(player.addATVPart(die.getValue()));
		} while(player.getDiceRoll() != 7);
		System.out.print(player.getName() + " built their ATV first. They are the winner!");
		
		kb.close();
		
	}//EOC main
	
}//EOC Project10

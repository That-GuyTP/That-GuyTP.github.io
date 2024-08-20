package HotPotato;

import java.util.Scanner;

public class HotPotatoGameFE {

	public static Scanner kb = new Scanner(System.in);
	public static HotPotatoGame hpg = new HotPotatoGame();
	public static final int MIN_TIME = 1;
	public static final int MAX_TIME = 10;
	public static void main(String[] args) {
		greeting();
		boolean playGame = true;
		while(playGame) {
			hpg.resetTime();
			int numPlayers = getNumberOfPlayers();
			addPlayers(numPlayers);
			
			boolean gameOver = false;
			while(!gameOver) {
				String player = hpg.getCurrentPlayer();
				System.out.println(player + ", enter a time to hold the potato from " + MIN_TIME + " to " + MAX_TIME);
				int time = kb.nextInt();
				kb.nextLine();
				if(time < MIN_TIME || time > MAX_TIME) {
					System.out.println("Incorrect value. ASsuming time is " + MAX_TIME);
					time = MAX_TIME;
				}//EOC IF 1
				if(hpg.hasLost(time)) {
					System.out.println("Player " + player + " has been eliminated!");
					hpg.resetTime();
				}else {
					hpg.addPlayer(player);
				}//EOC IF-ELSE
				gameOver = hpg.getWIn();
			}//EOC WHILE
			System.out.println("THe winner is " + hpg.getCurrentPlayer() + "!");
			System.out.println("Enter  \"yes\" to play again");
			String input = kb.nextLine();
			playGame = input != null && input.equalsIgnoreCase("yes");
			
		}//EOC WHILE
		System.out.println("Goodbye");
		kb.close();
	}
	
	public static void greeting() {
		System.out.println("Welcome to the Hot Potato Game!");
	}
	
	public static int getNumberOfPlayers() {
		int num = 0;
		while(num <= 1) {
			System.out.println("Enter the Number of Players. Must be greater than 1");
			num = kb.nextInt();
			kb.nextLine();
		}//EOC WHILE
		return num;
	}
	
	public static void addPlayers(int num) {
		int i = 0;
		while( i < num) {
			System.out.println("Enter your name player " + i);
			String input = kb.nextLine();
			if(input == null || input.isEmpty()) {
				System.out.println("Invalid Input");
				continue;
			}else {
				hpg.addPlayer(input);
			}//EOC IF-ELSE
			i++;
		}//EOC WHILE
	}
	
	
}

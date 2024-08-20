package edu.mtc.egr281.chapter05;

import java.util.Scanner;

public class DieGameArray {

	//Constants
	private static Die d;
	private static DiePlayer[] dp;
	//private static Scanner kb;
	
	public static void main(String[] args) {
		
		Scanner kb = new Scanner(System.in);
		
		setUpTheGame(kb);
		playTheGame();
		
		kb.close();
		
	}//EOC main
	
	private static void setUpTheGame(Scanner keys) {
		DieGameArray.d = new Die();
		
		System.out.print("Enter the number of player: ");
		DieGameArray.dp = new DiePlayer[keys.nextInt()];
		for(int i = 0; i < DieGameArray.dp.length; ++i) {
			System.out.print("Enter name of player #" + (i+1) + ": ");
			DieGameArray.dp[i] = new DiePlayer(keys.next());
		}
		
	}//EOC method setUpTheGame
	
	private static void playTheGame() {
		
		int index = -1;
		DiePlayer player;
		
		do {
			++index;
			player = DieGameArray.dp[index % DieGameArray.dp.length];
			d.roll();
			System.out.print(player.getName() + " rolls " + d.getValue());
			player.updateScore(d.getValue());
			System.out.println(", score is now " + player.getScore());
			
		} while(player.getScore() < 50);
		
		System.out.println("Winner is " + player.getName() + "!!");
		
	}//EOC method playTheGame
	
}//EOc DieGameArray

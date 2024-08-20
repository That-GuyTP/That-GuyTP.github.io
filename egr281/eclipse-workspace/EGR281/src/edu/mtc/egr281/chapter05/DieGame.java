package edu.mtc.egr281.chapter05;

import java.util.Scanner;

public class DieGame {

	public static void main(String[] args) {
		
		Scanner kb = new Scanner(System.in);
	
		System.out.print("Enter name of player #1 ");
		DiePlayer dp1 = new DiePlayer(kb.next());
		System.out.print("Enter name of player #2: ");
		DiePlayer dp2 = new DiePlayer(kb.next());
	
		Die d = new Die();
		
		//Play the game
		do {
			d.roll();
			System.out.print(dp1.getName() + " rolls " + d.getValue());
			dp1.updateScore(d.getValue());
			System.out.println(", score is now " + dp1.getScore());
			
			d.roll();
			System.out.print(dp2.getName() + " rolls " + d.getValue());
			dp2.updateScore(d.getValue());
			System.out.println(", score is now " + dp2.getScore());
		} while(dp1.getScore() < 50 && dp2.getScore() <50);
		
		System.out.print("Winner is");
		if(dp1.getScore() > dp2.getScore()) {
			System.out.println(dp1.getName());
		} else if(dp2.getScore() > dp1.getScore()) {
				System.out.println(dp2.getName());
		} else {
				System.out.println("both " + dp1.getName() + " and " + dp2.getName());
		}//EOC if
		
		kb.close();
	}//EOC main
	
}//EOC DieGame

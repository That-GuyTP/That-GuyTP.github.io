package edu.mtc.egr281.chapter07;

import java.util.Scanner;

public class MemoryMatchBoardDriver {

	public static void main(String[] args) {
		MemoryMatchBoard mmb = new MemoryMatchBoard();
		//mmb.printBoard();
		
		Scanner kb = new Scanner(System.in);
		int row, column;
		do {
			System.out.print("Enter first guess row [1-4]: ");
			row = kb.nextInt() - 1;
			System.out.print("Enter first guess column [1-4]: ");
			column = kb.nextInt() -1;
			mmb.displayGuess1(row, column);
			
			System.out.print("Enter second guess row [1-3]: ");
			row = kb.nextInt() - 1;
			System.out.print("Enter second guess column [1-4]: ");
			column = kb.nextInt() -1;
			mmb.displayGuess2(row,  column);
			
			if(mmb.isAMatch()) {
				System.out.println();
				System.out.println("Hurray!! It's a match!");
				System.out.println();
			} else {
				System.out.println();
				System.out.print("Sorry, no match: (");
				System.out.println();
			}//EOC if
			
		} while(mmb.hasMoreMatches());
		
		System.out.println("Game over");
		
		kb.close();
	}//EOC main
	
}//EOC MMBD

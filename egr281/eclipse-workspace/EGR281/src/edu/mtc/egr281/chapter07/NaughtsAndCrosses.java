package edu.mtc.egr281.chapter07;

import java.util.Scanner;

public class NaughtsAndCrosses {

	//Decs
	private static Scanner kb;
	private static int row, column, turn;
	private static char[][] board;
	private static char[] token;
	
	public static void main(String[] args) {
		
	//Open input stream
	kb = new Scanner(System.in);
	
	setUpTheGame();
	playTheGame();
	
	
	kb.close();	
	}//EOC main
	
	private static void setUpTheGame() {
		turn = 0;
		token = new char[] {'0', 'X'};
		board = new char[3][3];
		for(int row = 0; row < board.length; ++row) {
			for(int column = 0; column < board[row].length; ++column) {
				board[row][column] = '_';
			}//INNER
		}//OUTER
		
	}//EOC setUpTheGame
	
	private static void playTheGame() {
		char t;
		do {
			++turn;
			System.out.println();
			System.out.println("Turn #" + turn + ":");
			printTheBoard();
			t = token[turn % token.length];
			System.out.print(t + ", please enter a row: ");
			row = kb.nextInt() - 1;
			System.out.print(t + ", please enter a column: ");
			column = kb.nextInt() - 1;
			board[row][column] = t;
		} while(!isGameOver(row, column));
		
		System.out.println();
		System.out.print("In turn #" + turn + ", ");
		System.out.println(t + " wins!!");
		printTheBoard();
		
	}//EOC playTheGame
	
	private static boolean isGameOver(int rMove, int cMove) {
		boolean returnValue = false;
		
		if(board[rMove][0] == board[rMove][1] && board[rMove][1] == board[rMove][2]) {
			returnValue = true;
		} else if(board[0][cMove] == board[1][cMove] && board[1][cMove] == board[2][cMove]) {
			returnValue = true;
		} else if(board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[1][1] != '_') {
			returnValue = true;
		} else if(board[2][0] == board[1][1] && board[1][1] == board[0][2] && board[1][1] !='_' ) {
			returnValue = true;
		}
		
		return returnValue;
	}
	
	private static void printTheBoard() {
		System.out.println();
		System.out.println("   | 1 | 2 | 3 |");
		System.out.println("   -------------");
		for(int row = 0; row < board.length; ++row) {
			for(int column = 0; column < board[row].length; ++column) {
				if(column == 0) {
					System.out.print((row + 1) + ": | ");
				}//EOC if
				System.out.print(board[row][column] + " | ");
			}//INNER
			System.out.println();
		}//OUTER
	}//EOC printTheBoard
	
}//EOC NAC

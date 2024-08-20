package edu.mtc.egr281.chapter07;

public class MemoryMatchBoard {

	//Declared and initialized 2D char array
	private char[][] board = { {'!', '@', '#', '$'},
							   {'$', '%', '&', '@'},
							   {'+', '!', 'X', '&'},
							   {'#', 'X', '%', '+'} };
	
	private boolean[][] matched = { {false, false, false, false},
								 {false, false, false, false},
								 {false, false, false, false},
								 {false, false, false, false} };
	
	private int numberOfMatches;
	private int guess1row, guess1column, guess2row, guess2column;
	
	public MemoryMatchBoard() {
		this.numberOfMatches = 0;
	}//EOC constructor
	
	public boolean hasMoreMatches() {
		return (this.numberOfMatches != 8);
	}//EOC method hasMoreMatches
	
	public boolean isAMatch() {
		boolean returnValue = false;
		
		if(board[guess1row][guess1column] == board[guess2row][guess2column]) {
			this.matched[guess1row][guess1column] = true;
			this.matched[guess2row][guess2column] = true;
			++this.numberOfMatches;
			returnValue = true;
		}//EOC if
		
		return returnValue;
	}//EOC method isAMatch
	
	private void displayGuess(int row, int column) {
		for(int r = 0; r < board.length; ++r) {
			for(int c = 0; c < board[r].length; ++c) {
				if(this.matched[r][c]) {
					System.out.print(this.board[r][c]);
				} else if(this.guess1row == r && this.guess1column == c) {
					System.out.print(this.board[r][c]);
				} else if(row == r && column == c) {
					System.out.print(this.board[r][c]);
				} else {
					System.out.print("*");
				}//EOC if
				System.out.print(" ");
			}//EOC INNER
			System.out.println();
		}//EOC OUTER
	}//EOC DisplayGuess
	
	public void displayGuess1(int row, int column) {
		this.guess1row = row;
		this.guess1column = column;
		this.displayGuess(row,  column);
	}//EOC method displayGuess1
	
	public void displayGuess2(int row, int column) {
		this.guess2row = row;
		this.guess2column = column;
		this.displayGuess(row,  column);
	}//EOC displayGuess2
	
	public void printBoard() {
		for(int row = 0; row < board.length; ++row) {
			
			for(int column = 0; column < board[row].length; ++column) {
				System.out.print(board[row][column] + " ");
			}//INNER
		}//OUTER
	}//EOC method printBoard
	
}//EOC MMB

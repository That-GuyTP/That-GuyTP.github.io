package Homework04;

//THOMAS PETERSON
//This is the front end of the Obstacle Maze class. It also contains all my thoughts for how to complete this homeowork.

import java.util.Scanner;

public class ObstacleMazeFE {

	//PLANNING NOTES
	/* *Hard Requirements:
	 - User can choose when to terminate the program
	 - User can provide the filename for board and robot command
	 - Board file will create a board with obstcales, robot command file will give the list of moves to follow
	 - Board must be printed out after every move
	 - Player starts at top left, goal is bottom right.
	 - Player must be able to choose what direction they want to move (north, south, east, west). An invalid input for movement should be ignored and moved past.
	 - If the player runs into an obstcale or a boundary "CRASH" must be outputed and the program stops. The player is then reprompted to quit or restart
	 - If the player has inputed a file that runs out of commands before reaching the goal the program stops and the palyer is prompted to quit or restart.
	 
	   *Predetermined Characters:
	 - Empty Spaces - "_"
	 - Obstacles - "X"
	 - Available Moves - "Move Up", "Move Down", "Move Left," and "Move Right".
	 - Board File Format -
	   <<character00>><<character01>>...<<character09>>\n
	   <<character10>><<character11>>...<<character19>>\n
	 - Robot Command File Format -
	   <<Command00>>\n
	   <<Command01>>\n
	   ...
	   <<Command n>>
	 */
	
	//CONSTANTS
	private static Scanner kb = new Scanner(System.in);
	private static ObstacleMaze OM = new ObstacleMaze();
	
	public static void main(String[] args) {
		greeting();
		boolean quit = false;
		while(quit != true) {
			readBoardFile();
			readRobotFile();
			printBoard();
			playTheGame();
			System.out.println("");//For organization
			System.out.println("Would you like to restart? (y/n)");
			String choice = kb.nextLine();
			if(choice.equalsIgnoreCase("n")){
				quit = true;
			}//EOC IF-ELSEIF-ELSE
		}//EOC WHILE
		
		kb.close();
	}//EOC MAIN METHOD

	public static void greeting() {
		System.out.print( "Welcome to the Obstacle Maze Game. In this game you will try and guide a robot through a maze of obstacles."
			 + "\nYou must create a maze for the robot in a text file as well as add commands for the robot in a text file"
			 + "\nThe only valid commands are: \"Move Up\", \"Move Right\", \"Move Down\". or \"Move Left\""	
			 + "\nIf the robot runs into a obstacle, the edge of the board, or doesn't reach the end of the board, you lose!"
			 + "\nIf the robot does make it to the goal, you win!"
			 + "\nThe robot will start at the top left most position and must reach the bottom right most position."
			 + "\nTo start, please follow the directions below:");
		System.out.println("");
		System.out.println("---------------------------------------------------------------------");
	}//EOC greeting
	
	public static void readBoardFile() {
		System.out.println("Enter the file name of the board (Do NOT include a file extension or root paths):");
		String fileName = kb.nextLine();
		OM.readBoardFile(fileName + ".txt");
	}//EOC readBoardFile
	
	public static void readRobotFile() {
		System.out.println("Enter the file name of the robot commands (Do NOT include a file extension or root paths):");
		String fileName = kb.nextLine();
		OM.readRobotCommandFile(fileName + ".txt");
	}//EOC readRobotFile
	
	public static void printBoard() {
		OM.printBoard();
	}//EOC printBoard
	
	public static void playTheGame() {
		OM.gameProcess();
	}
	
}//EOC ObstacleMazeFE

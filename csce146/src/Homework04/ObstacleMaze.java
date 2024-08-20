package Homework04;

//THOMAS PETERSON
//This class will run all the need processes for the driver file ObstacleMazeFE

import java.util.Scanner;
import java.io.*;

public class ObstacleMaze {

	//CONSTANTS & VARIABLE ORGANIZATION
	private static final int BODY_FIELD_AMT = 10;
	private char[][] maze;
	private Robot robot = new Robot();
	private LinkedListQueue<String> path = new LinkedListQueue<>();	
	
	
	//READING BOARD FILE
	//EXPLAINATION
	//This is the file that will be used to set up the board. It first has a parameter to recieve the board's name from the FE class.
	//It then will create a new board of the size 10x10 since the two examples show that size. We create a try-catch and a file scanner and
	//beging to check the file filing in each column's spot in the board before moving to the next row. It continues untill we run out of column then row space.
	public void readBoardFile(String xBFile) {
		maze = new char[BODY_FIELD_AMT][BODY_FIELD_AMT];
		int row = 0;
		int col = 0;
		
		try {
			Scanner fileScanner = new Scanner(new File(xBFile));
			while(fileScanner.hasNextLine()) {
				if(row < BODY_FIELD_AMT) {
					String line = fileScanner.nextLine().trim();
					for(col = 0; col < BODY_FIELD_AMT; col++) {
						if (col < line.length()) {
							maze[row][col] = line.charAt(col);
						}else {
							maze[row][col] = '_';
						}//EOC IF-ELSE
					}//EOF FOR
					row++;
				}//EOC IF
			}//EOC WHILE
			fileScanner.close();//DON'T FORGET
		}catch(Exception e) {
			System.out.println("Sorry, there was an error locating the board file");
			e.printStackTrace();
		}//EOC TRY-CATCH
	}//EOC readBoardFile
	
	
	//READING ROBOT COMMAND FILE
	//This method has a similar start as the readBoardFile method. The difference is I'm checking each line for a valid command (while clearing out the rest of the line)
	//and if it's valid, adding that to the queue that the robot will follow in the ObstacleMazeFE class.
	public void readRobotCommandFile(String xRCFile) {		
		try {
			Scanner fileScanner = new Scanner(new File(xRCFile));
			while(fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine().trim();
				if(line.equalsIgnoreCase("Move Up") || line.equalsIgnoreCase("Move Right") || line.equalsIgnoreCase("Move Down") || line.equalsIgnoreCase("Move Left")) {
					path.enqueue(line);//Adds the command to the LLQueue "path".
				}//EOC IF
			}//EOC WHILE
			fileScanner.close();
		}catch(Exception e) {
			System.out.println("Sorry, there was an error locating the robot command file");
			e.printStackTrace();
		}//EOC TRY-CATCH
	}//EOC readRobotCommandFile
	
	
	//PRINTING THE MAZE
	//Printing the maze using a nested if loop and checking to see if the robot is equal to the space.
	public void printBoard() {
		for (int i = 0; i < BODY_FIELD_AMT; i++) {
			for(int j = 0; j < BODY_FIELD_AMT; j++) {
				if(i == robot.getY() && j == robot.getX()) {
					System.out.print("O");
				}else {
					System.out.print(maze[i][j] + "_");
				}//EOC IF-ELSEIF-ELSE
			}//EOC INNER FOR
		System.out.println();
		}//EOC FOR
	}//EOC printBoard
	
	
	//PLAYING THE GAME
	//I felt it better to include this method in this class because of my variable organization as well as looks sake.
	public void gameProcess() {
		boolean fail = false;
		while(path != null && fail != true) {
			String action = path.dequeue();
			switch(action) {
			case "Move Up":
				robot.moveUp();
				break;
			case "Move Right":
				robot.moveRight();
				break;
			case "Move Down":
				robot.moveDown();
				break;
			case "Move Left":
				robot.moveLeft();
				break;
			default:
				System.out.println("Invalid action");
				fail = true;
				break;
			}//EOC SWITCH
			if (wrongMove() == true) {
				fail = true;//Adding this for extra safety.
				break;
			}//EOC IF
		}// WHILE
		checkWin();
	}//EOC gameProcess
	
	
	//CHECK FOR CRASH/ERROR
	//This method is set up to see if the command has moved the robot out of bounds or into an error.
	public boolean wrongMove() {
		if(robot.getX() > 9 || robot.getY() > 9 || robot.getX() < 0 || robot.getY() < 0) {
			System.out.println("CRASH");
			return true;
		}else if(maze[robot.getY()][robot.getX()] == 'X') {
			System.out.println("CRASH");
			return true;
		}else {
			return false;
		}//EOC IF-ELSEIF-ELSE
	}//EOC wrongMove
	
	
	//CHECK IF ROBOT REACHED THE END
	public void checkWin() {
		if(robot.getX() == 9 && robot.getY() == 9) {
			System.out.println("You won!");
		}else {
			System.out.println("You lost!");
		}
	}//EOC checkWin
	
}//EOC ObstacleMaze

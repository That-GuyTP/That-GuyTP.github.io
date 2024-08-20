package edu.usc.csce145.chapter03;

import java.util.Random;

public class BranchingStatements_Sports {

	//Constants
	public static final int MAX = 5;
	
//Main method
	public static void main(String[] args) {
	
	//Die
	Random r;
	r = new Random();

//Input
	
	//Sports Pool List:
	/*
	 * Basketball
	 * Wrestling
	 * Soccer
	 * Tennis
	 * Football
	 */
	
	//Sport Chooser
	int choice = r.nextInt(MAX);
	//Any value from 0, 1, 2, 3, 4
	
	String sports ="";
	
	
	switch(choice) {
	case 0:
		sports = "Basketball";
		break;
	case 1:
		sports = "Wrestiling";
		break;
	case 2:
		sports = "Soccer";
		break;
	case 3:
		sports = "Tennis";
		break;
	case 4:
		sports = "Football";
		break;
	default:
		System.out.println("An error has occcured. Please retry the program.");
		System.exit(0);
	}//EOC switch
	//"break;" are needed to end cases. "default:" is used to run a case in the situation none of the cases are met.
	
//Output
	System.out.println("The computer picket " + sports + "!");
	
	System.exit(0);
	
	
	}//EOC main

}//EOC class

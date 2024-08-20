package edu.mtc.egr281.chapter03;

import java.util.Scanner;

public class SwitchIntDemo {

	public static void main(String[] args) {
	
	//STORE DATA
	Scanner inputStream = new Scanner(System.in);
	int numberOfBabies = 0;
	String outputString = "";
	
	//INPUT
	System.out.print("Enter number of babies: ");
	numberOfBabies = inputStream.nextInt();
	
	//PROCESS
	switch(numberOfBabies) {
	case 1:
		outputString = "Congrats";
		break;
	case 2:
		outputString = "Wow. Twins!";
		break;
	case 3:
		outputString = "Triplets!";
		break;
	case 4:
	case 5:
		outputString = "You're lying!";
		break;
	default:
		System.out.println("Impossible! Are you serious?");
		switch(inputStream.next().toUpperCase()) {
		case "Y":
		case "YES":
				outputString = "Incredible";
		default:
			outputString = "You got me!";
		}//EOC nested switch
	}//EOC switch
	
	//OUTPUT
	System.out.println(outputString);
	
	inputStream.close();
	}//EOC main
	
}//EOC SwitchIntDemo

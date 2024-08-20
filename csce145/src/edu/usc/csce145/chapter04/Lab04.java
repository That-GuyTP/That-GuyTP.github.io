package edu.usc.csce145.chapter04;

//Thomas Peterson

import java.util.Scanner;

public class Lab04 {

	public static void main(String args[]) {
		
	//Scanner
	Scanner kb;
	kb = new Scanner(System.in);
		
	//Prompt & Instancing Variables
	System.out.println("Welcome to the Book-Reward Points Counter! Please input the number of books you have purchased this month:");
	
	//Book Int
	int books;
	books = kb.nextInt();
	
	//>4 If Statement Catch
	if(books >= 4) {
		books = 4;
	}//EOC If
	//I'm including this if statement here to make outputing an expression for any amount of books over 4 easier. Since the value doesn't change regardless if the inputed amount is = or > 4, I can assign all values
	//>= 4 to 4.
		
	//String bookRewards
	String bookRewards;
	bookRewards = "";
	//The reason I set up a String variable here was to simplify the point calculation process. Since a Switch is required for this lab I figured it would be easier to have the system print a statement that is tied to 
	//an inputed value rather than trying to use some form of equation calculation.
	
	//Switch
	switch(books) {
	case 0:
		System.out.println("Looks like you haven't bought any books yet. Start buying to earn points!");
		System.exit(0);
		//I have a "System.exit(0);" command in this case so that the rest of the program doesn't run as a result of someone inputing 0 for their books.
		break;
	case 1:
		bookRewards = "5";
		break;
	case 2:
		bookRewards = "15";
		break;
	case 3:
		bookRewards = "30";
		break;
	case 4:
		bookRewards = "60";
		break;
	default:
		System.out.println("An error has occcured with the value you have attempted to input. Please retry the program.");
		System.exit(0);
	}//EOC switch
	
	//Output
	System.out.println("Congralations you have earned a total of " + bookRewards + " points from buying books. You can spend these points with you next book purchase.");
	System.out.println("Have a good day.");
	
	
	kb.close();
	
	}//EOC main
	
}//EOC class

package edu.usc.csce145.chapter02;
import java.util.Scanner;
//Importing the java utility scanner for further into the program.

//Thomas Peterson

public class Homework01 {
	
	//Constants
	public static final int currentYear = 2023;

	public static void main (String args[]) {
		
	//Scanner
		Scanner kb;
		kb = new Scanner(System.in);
		//declaring my scanner into the program. It looks a little
		//different than the way you may have taught in class but it was
		//the original way I was taught to do it.
		
	//Output & Input 1
		System.out.println("Hello and welcome to the IDCP or Individual Data Collection Program. To start, what is your first name?");
		String firstName;
		//I chose the String integer type since I'm expecting a name for the user's next input.
		firstName = kb.nextLine();
		
	//Output & Input 2
		System.out.println("Last name?");
		String lastName;
		lastName = kb.nextLine();
		
	//Output & Input 3
		System.out.println("Thank you. Now how old are you?");
		int age;
		age = kb.nextInt();
		int yearOfBirth;
		yearOfBirth = currentYear - age;
		//This is a calculaton of the person's birthyear. While it won't account for all birthdays, it will for most
		
	//Output & Input 4
		System.out.println("What is the best telephone number to reach you at? (Please do not include -, /, or (). Thank you!)");
		String phoneNumber;
		//I decided to make this a string rather than an in because I am going to be editing the number itself rather than computing anything
		//with it.
		kb.nextLine();
		phoneNumber = kb.nextLine();
		String areaCode = phoneNumber.substring(0,3);
		String part1 = phoneNumber.substring(3,6);
		String part2 = phoneNumber.substring(6,10);
		
	//Output & Input 5
		System.out.println("What are you majoring in?");
		String major;
		major = kb.nextLine();
		
	//Output & Input 6
		System.out.println("What year of college are you in? (Freshman, Sophmore, etc.)");
		String yearOfCollege;
		yearOfCollege = kb.nextLine();
		
	//Output & Input 7
		System.out.println("What is today's date? (Please format: MM/DD/YYYY");
		String date = kb.nextLine();
		//Here I am recording the sentence, and then tying the total sentence length
		//into readable positions for the later European conversion.
		String day = date.substring(3,5);
		String month = date.substring(0,2);
		String year = date.substring(6,10);
		
	//Output and Input 8 
		System.out.println("What is you favorite artist?");
		String artist; 
		artist = kb.nextLine();
		
		System.out.println("What is your favorite color?");
		String color;
		color = kb.nextLine();
		
		System.out.println("What is your favorite holiday?");
		String holiday;
		holiday = kb.nextLine();
		
	//Output 9
		System.out.println("Thank you for all of your inputed information. I am going to attempt to create a cool spy movie intro for you.");
		System.out.println("So your name is " + lastName + "? " + firstName + " " + lastName+ "? ");
		System.out.println("From what your Secret Service file shows, you were born in " + yearOfBirth + ".");
		System.out.println("Your favorite things are: " + artist + ", " + color + ", " + holiday + ".");
		System.out.println("I personally prefer Hardcore Disco Metal Jazz music, but anyway... I saw through your disguse!");
		System.out.println("You're going to have to try harder than a " + yearOfCollege + " in college, majoring in " + major + ".");
		System.out.println("My thugs managed to recover your phone. Your phone number is (" + areaCode + ")" + part1 + "-" + part2);
		System.out.println("Don't bother dying it! I'm going to use that to track the signal back to your base!");
		System.out.println("Make today, " + day + "." + month + "." + year + ", as the day I, Mr. Evil Villan Guy, finally captured the famous spy " + firstName + "!" );
		
		
		kb.close();
		//used to stop the scanner program from running after the program is complete.
		
	}//EOC main
	
}//EOC class

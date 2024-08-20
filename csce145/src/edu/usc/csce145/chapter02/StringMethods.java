package edu.usc.csce145.chapter02;

import java.util.Scanner;

public class StringMethods {

	static void main (String args[]) {
		
		//Scanner
		Scanner kb;
		kb = new Scanner(System.in);
		
		//Output and Input
		System.out.println("How many days are in a week?");
		int numberOfDays = kb.nextInt();
		System.out.println(numberOfDays);
		
		kb.nextLine();
		//Used to allow the use of .nextLine for a string data type after a previous Scanner used. 
		//It will read the remainder of the current line and clear itself for the String following.
		System.out.println("Enter a 2 word: ");
		String words = kb.nextLine();
		System.out.println("The word enters is: " + words);
		
		System.out.println("Are you having fun learning about Java? Enter \"true\" or \"false\": ");
		boolean response = kb.nextBoolean();
		//Boolean only reads "true" or "false" as an input.
		System.out.println("The user said " + response);
		kb.nextLine();
		//Used to clear the line to allow the following String to properly record a response.
		
		System.out.println("Enter a sentence:");
		String sentence = kb.nextLine();
		int length = sentence.length();
		//.length is used to record the amount of spaces used in the connected string or int.
		System.out.println("Total number of characters: " + length);
		
		int position = sentence.indexOf('d');
		//.indexOf() is used to find either a specific character or value in the connected data type. You can use ' to 
		//search for a specific character/value. Keep in mind, it is case sensitive.
		System.out.println("The index of the character \"d\" is: "+ position);
		
		String sub1 = sentence.substring(15, 20);
		//.substring is used to capture a specific part of a string and turn it into a value. Can be used to print specific
		//characters. Value in parathesis must set parameter.
		System.out.println("Substring 1: " + sub1);
		
		String sub2 = sentence.substring(4);
		//Writing just a single value in the paraenthesis above will print everything after that set value.
		System.out.println("Substring 2: " + sub2);
		
		char ch = sentence.charAt(5);
		//.charAt can be used to print a char of a String at that specific index/space value. Keep in mind that java starts
		//the count at 0 so instead of reading a full sentence at it's correct # of characters, a .charAt will read the
		//character at the correct java index value. Ex: "Math is fun" .charAt(2) = t, beacuse it reads M 0, a 1, t 2...
		System.out.println("The character at index 5 is:" + ch);
		
		
		kb.close();
		
	}//EOC main
	
}//EOC class

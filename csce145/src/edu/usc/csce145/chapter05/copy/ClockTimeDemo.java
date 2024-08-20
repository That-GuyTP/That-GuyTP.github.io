package edu.usc.csce145.chapter05.copy;

//Thomas Peterson

import java.util.Scanner;

public class ClockTimeDemo {

	public static void main(String[] args) {
		
		//Scanner
		Scanner kb;
		kb = new Scanner(System.in);
		
		//Variables
		int hour;
		int minutes;
		int seconds;
		String time;
		int answer;
		boolean stop = false;
		
	//INTRO AND LOOP START
	System.out.println("Hello and welcome to the time converter program.");
	do{
		
		//OBJECT 1
		System.out.println("Please enter the current hour in military time:");
		hour = kb.nextInt();
		System.out.println("Please enter the current minutes in military time:");
		minutes = kb.nextInt();
		System.out.println("Please enter the current seconds in military time:");
		seconds = kb.nextInt();
		kb.nextLine();//Clearing the scanner so that inputs can be properly read.
		TimeConverter tc1 = new TimeConverter(hour, minutes, seconds);
		System.out.print(tc1.displayTime());
		if(tc1.getNotPM() == false) {
			System.out.print(" AM");
		}else {
			System.out.print(" PM");
		}//EOC IF-ELSE
		
		//OBJECT 2
		System.out.println("\nPlease enter the military time in the formant \"HH:MM:SS\":");
		time = kb.nextLine();
		TimeConverter tc2 = new TimeConverter();
		tc2.updateTime(time);
		System.out.print(tc2.displayTime());
		if(tc1.getNotPM() == true) {
			System.out.print(" AM");
		}else {
			System.out.print(" PM");
		}//EOC IF-ELSE
		
		//REPROMPT
		System.out.println("\nWould you like to do this again? (Type 1 for yes & 2 for no)");
		answer = kb.nextInt();
		if(answer == 1) {
			stop = false;
		}else if(answer == 2) {
			stop = true;
		}else {
			System.out.println("Sorry that isn't a valid choice.");
			stop = true;
		}//EOC IF-ELSE CHAIN
		
	}while(stop != true);
		
		System.out.println("Exiting the program");
		kb.close();
		
	}//EOC MAIN METHOD

}//EOC ClockTimeDemo

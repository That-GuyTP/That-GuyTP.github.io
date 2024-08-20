package edu.mtc.egr281.chapter04;

//Thomas Peterson
//Project 05
//Current Date 10-02-2022
//Due Date 09-29-2022
//Using if else and switch statements, I will be displaying either the correct class for a Compass Reading/Writing Sample score 
//or the proper song lyric for the day of Christmas

import java.util.Scanner;

public class Project05 {

	public static void main(String[] args) {
		
		//Algorithm Design
		//Understand what the teacher is asking of me
		//Create a prompt of sorts that asks whether a teacher wants to input a compass reading score or do a Christmas thing
		//Use a scanner to prompt for both outputs.
		//Output data on both ends.
		//End program
		
		//Dec and Inst
		Scanner kb = new Scanner(System.in);
		int DayOfChristmas;
		java.util.ArrayList<String> ChristmasSong = new java.util.ArrayList<String>();
		
		System.out.println("Please enter whether you are looking for Compass Reading Score/Writing Sample Score or a Christmas surpise (0 for Scores, 1 for Christmas Surpise): ");
		String first_question = kb.next();
		
		//Compass reading Score/Writing Sample Score design
		if("0".equals(first_question)) {
			System.out.println("Please input whether you need a Compass Reading Score(0) or Writing Sample Score(1): ");	
			double second_question = kb.nextDouble();
				if(second_question == 0) {
				System.out.println("Enter Compass Reading Score: ");
				double crs_question = kb.nextDouble();
					if(crs_question <21) {
						System.out.println("Not a vaild score. Please try again.");
					} else if(crs_question >=21 && crs_question <45) {
						System.out.println("Placed into RDG 032.");
					} else if(crs_question >=45 && crs_question <64) {
						System.out.println("Placed into RDG 100.");
					} else if(crs_question >=64 && crs_question <73) {
						System.out.println("Placed into RDG 101, if it is required by their programe, or else no Reading course is required.");
					} else {
						System.out.println("No reading course is required.");
					}//EOC crs_question
				} else if(second_question == 1) {
				System.out.println("Enter Writing Sample Score: ");
				double wss_question = kb.nextDouble();
					if(wss_question == 1) {
						System.out.println("Placed into RDG 099 if their Compass Reading Score is above 20.");
					} else if(wss_question == 2) {
						System.out.println("Must complete RDG 099 if the Compass Reading Score is less than 46, otherwise they will take RDG 100.");
					} else if(wss_question == 3) {
						System.out.println("Must complete RDG 099 if the Compass Reading Score is less than 65, othewise they will take RDG 101");
					} else {
						System.out.println("Not a valid score. Please try again.");
					}//EOC wss_question
				}//EOC second_question
		
		//Christmas Song Design
		} else if("1".equals(first_question)){
			System.out.print("Thank you for choosing the Christmas surpise button. ");
			System.out.print("The suprise is the Twelve days of Christmas. ");
			System.out.println("Please enter what day of Christmas you are on, to find out what your true love sent you: ");
			DayOfChristmas = kb.nextInt();
				switch(DayOfChristmas) {
				case 12:
					ChristmasSong.add("Twelve drummers drumming");
				case 11:
					ChristmasSong.add("Eleven pipers piping");
				case 10:
					ChristmasSong.add("Ten lords a-leaping");
				case 9:
					ChristmasSong.add("Nine ladies dancing");
				case 8:
					ChristmasSong.add("Eight maids a-milking");
				case 7:
					ChristmasSong.add("Seven swans a-swimming");
				case 6:
					ChristmasSong.add("Six geese a-laying");
				case 5:
					ChristmasSong.add("Five golden rings");
				case 4:
					ChristmasSong.add("Four calling birds");
				case 3:
					ChristmasSong.add("Three french hents");
				case 2:
					ChristmasSong.add("Two turtle doves");
				case 1:
					ChristmasSong.add("A partridge in a Pear Tree");
					break;
				default: 
					System.out.print("Not a valid day, remember there were only 12 days of christmas!");
				}//EOC switch
					System.out.println(ChristmasSong);
					System.out.print(" Merry Christmas!");
		
		//Invalid Input Design
		} else {
			System.out.println("Not a valid option. Please try again.");
		}//EOC first_question
		
		kb.close();
	}//EOC main
		
}//EOC Project05



//SWITCH()
//case 0:
	//System.out.println("Please input whether you need a Compass Reading Score(0) or Writing Sample Score(1): ");
	//CRS_Question = kb.nextInt();
	//switch(CRS_Question) {
	//case <= 21:
			//CPS = "Placed into RDG 032";
	//case >=45, <64:
			//CPS = "Placed into RDG 100";
	//case >=64, <73:
			//CPS = "Placed into RDG101, if it is required by their programe, or else no Reading course is required.";
	//case >=73
			//CPS = "No reading course is required.";
	//default:
		//CRS = "Not a valid score. Please try again";
//EOC Switch THIS WAS A FAILED ATTEMPTED AT TRYING TO GET IT TO WORK THE FIRSTIME BEFORE REALIZING I SHOULD USE IF STATEMENTS


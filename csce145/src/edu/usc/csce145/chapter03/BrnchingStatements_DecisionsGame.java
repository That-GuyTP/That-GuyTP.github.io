package edu.usc.csce145.chapter03;

import java.util.Scanner;

public class BrnchingStatements_DecisionsGame {

	public static void main(String args[]) {
	
	//Declarations
		//Scanner
		Scanner kb;
		kb = new Scanner(System.in);
		
		//Varaibles
		String contestant1Prize = "nothing";
		String contestant2Prize = "nothing";
		String penPrize = "A black pen";
		String deskPrize = "a post-it notes";
		String bagPrize = "a Kit-Kat";
		
	//Input
		System.out.println("Contestant 1: I will give you this pen. Would you like to keep it or trade it for what\'s in my pocket or what\'s in my bag? Enter lee[, desk or pocket:");
		
		//Answer Variable Chain Start
		String answer;
		answer = kb.next();
		
		//Answer 1
		if(answer.equalsIgnoreCase("keep")) {
			//.equalsIgnoreCase is used for accepting inputs regardless of capitalizations.
			contestant1Prize = penPrize;
			
			//Answer 2
				System.out.println("Contestant 2: Would you like to choose the desk or bag prize? Enter bag or desk:");
				answer = kb.next();
			if(answer.equalsIgnoreCase("bag")) {
				contestant2Prize = bagPrize;
			}else if(answer.equalsIgnoreCase("desk")) {
				contestant2Prize = deskPrize;
			}else {
				System.out.println("Sorry that wasn't an option. You got nothing!");
			}//EOC Answer 2 if-block
			
		}else if(answer.equalsIgnoreCase("desk")) {
			contestant1Prize = deskPrize;
			
			//Answer 3
			System.out.println("contestant 2: Would you like to keep the pen or trade for what is in the bag? Enter keep or bag");
			answer = kb.next();
			if (answer.equalsIgnoreCase("keep")) {
				contestant2Prize = penPrize;
			}else if(answer.equalsIgnoreCase("bag")) {
				contestant2Prize = bagPrize;
			}else {
				System.out.println("Sorry that wasn't an option. You got nothing!");
			}//EOC Answer 3 if-block
			
		}else if(answer.equalsIgnoreCase("bag")){
			contestant2Prize = bagPrize;
			
			//Answer 4
			System.out.println("Contestant 2: Would you like to keep the pen or have what is on my desk? Enter keep or desk:");
			answer = kb.next();
			if(answer.equalsIgnoreCase("desk")) {
				contestant2Prize = deskPrize;
			}else if(answer.equalsIgnoreCase("Keep")){
				contestant2Prize = penPrize;
			}else {
				System.out.println("Sorry that wasn't an option. You got nothing!");
			}//EOC Answer 4 if-block
			
		}else {
			System.out.println("Sorry that wasn't an option. You get nothing!");
		}//EOC Answer 1 if-block
	
		
		kb.close();
		
	}//EOC main
	
}//EOC class

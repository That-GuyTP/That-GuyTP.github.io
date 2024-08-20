package edu.mtc.egr281.chapter03;

import java.util.Scanner;

public class SwitchEnumDemo {

	enum MovieRating {G, PG, PG13, R, MA}
	
	public static void main(String[] args) {
		
		//STORE
		Scanner kb = new Scanner(System.in);
		MovieRating rating;
		String inputString = "", outputString = "";
		
		//INPUT
		System.out.print("What is the movie rating? ");
		inputString = (kb.next()).toUpperCase();
		if("G".equals(inputString)) {
			rating = MovieRating.G;
		} else if("PG".equals(inputString)) {
			rating = MovieRating.PG;
		} else if("R".equals(inputString)) {
			rating = MovieRating.R;
		} else {
			rating = MovieRating.MA;
		}//EOC inputString
		
		//PROCESS
		switch(rating) {
		case G:
		case PG:
		case PG13:
			outputString = "Yes, you may go.";
			break;
		case R:
			outputString = "Maybe";
		default:
			outputString = "NO!";
		}//EOC switch
		
		//OUTPUT
		System.out.println(outputString);
		
		kb.close();
	}//EOC main
	
}//EOC SwitchEnumDemo

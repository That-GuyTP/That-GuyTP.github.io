package edu.mtc.egr281.chapter04;

public class SimpleWhile {

	public static void main(String[] args) {
		
		//Initialization
		int x = 10;
		int y = 0;
		
		//Boolean Expression
		while(y <= x) {
			
			//Body of loop
			System.out.println(y);
			
			//Update action
			y = y + 1;
			//or y += 1;
			//or ++y;
			
		}//EOC while
		
	}//EOC main
	
}//EOC SimpleWhile

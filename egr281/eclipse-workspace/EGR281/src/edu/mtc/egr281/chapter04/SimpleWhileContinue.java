package edu.mtc.egr281.chapter04;

public class SimpleWhileContinue {

	public static void main(String[] args) {
		
		//Initialization
		int x = 21;
		
		//Boolean Express
		while(x > 0) {
			
			//Update
			x -= 1;
			
			//Body
			if( (x % 2) == 1) {
				continue;
			}//EOC Body
			
			System.out.print(x + " ");
			
		}//EOC while
		
	}//EOC main
	
}//EOC SWC

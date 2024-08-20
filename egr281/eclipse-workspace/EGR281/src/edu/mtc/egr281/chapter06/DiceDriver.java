package edu.mtc.egr281.chapter06;

public class DiceDriver {

	public static void main(String[] args) {
		
		Dice d = new Dice();
		for(int i = 0; i < 12; ++i) {
			d.roll();
			System.out.print("Roll #" + (i+1) + ": " + d.getValue());
			if(d.isDoubles()) {
				System.out.print(" - DOUBLES!!");
			}//EOC if
			System.out.println();
		}//EOC for
		
	}//EOC method main
	
}//EOC class DiceDriver

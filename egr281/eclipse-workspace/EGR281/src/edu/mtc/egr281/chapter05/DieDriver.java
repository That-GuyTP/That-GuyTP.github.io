package edu.mtc.egr281.chapter05;

public class DieDriver { //"Driver" Used to test your code.

	public static void main(String[] args) {
		
		Die d = new Die();
		System.out.println("D has " + d.getNumberOfSides() + " sides");
		for(int i =0; i < 12; ++i) {
			d.roll();
			System.out.println("roll #" + i +": " + d.getValue());
		}//EOC for
		
		System.out.println("-------");
		
		Die d4 = new Die(4);
		System.out.println("d4 has " + d4.getNumberOfSides() + " sides");
		for(int i = 0; i < 8; ++i) {
			d4.roll();
			System.out.println("Roll #" + i + ": " + d4.getValue());
		}//EOC for
		
	}//EOC main
	
}//EOC DieDriver

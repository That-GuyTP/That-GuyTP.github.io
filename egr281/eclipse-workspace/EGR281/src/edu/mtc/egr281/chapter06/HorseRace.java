package edu.mtc.egr281.chapter06;

import java.util.Scanner;

public class HorseRace {

//CONSTANTS
	public static final int FINISH_LINE = 75;
	
//FIELDS
	private static Scanner kb;
	private static Dice dice;
	private static Horse[] horses;
	
//NO CONSTUCTORS BECAUSE - main method "driver" class
	
//METHODS
	public static void main(String[] args) {
		kb = new Scanner(System.in);
		dice = new Dice();
		
		System.out.print("Enter number of horses in the race: ");
		horses = new Horse[kb.nextInt()];
		
		//POPULATE horse into horse array
		for(int i = 0; i < horses.length; ++i) {
			System.out.print("Enter name of horse #" + (i+1) + ": ");
			horses[i] = new Horse(kb.next());
		}//EOC for
		
		//RACE THE HORSES
		int index = -1;
		Horse horse;
		
		do {
			//1 horse's turn
			++index;
			horse = horses[index % horses.length];
			
			dice.roll();
			horse.updateCurrentFurlong(dice.getValue());
			HorseRace.printHorseTrack(horse);
			
		}while(horse.getCurrentFurlong() < HorseRace.FINISH_LINE);
		
		System.out.println(horse.getName() + " is the winner!!");
		
		kb.close();
	}//EOC method main
	
	private static void printHorseTrack(Horse h) {
		System.out.print(h.getName() + ": ");
		for(int i = 0; i < HorseRace.FINISH_LINE; ++i) {
			if(i == h.getCurrentFurlong()) {
				System.out.print("*");
			} else {
				System.out.print("-");
			}//EOC if
		}//EOC for
		System.out.println();
	}//EOC method printHorseTrack
		
}//EOC class HorseRace

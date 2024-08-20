package edu.usc.csce145.chapter05.Species;

import java.util.Scanner;

public class Species {
	
	//PUBLIC VARIABLES
	//public determines the access of the variable. It allows this variable to be accessed outside of the class.
	//Setting a variable to private will prevent the value from being changed in another class or later in the same class. This is called encapsalation.
	private String name;
	private int population;
	private double growthRate;
	
	//METHOD readingInput
	//void means that the method cannot return a value. This method will not return any form of value/variable back to the user/program.
	public void readingInput() {
		
		//SCANNER
		Scanner kb;
		kb = new Scanner(System.in);
		
		//INPUT
		System.out.println("Enter the name of the species;");
		this.name = kb.nextLine();
		//"this." is used to declare what exact object is being used in the other class. I.e if we are creating sp1 the attached variables will be defined as this.sp1's name, population, growthRate, etc.
		System.out.println("What is the total population of the species?");
		this.population = kb.nextInt();
		System.out.println("What is the growth rate of the species?");
		this.growthRate = kb.nextDouble();
		
		
		kb.close();
		
	}//EOC METHOD readingInput
	
	//METHOD writeOutput
	public void writeOutput() {
		System.out.println("Endangered Species Data:");
		System.out.println("Name: " + this.name);
		System.out.println("Population: " + this.population);
		System.out.println("Growth rate: " + this.growthRate + "%");
		
	}//EOC METHOD writeOutput
	
	//METHOD getPopulationIn10
	public int getPopulationIn10() {
		
		//VARIABLES
		int result = 0;
		int count = 10;
		double populationAmount = this.population;
		
		//CACULATION & OUTPUT
		while(count > 0 && populationAmount > 0) {
			populationAmount += (this.growthRate/100) * populationAmount;
			count--;
		}//EOC WHILE
		if(populationAmount > 0) {
			result = (int) populationAmount;
		}//EOC IF
		return result;
	}//EOC METHOD getPopulationIn10

	
}//EOC Species

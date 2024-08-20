package edu.mtc.egr281.chapter06;

import java.util.Random;

public class Spinner {

	//Constants
	public static final int SECTORS = 6;
	
	//Fields
	private int value;
	private Random randomizer;
	
	//Constructors
	public Spinner() {
		this.setValue(0);
		this.randomizer = new Random();
	}//EOC default constructor "no-args" Used to instantiate ints and methods
	
	//Methods
	public int getValue() {
		return this.value;
	}//EOC method getValue
	
	private void setValue(int newValue) {
		this.value = newValue;
	}//EOC method setValue
	
	public int spin() {
		this.setValue(this.randomizer.nextInt(Spinner.SECTORS) + 1); //Add one because the computer will roll 0-5 and not 1-6
		return this.getValue();
	}//EOC method return //Dummy value for working with
	
}//EOC Spinner

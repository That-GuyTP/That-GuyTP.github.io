package edu.mtc.egr281.chapter06;

import java.util.Random;

public class Die {

	//CONSTANTS
	public static final int SIDES = 6;
	
	//FIELDS
	private int value;
	private int numberOfSides;
	private Random randomizer;
	
	//CONSTRUCTORS
	public Die() {
		this(Die.SIDES);
	}//EOC default constructor
	
	public Die(int newNumberOfSides) {
		this.setNumberOfSides(newNumberOfSides);
		this.randomizer = new Random();
		this.setValue(0);
	}//EOC constructor
	
	//METHODS
	public int getValue() {
		return this.value;
	}//EOC method getValue
	
	private void setValue(int newValue) {
		this.value = newValue;
	}//EOC method setValue
	
	public int getNumberOfSides() {
		return this.numberOfSides;
	}//EOC method getNumberOfSides
	
	private void setNumberOfSides( int newNumberOfSides) {
		this.numberOfSides = newNumberOfSides;
	}//EOc method setNumberOfSides
	
	public void roll() {
		this.setValue(this.randomizer.nextInt(this.getNumberOfSides()) + 1);
	}//EOC method roll
	
}//EOC Die

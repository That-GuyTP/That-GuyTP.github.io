package edu.mtc.egr281.chapter06;

public class Dice {

//CONSTANTS
	
	
//FIELDS
	private Die d1;
	private Die d2;
	
//CONSTRUCTORS
	public Dice() {
		this(Die.SIDES);
	}//EOC default constructor
	
	public Dice(int newNumberOfSides) {
		this.d1 = new Die(newNumberOfSides);
		this.d2 = new Die(newNumberOfSides);
	}//EOC constructor
	
//METHODS
	public void roll() {
		this.d1.roll();
		this.d2.roll();
	}//EOC method roll
	
	public int getValue() {
		return this.d1.getValue() + this.d2.getValue();
	}//EOC method getValue
	
	public int getNumberOfSides() {
		return this.d1.getNumberOfSides();
	}//EOC method getNumberOfSides
	
	public boolean isDoubles() { //boolean should begin with "is" or "has"
		return (this.d1.getValue() == this.d2.getValue());
	}//EOC method isDoubles
	
}//EOC class Dice

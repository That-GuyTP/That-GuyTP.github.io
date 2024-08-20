 package edu.mtc.egr281.chapter05;

import java.util.Random;

public class Die {

	//Fields
		private int value;
		private int numberOfSides;
		private Random numberGenerator;
	
	//Constant
		public static final int DEFAULT_NUMBER_OF_SIDES = 6;
	
	//Constructors
		//Must have different input ways.
		public Die() {
			this(Die.DEFAULT_NUMBER_OF_SIDES);
		}//EOC default constructor "no args"
		
		public Die(int newNumberOfSides) {
			this.setNumberOfSides(newNumberOfSides);
			this.numberGenerator = new Random();
			this.setValue(0);
			//this.setValue(this.numberGenerator.nextInt(this.getNumberOfSides);
		}//EOC constructor
	
	//Methods
		//Mutators
		public int getValue() {
			return this.value;
		}//EOC getValue
	
		private void setValue(int newValue) {
			this.value = newValue;
		}//EOC setValue
	
		public int getNumberOfSides() {
			return this.numberOfSides;
		}//EOC getNumberOfSides
	
		private void setNumberOfSides(int newNumberOfSides) {
			this.numberOfSides = newNumberOfSides;
		}//EOC setNumberOfSides
	
		public void roll() { //Set the winning number, based off a random number, given by the user. Making sure its not 0-5 and is 1-6
			this.setValue(this.numberGenerator.nextInt(this.getNumberOfSides())+1);
		}//EOC roll
		
}//EOC 

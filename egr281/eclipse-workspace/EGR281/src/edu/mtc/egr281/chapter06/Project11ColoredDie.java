package edu.mtc.egr281.chapter06;

//Thomas Peterson
//Project11
//Current Date: 11-11-2022
//Due Date: 11-10-22
//To create a snail racing game using object orientated programming methods.

//ALGORITHM DESIGN
//Essentially copy the "Die" example from files
//Change variables accordingly
//Add new String for whatever color the player chooses
//Add a method to get the value of the roll, and turn it into the number that will be used for the column increase.

//-color : String
//-randomizer : Random
//+getColor():String
//-setColor(String):void
//+roll():String

import java.util.Random;

public class Project11ColoredDie {

//Fields
	private String color;
	private Random randomizer;
	
//Constant

	
//Constructors
	public Project11ColoredDie(String newColor) {
		this.setColor(newColor);
		this.randomizer = new Random();
	}//EOC constructor
	
//METHODS
	public String getColor() {
		return this.color;
	}//EOC getColor
	
	private void setColor(String newColor) {
		this.color = newColor;
	}//EOC setColor;
	
	public String roll(int update) {
		
		this.setColor(update + this.getColor());
		
		return roll;
	}
	
}//EOC Project11ColoredDie

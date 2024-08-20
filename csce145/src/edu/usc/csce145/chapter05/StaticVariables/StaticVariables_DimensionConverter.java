package edu.usc.csce145.chapter05.StaticVariables;

public class StaticVariables_DimensionConverter {

	//NOTES
	//By pressing "0" on your numpad you can switch between two types of typing modes.
	//You can use ctrl + shift + numpad_divide to quick close all code blocks w/ the ability to.
	//You can use ctrl + pg_up/pg_down to quickly switch between classes.
	/*Types of Variabels
		 * Instance Vraiables - Properties/features of a class object
		 * Local Variables - Scope of local variable is limited to the blookc in which it is defined
		 * Access Variables - Public/private methods that are declared as such.
		 * Static Variables - Shared by all objects of a class. It's a constant that can be accessed by other classes for later use. If "final" isn't included it can be modified later. They are also known as
		 					  "class variables". 
	*/
	//"final" represents the fact that the variable will never change no matter what other methods are used to try to change it.
	//"Static methods" are methods that have no relation to any type of object. They can be called upon by other classes so long as they are labeled "public" without the need to declare an object of that method's class.
	//If you are overridding a parent method and need to return a value you would include the "super.methodName()" inside the line of code with the return and returning variable included. 
	//ie "return super.methodName() + ....";
			
	
	
	//CONSTANTS
	public static final int FEETTOINCHES = 12;
	
	
	//FEET TO INCHES METHOD
	//We include "static" in the declaration because this method is public to all other classes.
	public static double FeetToInches(double feet) {
		return feet * FEETTOINCHES;
	}//EOC FeetToInches
	
	
	//INCHES TO FEET
	public static double InchesToFeet(double inches) {
		return inches / FEETTOINCHES;
	}//EOC InchesToFeet
	
	
}//EOC StaticVariables_DimensionConverter

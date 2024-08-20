package edu.usc.csce145.chapter05.Species;

public class SpeciesDemo {

	public static void main(String args[]) {
		
		int populationAfter10;
		
		//OBJECTS
		//Objects are "tools" that are created using previously created code. This object is an object of the class(tool) "Species". Scanners are another example.
		//Objects access their origin class in order to pull from methods, variables, constants, etc in so that they can be used in another class.
		//You could, if desired, created multiple versions of this object to use for different species. So instead of it just being sp1, there could be a sp2, a sp3, etc.
		
		//OBJECT sp1
		Species sp1 = new Species();
	
		//Using this will recall a method from the object's original code/class.
		sp1.readingInput();
		sp1.writeOutput();
		
		//We have to declare a new variable that is an integer because the method getPopulatonIn10 is an integer type. It also isn't void so we can declare a variable here as well.
		populationAfter10 = sp1.getPopulationIn10();
		System.out.println("The population for species 1 after 10 years will be: " + populationAfter10);
		
		//OBJECT sp2
		Species sp2 = new Species();
		sp2.readingInput();
		sp2.writeOutput();
		populationAfter10 = sp2.getPopulationIn10();
		System.out.println("THe population for species 2 after 10 years will be: " + populationAfter10);
		
		//You could declare the values for an object in this class so that a method wouldn't need to be used. This can only be done in the main method.
		//This can be used to change the values of an object AFTER they have been declared/inputed by the user later in the class. Here's an example:
		/*
		  sp2.name = "Monarch Butterfly";
		  sp2.growthRate = 4;
		  sp2.writeOutput();
		 */
		//If the user inputed Red Panda for sp2 before this line, it will still print out the Red Panda and then proceed to output the sp2 with the changed name and value. Here's the output:
		/*
		 sp1....
		 ....
		 ....
		 
		 Red Panda
		 1500
		 1.5
		 
		 Monarch Butterfly
		 1500
		 4
		 */
		//This code will not work however, since in the "species" class these variables are "private" meaning that once they're set/declared, they cannot be changed.
		
		
		System.exit(0);
		
	}//EOC MAIN METHOD
	
}//EOC SpeciesDemo

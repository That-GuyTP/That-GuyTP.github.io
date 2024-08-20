package CatThing;

public class CatTester {

	public static void main(String args[]) {
		
		/*NOTES
		 - Add dummy code like "System.out.println("Test01");" to use as markers in order to find where the code is stopping and creating errors.
		 - The blue bar on the left side of the code screen is called the "Break-Point Bar". It will help show where potential erros are occuring. Clicking on the blue bar will add a "breakpoint" which will let the code run until that point
		   showing if that code works until that point. 
		 - In order to use a breakpoint you need to click on the "debug" icon to switch to debug mode. It will show a "call stack" when ran and show which methods and functions have been run in order.
		 - Using "step into" will run the next line of code in the function. Using "step over" will skip a line in the code.
		 */
		
		/*Inheritance
		 - Eastablishes a relationship between two classes where proprities and methods are absorbed from one class to another
		 - Uses the "extends" class definition to inherit pervious methods. Represented by a BOX ARROW in a diagram.
		 - U
		 */
		
		//EXAMPLE 3
		//Instances/Objects of the class Cat
		Cat cat01;
		cat01 = new Cat(); //This tells the compiler to create space in "heap" code for the object cat with the name cat01. The tag "new" means that the compiler needs to call upon the constructor "Cat()" and create an object of that constructor called cat01.
		Cat cat02 = new Cat("triscuit", 9.0, 4);
		Cat cat03;

		System.out.println(cat01); //This works without needing to call the toString() method because there is a default class precreated for this object. This predetermined method knows to automatically call the toString() method when System.out.println() is used.
		cat02.toString();
		
		//EXAMPLE 2
		Cat cat04 = new Cat();
		Cat cat05 = new Cat();
		System.out.println(cat04.equals(cat05)); //Equals true
		System.out.println(cat04 == cat05); //Equals false. This is because using "==" compares location of these objects and not their properties
		
		//EXAMPLE 3
		Cat cat06, cat07;
		cat06 = cat07 = new Cat();//This organization defines cat07 as Dr Boots when the next two lines are compiled. This is because defining these two objects using the same "new Cat();" makes them essentially the same object using the same memory ID location.
		cat06.setName("Dr Boots");
		System.out.println(cat07.getName());
		
		
		System.exit(0);
		
	}//EOC MAIN METHOD
	
}//EOD CatThingTester

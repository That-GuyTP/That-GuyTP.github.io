package edu.usc.csce145.chapter05.PetsProject;

public class PetsProject_PetDemo {
	//***Again. Keep in mind that my class is named "PetsProject_PetDemo" because of the way I organize my files. What the professor and presumably the rest of the class did was create a project called 
	//"Pets Project" and then create a "PetDemo" class inside of that with the following code. The name of the object will change of course to the lines: 
	//"Pet p1 = new Pet();"
	//"Pet p2 = new Pet();"
	
	public static void main(String[] args) {
		
		//OBJECTS
		PetsProject_Pet p1 = new PetsProject_Pet();
		System.out.println(p1.toString());

		//Using these set parameters for p2 the class "Pet" will automatically use these parameters for the values of the instance variables when it is defining p2.
		PetsProject_Pet p2 = new PetsProject_Pet("Biscuits", 1.5, "lizard");
		System.out.println("The second pet obeject is: ");
		System.out.println(p2.toString());
		
	}//EOC MAIN METHOD

}//EOC PetsProject_PetDemo

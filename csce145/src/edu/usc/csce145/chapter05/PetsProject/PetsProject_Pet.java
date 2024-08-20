package edu.usc.csce145.chapter05.PetsProject;

public class PetsProject_Pet {
	//*** Keep in mind that my class is named "PetsProject_Pet" because of the way I organize my files. What the professor and presumably the rest of the class did was create a project called "Pets Project" and then create
	//a "Pet" class inside of that with the following code. The names of your contructors will also change to "Pet" since you won't be including the "PetsProject_" portion of the class name.

	
	//NOTES
	/*Java is an Object-Orientted Programing Language. An object is a program created to complete a specific task. It has to stand by certain principles:
	 1. Encapsulation - If a user doesn't need to know about a certain object they shouldn't be able to see it/access it. While not required, it is expected and helps with organization. When you create "instance variables"
	    they are the variables that will be used specifically for this class and should be set to private for the sake of encapsulation.
	 2. 
	 */
	
	
	//INSTANCE VARIABLES
	//We are declaring these as private since we want to store a value from the user's input, but don't want that value to be able to be changed later.
	private String name;
	private double weight;
	private String type;
	
	
	//DEFAULT CONSTRUCTOR
	//Used to create or "construct" default values for your instance variables. Constructors do not need a return declarement ("void", "String", "int", etc.) since all they are used for is declaring default values.
	public PetsProject_Pet() {
		this.name = "No name selected";
		this.weight = 0;
		this.type = "unknown";
	}//EOC DEFAULT CONTRUCTOR
	
	
	//PARAMETERIZED CONSTRUCTOR
	//This contructor will set values to the variables based of the parameters given by the user of the program. This is known from the other class "PetDemo".
	public PetsProject_Pet(String xName, double xWeight, String xType) {
		this.setName(xName);
		this.setWeight(xWeight);
		this.setType(xType);
	}//EOC PARAMETERIZED CONTRSUCTPOR
	
	
	//ACCESSOR METHODS
	//Used to access values inside the data types we have declared (name, weight, type).
	public String getName() {
		return this.name;
	}//EOC METHOD getNAME
	
	public double getWeight() {
		return this.weight;
	}//EOC METHOD getWeight
	
	public String getType() {
		return this.type;
	}//EOC METHOD getType
	
	
	//MUTATOR METHODS
	//Used to allow a user to change the values of the variables above since they are set to private and are unchangeable. "(String xName)" is the parameter. This parameter will be used in another class to allow the user to
	//set the name of whatever object they are creating in that other class.
	//In example: if the user created object "pet1" and set the weight to 1.5. Using the setWeight method will allow them to update that value to whatever value they need to update it too.
	public void setName(String xName) {
		this.name = xName;
	}//EOC METHOD setName
	
	public void setWeight(double xWeight) {
		
		//We are using this if method to ensure the weight is a positive number since a pet cannot weigh negative weight. Make sure to not use a "System.exit();" since we still need this class to run.
		if(xWeight > 0) {
			this.weight = xWeight;
		}else {
			System.out.println("Invalid value provided for weight of the pet");
		}//EOC IF-ELSE
		
	}//EOC METHOD setWeight
	
	public void setType(String xType) {
		
		//While the type of pet is not limited to these examples, we are creating this if statement to ensure that the entered value is a pet and not some form of other value.
		if(xType.equalsIgnoreCase("Lizard") || xType.equalsIgnoreCase("dog") || xType.equalsIgnoreCase("cat") || xType.equalsIgnoreCase("bird") || xType.equalsIgnoreCase("fish")) {
			this.type = xType;
		}else {
			System.out.println("Invalid type provided for pet");
		}//EOC IF-ELSE
		
	}//EOC METHOD setType

	
	//PRINT METHOD
	public String toString() {
		return "Name: " + this.name + "\nType: " + this.type + "\nWeight: " + this.weight;
	}//EOC METHOD toString
	
}//EOC PetsProject_Pet

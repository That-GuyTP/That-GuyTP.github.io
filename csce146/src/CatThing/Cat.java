package CatThing;

//THOMAS PETERSON

//STEP 1 - DEFINE THE CLASS
public class Cat {

//STEP 2 - DATA - INSTANCE VARIABLES/CLASS CONSTANTS
	//INSTANCE VARIABLES
	private String name;
	private double weight;
	private int numberOfLegs;

	
//STEP 3 - CONSTRUCTORS
	//DEFAULT CONSTRUCTOR
	public Cat() {
		this.name = "N/A";
		this.weight = 1.0;
		this.numberOfLegs = 4;
	}//EOC DEFAULT CONSTRUCTOR
	
	//PARAMETER CONSTRUCTOR
	public Cat(String xName, double xWeight, int xNumberOfLegs) {
		this.name = xName;
		this.weight = xWeight;
		this.numberOfLegs = xNumberOfLegs;
	}//EOC PARAMETER CONSTRUCTOR
	
	
//STEP 4 - ACCESSOR METHODS
	public String getName() {
		return this.name;
	}//EOC getName
	
	public double getWeight() {
		return this.weight;
	}//EOC getWeight
	
	public int getNumberOfLegs() {
		return this.numberOfLegs;
	}//EOC getNumberOfLegs
	
	
//STEP 5 - MUTATOR METHODS
	public void setName(String xName) {
		if(xName != null) {
			this.name = xName;
		}else {
			this.name = "N/A";
		}//EOC IF-ELSE
	}//EOC setName
	
	public void setWeight(double xWeight) {
		if (xWeight > 0.0) {
			this.weight = xWeight;
		}else {
			this.weight = 1.0;
		}//EOC IF-ELSE
	}//EOC setWeight
	
	public void setNumberOfLegs(int xNumberOfLegs) {
		if (xNumberOfLegs >= 0 && xNumberOfLegs <= 4) {
			this.numberOfLegs = xNumberOfLegs;
		}else {
			this.numberOfLegs = 4;
		}//EOC IF-ELSE
	}//EOC setNumberOfLegs
	

//STEP 6 - OTHER NEEDED METHODS
	public String toString() {
		return "Name: " + this.name
			 + "\nWeight: " + this.weight
			 + "\nNumber Of Legs: " + this.numberOfLegs;
	}//EOC toString
	
	public boolean equals(Cat c1) {
		return c1 != null 
			&& this.name.equalsIgnoreCase(c1.getName())
			&& this.weight == c1.getWeight()
			&& this.numberOfLegs == c1.getNumberOfLegs();
	}//EOC equals
	
}//EOC Cat

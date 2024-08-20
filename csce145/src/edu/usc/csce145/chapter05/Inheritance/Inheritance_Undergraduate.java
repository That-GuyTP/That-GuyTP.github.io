package edu.usc.csce145.chapter05.Inheritance;

public class Inheritance_Undergraduate extends Inheritance_Student {

	//NOTES
	//This is a "Child" or "Derivied" class of Inheritance_Student.
	
	//INSTANCE VARIABLES
	private String minor;
	private int level;

	
	//DEFAULT CONSTRUCTOR
	public Inheritance_Undergraduate(){
		super();
		this.minor = "unknown";
		this.level = 1;
	}//EOC DEFAULT CONSTRUCTOR
	
	
	//PARAMETER CONTRUCTOR
	public Inheritance_Undergraduate(String xName, int xAge, int xID, String xMajor, double xGpa, String xMinor, int xLevel){
		super(xName, xAge, xID, xMajor, xGpa);//this must include all parameters of the previous parent class' (Inheritance_Student) super.
		this.setMinor(xMinor);
		this.setLevel(xLevel);
	}//EOC PARAMETER CONSTRUCTOR
	
	
	// ACCESSOR METHODS
	public String getMinor(){
		return this.minor;
	}//EOC getMinor
	
	public int getLevel(){
		return this.level;
	}//EOC getLevel
	
	
	//MUTATOR METHODS
	public void setMinor(String xMinor){
		if(xMinor.equalsIgnoreCase("Math")
		|| xMinor.equalsIgnoreCase("Data Science")
		|| xMinor.equalsIgnoreCase("English")){
			this.minor = xMinor;
		}else {
			System.out.println("Invalid value for student minor!");
		}//EOC IF-ELSE
	}//EOC setMinor
	
	public void setLevel(int xLevel){
		if(xLevel >= 1 && xLevel <= 4){
			this.level = xLevel;
		}else {
			System.out.println("Invalid value for student level!");
		}//EOC IF-ELSE
	}//EOC setLevel
	
	//METHOD OVERWRITING - Interheritance_Student's writeOutput method
	//Keeping parameters the same.
	public void writeOutput() {
		super.writeOutput(); //Calls on the parent class' (Inheritance_Student) writeOutput method
		System.out.println("Minor: " + this.minor
						 + "\nLevel: " + this.level);
	}//EOC writeOutput OVERWRITE
	
	
}//EOC Inheritance_Undergraduate

package edu.usc.csce145.chapter05.Inheritance;

public class Inheritance_Student extends Inheritance_Person {

	//NOTES
	//This is a "Child" or "Derivied" class of Inheritance_Person.
	
	
	//INSTANCE VARIABLES
	private int studentID;
	private String major;
	private double gpa;
	
	
	//DEFAULT CONSTRUCTOR
	public Inheritance_Student(){
		super();		//calls the base class-Person's default constructor
		this.studentID = 1;
		this.major = "no major selected";
		this.gpa = 4.0;
	}//EOC DEFAULT CONSTRUCTOR
	
	
	//PARAMETER CONSTRUCTOR
	public Inheritance_Student(String xName, int xAge, int xID, String xMajor, double xGpa){
		
		super(xName, xAge); //Calls upon the parent class' (Inheritance_Person) parameter method.
		this.setStudentID(xID);
		this.setMajor(xMajor);
		this.setGpa(xGpa);
	}//EOC PARAMETER CONSTRUCTOR
	
	
	//ACCESSOR METHODS
	public int getStudentID(){
		return this.studentID;
	}//EOC getStudentID
	
	public String getMajor(){
		return this.major;
	}//EOC getMajor
	
	public double getGpa(){
		return this.gpa;
	}//EOC getGPA
	
	
	//MUTATOR METHODS
	public void setStudentID(int xID){
		if(xID >= 1){
			this.studentID = xID;
		}else {
			System.out.println("Invalid value for student ID!");
		}//EOC IF-ELSE
	}//EOC setStudentID
	
	public void setMajor(String xMajor){
		if(xMajor.equalsIgnoreCase("Computer Science")
		|| xMajor.equalsIgnoreCase("Physics")
		|| xMajor.equalsIgnoreCase("Civil Engineering")){
			this.major = xMajor;
		}else {
			System.out.println("Invalid value for student major!");
		}//EOC IF-ELSE
			
	}//EOC setMajor
	
	public void setGpa(double xGpa){
		if(xGpa >= 1 && xGpa <= 4){
			this.gpa = xGpa;
		}else {
			System.out.println("Invalid value for student GPA!");
		}//EOC IF-ELSE
	}//EOC setGPA
	
	
	//method overriding - same method signature but different methodality
	public void writeOutput(){
		super.writeOutput(); //Calls upon the parent class' (Inheritance_Person) writeOutput method
		System.out.println("Student ID: "+this.studentID
				+"\nMajor: "+this.major
				+"\nGPA: "+this.gpa);
	}//EOC WRITE OUTPUT
	
}//EOC Inheritance_Student

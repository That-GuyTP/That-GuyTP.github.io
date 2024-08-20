package edu.usc.csce145.chapter05.Inheritance;

public class Inheritance_Person {
	
	//NOTES
	//This is the base class of this class project example. It will be used to set up Student & Undergraduate.
	
	
	//INSTANCE VARIABLES
	private String name;
	private int age;
	
	
	//DEFAULT CONSTRUCTOR
	public Inheritance_Person(){
		this.name = "unknown";
		this.age = 1;
	}//EOC DEFAULT CONSTRUCTOR
	
	
	//PARAMETER CONSTRUCTOR
	public Inheritance_Person(String xName, int xAge){
		this.setName(xName);
		this.setAge(xAge);
	}//EOC PARAMETER CONSTRUCTOR
	
	
	//ACCESSOR METHODS
	public String getName(){
		return this.name;
	}//EOC getName
	
	public int getAge(){
		return this.age;
	}//EOC getAge
	
	
	//MUTATOR METHODS
	public void setName(String xName){
		this.name = xName;
	}//EOC setName
	
	public void setAge(int xAge){
		if(xAge >= 0){
			this.age = xAge;
		}else {
			System.out.println("Invalid value for age!");
		}//EOC IF-ELSE
	}//EOC setAge
	
	
	//WRITE OUTPUT METHOD
	public void writeOutput(){
		System.out.println("Name: "+this.name
				+"\nAge: "+this.age);
	}//EOC writeOutput

}//EOC Inheritance_Person

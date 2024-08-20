package InheritanceExample;

//THOMAS PETERSON

//NOTES
//USE right click, source, generate getters and setters to macro out accessor and mutator methods

public class Person {
	
	//INSTANCE VARIABLES
	private String name;
	
	//DEFAULT CONSTRUCTOR
	public Person() {
		this.name = "None";
	}//EOC DEFAULT CONSTRUCTOR
	
	//PARAMETER CONSTRUCTOR
	public Person(String xName) {
		this.name = xName;
	}//EOC PARAMTER

	//ACCESSOR METHOD
	public String getName() {
		return this.name;
	}//EOC ACCESSOR METHOD

	//MUTATOR METHOD
	public void setName(String xName) {
		if(name != null) {
			this.name = xName;
		}else {
			this.name = "None";
		}//EOC IF-ELSE
	}//EOC MUTATOR METHOD
	
	
	//MISC METHODS
	public String toString() {
		return "Name: " + this.getName();
	}//EOC toString

	public boolean equals(Person xPerson) {
		return xPerson != null &&
				this.getName().equals(xPerson.getName());
	}//EOC equals

	
}//EOC Person

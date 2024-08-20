package InheritanceExample;

//THOMAS PETERSON

public class Student extends Person {

	//INSTANCE VARIABLES
	private int id;
	
	
	//DEFAULT CONSTRUCTOR
	public Student() {
		super();//Calls the "Person" class's default constructor
		this.id = 0;
	}//EOC DEFAULT CONSTRUCTOR
	
	
	//PARAMETER CONSTRUCTOR
	public Student(String xName, int xId) {
		super(xName); //calls the "Persons" class's parameter constructor
	}//EOC PARAMETER CONSTRUCTOR

	
	//ACCESSOR METHOD
	public int getId() {
		return id;
	}//EOC getId

	
	//MUTATOR METHOD
	public void setId(int xId) {
		if (id >= 0) {
			this.id = xId;
		}else {
			this.id = 0;
		}//EOC IF-ELSE
	}//EOC setId
	
	
	//MISC METHODS
	public String toString() {
		return super.toString() + "ID: " + this.id;
	}//EOC toString
	
	public boolean equals(Student xStudent) { //Using a student object and calling onto the person call works even if it's a larger into smaller data type because of polymorphism. 
		return xStudent != null 
			&& super.equals(xStudent)
			&& this.id == xStudent.getId();
	}//EOC equals
	
}//EOC Student

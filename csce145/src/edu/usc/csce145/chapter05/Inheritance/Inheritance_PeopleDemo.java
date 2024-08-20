package edu.usc.csce145.chapter05.Inheritance;

public class Inheritance_PeopleDemo {

	//NOTES
	//This demo is used to describe the Java principle of Polymorphism.
	//Polymorphism - having many forms.
	//The ideas of Inheritance and Polymorphism can be best drawn using a UML (Unified Modeling Language) diagram. THere is an example in today's lecture notes located in the Alg Dsgn Dashboard.

	/*UML DIAGRAM EXAMPLE NOTES:
	 * KEY:
	 * - = private
	 * + = public
	 * +/- IDENTIFIER: DATA TYPE/RETURN METHOD
	 * 
	 * 
	 * FOR Inheritance_Person
	 * - name: String
	 * - age: int
	 * --------------------
	 * + getName(): String
	 * + getAge(): int
	 * + setName(String xName): void
	 * + setAge(int xAge): void
	 * + writeOutput(): void
	 * 
	 * 
	 * For Inheritance_Student
	 * - studentID: int
	 * - major: String
	 * - gpa: double
	 * ---------------------
	 * + getStudentID(): int
	 * + getMajor(): String
	 * + getGpa(): double
	 * + setStudentID(int xID): void 
	 * + setMajor(String xMajor): void
	 * + setGpa(double xGpa): void
	 * + writeOutput(): void
	 */
			
	public static void main(String[] args) {
		
		//METHOD 1 - Default Method
		Inheritance_Undergraduate ug = new Inheritance_Undergraduate("John", 21, 1, "Computer Science", 2.5, "Data Science", 2);
		ug.writeOutput();
		
		//METHOD 2 - Array Method
		Inheritance_Person[] people = new Inheritance_Person[3];
		people[0] = new Inheritance_Person("Perry", 18);
		people[1] = new Inheritance_Student("Bella", 19, 2, "Civil Engineering", 3.2);
		people[2] = new Inheritance_Undergraduate("Aaron", 20, 3, "Physics", 3.5, "English", 2);
		//The compiler knows to call the proper method from the proper class based off the asignment given to the index above. Index 0 is assigned to the person class, Index 1 is assigned to the student class,
		//and index 2 is assigned to the undergraduate class. This way, it knows how to use the proper parameter constructors and write outputs for the print later.
		
		for(int i = 0; i < people.length; i ++) {
			people[i].writeOutput();
			System.out.println();
		}//EOC FOR
		
		
		
		System.exit(0); //Just making sure the program ends and doesn't take up CPU usage after the program is ran and finishes.
		
	}//EOC MAIN METHOD

}//EOC Inheritance_PeopleDemo

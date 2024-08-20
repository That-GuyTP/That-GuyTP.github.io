package InheritanceExample;

//THOMAS PETERSON

public class Tester {

	public static void main(String[] args) {
		
		//OBJECTS
		Person p = new Person();
		Student s = new Student();
		Undergraduate u = new Undergraduate();
		
		//PRINT OBJECTS
		System.out.println(p);
		System.out.println(s);
		System.out.println(u);
		
		//ARRAY SORT
		//This array is allowed because they are all child classes of the Person class. This displays the idea of Polymorphism
		Person[] people = new Person[3];
		people[0] = new Person("asdf");
		people[1] = new Student("asdf2", 4);
		people[2] = new Undergraduate("asdf3", 5, 2);
		
		//PRINT OBJECTS IN ARRAY
		for(int i = 0; i < people.length; i++) {
			System.out.println(people[i]);
		}//EOC FOR

	}//EOC MAIN METHOD

}//EOC Tester

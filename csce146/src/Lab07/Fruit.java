package Lab07;

//Thomas Peterson
//This is the fruit class of the Lab. This includes the required default constructor, parameter constructor, getters and setters, toString method, & compareTo method.
//On a side note it still bothers me that tomatos are fruits.

public class Fruit implements Comparable<Fruit>{
	
	//INSTANCE VARIABLES
	public String fruit;
	public double weight;
	
	//DEFAULT CONS
	public Fruit() {
		this.fruit = "apple";
		this.weight = 1.0;
	}///EOC DEF CONS
	
	//PARAMETER CONS
	public Fruit(String xFruit, double xWeight) {
		setFruit(xFruit);
		setWeight(xWeight);
	}//EOC PARA CONS
	
	//GETTERS AND SETTERS
	public String getFruit() {
		return this.fruit;
	}//EOC getFruit

	public void setFruit(String xFruit) {
		if(xFruit.equalsIgnoreCase("apple") || xFruit.equalsIgnoreCase("orange") || xFruit.equalsIgnoreCase("banana") || xFruit.equalsIgnoreCase("kiwi") || xFruit.equalsIgnoreCase("tomato")) {
			this.fruit = xFruit;
		}else {
			this.fruit = "apple";
		}//EOC IF-ELSE
	}//EOC setFruit

	public double getWeight() {
		return this.weight;
	}//getWeight

	public void setWeight(double xWeight) {
		if(xWeight > 0) {
			this.weight = xWeight;
		}else {
			this.weight = 1.0;
		}//EOC IF-ELSE
	}//EOC setWeight
	
	//TOSTRING METHOD
	public String toString() {
		return "Type: " + this.fruit + " Weight: " + this.weight;
	}//EOC toString
	
	//COMPARETO METHOD
	public int compareTo(Fruit o) {
		if(o == null) {
			return -1;
		}else if(this.weight > o.weight) {
			return 1;
		}else if(this.weight < o.weight) {
			return -1;
		}else {
			return this.fruit.compareTo(o.fruit);
		}//EOC IF-ELSEIF-ELSEIF-ELSE
	}//EOC compareTo

}//EOC Fruit
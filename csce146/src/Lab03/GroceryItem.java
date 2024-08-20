package Lab03;

//THOMAS PETERSON

public class GroceryItem {

	//INSTANCE VARIABLES
	private String name;
	private double value;
	
	
	//DEFAULT CONSTRUCTOR
	public GroceryItem() {
		this.name = "none";
		this.value = 0;
	}//EOC DEFAULT CONSTRUCTOR
	
	
	//PARAMETER CONSTRUCTOR
	public GroceryItem(String xName, double xValue) {
		this.setName(xName);
		this.setValue(xValue);
	}//EOC PARAMETER CONSTRUCTOR

	
	//ACCESSORS AND MUTATORS
	public String getName() {
		return this.name;
	}//EOC getName

	public void setName(String xName) {
		if(xName != null) {
			this.name = xName;
		}else {
			this.name = "none";
		}//EOC IF-ELSE
	}//EOC setName

	public double getValue() {
		return this.value;
	}//EOC getValue

	public void setValue(double xValue) {
		if(xValue >= 0) {
			this.value = xValue;
		}else {
			this.value = 0;
		}//EOC IF-ELSE
	}//EOC setValue
	
	
	//TOSTRING METHOD
	public String toString() {
		return "Grocery Item Name: " + this.getName() + " Value: " + this.getValue();
	}//EOC toString
	
	
	//EQUALS METHOD
	public boolean equals(GroceryItem g) {
		return g != null
				&& this.name.equalsIgnoreCase(g.getName())
				&& this.value == g.getValue();
	}//EOC equals
	
	
}//EOC GroceryItem

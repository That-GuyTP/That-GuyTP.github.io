package TacoLL;

public class Taco {

	private String name;
	private String location;
	private double price;
	
	public Taco() {
		this.name = this.location = "none";
		this.price = 0.0;
	}//EOC DEFAULT CONSTRUCTOR
	
	public Taco(String xName, String xLocation, double xPrice) {
		this.setName(xName);
		this.setLocation(xLocation);
		this.setPrice(xPrice);
	}

	//ACCESSORS AND MUTATORS
	public String getName() {
		return this.name;
	}

	public void setName(String xName) {
		if(xName != null)
			this.name = xName;
		else
			this.name = "None";
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String xLocation) {
		if(xLocation != null)
			this.location = xLocation;
		else
			this.location = "None";
	}	

	public double getPrice() {
		return price;
	}

	public void setPrice(double xPrice) {
		if(xPrice > 0.00)
			this.price = xPrice;
		else
			this.price = 0.00;
	}
	
	//MISC METHODS
	public String toString() {
		return "Name: " + this.name + " Location: " + this.location + " Price: " + this.price;
	}
	
	public boolean equals(Taco xTaco) {
		return xTaco != null 
				&& this.name.equals(xTaco.getName())
				&& this.location.equals(xTaco.getLocation())
				&& this.price == xTaco.getPrice();
	}
		
	
}//EOC Taco

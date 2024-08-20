package edu.usc.csce145.chapter05.PurchaseProject;

public class PurchaseProject_Product {
	
	//INSTANCE VARIABLES
	private String itemName;
	private double price;
	private int quantity;

	
	//DEFAULT CONSTRUCTOR
	public PurchaseProject_Product() {
		this.itemName = "no name yet";
		this.price = 0;
		this.quantity = 0;
	}//EOC DEFAULT CONSTRUCTOR
	
	
	//PARAMETERIZED CONSTRUCTOR
	//Keep in mind, this constructor will appear invalid until you have created the "setItemName" methods below.
	public PurchaseProject_Product(String xItemName, double xPrice, int xQuantity) {
		this.setItemName(xItemName);
		this.setPrice(xPrice);
		this.setQuantity(xQuantity);
	}//EOC PARAMETERIZED CONSTRUCTOR
	
	
	//COPY CONSTRUCTOR
	//Keep in mind, this contructor will appear invalid until you have created the "setItemName" and "getItemName" methods below.
	//This is used to copy the values of one object and paste it onto another. In example, if you have set values for object 1 you can then create an object 2, and set the values of object 1 to object 2.
	public PurchaseProject_Product(PurchaseProject_Product p){
		this.setItemName(p.getItemName());
		this.setPrice(p.getPrice());
		this.setQuantity(p.getQuantity());
	}//EOC COPY CONSTRUCTOR
	
	
	//MUTATOR METHODS
	public void setItemName(String xItemName) {
		this.itemName = xItemName;
	}//EOC setItemName
	
	public void setPrice(double xPrice) {
		if(xPrice > 0) {
			this.price = xPrice;
		}else {
			System.out.println("Invalid value for item price!");
		}//EOC IF-ELSE
	}//EOC setPrice
	
	public void setQuantity(int xQuantity) {
		if(xQuantity > 0) {
			this.quantity = xQuantity;
		}else {
			System.out.println("Error. Invalid quantity for product");
		}//EOC IF-ELSE
	}//EOC setQuantity
	
	
	//ACCESSOR METHODS
	public String getItemName() {
		return this.itemName;
	}//EOC getItemName

	public double getPrice() {
		return this.price;
	}//EOC getPrice
	
	public int getQuantity() {
		return this.quantity;
	}//EOC getQuantity
	
	
	//EQUALS METHOD
	//This method will be used to compare the values of 2 separate objects.
	public boolean equals(PurchaseProject_Product p) {
		return this.itemName.equalsIgnoreCase(p.getItemName()) 
			&& this.price == p.getPrice()
			&& this.quantity == p.getQuantity();
	}//EOC EQUALS METHOD
	
	
	//PRINT METHOD
	public String toString() {
		return "Product Name: " + this.itemName
				+ "\nPrice: " + this.price
				+ "\nQuantity: " + this.quantity;
	}//EOC PRINT METHOD
	
	
	//TOTAL COST METHOD
	public double totalCost() {
		return this.price * this.quantity;
	}//EOC TOTAL COST METHOD
	
	
	//OVERLOADING METHOD
	//When overloading a method it needs to be the same name of another method. The only difference is the parameters of the method, which allow us to have two methods with the same name.
	public double totalCost(int xQuantity) {
		if(xQuantity > 0) {
			return this.price * xQuantity;
		}else {
			return 0;
		}//EOC IF-ELSE
	}//EOC OVERLOAD METHOD
	
	
	//OVERLOADING METHOD 2
	//This overload version is being used for parameters being set in the PurchaseProject_ProductFrontEnd class for a new price and amount.
	public double totalCost(double xPrice, int xQuantity) {
		if(xPrice > 0 && xQuantity > 0) {
			return xPrice * xQuantity;
		}else {
			return 0;
		}//EOC IF-ELSE
	}//EOC OVERLOADING METHOD 2
	
	
}//EOC PurchaseProdject_Product

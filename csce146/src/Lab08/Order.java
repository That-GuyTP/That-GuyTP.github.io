package Lab08;

//Thomas Peterson

public class Order implements Comparable<Order>{

	//INSTANCE VARIABLES
	public String customer;
	public String foodOrder;
	public int cookingTime;
	public int arrivalTime;
	public int cookingTimeLeft;
	
	//DEFAULT CONSTRUCTOR
	public Order() {
		this.customer = "none";
		this.foodOrder = "none";
		this.cookingTime = 1;
		this.arrivalTime = 0;
		this.cookingTimeLeft = this.cookingTime;
	}//EOC DEF CON
	
	//PARAMETER CONSTRUCTOR
	public Order(String xCustomer, String xFoodOrder, int xCookingTime, int xArrivalTime) {
		setCustomer(xCustomer);
		setFoodOrder(xFoodOrder);
		setCookingTime(xCookingTime);
		setArrivalTime(xArrivalTime);
		setCookingTimeLeft(xCookingTime);
	}//EOC PARA CON

	//GETTERS & SETTERS
	public String getCustomer() {
		return this.customer;
	}//EOC getCustomer

	public void setCustomer(String xCustomer) {
		if(xCustomer != null) {
			this.customer = xCustomer;
		}else {
			this.customer = "none";
		}//EOC I-E
	}//EOC setCustomer

	public String getCurrentOrder() {
		return this.foodOrder;
	}//EOC getFoodOrder

	public void setFoodOrder(String xFoodOrder) {
		if(xFoodOrder != null) {
			this.foodOrder = xFoodOrder;
		}else {
			this.foodOrder = "none";
		}//EOC I-E
	}//EOC setFoodOrder

	public int getCookingTime() {
		return this.cookingTime;
	}//EOC getCookingTime

	public void setCookingTime(int xCookingTime) {
		if(xCookingTime >= 1) {
			this.cookingTime = xCookingTime;
		}else {
			this.cookingTime = 1;
		}//EOC I-E
	}//EOC xCookingTime

	public int getArrivalTime() {
		return this.arrivalTime;
	}//EOC getArrivalTime

	public void setArrivalTime(int xArrivalTime) {
		if(xArrivalTime >= 0) {
			this.arrivalTime = xArrivalTime;
		}else {
			this.arrivalTime = 0;
		}//EOC I-E
	}//EOC setArrivalTime

	public int getCookingTimeLeft() {
		return this.cookingTimeLeft;
	}//EOC getCookingTimeLeft

	public void setCookingTimeLeft(int xCookingTimeLeft) {
		if(xCookingTimeLeft >= 0 ) {
			this.cookingTimeLeft = xCookingTimeLeft;
		}else {
			this.cookingTimeLeft = cookingTime;
		}//EOC I-E
	}//EOC setCookingTimeLeft
	
	//TOSTRING METHOD
	public String toString() {
		return "Customer: " + this.customer + ", Order: " + this.foodOrder + ", Cooking Time Left: " + this.cookingTimeLeft;
	}//EOC toString
	
	//COMPARETO METHOD
	public int compareTo(Order o) {
		if(o == null) {
			return -1;
		}else if(this.cookingTime > o.cookingTime) {
			return 1;
		}else if(this.cookingTime < o.cookingTime) {
			return -1;
		}else {
			return 0;
		}//EOC I-EI-EI-E
	}//EOC compareTo
	
	//COOKFORONEMINUTE METHOD
	public void cookForOneMinute() {
		if(this.cookingTimeLeft > 0) {
			this.cookingTimeLeft--;
		}//EOC IF
	}//EOC cookForOneMInute
	
	//ISDONE METHOD
	public boolean isDone() {
		return this.cookingTimeLeft == 0;
	}//EOC isDone
	
}//EOC Order

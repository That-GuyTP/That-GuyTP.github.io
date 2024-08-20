package Homework07;

//Thomas Peterson
//This class is used to define the properties of a "Sheep" object.

public class Sheep implements Comparable<Sheep>{
	
	//INSTANCE VARIABLES
	public String name;
	public int shearingTime;
	public int arrivalTime;
	
	//DEFAULT CONSTRUCTOR
	public Sheep() {
		this.name = "none";
		this.shearingTime = 1;
		this.arrivalTime = 1;
	}//EOC DEF CON
	
	//PARAMETER CONSTRUCTOR
	public Sheep(String xName, int xSTime, int xATime) {
		setName(xName);
		setShearingTime(xSTime);
		setArrivalTime(xATime);
	}//EOC PARA CON

	//GETTERS & SETTERS
	public String getName() {
		return this.name;
	}//EOC getName

	public void setName(String xName) {
		if(xName != null) {
			this.name = xName;
		}else {
			this.name = "none";
		}//EOC I-E
	}//EOC setName

	public int getShearingTime() {
		return this.shearingTime;
	}//EOC getSHearingTime

	public void setShearingTime(int xSTime) {
		if(xSTime >= 1) {
			this.shearingTime = xSTime;
		}else {
			this.shearingTime = 1;
		}//EOC I-E
	}//EOC setShearingTime

	public int getArrivalTime() {
		return this.arrivalTime;
	}//EOC getArrivalTime

	public void setArrivalTime(int xATime) {
		if(xATime >= 1) {
			this.arrivalTime = xATime;
		}else {
			this.arrivalTime = 0;
		}//EOC I-E
	}//EOC setArrivalTime
	
	
	//COMPARETO METHOD
	//This method has been adjusted to reflect the required "sort by shear time & names alphabetically".
	public int compareTo(Sheep o) {
		if(o == null) {
			return -1;
		}else if(this.shearingTime > o.shearingTime) {
			return 1;
		}else if(this.shearingTime < o.shearingTime) {
			return -1;
		}else {
			return this.name.compareTo(o.name);//If shearing times are compared and sorted as needed, this compares the name of each sheep and sorts it by that.
		}//EOC IF-ELSEIF-ELSEIF-ELSE
	}//EOC compareTo
	
	
	//TOSTRING METHOD
	public String toString() {
		return "Name: " + this.name + ", Sheer Time: " + this.shearingTime + ", ArrivalTime: " + this.arrivalTime;
	}//EOC toString
	
}//EOC Sheep

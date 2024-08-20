package edu.usc.csce145.chapter05.copy;

//Thomas Peterson

public class TimeConverter {

	//INSTANCE VARIABLES
	private int hours;
	private int minutes;
	private int seconds;
	private boolean notPM;
	
	
	//DEFAULT CONSTRUCTOR
	public TimeConverter() {
		this.hours = 0;
		this.minutes = 0;
		this.seconds = 0;
		this.notPM = true;
	}//EOC DEFAULT CONSTRUCTOR
	
	
	//PARAMETERIZED COSNTRUCTOR
	public TimeConverter(int xHours, int xMinutes, int xSeconds) {
		this.setHours(xHours);
		this.setMinutes(xMinutes);
		this.setSeconds(xSeconds);
	}//EOC PARAMETER CONSTRUCTOR
	
	
	//ACCESSOR METHODS
	public int getHours() {
		return this.hours;
	}//EOC getHours
	
	public int getMinutes() {
		return this.minutes;
	}//EOC getMinutes
	
	public int getSeconds() {
		return this.seconds;
	}//EOC getSeconds
	
	public boolean getNotPM() {
		return this.notPM;
	}//EOC getNotPM
	
	
	//MUTATOR METHODS
	public void setHours(int xHours) {
		
		//VALID INPUT CHECK
		if(xHours >= 0 & xHours <= 23) {
			this.hours = xHours;
		}else {
			System.out.println("Sorry, that isn't a valid amount of hours.");
		}//EOC IF-ELSE
		
		//CONVERSION TO 12
		if(this.hours > 12) {
			this.hours = this.hours - 12;
			this.notPM = false;
		}//EOC IF
		
	}//EOC setHours
	
	public void setMinutes(int xMinutes) {
		if(xMinutes >= 0 & xMinutes <= 59) {
			this.minutes = xMinutes;
		}else {
			System.out.println("Sorry, that isn't a valid amount of minutes.");
		}//EOC IF-ELSE
	}//EOC setMinutes
	
	public void setSeconds(int xSeconds) {
		if(xSeconds >= 0 && xSeconds <= 59) {
			this.seconds = xSeconds;
		}else {
			System.out.println("Sorry, that isn't a valid amount of seconds.");
		}//EOC IF-ELSE
	}//EOC setSeconds
	
	
	//updateTime METHOD
	public void updateTime(int xHours, int xMinutes, int xSeconds) {
		if(xHours > 12) {
			this.hours = (xHours - 12);
			this.notPM = false;
		}//EOC IF
	}//EOC updateTime
	
	
	//OVERLOAD METHOD
	public void updateTime(String HR_MN_SS){
		this.setHours(Integer.parseInt(HR_MN_SS.substring(0,2)));
		this.setMinutes(Integer.parseInt(HR_MN_SS.substring(3,5)));
		this.setSeconds(Integer.parseInt(HR_MN_SS.substring(6,8)));
	}//EOC OVERLOAD updateTime
	
	
	//displayTime METHOD
	public String displayTime() {
		return "Time entered convered to 12 hour time ---> " + this.hours + ":" + this.minutes + ":" + this.seconds;
	}//EOC displayTime
	
}//EOC TimeConverter

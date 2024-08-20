package edu.usc.csce145.chapter06.BookStore;

public class BookStore_Book {

	//INSTANCE VARIABLES
	private String title;
	private String author;
	private double price;
	
	
	//DEFAULT CONSTUCTOR
	public BookStore_Book() {
		this.title = "unknown";
		this.author = "anonymous";
		this.price = 1;
	}//EOC DEFAULT CONSTRUCTOR
	
	
	//PARAMETER CONSTRUCTOR
	public BookStore_Book(String xTitle, String xAuthor, double xPrice) {
		this.setTitle(xTitle);
		this.setAuthor(xAuthor);
		this.setPrice(xPrice);
	}//EOC PARAMETER
	
	
	//ACCESSOR METHODS
	public String getTitle() {
		return this.title;
	}//EOC getTitle
	
	public String getAuthor() {
		return this.author;
	}//EOC getAuthor
	
	public double getPrice() {
		return this.price;
	}//EOC getPrice
			
	
	//MUTATOR METHODS
	public void setTitle(String xTitle) {
		this.title = xTitle;
	}//EOC setTitle
	
	public void setAuthor(String xAuthor) {
		this.author = xAuthor;
	}//EOC setAuthor
	
	public void setPrice(double xPrice) {
		if(xPrice > 0) {
			this.price = xPrice;
		}else {
			System.out.println("Sorry that isn't a valid price");
		}//EOC IF-ELSE
	}//EOC setPrice
		
	
	//MISC METHODS
	public boolean equals(BookStore_Book bk) {
		return this.title.equalsIgnoreCase(bk.getTitle()) 
			&& this.author.equalsIgnoreCase(bk.getAuthor())
			&& this.price == bk.getPrice();	
	}//EOC EQUALS METHOD
	
	public String toString() {
		return "Title: " + this.title
			 + "\nAuthor: " + this.author
			 + "\nPrice: $" + this.price;
	}//EOC toString
	
	
}//EOC BookStore_Book

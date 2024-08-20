package edu.usc.csce145.chapter06.BookStore;

public class BookStore_BookCase {

	//NOTES
	//We made a change to the toString Method by adding "[i]" next to "books" in line 63.
	
	//INSTANCE VARIABLES
	private BookStore_Book[] books; //Since BookStore_Book is made of 3 different kinds of data types, it has to be delcared plain without a data type tag.
	public static final int MAX_BOOKS = 50;
	
	
	//DEFAULT CONSTRUCTOR
	public BookStore_BookCase() {
		books = new BookStore_Book[MAX_BOOKS];
	}//EOC DEFAULT CONSTRUCTOR
	
	
	//BOOKCASE SIZE CONSTRUCTOR
	public BookStore_BookCase(int count) {
		if(count > 0) {
			books = new BookStore_Book[count];
		}//EOC IF
	}//EOC PARAMETER CONSTRUCTOR
	
	
	//ACCESSOR METHODS
	public BookStore_Book[] getBooks() {
		return this.books;
	}//EOC ACCESSOR METHODS
	
	
	//INSERTBOOK METHOD
	public void insertBook(String xTitle, String xAuthor, double xPrice) {
		for(int i = 0; i < books.length; i++) {
			if(books[i] == null) { //This if loop used to add the new book to the end of the current ammount of books on the bookcase.
				books[i] = new BookStore_Book(xTitle, xAuthor, xPrice);
				break; //Used to stop the loop from continuing on past the newly added book's spot in the array. Prevents overrunning and duplication.
			}else if((i + 1) == books.length) { //Added incase the book that is trying to be added would cause the 50 capacity to run over.
				System.out.println("Bookcase is full! Cannot insert new books!");
			}//EOC INNER IF-ELSE
		}//EOC IF
	}//EOC insertBook
	
	
	//REMOVEBOOK METHOD
	public void removeBook(BookStore_Book bk) {
		for(int i = 1; i < books.length; i ++) {
			if(books[i] != null && books[i].equals(bk) == true) { //Checking to see if the book is there in the array in the first place. If it isn't there then the loop won't work.
				books[i] = null; //If the book is there it "takes the book off the shelf" or sets the value in that array position to null.
				break;
			}else if((i + 1) == books.length) {
				System.out.println("Book not found in bookcase. Cannot remove the book.");
			}//EOC IF-ELSE IF
		}//EOC FOR
	}//EOC removeBook
	
	
	//TOSTRING METHOD
	public String toString() {
		String result = "";
		for(int i = 0; i < books.length; i++) {
			result += books[i].toString() + "\n";
		}//EOC FOR
		return result;
	}//EOC toString
	
	
	//EQUALS METHOD
	public boolean equals(BookStore_BookCase b) { //"b" is a new bookcase. It is not the original one with our books on it. We are comparing the books on each.
		for(int i = 0; i < books.length; i++) {
			if(books[i] == null && b.getBooks()[i] != null && books[i].equals(b.getBooks()[i]) == false) {
				return false;
			}else {
				return true;
			}//EOC IF-ELSE
		}//EOC FOR
		return true;
	}//EOC equals
	
	
}//EOC BookStore_BookCase

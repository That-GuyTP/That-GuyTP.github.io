package edu.usc.csce145.chapter06.BookStore;

import java.util.Scanner;

public class BookStore_BooksDatabase {

	//NOTES
	//Today (11/30/2023) We changed:
	/*Case 2
	 	*Added the details of the case. 	
	 *Case 3
	 	*We added a "break;"
	 */
	
	public static void main(String[] args) {
		
		//SCANNER
		Scanner kb;
		kb = new Scanner(System.in);
		
		//VARIABLES
		boolean quit = false;
		
		//INTRO
		BookStore_BookCase bk = new BookStore_BookCase();
		System.out.println("Welcome to the Books Database!!!");
		while(quit == false) {
			System.out.println();
			System.out.println("Enter 1 to insert a book. "
							 + "\nEnter 2 to remove a book. "
							 + "\nEnter 3 to view to BookCase. "
							 + "\nEnter 4 to exit");
			int choice = kb.nextInt();
			kb.nextLine();//Clears the rest of the line in order for the scanner to read properly.
			
			//SWITCH
			switch(choice) {
			case 1:
				System.out.println("What is the title of the book?");
				String title = kb.nextLine();
				System.out.println("Who is the author");
				String author = kb.nextLine();
				System.out.println("Enter the price of the book:");
				double price = kb.nextDouble();
				bk.insertBook(title, author, price); //Adding the book to the bookcase. Calls on the insertBook method from BookStore_BookCase.
				break;
			case 2:
				System.out.println("Enter the title of the book: ");
				title = kb.nextLine();
				System.out.println("Enter the author of the book:");
				author = kb.nextLine();
				System.out.println("Enter the price of the book:");
				price = kb.nextInt();
				bk.removeBook(new BookStore_Book(title, author, price));
				break;
			case 3:
				System.out.println(bk.toString());
				break;
			case 4:
				quit = true;
				break;
			default:
				System.out.println("Sorry, an error has occured. Invalid input entered. Closing Program.");
			}//EOC switch
		}//EOC WHILE
		
		kb.close();
		
	}//EOC MAIN METHOD
	
}//EOC BookStore_BooksDatabase

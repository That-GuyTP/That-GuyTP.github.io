package edu.usc.csce145.chapter05.copy;

import java.util.Scanner;

public class ConcertPromoter {

	public static void main(String[] args) {

		Scanner key = new Scanner(System.in);
		Concert concert = new Concert();
		System.out.println("Welcome to the Concert Promotion tool!");
		String input = "";

		while(input.equalsIgnoreCase("quit")!= true){
			System.out.println("Currently the concert featuring the band: "+concert.getBandName());
			System.out.println("Has sold "+concert.getNumOfTicketsByPhone()+" tickets by phone");
			System.out.println("Has sold "+concert.getNumOfTicketsAtVenue()+" tickets at the venue");
			System.out.println("And has grossed $"+concert.totalSales());
			
			System.out.println("What would you like to do?\n" +
					"Enter 1: To change name\n" +
					"Enter 2: To change ticket by phone price\n" +
					"Enter 3: To change ticket at venue price\n" +
					"Enter 4: To add tickets by phone\n" +
					"Enter 5: To add tickets at the venue\n" +
					"Enter 6: To find out how many tickets are remaining\n" +
					"Enter 7: To find out how many total tickets have been sold\n" +
					"Enter 8: To change the venue's capacity\n" +
					"Enter 9: To start a new concert\n" +
					"Enter 0: To Quit\n");
			int choice = key.nextInt();
			key.nextLine();

			switch(choice)
			{
			case 1:
				System.out.println("Enter the name of the band");
				concert.setBandName(key.nextLine());
				break;
			case 2:
				System.out.println("Enter the new price by phone");
				concert.setPriceByPhone(key.nextDouble());
				break;
			case 3:
				System.out.println("Enter the new price at the venue");
				concert.setNumberOfTicketsAtVenue(choice);
				break;
			case 4:
				System.out.println("Enter a number of tickets to add by phone");
				concert.buyTicketsByPhone(key.nextInt());
				break;
			case 5:
				System.out.println("Enter a number of tickets to add at the venue");
				concert.buyTicketsAtVenue(key.nextInt());
				break;
			case 6:
				System.out.println("The number of tickets remaining are "+concert.ticketsRemaining());
				break;
			case 7:
				System.out.println("The number of tickets sold are "+concert.totalNumberOfTicketsSold());
				break;
			case 8:
				System.out.println("Enter the new capacity");
				concert.setCapacity(key.nextInt());
				break;
			case 9:
				System.out.println("Starting a new concert");
				System.out.println("Enter the name of the band");
				String name = key.nextLine();
				System.out.println("Enter the capacity of the venue");
				int capacity = key.nextInt();
				System.out.println("Enter the price by phone");
				double priceByPhone = key.nextDouble();
				System.out.println("Enter the price at the venue");
				double priceAtVenue = key.nextDouble();
				concert = new Concert(name,capacity,priceByPhone,priceAtVenue);				
				break;
			case 0:
				input = "quit";
				break;
				}//EOC SWITCH
			
		}//EOC WHILE
		
		key.close();
		
	}//EOC MAIN METHOD
	
}//EOC ConcertPromoter

package edu.usc.csce145.chapter06.HW07;

//Thomas Peterson

import java.util.Scanner;

public class ShipNavigator {

	public static void main(String args[]) {
		
		//VARIABLES
		String shipName;
		String launchDate;
		String typeOfShip;
		int passangerCapacity;
		int numberOfCrewMembers;
		double tonnage;
		double maxSpeed;
		String another = "Yes";
		
		//OBJECTS
		int i = 15;
		Ship[] Ships = new Ship[i];
		
		//SCANNER
		Scanner kb;
		kb = new Scanner(System.in);
		
	//PROGRAM START
		System.out.println("Welcome to the Ship Navigator program.");
		while(another.equalsIgnoreCase("Yes")) {
			System.out.println("Please enter the name of the ship:");
			shipName = kb.nextLine();
			System.out.println("Please enter the launch date of the ship (MM/DD/YYYY)");
			launchDate = kb.nextLine();
			
		//TYPE OF SHIP
			System.out.println("Enter whether it is a cargo or cruise ship");
			typeOfShip = kb.nextLine();
			//CARGO
			if (typeOfShip.equalsIgnoreCase("Cargo")) {
				System.out.println("Please enter the tonnage (DMT):");
				tonnage = kb.nextDouble();
				kb.nextLine();
				System.out.println("Please enter the max speed (MPH): ");
				maxSpeed = kb.nextDouble();
				kb.nextLine();
				Ships[i-1] = new CargoShip(shipName, launchDate, tonnage, maxSpeed);
			//CRUISE
			}else if(typeOfShip.equalsIgnoreCase("Cruise")) {
				System.out.println("Please enter the passenger capacity: ");
				passangerCapacity = kb.nextInt();
				kb.nextLine();
				System.out.println("Please enter the number of crew members: ");
				numberOfCrewMembers = kb.nextInt();
				kb.nextLine();
				Ships[i-1] = new CruiseShip(shipName, launchDate, passangerCapacity, numberOfCrewMembers);
			//WRONG INPUT
			}else {
				System.out.println("Sorry, there has been an error. Goodbye");
				System.exit(0);
			}//EOC IF-ELSE_IF-ELSE
			
		//PRINTING
			String result = "";
			for(int j = 0; j < Ships.length; j++) {
				result += Ships.toString() + "\n";
			}//FOR
			System.out.print(result);
			kb.nextLine();
			
			//CONTINUE ASK
			System.out.println("Would you like to add another ship (Yes or No)?");
			another = kb.nextLine();
			if(another.equalsIgnoreCase("Yes")) {
				another = "Yes";
				i++;
			}else if(another.equalsIgnoreCase("No")) {
				another = "n";
			}else {
				System.out.println("Sorry, that was an invalid input. Ending program");
				another = "n";
			}//EOC IF-ELSE_IF-ELSE
		}//EOC WHILE
		
		System.out.println("Goodbye");
		kb.close();
		
		
	}//EOC MAIN METHOD
	
}//EOC ShipNavigator

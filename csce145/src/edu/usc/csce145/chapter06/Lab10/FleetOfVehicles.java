package edu.usc.csce145.chapter06.Lab10;

import java.util.Scanner;

	//A class that keeps a fleet of different types of vehicles
	public class FleetOfVehicles {
		//An array of vehicles
		private Vehicle[] fleet;
		public static final int MAX_VEHICLES = 100;
		public FleetOfVehicles()
		{
			fleet = new Vehicle[MAX_VEHICLES];
		}
		public Vehicle[] getFleet()
		{
			return this.fleet;
		}
		//Adds a new vehicle to the first empty spot in the fleet array
		public void addVehicle(Vehicle aV)
		{
			for(int i=0;i<fleet.length;i++)
			{
				if(fleet[i] == null)
				{
					fleet[i] = aV;
					return;
				}
			}
			//If it reaches here the array is full
			System.out.println("The fleet of vehicles is full!");
		}
		//Removes a vehicle.  It searches through the array for a vehicle that is equal to
		//the parameter and once it is found that value is set to null
		public void removeVehicle(Vehicle aV)
		{
			for(int i=0;i<fleet.length;i++)
			{
				if(fleet[i] != null && fleet[i].equals(aV))
				{
					fleet[i] = null;
					return;
				}
			}
			//If it reaches here then the vehicle was not found
			System.out.println("The vehicle was not found");
		}
		
		//A static key to be used throughout the frontend
		static Scanner key;
		//Entry point of the program
		public static void main(String[] args)
		{
			key = new Scanner(System.in);//Construct the key
			
			System.out.println("Welcome to the fleet manager");
			FleetOfVehicles fOfV = new FleetOfVehicles();//Creates a new instance of the FleetOfVehicles to be used
			
			boolean quit = false;
			while(!quit)//Runs until the user quits
			{
				printOptions();
				int pick = key.nextInt();
				key.nextLine();
				switch(pick)
				{
				case 1: //Add vehicle
					fOfV.addVehicle(makeAVehicleDialog());
					break;
				case 2: //Remove vehicle
					fOfV.removeVehicle(makeAVehicleDialog());
					break;
				case 9:
					quit = true;
					break;
				default:
					System.out.println("Invalid input");
				}
				System.out.println("The Fleet currently");
				printFleet(fOfV);
			}
			System.out.println("Goodbye!");
		}
		//Displays the options to the user
		public static void printOptions()
		{
			System.out.println("Enter 1: to add a Vehicle\nEnter 2: to remove a Vehicle\nEnter 9 to quit");
		}
		//Returns an instance of a vehicle based on user input
		public static Vehicle makeAVehicleDialog()
		{
			Vehicle retV;
			int pick = 0;
			System.out.println("Enter 1: if it is a car\nEnter 2: if it is a truck\nEnter 3: if it is unclassified");
			pick = key.nextInt();
			key.nextLine();
			while(pick != 1 && pick != 2 && pick != 3)
			{
				System.out.println("Invalid choice pick again");
				pick = key.nextInt();
				key.nextLine();
			}
			System.out.println("Enter the manufacturer's name");
			String manuName = key.nextLine();
			System.out.println("Enter the number of cylinders");
			int cylinders = key.nextInt();
			key.nextLine();
			System.out.println("Enter the owner's name");
			String ownersName = key.nextLine();
			
			switch(pick)
			{
			case 1://Constructing a car
				System.out.println("Enter the car's gas mileage");
				double mileage = key.nextDouble();
				key.nextLine();
				System.out.println("Enter the number of passengers");
				int passengers = key.nextInt();
				key.nextLine();
				retV = new Car(manuName,cylinders,ownersName,mileage,passengers);
				break;
			case 2://Constructing a truck
				System.out.println("Enter the truck's load capacity");
				double loadCap = key.nextDouble();
				key.nextLine();
				System.out.println("Enter the truck's towing capacity");
				double towCap = key.nextDouble();
				key.nextLine();
				retV = new Truck(manuName,cylinders,ownersName,loadCap,towCap);
				break;
			default:
				retV = new Vehicle(manuName,cylinders,ownersName);
			}
			
			return retV;
		}
		public static void printFleet(FleetOfVehicles fV)
		{
			for(Vehicle v : fV.getFleet())
			{
				if(v == null)
					continue;
				System.out.println(v);
				System.out.println();
			}
		}
	}
	

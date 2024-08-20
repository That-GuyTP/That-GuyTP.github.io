package edu.mtc.egr281.chapter05;

//Thomas Peterson
//Project09
//10-27-2022
//10-27-2022
//In this project I will create a program that calculates the amount of money it cost a trucker to drive to his locations.

//ALGORITHM
//Figure out what he wants from me
//Set up String and int arrays in order to have the values needed for the project
//Set up my scanner(kb) and any constants/integers needed for calculations and data retrieval
//Create a small Prompt asking for the user's start city and amount of cities traveled.
//Output total mileage, reimbursement amount, cities visited, and cost for heading straight home.

import java.util.Scanner;
import java.text.DecimalFormat;

public class Project09 {
	
	//ARRAYS
		public static int[] cityNamesValues = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		
		public static String[] cityNames = {"Boston", "Chicago", "Dallas", "Reno", "Los Angeles", "Miami", "New Oreleans", "Toronto", "Vancouver", "Wash DC"};
//Use kb.next(); for the first word in the entry
//Use kb.nextLine(); for the enter line but an added /n
//Could turn the city names into numbers and instruct the user to use numbers.
									 
		public static int[][] mileValues = { {0, 1004, 1753, 2752, 3017, 1520, 1507, 609, 3155, 488},
											 {1004, 0, 921, 1780, 2048, 1397, 919, 515, 2176, 709},
											 {1753, 921, 0, 1230, 1399, 1343, 517, 1435, 2234, 1307},
											 {2752, 1780, 1230, 0, 272, 2570, 1732, 2251, 1322, 2420},
											 {3017, 2048, 1399, 272, 0, 2716, 1858, 2523, 1278, 2646},
											 {1520, 1397, 1343, 2570, 2716, 0, 860, 1494, 3447, 1057},
											 {1507, 919, 517, 1732, 1858, 860, 0, 1307, 2734, 1099},
											 {609, 515, 1435, 2251, 2523, 1494, 1307, 0, 2820, 571},
											 {3155, 2176, 2234, 1322, 1278, 3447, 2734, 2820, 0, 2887},
											 {448, 709, 1307, 2420, 2646, 1057, 1099, 571, 2887, 0} };
	
	public static final double MILEAGE_RATE = .585;
	
	//private static boolean find(String word) {
	//	for(int i = 0; i < cityNames.length; ++i) {
	//		if(word.equals(cityNames[i]))
	//			return true;
	//	}
	//	return false;
	//}//EOC method findCityName
	//DOING TOO MUCH. NOT REQUIRED TO MAKE A RETURN VALUE.
	
	public static void main(String[] args) {
		//SCANNERS && INTS
			Scanner kb = new Scanner(System.in);
			int row = 0;
			int column = 0;
			int totalCities = 0;
			int cityValue = 0;
			int startRow = 0;
			int endColumn = 0;
			int totalGasMiles = 0;
			int homeGasMiles = 0;
			int startCity, currentCity, nextCity, endCity;
			double cost = 0;
			
			
		//DECIMAL FORMAT
			DecimalFormat cash = new DecimalFormat("#.00");
			
		//WELCOME
			System.out.print("Welcome to TMRcalculator. Please use this program to enter the cities you've traveled to. ");
			System.out.println("This will allow the company to reimburse you for your gas miles.");
			System.out.println("We reimburse travel to Boston[0], Chicago[1], Dallas[2], Reno[3], Los Angeles[4], Miami[5], New Oreleans[6], Toronto[7], Vancouver[8] & Wash DC[9] only!");
			System.out.println("City names are tied to the number inside the brakets to the right of the word. Please use that number if you wish to input that city. Only input 1 city per prompt.");
		
		//INPUT
			//PROMPT
				//START CITY
				System.out.print("Please enter the city you started in: ");
				currentCity = kb.nextInt();
				
				//TOTAL CITIES
				System.out.print("How many cities have you traveled to? ");
				totalCities = kb.nextInt();
				
		//PROCESS
			for(int j = 0; j < totalCities; ++j) {	
				//START CITY
				if(j == 0) {
					startRow = currentCity;
				}//EOC startCity
				
				System.out.println("Please enter what city you traveled to next: "); //Why does this keep popping up twice?
				nextCity = kb.nextInt();
				
				//END CITY (MUST BE HERE FOR variable "j")
				if(j == totalCities -1) {
					endColumn = nextCity;
				}//EOC endCity if
				
				//VERIFY CITY NAME & SET IT EQUAL TO A VARIABLE //Maybe it's not finding the right names? Logic error?
					//ROW
						row = currentCity;
				
					//COLUMN
						column = nextCity;
							
						//CALCULATE DISTANCE AND COST
						cityValue = cityValue + mileValues[row][column];
						totalGasMiles = totalGasMiles + cityValue;	
						
						//MAKE CURRENT CITY EQUAL TO THE ONE WE JUST TRAVELED TOO
						currentCity = nextCity;//Could it be this line?  

			}//EOC OUTER 
				
			//END CITY TO START CITY
;
				homeGasMiles = mileValues[startRow][endColumn];
				cost = (totalGasMiles + homeGasMiles) * MILEAGE_RATE;

		//OUTPUT
			System.out.println("You started in: " + cityNames[startRow] + ". And ended in: " + cityNames[endColumn] + ".");
			System.out.println("You traveled a total of " + totalGasMiles + " miles.");
			System.out.println("Your reimbursement is a total of: $" + cash.format(cost));
			System.out.println("Please make sure to contact your employer for reimbursement.");
			System.out.println("Thank you for using TMRcalculator. Have a great day.");	
			
			kb.close();
	}//EOC main
	
}//EOC Project09

package edu.usc.csce145.chapter04;

//Thomas Peterson

import java.util.Scanner;
import java.util.Random;

public class Homework02 {

	//Constants
	public static final int FOUR = 4;
	
	public static void main(String args[]) {
		
	//Scanner
		Scanner kb;
		kb = new Scanner(System.in);
		
	//Variable Constants
		int foodChoice;
		foodChoice = 0;
		//This integer will be used later during the refrigerator choice option. It will be used to determine which ending a player gets based of what kind of food they picked.
		int ending;
		ending = 0;
		//I decided to use numeric values for each of the endings. I feel this will simplfy the process of adding ending to each path by allowing me to better organize the endings all into one switch.
		
		String answer;
		answer = "";
		String answer2;
		answer2 = "";
		String answer3;
		answer3 = "";
		String answer4;
		answer4 = "";
		
		Random r;
		r = new Random();
		int randomChoice = r.nextInt(FOUR);
		//The reason I am adding a random is merely for bragging rights and the fun of it. I will later use this in the choice the player can make in the refrigerator.
		
	//Program Start
		System.out.println("Welcome to the choose your own adventure game, \"Morning Routine\")");
		System.out.println("In this game you are required to follow the choices presented in order to reach several different endings. Please type the word within the quotations you wish in order "
							+ "to proceed down that path.");
		System.out.println("Lets start.");
		System.out.println("");
		//The reason for this additional println is for alignment sake. This is NOT an error.
		
		//Game Start
		System.out.println("You wake up in your bed, ready to start the day. You are presented with a choice. Do you want to: Go back to sleep? (Type \"Sleep\"), ");
		System.out.println("go take a shower? (Type \"Shower\"), go eat breakfast? (Type \"Breakfast\"), or go straight to class (Type \"Class\")? ");
		answer = kb.next();
		
		//Choice 1
		if(answer.equalsIgnoreCase("Sleep")) {
			System.out.println("Uh oh! You went back to sleep and sleep past your class!");
			ending = 1;
		
		//Choice 2
		}else if(answer.equalsIgnoreCase("Class")) {
			System.out.println("You decided that you'll just tough it out and go straight to class. That choice doens't go too well for you.");
			ending = 6;
			
		//Choice 3
		}else if(answer.equalsIgnoreCase("shower")) {
			System.out.println("You get up out of bed, shower, and get dressed.");
			System.out.println("Do you want to eat breakfast(Type \"Breakfast\") or do you want to go straight to school(Type \"School\")?");
			answer4 = kb.next();
			
			//Choice 3a
			if(answer4.equalsIgnoreCase("breakfast")) {
				System.out.println("You walk up to the refrigerator and are given the choice of: An apple(Type \"Apple\"), yogurt(Type \"Yogurt\"),"
									+ "a piece of cake(Type \"Cake\", or reach in and grab something at random(Type\"Random\")");
				answer2 = kb.next();
				
				//Refrigerator Choice
				if(answer2.equalsIgnoreCase("apple")) {
					foodChoice = 1;
					System.out.println("You choose to eat an apple and are satisfied with your healthy breakfast.");
				}else if(answer2.equalsIgnoreCase("yogurt")) {
					foodChoice = 1;
					System.out.println("You choose to eat some yogurt and are satisfied with your healthy breakfast.");
				}else if(answer2.equalsIgnoreCase("cake")) {
					foodChoice = 2;
					System.out.println("You choose to eat a slice of cake for breakfast. You are happy, but your stomach might not be.");
				}else if(answer2.equalsIgnoreCase("random")) {
					
					//Switch randomChoice
					//Here is where the random choice object is used (See Choice 4 also). It works by taking a random number from 0-3 and then assigning that number to the variable "randomChoice".
					//The random choice switch will then output a case based off whatever value was picked.
					switch(randomChoice) {
					case 0:
						System.out.println("You reached into the fridge and pulled out an apple.");
						foodChoice = 1;
						break;
					case 1:
						System.out.println("You reached into the fridge and pulled out an yogurt.");
						foodChoice = 1;
						break;
					case 2:
						System.out.println("You reached into the fridge and pulled out an cake! You eat it and head straight to school.");
						System.out.print(" While you are very happy with your choice, your body doesn't seem to agree");
						ending = 4;
						break;
					case 3:
						System.out.println("You reached into the fridge and pulled out a... expired sandwhich?"
											+ " Well, you made the choice now you have to commit. Looks like you won't make it to class today...");
						ending = 2;
						break;
					default:
						System.out.println("Hmm there seemed to be an error. Exiting program");
						System.exit(0);
					}//EOC Switch randomChoice
					
				}//EOC Refrigerator Choice
				
				System.out.println("After breakfast, you head out for school.");
				if(foodChoice == 1) {
					System.out.println("You have a very product day at school having showered and eaten something healthy.");
					ending = 9;
				}else if(foodChoice == 2) {
					System.out.println("You feel refreshed having taken a shower... but crash halfway through the day because of your breakfast choice.");
					ending = 5;
				}//EOC if-block foodChoice
				
			//Choice 3b
			}else if(answer4.equalsIgnoreCase("school")) {
				System.out.println("Ready to start the day you head out to school feeling refreshed... but hungry."
									+ " As a result, you don't do too well in class today.");
				ending = 3;
			}//EOC 3 Subchoices
			
		//Choice 4
		}else if(answer.equalsIgnoreCase("breakfast")) {
			System.out.println("You walk up to the refrigerator and are given the choice of: An apple(Type \"Apple\"), yogurt(Type \"Yogurt\"),"
								+ "a piece of cake(Type \"Cake\", or you can reach in and grab something at random(Type\"Random\").");
			answer2 = kb.next();
			
			//Refrigerator Choice
			if(answer2.equalsIgnoreCase("apple")) {
				foodChoice = 1;
				System.out.println("You choose to eat an apple and are satisfied with your healthy breakfast.");
			}else if(answer2.equalsIgnoreCase("yogurt")) {
				foodChoice = 1;
				System.out.println("You choose to eat some yogurt and are satisfied with your healthy breakfast.");
			}else if(answer2.equalsIgnoreCase("cake")) {
				foodChoice = 2;
				System.out.println("You choose to eat a slice of cake for breakfast. You are happy, but your stomach might not be.");
			}else if(answer2.equalsIgnoreCase("random")) {
				
				//Switch randomChoice
				switch(randomChoice) {
				case 0:
					System.out.println("You reached into the fridge and pulled out an apple. Making a good choice you're satisfied.");
					foodChoice = 1;
					break;
				case 1:
					System.out.println("You reached into the fridge and pulled out an yogurt. Making a good choice you're satisfied.");
					foodChoice = 1;
					break;
				case 2:
					System.out.println("You reached into the fridge and pulled out an cake! You're happy though your stomach isn't sure that you made the right choice.");
					foodChoice = 2;
					break;
				case 3:
					System.out.println("You reached into the fridge and pulled out a... expired sandwhich?"
										+ " Well, you made the choice now you have to commit. Looks like you won't make it to class today...");
					ending = 2;
					break;
				default:
					System.out.println("Hmm there seemed to be an error. Exiting program");
					System.exit(0);
				}//EOC Switch randomChoice
	
			}//EOC Refrigerator Choice
			
			//Choice 4a
			System.out.println("After you've eaten breakfast you decide what to do next. Do you want to take a shower(Type \"Shower\" or head straight to school(Type \"School\")?");
			answer3 = kb.next();
			
			//Choice 4a1
			if(answer3.equalsIgnoreCase("shower")) {
				System.out.println("You take a nice refreshing shower. After the shower, you get dressed and head to school.");
				
				//If-foodchoice
				if(foodChoice == 1) {
					System.out.println("You show up to class feeling great because of your shower and last through the day because you ate something healthy!");
					ending = 9;
				}else if(foodChoice == 2) {
					System.out.println("You show up to class feeling great because of your shower... but crash halfway through the day because you didn't eat something healthy for breakfast.");
					ending = 5;
				}//EOC if-foodchoice
			
			//Choice 4a2
			}else if(answer3.equalsIgnoreCase("school")) {
				
				//If-foodchoice
				if(foodChoice == 1) {
					System.out.println("Though your stomach may feel great, your classmates aren't to pleased with you skipping a shower.");
					ending = 7;
				}else if(foodChoice == 2) {
					System.out.println("You ate something unhealthy and didn't shower before heading to school... I don't think those were the best options.");
					ending = 8;
				}//EOC if-foodchoice
		
			}//EOC Choice 4a2
			
		}//EOC Choices
		
	//Endings
	//Here are my ending cases. Based off choices made in the game, the ending path will assign a value to the variable "ending". That value will be read and output which ending they have recieved based off their choices.
		switch(ending) {
		case 1:
			System.out.println("Ending - Sleepyhead (1/9)");
			break;
		case 2:
			System.out.println("(Secret) Ending - Stomachache (2/9)");
			break;
		case 3:
			System.out.println("Ending - Partially Ready (3/9)");
			break;
		case 4:
			System.out.println("Ending - Crumbling Food Pyramid (4/9)");
			break;
		case 5:
			System.out.println("Ending - Happy Body, Sad Stomach (5/9)");
			break;
		case 6:
			System.out.println("Ending - A Rushed Start (6/9)");
			break;
		case 7:
			System.out.println("Ending - Elephant in the Room (7/9)");
			break;
		case 8:
			System.out.println("Ending - Bad Choices Lead to Bad Outcomes (8/9)");
			break;
		case 9:
			System.out.println("Ending - Perfect Day (9/9)");
			break;
		default:
			System.out.println("Hmm there seemed to be an error. Exiting program");
			System.exit(0);
		}//EOC Switch Ending
	
		
		kb.close();
		
	}//EOC main
	
}//EOC class

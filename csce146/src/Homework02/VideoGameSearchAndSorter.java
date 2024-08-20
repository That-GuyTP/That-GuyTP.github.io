package Homework02;

//THOMAS PETERSON
//This is the front end class for the homework. This is what should be run in order to use the program.

import java.util.Scanner;

public class VideoGameSearchAndSorter {

	//DECLARATIONS
	private static Scanner kb = new Scanner(System.in);
	private static VideoGameManager vgManager = new VideoGameManager();
	
	public static void main(String[] args) {
		System.out.println("Hello and welcome to the VideoGame search and sorter!");
		System.out.println("This program will allow you to search a list of video games of your choosing and print out the sorted results.");
		System.out.println();//For visibility sake.
		boolean quit = false;
		while(!quit) {
			printChoices();
			int choice = kb.nextInt();
			kb.nextLine();
			switch(choice) {
			case 1:
				readVGFile();
				break;
			case 2:
				searchForGames();
				break;
			case 3:
				vgManager.printVGList();
				break;
			case 4:
				writeVGFile();
				break;
			case 5:
				System.out.println("Quitting the program. Have a good day!");
				quit = true;
				break;
			case 0:
				printAvailableGames();
				break;
			default:
				System.out.println("---Invalid Input---");
			}//EOC SWITCH
		}//EOC WHILE
		

	}//EOC MAIN METHOD
	
	public static void printChoices() {
		System.out.println("Enter 1 to load the video game list file\n"
						 + "Enter 2 to search the database\n"
						 + "Enter 3 to print results to the console\n"
						 + "Enter 4 to write results into a file\n"
						 + "Enter 5 to quit the program\n"
						 + "Enter 0 to show avaliable games in list");
	}//EOC printChoices

	public static void readVGFile() {
		System.out.println("Enter the file name to read a VG List (Do NOT include a file extension or root paths)");
		String fileName = kb.nextLine();
		vgManager.readVGFile(fileName + ".txt");
	}//EOC readVGFile
	
	public static void writeVGFile() {
		System.out.println("Enter the file name to write a VG List File");
		String fileName = kb.nextLine();
		vgManager.writeVGFile(fileName);
	}//EOC writeVGFile
	
	public static void searchForGames() {
		System.out.println("Enter video game title or enter \"*\" to search through all video game titles");
		String name = kb.nextLine();
		System.out.println("Enter the console name or enter \"*\" to search through all consoles");
		String console = kb.nextLine();
		vgManager.sortAndStore(name, console);
		vgManager.printVGList();// Ensuring that the search results are printed after every new search parameter.
	}//EOC searchForGames
	
	public static void printAvailableGames() {
		vgManager.printReadFile();
	}//EOC printAvailableGames
	
}

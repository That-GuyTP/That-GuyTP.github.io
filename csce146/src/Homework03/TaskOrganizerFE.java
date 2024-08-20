package Homework03;

//THOMAS PETERSON
//NOTES
//This is the front end class for the Task Organizer program.
//It is what will be run to complete any needed processes for a task list.

import java.util.Scanner;
import java.io.*;

public class TaskOrganizerFE {

	//CONSTANTS
	private static TaskOrganizer TO = new TaskOrganizer();
	private static Scanner kb = new Scanner(System.in);
	
	//MAIN METHOD
	public static void main(String[] args) {
		System.out.println("Hello and welcome to the Task Organizer Program");
		boolean quit = false;
		while(!quit) {	
			options();
			int choice = kb.nextInt();
			kb.nextLine();
			switch(choice) {
			case 1:
				addTask();
				break;
			case 2:
				removeTask();
				break;
			case 3:
				printTasks();
				break;
			case 4:
				readTaskFile();
				break;
			case 5:
				writeTaskFile();
				break;
			case 0:
				System.out.println("Exiting the program");
				quit = true;
				break;
			default:
				System.out.println("---INVALID INPUT---");
			}//EOC SWITCH
		}//EOC WHILE
	}//EOC MAIN METHOD

	//OPTIONS
	public static void options() {
		System.out.println("Please enter the number for the follow options");
		System.out.println("Enter 1 to add a task."
				+ "\nEnter 2 to remove a task"
				+ "\nEnter 3 to print all tasks"
				+ "\nEnter 4 to read a task file"
				+ "\nEnter 5 to write a task file"
				+ "\nEnter 0 to exit");
	}
	
	//ADD
	public static void addTask() {
		System.out.println("Enter task action: ");
		String action = kb.nextLine();
		System.out.println("Enter task priority (0-4): ");
		int priority = kb.nextInt();
		kb.nextLine();//CLEARS LINE FOR SCANNER
		Task xTask = new Task(action, priority);
		TO.addTask(xTask);
	}
	
	//REMOVE
	public static void removeTask() {
		System.out.println("Enter task action: ");
		String action = kb.nextLine();
		System.out.println("Enter task priority (0-4): ");
		int priority = kb.nextInt();
		kb.nextLine();//CLEARS LINE FOR SCANNER
		Task xTask = new Task(action, priority);
		TO.removeTask(xTask);
	}
	
	//PRINT
	public static void printTasks() {
		TO.printTasks();
	}
	
	//READ
	public static void readTaskFile() {
		System.out.println("Enter the file name to read a Task List (Do NOT include a file extension or root paths)");
		String fileName = kb.nextLine();
		TO.readTaskFile(fileName + ".txt");
	}
	
	//WRITE
	public static void writeTaskFile() {
		System.out.println("Enter the file name to write a Task List File");
		String fileName = kb.nextLine();
		TO.writeTaskFile(fileName);
	}//EOC writeVGFile
	
}//EOC TaskOrganizerFE

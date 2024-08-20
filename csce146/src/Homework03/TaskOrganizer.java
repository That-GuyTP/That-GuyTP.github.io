package Homework03;

//THOMAS PETERSON
//NOTES
//This class functions as the backend of the TaskOrganizer program. It contains all needed processes to store actions in a general linked list.
//There is probably a cleaner way to right these methods but I found this way that works
//I recommend using "ctrl + shift + numPad /" to collapse all methods for an easier view.
//

import java.util.Scanner;
import java.io.*;

public class TaskOrganizer {

	//CONSTANTS
	public static final String DELIM = "\t";
	public static final int BODY_FIELD_AMT = 2;
	private GenLL<Task>[] tasks;
	
	//DEFAULT CON
	public TaskOrganizer() {
		tasks = new GenLL[5];
		for (int i = 0; i < tasks.length; i++) { // to ensure the correct size is created
			tasks[i] = new GenLL<>();
		}//EOF FOR
	}
	
	//ADDING A TASK
	public void addTask(Task xTask) {
		if(xTask != null && xTask.getPriority() >= 0 && xTask.getPriority() <= 4) {
			GenLL<Task> temp = tasks[xTask.getPriority()];
			temp.reset();////////
			
			//////////
			if(!temp.hasMore()) {
				tasks[xTask.getPriority()].add(xTask);
				System.out.println("Added task: " + xTask);
			}//EOC IF
			///////////
			
			while(temp.hasMore()) {//In this while loop I'm checking for duplicates. I'm creating a temporary copy of the task list as well as a temporary node that copies the current inputed xTask. If xTask is equal to anything on the list, it isn't added.
				Task dupCheck = temp.getCurrent();
				if(dupCheck.getAction().equals(xTask.getAction()) && dupCheck.getPriority() == (xTask.getPriority())) {
					System.out.println("Sorry, that action is already included. That action can't be added.");
				}//EOC IF
				temp.gotoNext();
			}//EOC WHILE
		}else {
			System.out.println("Sorry, there was an error adding the task");
		}//EOC IF-ELSE
		System.out.println("Added task: " + xTask);
	}
	
	//REMOVING A TASK
	public void removeTask(Task xTask) {
		if(xTask != null && xTask.getPriority() >= 0 && xTask.getPriority() <= 4) {
			GenLL<Task> temp = tasks[xTask.getPriority()];
			temp.reset();
			while(temp.hasMore()) {
				Task currentTask = temp.getCurrent();
				if (currentTask.equals(xTask)) {
					temp.removeCurrent();
					System.out.println("Confirming that task: " + xTask + " was removed");
					return;
				}//EOC INNER IF
				temp.gotoNext();
			}//EOC WHILE
			System.out.println("Sorry, there was an error removing the task");
		}//EOC OUTER IF
	}
	
	//PRINTING ALL TASKS
	public void printTasks() {
		System.out.println("Prining all current tasks in order:");
		for(int i = 0; i < tasks.length; i++) {
			GenLL<Task> temp = tasks[i];
			temp.reset();
			while (temp.hasMore()) {
				System.out.println("Priority " + i + ": " + temp.getCurrent());
				temp.gotoNext();
			}//EOC WHILE
		}//EOC FOR
		System.out.println("");//FOR ORGANIZATION SAKE
	}
	
	//READ A TASK FILE
	public void readTaskFile(String xName) {
		try {
			Scanner fileScanner = new Scanner(new File(xName));
			while(fileScanner.hasNextLine()) {
				String fileLine = fileScanner.nextLine();
				String[] splitLines = fileLine.split(DELIM);
				if(splitLines.length >= BODY_FIELD_AMT) {
					int priority = Integer.parseInt(splitLines[0].trim());
					String action = splitLines[1].trim();
					Task xTask = new Task(action, priority);
					this.addTask(xTask);
				}//EOC IF
			}//EOC WHILE
			fileScanner.close();//DON'T FORGET
		}catch(Exception e) {
			System.out.println("Sorry, there was an error locating the file");
			e.printStackTrace();
		}//EOC TRY-CATCH
	}
	
	//WRITE A TASK FILE
	public void writeTaskFile(String xName) {
		try {
			PrintWriter fileWriter = new PrintWriter(new FileOutputStream(xName));
			for(int i = 0; i < tasks.length; i++) {
				GenLL<Task> temp = tasks[i];
				temp.reset();
				while(temp.hasMore()) {
					Task xTask = temp.getCurrent();
					fileWriter.write(xTask.getPriority() + DELIM + xTask.getAction() + "\n");
					temp.gotoNext();
				}//EOC WHILE
			}//EOC FILE
			fileWriter.close();
		}catch (Exception e) {
			System.out.println("Sorry, there was an error printing the file");
			e.printStackTrace();
		}//EOC TRY-CATCH
	}
	
	
}//EOC

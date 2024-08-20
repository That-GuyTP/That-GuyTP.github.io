package Homework02;

//THOMAS PETERSON
//This class is the manager of the created GenLL of video games and contains all needed methods for the homework.
/* NEEDED FUNCTIONS
 1. To read a video game file and differentiate between video games
 2. To add each video game into it's own node in a general linked list
 3. To be able to sort the linked list by a video game's name, console, or both.
 4. To print out the sorted/filtered list to the user.
 5. To write the output in a separate file if asked.
 */

import java.util.Scanner;
import java.io.*;

public class VideoGameManager {

	//CONSTANTS
	public static final String DELIM = "\t";
	public static final int BODY_FIELD_AMT = 2;
	private GenLL<VideoGame> videogames;
	private GenLL<VideoGame> searchResults;
	
	//DEFAULT CONSTRUCTOR
	public VideoGameManager() {
		videogames = new GenLL<VideoGame>();
		searchResults = new GenLL<VideoGame>();
	}
	
	//READING VIDEO GAME FILE
	/* I ran into an error with the way the example file was formated. So when I redid the format to ensure it included things like "\t" and "\n" I still had no video games being stored.
	 * So through research I found that I can use the ".trim()" in a way that would be able to include this in the search without having to worry about it not being able to pick up things 
	 * like [NA] or [EU].
	 */
	public void readVGFile(String fileName) {
		try {
			Scanner fileScanner = new Scanner(new File(fileName));
			while(fileScanner.hasNextLine()) {
				String fileLine = fileScanner.nextLine();
				String[] splitLines = fileLine.split(DELIM); 
				if(splitLines.length >= BODY_FIELD_AMT) {
					String name = splitLines[0].trim();//Again trim() removes any excess lines which helps prevent errors.
					String console = splitLines[1].trim();
					console = console.replaceAll("\\[.*?\\]", "").trim(); //This should remove any bracketed statement from the console string which I believe is the source of the errors.
					VideoGame xVG = new VideoGame(name, console);
					this.addVideoGame(xVG);
				}//EOC IF
			}//EOC WHILE
			fileScanner.close();//DONT FORGET
		} catch(Exception e) {
			System.out.println("Sorry there was an error locating the file");
			e.printStackTrace();
		}//EOC TRY-CATCH
	}//EOC readVGFile

	//WRITING VIDEO GAME FILE
	public void writeVGFile(String fileName) {
		try {
			PrintWriter fileWriter = new PrintWriter(new FileOutputStream(fileName));
			searchResults.reset(); //resets current reference back to start/head
			while(searchResults.hasMore()) {
				VideoGame xVG = searchResults.getCurrent();
				fileWriter.println(xVG.getName() + DELIM + xVG.getConsole());
				searchResults.gotoNext();
			}//EOC WHILE
			fileWriter.close();
			System.out.println("Added list of games to the file " + fileName);
		}catch (Exception e) {
			System.out.println("Sorry there was an error while attempting to write a file.");
			e.printStackTrace();
		}//EOC TRY-CATCH
	}//EOC writeVGFile
	
	//ADDING A VIDEO GAME TO GENLL
	public void addVideoGame(VideoGame xVG) {
		if (xVG != null) {
			videogames.add(xVG); //Method already in GenLL.java
		}//EOC IF
		System.out.println("Sorry cannot add a null video game");
	}
	
	//SORT VIDEO GAMES
	/* EXPLANATION
	 * In short I'm attempting a bit of a more complicated method of sorting
	 * I create booleans that compare the inputed video game or console to whatever the current videogames node name or console is.
	 * I ensure that each name and/or console is lowercase to prevent any form of errors, similar to what we did is the shrek script reading demo.
	 * These booleans also include a check for the wildcard "*" incase the user wants to search the parameter without restrictions.
	 * The videogames LL that contains the entire list of video games is reset to the start and the program searches each node until none are left to see if they met the parameters of the search.
	 * If the video game meets the paramters, it's added to the searchResults GenLL.
	 */
	public void sortAndStore(String xName, String xConsole) {
		videogames.reset(); //Ensuring that the LL is back to the start
		while(videogames.hasMore()) {
			//VARIABLES
			VideoGame xVG = videogames.getCurrent();
			boolean nameMatch = xName.equals("*") || xVG.getName().toLowerCase().contains(xName.toLowerCase());
			boolean consoleMatch = xConsole.equals("*") || xVG.getConsole().toLowerCase().contains(xConsole.toLowerCase());
			if (nameMatch && consoleMatch) {
				searchResults.add(xVG);// Adds the valid video game from the videogames GenLL to the searchResults GenLL.
			}//EOC IF
			videogames.gotoNext();
		}//EOC WHILE
	}//EOC sortAndPrint
	
	//PRINT SORTED LIST
	public void printVGList() {
		System.out.println("Here are the search results:");
		searchResults.reset();
		while(searchResults.hasMore()) {
			System.out.println(searchResults.getCurrent());
			searchResults.gotoNext();
		}//EOC WHILE
		System.out.println();//Clearing the line for visual sake.
	}//EOC printVGList
	
	//PRINT READ FILE
	//A test method to see if the list was read and stored properly.
	public void printReadFile() {
		System.out.println("Here's what I read from the file: ");
		videogames.reset();
		while(videogames.getCurrent() != null) {
			System.out.println(videogames.getCurrent());
			videogames.gotoNext();
		}//EOC WHILE
		System.out.println();//Clearing the line for visual sake.
	}//EOC printReadFile
	
}//EOC VideoGameManager
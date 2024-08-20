package edu.usc.csce145.chapter10.ReadingFIles;

import java.util.Scanner;
import java.io.*;

public class FileIO {

	//NOTES
	//I have figured out a way to properly organize my files. No longer should you have to worry about deleting some form of extra name for a class name.
	//First step is to import tools/packages
	//We add a ".*" to the end of java.io so that it imports all assosicated packages inside the java.io package.
	//You can locate the file01.txt file we created inside you eclipse folder wherever you have it organized.
	//Inputs added to the textfile will continue to add onto it unless the "true" in line 24 is changed to "false".
	//Using textfiles helps save time and allow us to read out inputs/outputs in order to ensure that the data is being processed properly.
	
	
	public static void main(String[] args) {
		
		try {
			//SCANNER
			Scanner kb = new Scanner(System.in);
			System.out.println("Standard write out to a file");
			
			//CREATING A PrintWriter 
			PrintWriter output = new PrintWriter(new FileOutputStream("file01.txt", true)); //Creating a new FileOutputStream object and calling it file01.txt. This is being imported to the PrintWriter class.
			System.out.println("enter 3 sentences:");
			for(int i = 0; i < 3; i++) {
				output.println(kb.nextLine());
			}//EOC FOR
			output.close(); //This closes the FileOutputStream and tells the program that you are not expecting any more info coming from the user.
			kb.close();
			
			//READING A FILE
			Scanner fileReader = new Scanner(new File("file01.txt")); //This is also from Scanner class like kb. The difference is this Scanner object is reading values from the "file01.txt" file instead of reading values from the console.
			while(fileReader.hasNextLine()) {
				System.out.println(fileReader.nextLine());
			}
			fileReader.close();
			
		}catch(IOException e) { //Creating a standard exception.
			System.out.println(e.getMessage());
		}//EOC TRY-CATCH

	}//EOC MAIN METHOD

}//EOC ReadingFIles_FileIO

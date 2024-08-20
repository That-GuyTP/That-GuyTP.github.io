package Lab02;

//THOMAS PETERSON
/* BRAINSTORMING
 * Follow iinstructions
 * Withinn pastTense create a way to read a file, create whats needed, and then print it out.
 * Make sure to include a try catch
 * A scanner will be needed to check for "is" and replace with "was"
 * Reread example of using PrintWriter import util
 * Take it one sentence at a time.
 * For totalTubeVolume follow a similar file scanning method
 * 
 */

import java.util.Scanner;
import java.io.*;

public class FileIOSolutions {
	
	//pastTense METHOD
	/* PROCESS
	 * The file takes the parameters given by the user in the front end file "FileIOProblems.java"
	 * It uses inputed files (added because of the parameter) and changes them into strings. We can use another name here since in the front-end the name of the input file is correct.
	 * We create a scanner for the ItIs(ItIs.txt) and have it scan for "is" inside of the file.
	 * When it finds a word containing that, it will replace the word with "was" and continue.
	 * Once the scanner runs out of words the loop closes and the file is printed to the console as well as to a file called "ItWas.txt".
	 * We also have an exception catch just in case the ItIs.txt file cannot be found.
	 */
	public static void pastTense(String ItIs, String ItWas) {
		try {
			//INSTANTIATION OF TOOLS
			Scanner fileScanner = new Scanner(new File(ItIs));
			PrintWriter WasFileOutput = new PrintWriter(ItWas);
			
			//SCANNING FOR IS AND REPLACING WITH WAS
			while (fileScanner.hasNext()) {
				String word = fileScanner.next();
				if (word.equalsIgnoreCase("is")) {
					word = "was";
				}//EOC IF
				System.out.println(word + " ");
				WasFileOutput.print(word + " ");
			}//EOC WHILE
			
			//MAKING SURE TO CLOSE RESOURCES TO PREVENT CRASHES
			fileScanner.close();
			WasFileOutput.close();
			System.out.println(); //Clearing line for following options
			
		//EXCEPTION CATCH
		} catch (Exception e) {
			System.out.println("Sorry there was an error locating the file");
			e.printStackTrace();
		}//EOC TRY-CATCH
	}//EOC pastTense
	
	
	//totalTubeVolume METHOD
	/* PROCESS
	 * Similar to pastTEnse this will use a parameter to read a file and then start some form of process
	 * We define a default value for our totalVolume and then begin scanning the file for the info we need.
	 * It will then check if each line in the file has all three needed parts: tubeID, radius, & height.
	 * It will define the needed values as variables, calculate the volume, and then add that volume to the total volume
	 * Once the scanner has run out of tubes to calculate, it will close the scanner and then return the value needed for output in the front-end.
	 */
	public static double totalTubeVolume(String Tubes) {
		
		//DECLARING DEFAULT VALUE
		double totalVolume = 0.0;
		int limitOfTubes = 0;
		
		//SCANNING FILE AND BEGINING PROCESS
		try {
			Scanner fileScanner = new Scanner(new File("./" + Tubes));//Only works if you add the "./" to it. Annoying cheeky detail by lab requirements.
			
			while (fileScanner.hasNextLine() && limitOfTubes <= 100) {
				String line = fileScanner.nextLine();
				String[] split = line.split("\t");
				if(split.length == 3) {
					//int tubeID = Integer.parseInt(split[0]); //DEFINING WHICH TUBE IT IS. FOR TESTING PURPOSES
					double radius = Double.parseDouble(split[1]);
					double height = Double.parseDouble(split[2]);
					double volume = Math.pow(radius, 2) * Math.PI * height; //VOLUME FORMULA. I DECIDED THAT THIS WAS SIMPLE ENOUGH TO DO SINCE THERE ARE NO RESTRICTIONS ON USING "MATH." FOR PRESET VALUES.
					totalVolume += volume;
					++limitOfTubes;
				}//EOC IF
			}//EOC WHILE
			fileScanner.close(); //CLOSING SCANNER
		}catch (Exception e) {
			System.out.println("Sorry there was an error locating the file");
			e.printStackTrace();
		}//EOC TRY-CATCH
		
		return totalVolume; //MAKING SURE TO RETURN THE TOTAL VOLUME VALUE
		
	}//EOC totalTubeVolume
	
	
	/* PROCESS METHOD (FOR THE SAKE OF HAVING IT)
	 * public static double Process(double radius, double height) {
	 * 		double totalVolume = 0.0;
	 * 		totalVolume += Math.pow(radius, 2) * Math.PI * height;
	 * 		return totalVolume;
	 * }//EOC Process
	 */
	
}//EOC FileIOSolutions

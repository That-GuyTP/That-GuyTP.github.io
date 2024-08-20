package SimpleFileIO;

//THOMAS PETERSON

import java.util.Scanner;
import java.io.*; //File input and output. This importants all tools in the "io" toolbox using "*".

public class ShrekAnalyzer {

	public static void main(String[] args) {
		
		//SCANNER
		Scanner kb;
		kb = new Scanner(System.in);
		
		//INTRO
		System.out.println("Welcome to the Shrek Analyzer. "
						 + "\nEnter a world and I'll count the amount.");
		String word = kb.nextLine(); //Using this command puts the code into "waiting" mode using the IO.
		int count = lookForWord(word);
		System.out.println("The word " + word + " appears " + count + " number of times.");
		kb.close();
		
	}//EOC MAIN METHOD
	
	public static int lookForWord(String Word) { //We use the "static" type so it can be used in the main method later.
		if(Word == null) 
			return 0;
		int ret = 0;{
			try {
				Scanner fileScanner = new Scanner(new File("./Shrek.txt"));
				while(fileScanner.hasNext()) {
					String next = fileScanner.next();
					if(next.toUpperCase().contains(Word.toUpperCase())) { //looks for substrings regardless of punctiation or grammer. So long as "word" is typed out, it will count it.
						ret++;
					}//EOC IF
				}//EOC WHILE
				fileScanner.close();//DON'T FORGET
			}catch(Exception e) {
				e.printStackTrace();
			}//EOC TRY-CATCH
		return ret;
		}//EOC IF
	}//EOC lookForWord
	
}//EOC ShrekAnalyzer

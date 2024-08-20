package Lab01;

//Thomas Peterson
//This is the main class required in the lab. It will be used to create methods pointed out in the instructions and "WordSorterFrontEnd" class given to us.
//The goal is to copy the created array, sort according to the method created, and then return that array back to the front-end to print out.

public class WordHelper {
	
	//SORT BY VOWELS
	/*This method is our method used to sort each word (from smallest to largest) by the amount of vowels it has.
	*The method first creats a copy of the given array so that it make be changed without effecting the original.
	*We then use a bubble sort to switch words around depending if the one after it has more vowels.
	*We know the amount of vowels a word has because of the countVowels method created below.
	*/
	public static String[] sortByVowels(String[] vowelSorted) {
		String[] copy = copyWordArray(vowelSorted); //Creates a copy of the given array
		for(int i = 0; i < copy.length - 1; i++) {
			for(int j = 0; j < copy.length - i - 1; j++) {
				if(countVowels(copy[j]) > countVowels(copy[j + 1])) {
					String temp = copy[j];
					copy[j] = copy[j + 1];
					copy[j + 1] = temp;
				}//EOC IF
			}//EOC INNER FOR
		}//EOC FOR
		return copy;
	}//EOC sortByVowels
	
	//SORT BY CONSONANTS
	//Same process as above. Only difference is we're sorting by least to greatest consonants instead of vowels.
	public static String[] sortByConsonants(String[] conSorted) {
		String[] copy = copyWordArray(conSorted); //Creates a copy of the parameter array as a seperate String array named "copy".
		for(int i = 0; i < copy.length - 1; i++) {
			for(int j = 0; j < copy.length - i - 1; j++) {
				if(countConsonants(copy[j]) > countConsonants(copy[j + 1])) {
					String temp = copy[j];
					copy[j] = copy[j + 1];
					copy[j + 1] = temp;
				}//EOC IF
			}//EOC INNER FOR
		}//EOC FOR
		return copy;
	}//EOC sortByConsonants
	
	//SORT BY LENGTH
	/*Since this doesn't need to look for specific characters, it doesn't need a seperate method to look for characters. We can just determine the length of the word.
	*This uses a bubble sort to determine a word's length and then compare that value to the next word length value in the array order.
	*It then will move the two words arround from smallest to largest.
	*/
	public static String[] sortByLength(String[] lenSorted) {
		String[] copy = copyWordArray(lenSorted);
		for(int i = 0; i < copy.length - 1; i++) {
			for(int j = 0; j < copy.length - i - 1; j++) {
				if(copy[j].length() > copy[j + 1].length()) {
					String temp = copy[j];
					copy[j] = copy[j + 1];
					copy[j + 1] = temp;
				}//EOC IF
			}//EOC INNER FOR
		}//EOC FOR
		return copy;
	}//EOC sortByLength
	
//INNER METHODS
	//COPY ARRAY
	/*This method is used to create a copy of the array so we can move indexes around without messing up the original order for other methods.
	*What is happening is we are created another array "words" with the same length as the first array "words".
	*We are then just copying each index word to the same index in the "words" array. Then returning that array so we can use the copy later.
	*/
	public static String[] copyWordArray(String[] words) {
		String[] copy = new String[words.length];
		for (int i = 0; i < copy.length; i++) {
			copy[i] = words[i];
		}//EOC FOR
		return copy;
	}//EOC copyWordArray
	
	//COUNT VOWELS
	/*In this method where are taking a word from the string array created from the text file and checking the vowel count.
	*This method will take the word and go through each character of the word checking if that letter is a vowel or not. If the letter is a vowel it will increased the vowelCount by 1 and move to the next character.
	*If the character isn't a vowel, it moves onto the next character.
	*After all of the characters have been checked, the method returns the vowelCount integer to be used in the sortByVowels.
	*/
	public static int countVowels(String words) {
		int vowelCount = 0;
		for(int i = 0; i < words.length(); i++) {
			char ch = words.charAt(i);
			if(ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' ||
			   ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U') {
				vowelCount++;
			}//EOC IF
		}//EOC FOR
		return vowelCount;
	}//EOC countVowels
	
	//COUNT CONSONANTS
	/*This is essentially the same method as the "coultVowels" method.
	*I decided to experiment with using a different way to write the method above.
	*This version includes using a for-each loop (Using :) to turn the word String array into a char Array.
	*We then check each character in a word for any of the letters included in the "" below.
	*The "indexOf" command will return a positive value if a consonant is found. Otherwise it will return -1 causing the loop to move to the next value.
	*I believe this is my first time ever using this version of a character count loop so hopefully it goes well.
	*/
	public static int countConsonants(String words) {
		int consonantsCount = 0;
		for(char c : words.toCharArray()){
			if("bcdfghjklmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ".indexOf(c) != -1) {
				consonantsCount++;
			}//EOC IF
		}//EOC FOR
		return consonantsCount;
	}//EOC countConsonants
	
}//EOC WordHelper

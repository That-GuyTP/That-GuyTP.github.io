package Lab06;

//THOMAS PETERSON

public class SortSorterBE {

	//INSTANCE VARIABLES & CONSTANTS
	private String[] sortList;
	private int size;
	public static String sort = "sort";
	
	//CONSTRUCTOR
	public SortSorterBE(int xAmount) {
		sortList = new String[xAmount];
		size = 0;
	}//EOC CONSTRUCTOR
	
	//GETTER AND SETTER FOR SIZE
	public int getSize() {
		return this.size;
	}//EOC getSize
	
	public void setSize(int xSize) {
		this.size = xSize;
	}//EOC setSize
	
	//ADDSTRINGS
	public void addString(String xStr) {
		sortList[size] = xStr;
		size++;
	}//EOC addString
	
	//public void sortStringArray() {
	
	//MERGE SORT METHOD
	public void mergeSort(int left, int right) {
		if(left < right) { //CHECKING IF LEFT HAS BECOME BIGGER THAN RIGHT
			int mid = (left + right) / 2;
			mergeSort(left, mid);
			mergeSort(mid + 1, right);
			merge(left, mid, right);
		}//EOC IF
	}//EOC mergeSort
	
	//MERGE METHOD
	//Based of the method shown by the professor in lecture. Changed the names of the variables for my own personal clarification.
	/*PROCESS
	 * Create a temp array (to store each string) and needed variables.
	 * Using the "sortCount" method to see how many "sorts" a string has, the left and right sub-arrays are compared.
	 * Whichever has more "sorts" is stored last. If both have the less or equal they're stored accordingly.
	 * After all strings are compared and sorted the temp array is used to refill the original array "sortList" with the words in the correct order.
	 */
	private void merge(int left, int mid, int right) {
		String[] temp = new String[right - left + 1];
		int i = left;
		int j = mid + 1;
		int k = 0;
		
		//L VS R SORT
		while(i <= mid && j <= right) {
			if(sortCount(sortList[i]) <= sortCount(sortList[j])) {
				temp[k] = sortList[i];
				++k;
				++i;
			}else {
				temp[k] = sortList[j];
				++k;
				++j;
			}//EOC IF-ELSE
		}//EOC WHILE
		
		while(i <= mid) {
			temp[k] = sortList[i];
			++k;
			++i;
		}//EOC WHILE
		while(j <= right) {
			temp[k] = sortList[j];
			++k;
			++j;
		}//EOC WHILE
		
		//PUTTING SORTED LIST BACK INTO SORTLIST ARRAY
		for(int l = 0; l < temp.length; l++) {
			sortList[left + l] = temp[l];
		}//EOC FOR
	}//EOC merge
	
	//SORT COUNT METHOD
	//This method will take a string from the string array and analyze the string by a set section. The section is the size 4 and will be moved over 1 character after the current section
	//is checked. If the section spells "sort". The sortAmt for that string in the array is increased. 
	private int sortCount(String xStr) {
		int sortAmt = 0;
		for(int i = 0; i < xStr.length() - 3; i++) {
			if(xStr.substring(i, i + 4).equalsIgnoreCase(sort)) {
				sortAmt ++;
			}//EOC IF
		}//EOC FOR
		return sortAmt;
	}//EOC sortCourt
	
	//PRINT METHOD
	public void print() {
		for(String temp : sortList) {
			if(temp != null) {
				System.out.println(temp);
			}//EOC IF
		}//EOC FOR
	}//EOC PRINT
	
}//EOC SOrtSorterBE

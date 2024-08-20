package SortingAlgorithms;

public class SortingAlgos {

	public static void main (String[] args) {
		
	}//EOC MAIN METHOD
	
	//MERGE SORT
	public static void mergeSort(int[] a) {
		int size = a.length;
		
		//CHECKING SIZE
		if(size < 2) {//HALTING CONDITION
			return;
		}//EOC IF
		
		//DEFINING MID
		int mid = size /2 ;
		int leftSize = mid;
		int rightSize = size - mid;
		int[] left = new int[leftSize];
		int[] right = new int[rightSize];
		
		//MOVING DATA INTO SEPERATE ARRAYS
		for(int i = 0; i < mid; i++) {
			left[i] = a[i];
		}//EOC FOR
		for(int i = mid; i < size; i++) {
			right[i-mid] = a[i];
		}//EOC FOR
		
		//RECURSIVE CALLS
		mergeSort(left);
		mergeSort(right);
		merge(left, right, a);
	}//EOC mergeSort
	
	public static void merge(int[] left, int[] right, int[] a) {
		int leftSize = left.length;
		int rightSize = right.length;
		int i = 0;//L Index
		int j = 0; //R Index
		int k = 0; //Merged Array a's Index
		
		//EVALUATING R VS L FOR WHICH IS SMALLER & ASSIGNING TO MERGE ARRAY
		while(i<leftSize && j< rightSize) {
			if(left[i] < right[j]) {
				a[k] = left[i];
				i++;
				k++;
			}else {
				a[k] = right[j];
				j++;
				k++;
			}//EOC IF-ELSE
		}//EOC WHILE
		
		//HANDLING LEFT OVERS
		while(i < leftSize) {
			a[k] = left[i];
			i++;
			k++;
		}//EOC WHILE
		while(j < rightSize) {
			a[k] = right[j];
			j++;
			k++;
		}//EOC WHILE
		
	}//EOC merge
	
	//QUICK SORT
	public static void quickSort(int[] a, int start, int end) {
		if(start >= end) {//HALTING CONDITION
			return;
		}//EOC IF
		int pivotLocation = partition(a, start, end);
		quickSort(a, start, pivotLocation-1);
		quickSort(a, pivotLocation + 1, end);
	}//EOC quickSort
	
	public static int partition(int[] a, int start, int end) {
		//FOR REFERENCE THIS PIVOT STARTS AT THE END. DOESN'T MATTER WHERE IT STARTS IN THE END.
		int pivot = a[end];//LAST VALUE
		int i = start;
		for(int j = start; i <= end; j++) {
			if(a[j] < pivot) {
				//SWAP
				int temp = a[i];
				a[i] = a[j];
				a[j] = temp;
				i++;
			}//EOC IF
		}//EOC FOR
		
		//LAST SWAP FOR THE PIVOT
		int temp = a[i];
		a[i] = a[end];
		a[end] = temp;
		return i;//LOCATION OF PIVOT
	}//EOC PARTITION
	
}//EOC SortingAlgos

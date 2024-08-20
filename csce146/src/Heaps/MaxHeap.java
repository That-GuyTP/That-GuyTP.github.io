package Heaps;

public class MaxHeap <T extends Comparable<T>>{

	private T[] heap;
	public static final int DEF_SIZE = 128;
	private int size;//Size of the heap and last null index.
	
	//DEF CON
	public MaxHeap() {
		init(DEF_SIZE);
	}
	
	//PARA CON
	public MaxHeap(int xSize) {
		init(xSize);
	}
	
	//INIT CON
	private void init(int size) {
		size = 0;
		if(size >=1) {
			heap =(T[])(new Comparable[size]);
		}else {
			heap = (T[])(new Comparable[DEF_SIZE]);
		}//EOC I-E
	}//EOC init
	
	//ADD TO HEAP
	public void add(T xData) {
		if(heap[heap.length - 1] != null) { //AKA array is full.
			return;
		}//EOC IF
		heap[size] = xData;
		bubbleUp();
		size++;
	}//EOC add
	
	private void bubbleUp() {
		int index = size;
		while(index > 0 && heap[(index - 1) / 2].compareTo(heap[index]) < 0) { //Compare parent to current value. If < 0, swap.
			T temp = heap[(index - 1) / 2]; //temp is set equal to the parent.
			heap[(index -1 ) / 2] = heap[index]; //parent value is changed to current value.
			heap[index] = temp; // the current value is changed to the parent value.
			index = (index - 1) / 2; //index is set to the parent value. Process moves to next parent for comparison.
		}//EOC WHILE
	}//EOC bubbleUp
	
	//REMOVE
	public T remove() {
		if(size <= 0) {
			return null;
		}//EOC IF
		T ret = heap[0];
		heap[0] = heap[size - 1];//Replace root with last number in heap.
		heap[size - 1] = null; //Remove duplicate.
		size--; //Reduce heap size to adjust for removed value.
		bubbleDown();
		return ret;
	}//EOC remove
	
	private void bubbleDown() {
		int index = 0; //Start at the root
		while(index * 2 + 1 < size) { //Determine which child is larger.
			int bigIndex = index * 2 + 1; //Assumes left is larger.
			if(index * 2 + 2 < size && heap[index * 2 + 1].compareTo(heap[index * 2 + 2]) < 0) { //Check if we have a right child attached to node.
				bigIndex = index * 2 + 2;
			}//EOC IF
			if(heap[index].compareTo(heap[bigIndex]) < 0) {//Compare to parent.
				T temp = heap[index]; //Swap like normal.
				heap[index] = heap[bigIndex];
				heap[bigIndex] = temp;
			}else { //Break. Node is where it needs to be.
				break;
			}//EOC I-E
			index = bigIndex;
		}//EOC WHILE
	}//EOC bubbleDown
	
	//PEEK 
	//Check first value in heap.
	public T peek() {
		return heap[0];
	}//EOC peek
	
	//PRINT THE HEAP
	//Heaps are structured like arrays so we can print them like arrays.
	public void print() {
		for(int i = 0; i < size; i++) {
			System.out.println(heap[i]);
		}//EOC FOR
	}//EOC print
	
}//EOC MaxHeap

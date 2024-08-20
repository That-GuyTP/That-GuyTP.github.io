package Homework07;

//Thomas Peterson
//This is my MinHeap file for HW07. I ensured that all extra comments added as notes during the lecture were removed for visual sake.

public class MinHeap <T extends Comparable<T>>{
	
	//INSTANCE VARIABLES
	private T[]heap;
	public static final int DEF_SIZE = 128;
	private int lastIndex;
			
	//DEFAULT CONSTRUCTOR
	public MinHeap() {
		init(DEF_SIZE);
	}//DEF CON
	
	//PARAMETER CONSTRUCTOR
	public MinHeap(int xSize) {
		init(xSize);
	}//PARA CON
		
	//INIT CONSTRUCTOR
	private void init(int xSize) {
		if(xSize > 0) {
			heap =(T[])(new Comparable[xSize]);
			lastIndex = 0;
		}else {
			init(DEF_SIZE);
		}//EOC I-E
	}//EOC init
		
	//ADD TO HEAP
	public void add(T xData) {
		if(lastIndex >= heap.length) {
			return;
		}//EOC IF
		heap[lastIndex] = xData;
		bubbleUp(lastIndex);
		lastIndex++;
	}//EOC add
			
	private void bubbleUp(int index) {
		//int index = lastIndex;
		while(index > 0 && heap[(index - 1) / 2].compareTo(heap[index]) > 0) {
			T temp = heap[(index - 1) / 2];
			heap[(index -1 ) / 2] = heap[index];
			heap[index] = temp;
			index = (index - 1) / 2;
		}//EOC WHILE
	}//EOC bubbleUp
			
	//REMOVE INDEX FROM HEAP
	public T remove() {
		if(lastIndex == 0) {
			return null;
		}//EOC IF
		T ret = heap[0];
		heap[0] = heap[lastIndex - 1];
		heap[lastIndex - 1] = null; 
		lastIndex--; 
		bubbleDown();
		return ret;
	}//EOC remove
			
	private void bubbleDown() {
		int index = 0;
		while(index * 2 + 1 < lastIndex) {
			int smallIndex = index * 2 + 1;
			if(index * 2 + 2 < lastIndex && heap[index * 2 + 1].compareTo(heap[index * 2 + 2]) > 0) {
				smallIndex = index * 2 + 2;
			}//EOC IF
			if(heap[index].compareTo(heap[smallIndex]) > 0) {
				T temp = heap[index]; 
				heap[index] = heap[smallIndex];
				heap[smallIndex] = temp;
			}else { 
				break;
			}//EOC I-E
			index = smallIndex;
		}//EOC WHILE
	}//EOC bubbleDown
			
	//PRINT THE HEAP
	public void print() {
		for(T h : heap) {
			if(h == null) {
				break;
			}//EOC I
			System.out.println(h);
		}//EOC FOR
	}//EOC print
			
	//HEAP SORT
	public void heapSort(T[] array) {
		if(array == null) {
			return;
		}//EOC IF
		MinHeap<T> mHeap = new MinHeap<T>(array.length);
		for(int i = 0; i < array.length; i++) {
			mHeap.add(array[i]);
		}//EOC FOR
		for(int i = 0; i < array.length; i++) {
			array[i] = mHeap.remove();
		}//EOC FOR
	}//EOC heapSort
	
}//EOC MinHeap

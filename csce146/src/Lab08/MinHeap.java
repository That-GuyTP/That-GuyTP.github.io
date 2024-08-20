package Lab08;

//Thomas Peterson

public class MinHeap <T extends Comparable<T>>{
	
	//INSTANCE VARIABLES
	private T[]heap;
	public static final int DEF_SIZE = 128;
	private int lastIndex;//First null element.
			
	//DEF CON
	public MinHeap() {
		init(DEF_SIZE);
	}//DEF CON
	
	//PARA CON
	public MinHeap(int xSize) {
		init(xSize);
	}//PARA CON
		
	//INIT CON
	private void init(int xSize) {
		if(xSize > 0) {
			heap =(T[])(new Comparable[xSize]);
			lastIndex = 0;//Defines root index as 0 and first null element is meant to be the root.
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
		bubbleUp();
		lastIndex++;
	}//EOC add
			
	private void bubbleUp() {
		int index = lastIndex;
		while(index > 0 && heap[(index - 1) / 2].compareTo(heap[index]) > 0) { //Compare parent to current value. If > 0, swap.
			T temp = heap[(index - 1) / 2]; //temp is set equal to the parent.
			heap[(index -1 ) / 2] = heap[index]; //parent value is changed to current value.
			heap[index] = temp; // the current value is changed to the parent value.
			index = (index - 1) / 2; //index is set to the parent value. Process moves to next parent for comparison.
		}//EOC WHILE
	}//EOC bubbleUp
			
	//REMOVE INDEX FROM HEAP
	public T remove() {
		if(lastIndex == 0) {
			return null;
		}//EOC IF
		T ret = heap[0];
		heap[0] = heap[lastIndex - 1];//Replace root with last number in heap.
		heap[lastIndex - 1] = null; //Remove duplicate.
		lastIndex--; //Reduce heap size to adjust for removed value.
		bubbleDown();
		return ret;
	}//EOC remove
			
	private void bubbleDown() {
		int index = 0; //Start at the root
		while(index * 2 + 1 < lastIndex) { //Examining left child until you reach the end of the tree.
			int smallIndex = index * 2 + 1; //smallIndex = leftChild
			if(index * 2 + 2 < lastIndex && heap[index * 2 + 1].compareTo(heap[index * 2 + 2]) > 0) { //Check if we have a right child attached to node.
				smallIndex = index * 2 + 2;
			}//EOC IF
			if(heap[index].compareTo(heap[smallIndex]) > 0) {//Compare to parent.
				T temp = heap[index]; //Swap like normal.
				heap[index] = heap[smallIndex];
				heap[smallIndex] = temp;
			}else { 
				break; //Break. Node is where it needs to be.
			}//EOC I-E
			index = smallIndex;
		}//EOC WHILE
	}//EOC bubbleDown
			
	
	//PRINT THE HEAP
	//Heaps are structured like arrays so we can print them like arrays.
	public void print() {
		for(T h : heap) {
			if(h == null) {
				break;
			}//EOC I
			System.out.println(h);
		}//EOC FOR
	}//EOC print
			
	//PEEK 
	//Check first value in heap.
	public T peek() {
		return heap[0];
	}//EOC peek
	
	public void heapSort(T[] array) {
		if(array == null) {
			return;
		}//EOC IF
		MinHeap<T> mHeap = new MinHeap<T>(array.length);//Create a minheap instance inside this minheap instance to sort this array.
		for(int i = 0; i < array.length; i++) {
			mHeap.add(array[i]);//Add all values of the parameter array into the newly created mHeap minheap array.
		}//EOC FOR
		for(int i = 0; i < array.length; i++) {
			array[i] = mHeap.remove();
		}//EOC FOR
	}//EOC heapSort
					
}//EOC MinHeao

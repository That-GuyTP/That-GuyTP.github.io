package Heaps;

//NOTES
//Heaps have a O(n(lgn)) complexity.

import java.util.Random;

public class Tester {

	public static void main(String[] args) {
		
		//MaxHeap Example
		Random r = new Random(100);
		MaxHeap<Integer> iHeap = new MaxHeap<Integer>(16);
		
		System.out.println("Random values chosen: ");
		for(int i = 0; i < 16; i++) {
			int temp = r.nextInt(50);
			System.out.println(temp);
			iHeap.add(temp);
		}//EOC FOR
		System.out.println();
		
		System.out.println("Sorted: ");
		for(int i = 0; i < 16;i++) {
			System.out.println(iHeap.remove());
		}//EOC FOR
		System.out.println("");
		
		
		//MinHeap/HeapSort Example
		MinHeap<Integer> iHeap2 = new MinHeap<Integer>();
		Integer[] iArray = {5, 1, 4, 3, 6, 2};
		iHeap2.heapSort(iArray);
		for(Integer i : iArray) {
			System.out.println(i);
		}//EOC FOR
		
	}//EOC MAIN METHOD

}//EOC Tester

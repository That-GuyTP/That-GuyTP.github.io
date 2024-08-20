package Graphs;

import java.util.*;

public class AdjMatrixGraph {

	//INSTANCE VARIABLES & CONSTANTS
	private double[][] adjMatrix;
	public static final int DEF_VERT = 10; //Static size of Adjaceny Matrix used in this example.
	private LinkedList<Integer> markedList;
	
	//DEF CON
	public AdjMatrixGraph() {
		init(DEF_VERT);
	}//EOC def
	
	//PARA CON
	public AdjMatrixGraph(int verts) {
		init(verts);
	}//EOC para
	
	//INIT CON
	private void init(int verts) {
		if(verts <= 1) { //CHECKING IF MATRIX IS JUST A 1x1.
			init(DEF_VERT);
			return;
		}//EOC IF
		adjMatrix = new double[verts][verts];
		for(int i = 0; i < adjMatrix.length; i++) {
			for(int j = 0; j < adjMatrix[i].length; j++) {
				adjMatrix[i][j] = 0.0;
			}//EOC INNER FOR
		}//EOC FOR
		markedList = new LinkedList<Integer>();
	}//EOC init
	
	
	//ADDING AN EDGE
	public void addEdge(int fromVert, int toVert, double weight) {
		if(!isValid(fromVert) || !isValid(toVert)) {//you have an invalid Matrix
			return;
		}//EOC IF
		adjMatrix[fromVert][toVert] = weight;
	}//EOC addEdge
	
	private boolean isValid(int index) {
		return index >= 0 && index < adjMatrix.length;
	}//EOC isValid
	
	
	//PRINT DFS
	public void printDFS() {
		markedList.clear();//Clears the marked list before being used.
		printDFS(0); //Print from vertex 1 aka vertex at index 0. Can be any vertex, but for this example we choice 0.
	}//EOC PUBLIC printDFS
	
	private void printDFS(int vertex) {
		System.out.println(vertex);
		markedList.add(vertex);
		for(int i = 0; i < adjMatrix.length; i++) { //Start scan for first non zero vertex.
			if(adjMatrix[vertex][i] != 0 && !markedList.contains(i)) { //Check if there is an edge && the current vertex isn't itself.
				printDFS(i);
			}//EOC IF
		}//EOC FOR
	}//EOC PRIVATE printDPS
	
}//EOC AdjMatrixGraph

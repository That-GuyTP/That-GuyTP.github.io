package Lab07;

//Thomas Peterson
//This is the LinkedBST class of the lab. It contains the required node class, default constructor as well as the add, search, remove, preorder, inorder, postorder methods.

public class LinkedBST <T extends Comparable<T>>{ //Ensures data is of same type and comparable

	//NODE CLASS
	public class Node {
		T data;
		Node leftChild;
		Node rightChild;
		
		public Node(T xData) {
			data = xData;
			leftChild = rightChild = null;
		}//EOC CONSTRUCTOR
		
	}//EOC Node
	
	//NODE INSTANTIATION AND DEF CONS
	private Node root;
	public LinkedBST() {
		root = null;
	}//EOC DEF CONS
	
	//ADD DATA
	public void add(T xData) {
		if(root == null) {
			root = new Node(xData);
		}else {
			add(root,xData);
		}//EOC IF-ELSE
	}//EOC public ADD
	
	private Node add(Node xNode, T xData) {
		if(xNode == null) {//LEAF
			xNode = new Node(xData);
		}else if(xData.compareTo(xNode.data) < 0) { //GO LEFT
			xNode.leftChild = add(xNode.leftChild, xData);
		}else if(xData.compareTo(xNode.data) > 0) { //GO RIGHT
			xNode.rightChild = add(xNode.rightChild, xData);
		}//EOC I-EI-EI
		return xNode;
	}//EOC private ADD
	
	//SEARCH
	public boolean search(T xData) {
		return search(root, xData);
	}//EOC PUBLIC search
	
	private boolean search(Node xNode, T xData) {
		if(xNode == null) { //NO VALUE FOUND
			return false;
		}else if(xData.compareTo(xNode.data)< 0) { //GO LEFT
			return search(xNode.leftChild, xData);
		}else if(xData.compareTo(xNode.data)> 0) { //GO RIGHT
			return search(xNode.rightChild, xData);
		}else { //VALUE LOCATION FOUND
			return true;
		}//EOC I-EI-EI-E
	}//EOC PRIVATE search
	
	//REMOVE & FINDMININTREE
	public void remove(T xData) {
		root = remove(root, xData);
	}//EOC PUBLIC remove
	
	private Node remove(Node xNode, T xData) {
		if(xNode == null) {
			return null;
		}else if(xData.compareTo(xNode.data) < 0) {
			xNode.leftChild = remove(xNode.leftChild, xData);
		}else if(xData.compareTo(xNode.data) > 0) {
			xNode.rightChild = remove(xNode.rightChild, xData);
		}else {
			if(xNode.rightChild == null) {
				return xNode.leftChild;
			}else if(xNode.leftChild == null) {
				return xNode.rightChild;
			}//EOC INNER I-EI
				Node temp = findMinInTree(xNode.rightChild);
				xNode.data = temp.data;
				xNode.rightChild = remove(xNode.rightChild, temp.data);
		}//EOC I-EI-EI-E
		return xNode;
	}//EOC PRIVATE remove
	
	private Node findMinInTree(Node xNode) {
		if(xNode == null) {
			return null;
		}else if(xNode.leftChild == null) {
			return xNode;
		}else {
			return findMinInTree(xNode.leftChild);
		}//EOC IF-EI-E
	}//EOC findMinInTree
	
	//PREORDER METHODS
	public void printPreorder() {
		printPreorder(root);
	}//EOC PUBLIC Preorder
	
	private void printPreorder(Node xNode) {
		if(xNode == null) {
			return;
		}//EOC IF
		System.out.println(xNode.data);
		printPreorder(xNode.leftChild);
		printPreorder(xNode.rightChild);
	}//EOC PRIVATE printPreorder
	
	//INORDER METHODS
	public void printInorder() {
		printInorder(root);
	}//EOC PUBLIC Inorder
	
	private void printInorder(Node xNode) {
		if(xNode == null) {
			return;
		}//EOC IF
		printPreorder(xNode.leftChild);
		System.out.println(xNode.data);
		printPreorder(xNode.rightChild);
	}//EOC PRIVATE Inorder
	
	//POSTORDER METHODS
	public void printPostorder() {
		printPostorder(root);
	}//EOC PUBLIC Postorder
	
	private void printPostorder(Node xNode) {
		if(xNode == null) {
			return;
		}//EOC IF
		printPostorder(xNode.leftChild);
		printPostorder(xNode.rightChild);
		System.out.println(xNode.data);
	}//EOC PRIVATE printPostorder
	
}//EOC LinkedBSTree

package Trees;

public class LinkedBSTree <T extends Comparable<T>>{ //Ensures data is of same type and comparable

	//DECLARING WHAT A "NODE" IS FOR THE TREE
	public class Node {
		T data;
		Node leftChild;
		Node rightChild;
		
		public Node(T xData) {
			data = xData;
			leftChild = rightChild = null;
		}//EOC CONSTRUCTOR
		
	}//EOC Node
	
	//NODE CREATION AND SETUP
	private Node root; //FIRST ELEMENT / SAME AS HEAD
	public LinkedBSTree() {
		root = null;
	}//EOC DEFAULT CONS
	
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
		}//EOC IF-ELSEIF-ELSEIF //No need for an else since any other value would have to equal 0, meaning it's a duplicate, meaning we don't add it.
		return xNode;//Must add because this method will eventually reach a leaf/null point.
	}//EOC private ADD
	
	//DEPTH TRAVERSAL METHODS
	
		//PREORDER METHOD
		//Will search through the three from the left-most node to the right-most node.
		public void printPreorder() {
			printPreorder(root);
		}//EOC Preorder
		private void printPreorder(Node xNode) {
			if(xNode == null) {
				return;
			}//EOC IF
			System.out.println(xNode.data);
			printPreorder(xNode.leftChild);
			printPreorder(xNode.rightChild);
		}//EOC printPreorder
		
		//INORDER METHOD
		//Will search through the tree starting from the node and then directly left-right from each node. Think of "squishing" the tree.
		public void printInorder() {
			printInorder(root);
		}//EOC INORDER
		private void printInorder(Node xNode) {
			if(xNode == null) {
				return;
			}//EOC IF
			printPreorder(xNode.leftChild);
			System.out.println(xNode.data);
			printPreorder(xNode.rightChild);
		}//EOC printPreorder
		
		//POSTORDER METHOD
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
		
	//SEARCH
	public boolean search(T xData) {
		return search(root, xData);
	}//EOC PUBLIC search
	private boolean search(Node xNode, T xData) {
		if(xNode == null) {//LEAF
			return false;
		}else if(xData.compareTo(xNode.data) < 0) { //"xData - xNode's data" is negative GO LEFT
			return search(xNode.leftChild, xData);
		}else if(xData.compareTo(xNode.data) > 0) {// GO RIGHT
			return search(xNode.rightChild, xData);
		}else {
			return true;
		}//EOC I-EI-EI-E
		
	}//EOC PRIVATE SEARCH
	
	//REMOVE
	public void remove(T xData) {
		root = remove(root, xData);//Replaces root value with entered data.
	}//EOC PUBLIC remove METHOD
	private Node remove(Node xNode, T xData) {
		//SEARCH FOR DATA
		if(xNode == null) {
			return null;//AKA DATA NOT FOUND
		}else if(xData.compareTo(xNode.data) < 0) { //GO LEFT
			xNode.leftChild = remove(xNode.leftChild, xData);
		}else if(xData.compareTo(xNode.data)> 0) { //GO RIGHT
			xNode.rightChild = remove(xNode.rightChild, xData);
		}else {
			//We found the data needed. Now to destroy(replace & return).
			if(xNode.rightChild == null) {
				return xNode.leftChild;
			}else if(xNode.leftChild == null) {//IF first if wasn't true we only had 1 child.
				return xNode.rightChild;
			}//EOC INNER I-EI
			
			//2 CHILDREN
			Node temp = findMinInTree(xNode.rightChild);
			xNode.data = temp.data;
			xNode.rightChild = remove(xNode.rightChild, temp.data);//REMOVES DUPE
		}//EOC I-EI-EI-E
		return xNode;
	}//EOC PRIVATE remove METHOD
	
	//REMOVE PART 2 - USING MINI MAX & 2 CHILDREN TRAVERSE
	private Node findMinInTree(Node xNode) {
		if(xNode == null) {
			return null;
		}else if(xNode.leftChild == null) {
			return xNode;
		}else {
			return findMinInTree(xNode.leftChild);
		}//EOC I-EI-E
	}//EOC 2 CHILDREN TRAVERSE
}//EOC LinkedBSTree

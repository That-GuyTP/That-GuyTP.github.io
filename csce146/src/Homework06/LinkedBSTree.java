package Homework06;

//Thomas Peterson
//This is my LinkedBSTree class from my notes in class. It contains all needed methods. I have removed any excess notes take during class to help with visuals.
//The only methods that aren't perfect are writeShapeFile() & removeShapesGreaterThan().

import java.util.Scanner;
import java.io.*;

public class LinkedBSTree <Shape extends Comparable<Shape>>{ //Ensures data is of same type and comparable

	//DECLARING WHAT A "NODE" IS FOR THE TREE
	public class Node {
		Shape data;
		Node leftChild;
		Node rightChild;
		
		public Node(Shape xData) {
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
	public void add(Shape xData) {
		if(root == null) {
			root = new Node(xData);
		}else {
			add(root,xData);
		}//EOC IF-ELSE
	}//EOC public ADD
	private Node add(Node xNode, Shape xData) {
		if(xNode == null) {//LEAF
			xNode = new Node(xData);
		}else if(xData.compareTo(xNode.data) < 0) { //GO LEFT
			xNode.leftChild = add(xNode.leftChild, xData);
		}else if(xData.compareTo(xNode.data) > 0) { //GO RIGHT
			xNode.rightChild = add(xNode.rightChild, xData);
		}//EOC IF-ELSEIF-ELSEIF
		return xNode;
	}//EOC private ADD
	
	//DEPTH TRAVERSAL METHODS
	
		//PREORDER METHOD
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
	public boolean search(Shape xData) {
		return search(root, xData);
	}//EOC PUBLIC search
	private boolean search(Node xNode, Shape xData) {
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
	public void remove(Shape xData) {
		root = remove(root, xData);
	}//EOC PUBLIC remove METHOD
	private Node remove(Node xNode, Shape xData) {
		//SEARCH FOR DATA
		if(xNode == null) {
			return null;//AKA DATA NOT FOUND
		}else if(xData.compareTo(xNode.data) < 0) { //GO LEFT
			xNode.leftChild = remove(xNode.leftChild, xData);
		}else if(xData.compareTo(xNode.data)> 0) { //GO RIGHT
			xNode.rightChild = remove(xNode.rightChild, xData);
		}else {
			if(xNode.rightChild == null) {
				return xNode.leftChild;
			}else if(xNode.leftChild == null) {
				return xNode.rightChild;
			}//EOC INNER I-EI
			
			//2 CHILDREN
			Node temp = findMinInTree(xNode.rightChild);
			xNode.data = temp.data;
			xNode.rightChild = remove(xNode.rightChild, temp.data);
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
	
	//READ SHAPE FILE
	public void readShapeFile(String xFile) {
        try {
            File file = new File(xFile);
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String fileLine = fileScanner.nextLine();
                String[] splitLines = fileLine.split("\t");//Using "\t" rather than creating a constant string "DELIM".
                if (splitLines.length > 0) {
                    String shapeName = splitLines[0];
                    Shape newShape = null;
                    switch (shapeName) { //Went with a switch case as I felt it was the easiest to compare the inputed type of Shape.
                        case "Rectangle":
                            if(splitLines.length == 3) {
                                double length = Double.parseDouble(splitLines[1]);
                                double width = Double.parseDouble(splitLines[2]);
                                newShape = (Shape) new Rectangle(length, width);
                            }//EOC I
                            break;
                        case "Circle":
                            if(splitLines.length == 2) {
                                double radius = Double.parseDouble(splitLines[1]);
                                newShape = (Shape) new Circle(radius);
                            }//EOC IF
                            break;
                        case "Right Triangle":
                            if(splitLines.length == 3) {
                                double base = Double.parseDouble(splitLines[1]);
                                double height = Double.parseDouble(splitLines[2]);
                                newShape = (Shape) new RightTriangle(base, height);
                            }//EOC IF
                            break;
                        default:
                            System.out.println("Not a valid shape type");
                            break;
                    }//EOC SWITCH
                    if (newShape != null) {
                        add(newShape);
                    }//EOC INNER IF
                }//EOC IF
            }//EOC WHILE
            fileScanner.close();//DON'T FORGET
        } catch (FileNotFoundException e) {
            System.out.println("Sorry, there was an error locating the file");
        }//EOC TRY-CATCH
    }//EOC readShapeFile

	//WRITE SHAPE FILE
	public void writeShapeFile(String xName) {
		try {
			PrintWriter fileWriter = new PrintWriter(new FileOutputStream(xName));
			writeShapeFile(root, fileWriter);
		}catch (Exception e) {
			System.out.println("Sorry, there was an error printing the file");
			e.printStackTrace();
		}//EOC TRY-CATCH
	}//EOC PUBLIC writeShapeFile
	
	private void writeShapeFile(Node xNode, PrintWriter xWriter) {
		if(xNode != null) {
			writeShapeFile(xNode.leftChild, xWriter);
			xWriter.write(xNode.data + "\n");
			writeShapeFile(xNode.rightChild, xWriter);
		}//EOC IF
	}//EOC PRIVATE writeShapeFile
	
	//FIND LARGEST AREA METHOD
	public Shape findLargestArea() {
		if (root == null) {//Tree is empty
			return null;
		}//EOC IF
		Node temp = root;
		while(temp.rightChild != null) {
			temp = temp.rightChild;
		}//EOC WHILE
		return temp.data;
	}//EOC findLargestArea
	
	//REMOVE ANY SHAPES GREATER THAN A VALUE METHOD
	/*Thoughts on potiental fixes
	 * Correctly identify and declare a temp node with the value desired and properly compare it to the node on the tree currently.
	 */
	public void removeShapesGreaterThan(Shape xValue) {
		root = removeShapesGreaterThan(root, xValue);
	}//EOC PUBLIC removeShapesGreaterThan
	
	private Node removeShapesGreaterThan(Node xNode, Shape xValue) {
		if(xNode == null) {
			return null;//DATA NOT FOUND
		}else if(xValue.compareTo(xNode.data) < 0) { //GO LEFT
			xNode.leftChild = remove(xNode.leftChild, xValue);
		}else if(xValue.compareTo(xNode.data)> 0) { //GO RIGHT
			xNode.rightChild = remove(xNode.rightChild, xValue);
		}else { //DATA FOUND
			xNode.rightChild = null;
			xNode.leftChild = null;
			xNode.data = null;
		}//EOC I-EI-EI-E
		return xNode;
	}//EOC PRIVATE removeShapesGreaterThan
	
}//EOC LinkedBSTree

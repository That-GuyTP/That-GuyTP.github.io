package edu.usc.csce145.chapter05.copy;

//Thomas Peterson

import java.util.Scanner;

public class Lab07 {

	public static void main(String args[]) {
			
			//SCANNER
			Scanner kb;
			kb = new Scanner(System.in);
			
			//MATRIX 1 SIZE
			System.out.println("Enter the rows in matrix 1:");
			int mat1_rows = kb.nextInt();
			System.out.println("Enter the columns in matrix 1:");
			int mat1_cols = kb.nextInt();
			
			//MATRIX 2 SIZE
			System.out.println("Enter the rows in matrix 2:");
			int mat2_rows = kb.nextInt();
			System.out.println("Enter the columns of matrix 2:");
			int mat2_cols = kb.nextInt();
			
			//COMPATIABILITY CHECK
			//Determining if the two matrices are equal in size so they may be added. It then reprompts the user for the sizes.
			while (mat1_cols != mat2_cols | mat1_rows != mat2_rows) {
				System.out.println("Sorry, these matrices don't match. Please try again and input compatible MATRICES.");
				System.out.println();
				System.out.println("Enter the rows in matrix 1:");
				mat1_rows = kb.nextInt();
				System.out.println("Enter the columns in matrix 1:");
				mat1_cols = kb.nextInt();
				
				//MATRIX 2 SIZE
				System.out.println("Enter the rows in matrix 2:");
				mat2_rows = kb.nextInt();
				System.out.println("Enter the columns of matrix 2:");
				mat2_cols = kb.nextInt();
			}//EOC COMPATIABILITY CHECK
			
			//DELCARING MATRICES
			int[][] matrix1 = new int[mat1_rows][mat1_cols];
			int[][] matrix2 = new int[mat2_rows][mat2_cols];
			
			//STORING MATRIX 1 VALUES
			for(int i = 0; i < mat1_rows; i++) {
				for(int j = 0; j < mat1_cols; j++) {
					System.out.println("Enter the value of matrix1 at position ("+i+", "+j+"):");
					matrix1[i][j] = kb.nextInt();
				}//EOC MAT1_COLS
			}//EOC MAT1_ROWS
			
			//STORING MATRIX 2 VALUES
			for(int i = 0; i < mat2_rows; i++) {
				for(int j = 0; j < mat2_cols; j++) {
					System.out.println("Enter the value of matrix2 at position ("+i+", "+j+"):");
					matrix2[i][j] = kb.nextInt();
				}//EOC MAT2_COLS
			}//EOC MAT2_ROWS
			
			//MATRIX ADDITION
			//This is where the values are being added. What is essientally happening is the for loop is taking the value of matrix 1 at position (i, j) and adding the value of matrix 2 at the same position,
			//then taking that value, assigning it to the variable sum, and then assigning the value of the new matrix (sumMatrix) at the same position to the sum. The loop them resets the value of sum back to 0 for
			//logic sake.
			int[][] sumMatrix = new int [mat1_rows][mat1_cols];
			int sum = 0;
			for(int i = 0; i < mat1_rows; i++) {
				for(int j = 0; j < mat2_cols; j++) {
					sum = (matrix1[i][j] + matrix2[i][j]);
					sumMatrix[i][j] = sum;
					sum=0;
				}//EOC FOR J
			}//EOC FOR I
			
			//PRINT MATRIX
			System.out.println();
			System.out.println("Here is the added matrices:");
			for(int i = 0; i < mat1_rows; i++) {
				for(int j = 0; j < mat2_cols; j++) {
					System.out.print(sumMatrix[i][j]+" ");
				}//EOC FOR J
				System.out.println();
			}//EOC FOR I
			
			
			kb.close();
			
		}//EOC main method
		
	}//EOC Lab07

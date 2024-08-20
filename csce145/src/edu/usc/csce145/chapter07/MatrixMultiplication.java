package edu.usc.csce145.chapter07;

import java.util.Scanner;

public class MatrixMultiplication {

	public static void main(String[] args) {
		
		/* Example
		 ***Number of rows M1 and number of columns M2 of the arrays must match.***
		 ***Matrices are described as rows x columns.***
		 M1        M2
		 1 2       1 0
		 3 4       0 1
		 Can be multiplied
		 
		 Will be multiplied like: 
		 (M1R1 * M2C1)+(M1R2 * M2C2).... etc 
		 (1 * 1)+(2 * 0)      (1 * 0)+(2 * 1)      (3 * 1)+(4 * 0)     (3 * 0)+(4 * 1)
		 Output:
		 1  2
		 3  4
		 
		 M1        M2
		 1 2 3     1 0
		 4 5 6     0 1
		 Cannot be multiplied
		 */
		
		//Scanner
		Scanner kb;
		kb = new Scanner(System.in);
		
		//Matrix 1 Size
		System.out.println("Enter the rows in matrix 1:");
		int mat1_rows = kb.nextInt();
		System.out.println("Enter the columns in matrix 1:");
		int mat1_cols = kb.nextInt();
		
		//Matrix 2 Size 1
		System.out.println("Enter the rows in matrix 2:");
		int mat2_rows = kb.nextInt();
		
		//Variability Check
		if(mat1_cols != mat2_rows) {
			System.out.println("Sorry, I cannot multiply these matrices. Dimensions mismatch.");
			System.exit(0);
		}//EOC Variability Check
		
		//Matrix 2 Size 2
		System.out.println("enter the columns of matrix 2:");
		int mat2_cols = kb.nextInt();
		
		//Creating The Matrices
		int[][] matrix1 = new int[mat1_rows][mat1_cols];
		int[][] matrix2 = new int[mat2_rows][mat2_cols];
		
		//Storing Values Matrix 1
		for(int i = 0; i < mat1_rows; i++) {
			for(int j = 0; j < mat1_cols; j++) {
				System.out.println("Enter the value of matrix1 at position ("+i+", "+j+"):");
				matrix1[i][j] = kb.nextInt();
			}//EOC mat1_cols
		}//EOC mat1_rows
		
		//Storing Values Matrix 2
		for(int i = 0; i < mat2_rows; i++) {
			for(int j = 0; j < mat2_cols; j++) {
				System.out.println("Enter the value of matrix2 at position ("+i+", "+j+"):");
				matrix2[i][j] = kb.nextInt();
			}//EOC mat2_cols
		}//EOC mat12_rows
		
		//Matrix Multiplication
		/*The loop will begin by flowing through i and j loops, assigning their value to 0. It then begins the k loops where it calculates the value for the product Matrix at position (0,0). Once finished it will use the 
		 sum variable to assign the value to that position and reset the sum back to 0 before moving onto the next position in the matrix. It wil continue until all values are multiplied and applied to the correct
		 positions. It will first move back into the j loop, increasing it by 1 before moving back into the k loop for the next position value calculation.
		 */
		int[][] productMatrix = new int [mat1_rows][mat2_cols];
		int sum = 0;
		for(int i = 0; i < mat1_rows; i++) {
			for(int j = 0; j < mat2_cols; j++) {
				for(int k = 0; k < mat1_cols; k++) {
					sum += matrix1[i][k] * matrix2[k][j];
				}//EOC for k
				productMatrix[i][j] = sum;
				sum = 0;
			}//EOC for j
		}//EOC for i
		
		//Print Product Matrix
		for(int i = 0; i < mat1_rows; i++) {
			for(int j = 0; j < mat2_cols; j++) {
				System.out.print(productMatrix[i][j]+" ");
			}//EOC for j
			System.out.println();
		}//EOC for i
		
		
		kb.close();
		
	}//EOC main method
	
}//EOC MatrixMultiplication

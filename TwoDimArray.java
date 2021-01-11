package ca.queensu.cs.cisc124.asgmt.a4;

import java.util.Arrays;

/**
 * Very short tutorial on two-dimensional arrays in Java.
 */
public class TwoDimArray {

	public static void main(String[] args) {
		/*
		 * A two-dimensional array is actually an array where
		 * the elements are also arrays.
		 * 
		 * We can create a 3x3 array of double like so:
		 */
		double[][] arr = new double[3][3];
		System.out.println(Arrays.deepToString(arr));
		/*
		 * arr[0] is an array of 3 doubles
		 * arr[1] is an array of 3 doubles
		 * arr[2] is an array of 3 doubles
		 */
		
		/*
		 * You can think of arr[0] as being the first
		 * row of a matrix, arr[1] as being the second
		 * row of a matrix, and arr[2] as being the third
		 * row of a matrix.
		 */
		
		/*
		 * To set an element of the matrix, use two indexes
		 * to set the appropriate array element. For example,
		 * to set the element at row 0 and column 0:
		 */
		arr[0][0] = 1.0;
		System.out.println(Arrays.deepToString(arr));
		
		/*
		 * To set the element at row 1 and column 2:
		 */
		arr[1][2] = 500.0;
		System.out.println(Arrays.deepToString(arr));
		
		/*
		 * To set the element at row 2 and column 0:
		 */
		arr[2][0] = 1000.0;
		System.out.println(Arrays.deepToString(arr));
		
		/*
		 * To loop over all of the elements of the matrix
		 * use a pair of nested loops:
		 */
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				double value = arr[row][col];
				String s = String.format("row : %d, col: %d, value: %f", row, col, value);
				System.out.println(s);
			}
		}
	}
}

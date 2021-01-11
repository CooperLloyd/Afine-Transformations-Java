package ca.queensu.cs.cisc124.asgmt.a4;


import java.util.Arrays;

/**
 * A basic matrix class for 3x3 real-valued matrices. 
 * 
 * <p>
 * The rows and columns of the matrix are indexed using a 0-based
 * index (i.e., the first row is row 0 and the first column is
 * column 0).
 */
public class Matrix3 {
	// ADD YOUR FIELD/FIELDS AFTER HERE
	public static final int MAT_SIZE = 3;
	private double[][] data;

	// ADD YOUR CONSTRUCTORS AFTER HERE
	public Matrix3() {
		this.data = new double[3][3];
		this.identity_matrix();
	}

	public Matrix3(Matrix3 other) {
		this.data = other.data;
	}

	// SOME METHODS ARE SUGGESTED HERE

	/**
	 * Returns the element at the specified row and column of
	 * this matrix.
	 *
	 * @param row the row index
	 * @param col the column index
	 * @return the element at the specified row and column
	 * @throws IndexOutOfBoundsException if the row or column index is out of bounds
	 */
	public double get(int row, int col) {
		if (row >= data.length || col >= data[row].length) {
			throw new IndexOutOfBoundsException();
		} else {
			return this.data[row][col];
		}
	}

	/**
	 * Premultiplies this matrix by the specified matrix changing
	 * the elements of this matrix.
	 *
	 * @param other the matrix to premultiply this matrix by
	 * @return a reference to this matrix
	 */
	public Matrix3 premultiply(Matrix3 other) {
		double[][] result = new double[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				result[i][j] = 0;
				for (int k = 0; k < 3; k++) {
					result[i][j] += other.get(i, k) * this.data[k][j];
				}
			}
		}
		this.data = result;
		return this;
	}

	/**
	 * Postmultiplies this matrix by the specified matrix changing
	 * the elements of this matrix.
	 *
	 * @param other the matrix to postmultiply this matrix by
	 * @return a reference to this matrix
	 */
	public Matrix3 postmultiply(Matrix3 other) {
		double[][] result = new double[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				result[i][j] = 0;
				for (int k = 0; k < 3; k++) {
					result[i][j] += this.data[i][k] * other.get(k, j);
				}
			}
		}
		this.data = result;
		return this;
	}

	public void set_element(double val, int row, int col) {
		this.data[row][col] = val;
	}

	public void set(int row, int col, double value) {
		this.set_element(value, row, col);
	}

	// ADD OTHER METHODS AFTER HERE
	public void identity_matrix() {
		this.set_element(1, 0, 0);
		this.set_element(0, 0, 1);
		this.set_element(0, 0, 2);
		this.set_element(0, 1, 0);
		this.set_element(1, 1, 1);
		this.set_element(0, 1, 2);
		this.set_element(0, 2, 0);
		this.set_element(0, 2, 1);
		this.set_element(1, 2, 2);
	}


	@Override
	public boolean equals(Object o) {

		if (!(o instanceof Matrix3)){
			return false;
		}
		Matrix3 obj = (Matrix3) o;

		for (int i = 0; i < data.length; i++){
			for (int x = 0; x < data[i].length; x++){
				if (obj.get(i, x) != this.get(i, x)){
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		return Arrays.hashCode(data);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < data.length; i++) {
			sb.append("[");
			for (int x = 0; x < data[i].length; x++) {
				sb.append(data[i][x]).append(",");
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append("],");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}
}
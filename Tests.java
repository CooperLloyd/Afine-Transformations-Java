package ca.queensu.cs.cisc124.asgmt.a4;

public class Tests {

	// see https://floating-point-gui.de/errors/comparison/
	public static boolean nearlyEqual(double a, double b, double epsilon) {
		final double absA = Math.abs(a);
		final double absB = Math.abs(b);
		final double diff = Math.abs(a - b);

		if (a == b) { // shortcut, handles infinities
			return true;
		} else if (a == 0 || b == 0 || (absA + absB < Double.MIN_NORMAL)) {
			// we want absolute error here
			return diff < epsilon;
		} else { // use relative error
			return diff / Math.min((absA + absB), Double.MAX_VALUE) < epsilon;
		}
	}

	public static boolean areSimilar(Matrix3 a, Matrix3 b) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (!nearlyEqual(a.get(i, j), b.get(i, j), 1e-9)) {
					System.out.println(a.get(i, j) + ", " + b.get(i, j));
					return false;
				}
			}
		}
		return true;
	}

	public static String toString(Matrix3 a) {
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < 3; i++) {
			b.append("[");
			b.append(a.get(i, 0));
			for (int j = 1; j < 3; j++) {
				b.append(", ");
				b.append(a.get(i, j));
			}
			b.append("]\n");
		}
		return b.toString();
	}
	
	/**
	 * Returns a new matrix having the values given in the
	 * specified array. The values of the matrix are filled in
	 * from left to right and top to bottom.
	 * 
	 * @param vals an array
	 * @return a matrix having the values given in the specified array
	 */
	public static Matrix3 from(double[] vals) {
		Matrix3 a = new Matrix3();
		int index = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				a.set(i, j, vals[index++]);
			}
		}
		return a;
	}
	
	/**
	 * Copies values for an array into a matrix.
	 * 
	 * @param a a matrix
	 * @param vals an array
	 */
	public static void copyFrom(Matrix3 a, double[] vals) {
		int index = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				a.set(i, j, vals[index++]);
			}
		}
	}
	
	public static Matrix3 identity() {
		Matrix3 a = new Matrix3();
		a.set(0, 0, 1.0);
		a.set(1, 1, 1.0);
		a.set(2, 2, 1.0);
		return a;
	}
	
	public static void identity(Matrix3 a) {
		a.set(0, 0, 1.0);
		a.set(0, 1, 0.0);
		a.set(0, 2, 0.0);
		
		a.set(1, 0, 0.0);
		a.set(1, 1, 1.0);
		a.set(1, 2, 0.0);
		
		a.set(2, 0, 0.0);
		a.set(2, 1, 0.0);
		a.set(2, 2, 1.0);
		
	}
}

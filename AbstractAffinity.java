package ca.queensu.cs.cisc124.asgmt.a4;

import ca.queensu.cs.cisc124.notes.basics.geometry.Point2;
import ca.queensu.cs.cisc124.notes.basics.geometry.Vector2;

/**
 * Superclass for 2D affine transformations or affinities.
 * An affinity maps lines to lines and preserves parallelism
 * but may change the distance between points and the angle
 * between lines. Examples of affine transformations include
 * rotation, translation, scaling, and shear.
 *
 * <p>
 * Any combination of affine transformations is also an
 * affine transformation.
 *
 * <p>
 * Every affinity can be represented by a 3x3 matrix where
 * the last row of the matrix is {@code [0, 0, 1]}. This class
 * provides a {@code Matrix3} object for subclasses to
 * manipulate. It also provides methods for transforming
 * and mapping points using matrix multiplication. Subclasses
 * should override these methods to provide more efficient
 * implementations of these methods.
 */
public abstract class AbstractAffinity {
	/**
	 * The matrix representation of this transformation.
	 */
	protected Matrix3 mat;

	/**
	 * Initializes this affinity to the identity transformation.
	 */
	public AbstractAffinity() {
        this.mat = new Matrix3();
	}

	/**
	 * Initializes this affinity by copying another transformation.
	 * This implementation makes an independent copy of the matrix
	 * belonging to the other transformation.
	 *
	 * @param other an affinity to copy
	 */
	public AbstractAffinity(AbstractAffinity other) {
        this.mat = other.copyMatrix();
	}


	/**
	 * Transforms the specified point using this transformation
	 * changing the coordinates of the point.
	 *
	 * @param p a point to transform
	 * @return the transformed point p
	 */
	public Point2 transform(Point2 p) {
		double[] vec = new double[]{p.x(),p.y(),1};
		int m = Matrix3.MAT_SIZE;
		int n = Matrix3.MAT_SIZE;
		double[] y = new double[m];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				y[i] += this.mat.get(i, j) * vec[j];
			}
		}
		p.set(y[0],y[1]);
		return p;
	}



	/**
	 * Maps the specified point using this transformation
	 * to produce a new point.
	 *
	 * @param p a point to map
	 * @return a new point equal to this transformation applied to p
	 */
	public Point2 map(Point2 p) {
		// ALREADY DONE FOR YOU
		Point2 q = new Point2(p);
		return this.transform(q);
	}


	/**
	 * Returns a new copy of the matrix representation of this
	 * transformation.
	 *
	 * @return a new copy of the matrix representation of this
	 * transformation
	 */
	public Matrix3 copyMatrix() {
        return new Matrix3(this.mat);
	}

	/**
	 * Returns a hash code for this transformation. The returned
	 * hash code is the hash code of the transformation matrix.
	 *
	 * @return a hash code for this transformation
	 */
	@Override
	public int hashCode() {
		// ALREADY DONE FOR YOU
		return this.mat.hashCode();
	}

	/**
	 * Compares the specified object with this affinity for equality. Returns
	 * true if and only if the specified object is also an AbstractAffinity
	 * having a matrix representation equal to this transformation.
	 *
	 * @param obj the object to be compared for equality with this transformation
	 * @return true if the specified object is equal to this transformation
	 */
	
	@Override
	public boolean equals(Object obj) {
        if (!(obj instanceof AbstractAffinity)) { // of same type
            return false;
        }
        AbstractAffinity other = (AbstractAffinity) obj;
        String t = this.mat.toString();
        String o = other.mat.toString();
        return t.equals(o);// check for equal matrix value
    }


	/**
	 * Returns the string representation of the matrix representation
	 * of this transformation.
	 *
	 * <p>
	 * The nine elements of the matrix will appear in an easily
	 * readable form in the returned string.
	 *
	 * @return a string representation of this transformation
	 */
	@Override
	public String toString() {
        return this.mat.toString();
	}

}

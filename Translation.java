package ca.queensu.cs.cisc124.asgmt.a4;

import ca.queensu.cs.cisc124.notes.basics.geometry.Point2;
import ca.queensu.cs.cisc124.notes.basics.geometry.Vector2;

/**
 * A 2D translation transformation. A translation moves
 * points by a vector amount.
 *
 * <p>
 * A translation matrix has the invariant that the upper-left
 * 2x2 block is equal to the 2x2 identity matrix. This class
 * preserves that invariant.
 *
 */
public class Translation extends AbstractAffinity {

	/**
	 * Initializes a translation by the vector {@code (0, 0)}.
	 */
	public Translation() {
		super();
		this.setVector(0,0);
	}

	/**
	 * Initializes a translation by the vector {@code (x, y)}.
	 *
	 * @param x the amount to move a point in the x direction
	 * @param y the amount to move a point in the y direction
	 */
	public Translation(double x, double y) {
		super();
		this.setVector(x,y);
	}

	/**
	 * Translates the specified point using this transformation
	 * changing the coordinates of the point.
	 *
	 * <p>
	 * This method provides a more efficient
	 * implementation than the superclass method by avoiding
	 * the full 3x3 matrix multiplication.
	 *
	 * @param p a point to translate
	 * @return the translated point p
	 */
	@Override
	public Point2 transform(Point2 p) {
		Vector2 vec = this.getVector();
		p.set(vec.x()+ p.x(), vec.y()+ p.y());
		return p;
	}

	/**
	 * Returns the vector corresponding to this translation.
	 *
	 * @return the vector corresponding to this translation
	 */
	public Vector2 getVector() {
		return new Vector2(this.mat.get(0,2),this.mat.get(1,2));
	}

	/**
	 * Changes the translation vector for this transformation
	 * returning the previous vector.
	 *
	 * <p>
	 * This method re-computes the elements of the matrix
	 * representing this transformation.
	 *
	 * @param x the amount to move a point in the x direction
	 * @param y the amount to move a point in the y direction
	 */
	public void setVector(double x, double y) {
		this.mat.identity_matrix();
		this.mat.set_element(x,0,2);
		this.mat.set_element(y,1,2);
	}

	/**
	 * Returns the string starting with the string {@code "translation: "}
	 * followed by the string representation of the superclass.
	 *
	 * @return a string representation of this transformation
	 */
	@Override
	public String toString() {
		return "translation: " + super.toString();
	}
}

package ca.queensu.cs.cisc124.asgmt.a4;

import ca.queensu.cs.cisc124.notes.basics.geometry.Point2;

/**
 * A 2D rotation transformation. A rotation rotates
 * points about the origin {@code (0, 0)} by some
 * angle specified in radians.
 *
 * <p>
 * A rotation matrix has the invariant that the determinant
 * of the matrix is equal to 1. This class preserves that
 * invariant.
 *
 * <p>
 * Rotations that differ by an integer multiple of {@code 2 * pi}
 * radians are equal.
 *
 */
public class Rotation extends AbstractAffinity {

	// the angle of rotation for this rotation
	private double radians;

	/**
	 * Initializes a rotation of 0 radians.
	 */
	public Rotation() {
		super();
		this.radians = 0;
	}

	/**
	 * Initializes a rotation of the specified number
	 * of radians.
	 *
	 * @param radians the angle of rotation
	 */
	public Rotation(double radians) {
		super();
		this.setRadians(radians);
	}

	/**
	 * Initializes this rotation by copying the specified
	 * rotation.
	 *
	 * @param other the rotation to copy
	 */
	public Rotation(Rotation other) {
		super();
		this.radians = other.getRadians();
		this.mat = other.mat;
		this.setRadians(other.getRadians());
	}

	/**
	 * Rotates the specified point using this transformation
	 * changing the coordinates of the point.
	 *
	 * <p>
	 * This method provides a slightly more efficient
	 * implementation than the superclass method by avoiding
	 * the full 3x3 matrix multiplication.
	 * @param p a point to rotate
	 * @return the rotated point p
	 */
	@Override
	public Point2 transform(Point2 p) {
		double x = (Math.cos(getRadians()) * p.x()) - (Math.sin(getRadians()) * p.y());
		double y = (Math.sin(getRadians()) * p.x()) + (Math.cos(getRadians()) * p.y());
		p.set(x, y);
		return p;
	}

	/**
	 * Returns the angle of rotation for this transformation.
	 *
	 * @return the angle of rotation for this transformation
	 */
	public double getRadians() {
		// ALREADY DONE FOR YOU
		return this.radians;
	}

	/**
	 * Changes the angle of rotation for this transformation.
	 *
	 * <p>
	 * This method re-computes the elements of the matrix
	 * representing this transformation if the specified
	 * angle is different from the current angle of rotation.
	 *
	 * @param radians the angle of rotation
	 * @return the previous angle of rotation
	 */
	public void setRadians(double radians) {
		this.radians = radians;
		this.mat.identity_matrix();
		this.mat.set_element(Math.cos(radians),0,0);
		this.mat.set_element(-1*(Math.sin(radians)),0,1);
		this.mat.set_element(Math.sin(radians),1,0);
		this.mat.set_element(Math.cos(radians),1,1);
	}

	/**
	 * Returns the string starting with the string {@code "rotation: "}
	 * followed by the string representation of the superclass.
	 *
	 * @return a string representation of this transformation
	 */
	@Override
	public String toString() {
		return "rotation: " + super.toString();
	}
}

package ca.queensu.cs.cisc124.asgmt.a4;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.junit.rules.Timeout;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Matrix3Test {

	@Rule
    public Timeout globalTimeout = Timeout.seconds(1);
	
	

	/**
	 * Tests if the we can get the 9 elements of the matrix
	 * without throwing an exception or getting Double.NaN
	 */
	@Test
	public void test01_ctor() {
		Matrix3 a = new Matrix3();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				double aij = a.get(i, j);
				assertFalse(Double.isNaN(aij));
			}
		}
	}

	/**
	 * Tries to copy the matrix [1 2 3; 4 5 6; 7 8 9]
	 * Requires a working set method
	 */
	@Test
	public void test02_copyCtor() {
		Matrix3 a = new Matrix3();
		Matrix3 exp = new Matrix3();
		double aij = 1.0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				a.set(i, j, aij);
				exp.set(i, j, aij++);
			}
		}
		Matrix3 b = new Matrix3(a);
		if (!Tests.areSimilar(b, exp)) {
			fail("expected:\n" + Tests.toString(exp) + "got:\n" + Tests.toString(a));
		}
	}


	@Test
	public void test03a_postmultiply() {
		Matrix3 a = Tests.identity();
		Matrix3 b = new Matrix3();
		a.postmultiply(b);
		
		Matrix3 exp = new Matrix3();
		if (!Tests.areSimilar(a, exp)) {
			fail("expected:\n" + Tests.toString(exp) + "got:\n" + Tests.toString(a));
		}
	}
	
	@Test
	public void test03b_postmultiply() {
		double[] avals = {-1.1, 1.6, -3.3, 2.1, -4.7, -2.2, -4.5, -4.0, 3.2};
		double[] bvals = {1.9, -1.8,  4.5, -4.7, -0.6, -1.2,  2.7,  3.0, -3.1};
		double[] xvals = {-18.52, -8.88, 3.36, 20.14, -7.56, 21.91, 18.89, 20.1, -25.37};
		
		Matrix3 a = Tests.from(avals);
		Matrix3 b = Tests.from(bvals);
		Matrix3 exp = Tests.from(xvals);
		
		a.postmultiply(b);
		
		if (!Tests.areSimilar(a, exp)) {
			fail("expected:\n" + Tests.toString(exp) + "got:\n" + Tests.toString(a));
		}
	}
	
	@Test
	public void test04a_premultiply() {
		Matrix3 a = Tests.identity();
		Matrix3 b = new Matrix3();
		a.premultiply(b);
		
		Matrix3 exp = new Matrix3();
		if (!Tests.areSimilar(a, exp)) {
			fail("expected:\n" + Tests.toString(exp) + "got:\n" + Tests.toString(a));
		}
	}
	
	@Test
	public void test04b_premultiply() {
		double[] avals = {-1.1, 1.6, -3.3, 2.1, -4.7, -2.2, -4.5, -4.0, 3.2};
		double[] bvals = {1.9, -1.8,  4.5, -4.7, -0.6, -1.2,  2.7,  3.0, -3.1};
		double[] xvals = {-26.12, -6.50, 12.09, 9.31, 0.10, 12.99, 17.28, 2.62, -25.43};
		
		Matrix3 a = Tests.from(avals);
		Matrix3 b = Tests.from(bvals);
		Matrix3 exp = Tests.from(xvals);

		a.premultiply(b);
		
		if (!Tests.areSimilar(a, exp)) {
			fail("expected:\n" + Tests.toString(exp) + "got:\n" + Tests.toString(a));
		}
	}

}

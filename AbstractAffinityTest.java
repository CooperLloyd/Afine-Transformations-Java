package ca.queensu.cs.cisc124.asgmt.a4;

import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ca.queensu.cs.cisc124.notes.basics.geometry.Point2;
import ca.queensu.cs.cisc124.notes.basics.geometry.Vector2;

import org.junit.rules.Timeout;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AbstractAffinityTest {

	@Rule
	public Timeout globalTimeout = Timeout.seconds(1);

	class TestAffinity extends AbstractAffinity {
		public TestAffinity() {
			super();
		}

		public TestAffinity(TestAffinity other) {
			super(other);
		}

		public Matrix3 getMatrix() {
			return this.mat;
		}
	}

	@Test
	public void test01_ctor() {
		TestAffinity t = new TestAffinity();
		Matrix3 a = t.getMatrix();
		Matrix3 exp = Tests.identity();
		if (!Tests.areSimilar(a, exp)) {
			fail("expected:\n" + Tests.toString(exp) + "got:\n" + Tests.toString(a));
		}
	}

	@Test
	public void test02_copyCtor() {
		TestAffinity t = new TestAffinity();
		Matrix3 a = t.getMatrix();
		double[] avals = {-1.1, 1.6, -3.3, 2.1, -4.7, -2.2, 0.0, 0.0, 1.0};
		Tests.copyFrom(a, avals);
		
		TestAffinity u = new TestAffinity(t);
		Matrix3 b = u.getMatrix();
		
		Matrix3 exp = new Matrix3();
		Tests.copyFrom(exp, avals);
		
		if (!Tests.areSimilar(b, exp)) {
			fail("expected:\n" + Tests.toString(exp) + "got:\n" + Tests.toString(b));
		}
		assertNotSame("failed to make a new copy of the matrix in the copy constructor", 
				a, b);
	}
	
	@Test
	public void test03a_transform() {
		TestAffinity t = new TestAffinity();
		Matrix3 a = t.getMatrix();
		Tests.identity(a);
		
		Point2 p = new Point2(-1.1, 2.2);
		t.transform(p);
		
		Point2 exp = new Point2(p);
		Vector2 v = exp.to(p);
		
		if (v.mag() > 1e-9) {
			fail("expected: " + exp + ", got: " + p);
		}
	}
	

	@Test
	public void test03b_transform() {
		TestAffinity t = new TestAffinity();
		Matrix3 a = t.getMatrix();
		double[] avals = {-1.1, 1.6, -3.3, 2.1, -4.7, -2.2, 0.0, 0.0, 1.0};
		Tests.copyFrom(a, avals);
		
		Point2 p = new Point2(-1.1, 2.2);
		System.out.println(p.toString());
		t.transform(p);
		System.out.println(p.toString());
		Point2 exp = new Point2(1.43, -14.85);
		Vector2 v = exp.to(p);
		
		if (v.mag() > 1e-9) {
			fail("expected: " + exp + ", got: " + p);
		}
	}


	@Test
	public void test04_copyMatrix() {
		TestAffinity t = new TestAffinity();
		Matrix3 a = t.getMatrix();
		double[] avals = {-1.1, 1.6, -3.3, 2.1, -4.7, -2.2, 0.0, 0.0, 1.0};
		Tests.copyFrom(a, avals);
		
		Matrix3 b = t.copyMatrix();
		
		Matrix3 exp = Tests.from(avals);
		
		if (!Tests.areSimilar(b, exp)) {
			fail("expected:\n" + Tests.toString(exp) + "got:\n" + Tests.toString(b));
		}
		assertNotSame("failed to make a new copy of the matrix in copyMatrix", 
				a, b);
	}

	@Test
	public void test05a_equals() {
		TestAffinity t = new TestAffinity();
		assertEquals(t, t);
	}

	@Test
	public void test05b_equals() {
		TestAffinity t = new TestAffinity();
		boolean got = t.equals(null);
		assertEquals(false, got);
	}
	
	@Test
	public void test05c_equals() {
		TestAffinity t = new TestAffinity();
		boolean got = t.equals("");
		assertEquals(false, got);
	}
	
	@Test
	public void test05d_equals() {
		TestAffinity t = new TestAffinity();
		Matrix3 a = t.getMatrix();
		System.out.println(t.getMatrix().toString());

		double[] avals = {-1.1, 1.6, -3.3, 2.1, -4.7, -2.2, 0.0, 0.0, 1.0};
		Tests.copyFrom(a, avals);
		
		Matrix3 b = Tests.from(avals);
		assertEquals(a, b);
	}
	
}

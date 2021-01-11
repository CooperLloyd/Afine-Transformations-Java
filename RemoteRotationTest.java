package ca.queensu.cs.cisc124.asgmt.a4;

import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ca.queensu.cs.cisc124.notes.basics.geometry.Point2;

import org.junit.rules.Timeout;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RemoteRotationTest {

	@Rule
	public Timeout globalTimeout = Timeout.seconds(1);
	
	@Test
	public void test01_ctor() {
		RemoteRotation t = new RemoteRotation();
		Matrix3 a = t.copyMatrix();
		Matrix3 exp = Tests.identity();
		if (!Tests.areSimilar(a, exp)) {
			fail("expected:\n" + Tests.toString(exp) + "got:\n" + Tests.toString(a));
		}
	}

	@Test
	public void test02a_ctor() {
		RemoteRotation t = new RemoteRotation(Math.PI / 2, new Point2());
		Matrix3 a = t.copyMatrix();
		
		double[] xvals = {0, -1, 0, 1, 0, 0, 0, 0, 1};
		Matrix3 exp = Tests.from(xvals);
		if (!Tests.areSimilar(a, exp)) {
			fail("expected:\n" + Tests.toString(exp) + "got:\n" + Tests.toString(a));
		}
	}
	
	@Test
	public void test02b_ctor() {
		RemoteRotation t = new RemoteRotation(Math.PI / 2, new Point2(1.5, 2.5));
		Matrix3 a = t.copyMatrix();
		
		double[] xvals = {0, -1, 4, 1, 0, 1, 0, 0, 1};
		Matrix3 exp = Tests.from(xvals);
		if (!Tests.areSimilar(a, exp)) {
			fail("expected:\n" + Tests.toString(exp) + "got:\n" + Tests.toString(a));
		}
	}

	@Test
	public void test03_copyCtor() {
		RemoteRotation t = new RemoteRotation(Math.PI / 2, new Point2(1.5, 2.5));
		RemoteRotation u = new RemoteRotation(t);
		Matrix3 a = u.copyMatrix();
		
		double[] xvals = {0, -1, 4, 1, 0, 1, 0, 0, 1};
		Matrix3 exp = Tests.from(xvals);
		if (!Tests.areSimilar(a, exp)) {
			fail("expected:\n" + Tests.toString(exp) + "got:\n" + Tests.toString(a));
		}
	}

	@Test
	public void test04a_getRadians() {
		RemoteRotation t = new RemoteRotation(Math.PI / 2, new Point2(1.5, 2.5));
		assertEquals(Math.PI / 2, t.getRadians(), 1e-9);
	}
	
	@Test
	public void test04b_getRadians() {
		RemoteRotation t = new RemoteRotation(Math.PI / 2, new Point2(1.5, 2.5));
		RemoteRotation u = new RemoteRotation(t);
		assertEquals("copy ctor not setting this.radians",
				Math.PI / 2, u.getRadians(), 1e-9);
	}

	@Test
	public void test05a_getCenter() {
		RemoteRotation t = new RemoteRotation(Math.PI / 2, new Point2(1.5, 2.5));
		assertEquals(new Point2(1.5, 2.5), t.getCenter());
	}
	
	@Test
	public void test05b_getCenter() {
		RemoteRotation t = new RemoteRotation(Math.PI / 2, new Point2(1.5, 2.5));
		RemoteRotation u = new RemoteRotation(t);
		assertEquals("copy ctor not setting this.center",
				new Point2(1.5, 2.5), u.getCenter());
	}
	
	@Test
	public void test05c_getCenter() {
		RemoteRotation t = new RemoteRotation(Math.PI / 2, new Point2(1.5, 2.5));
		
		// try to mutate the center point
		Point2 c = t.getCenter();
		c.set(0, 0);
		
		assertEquals("getCenter should return a new Point2 object",
				new Point2(1.5, 2.5), t.getCenter());
	}

	@Test
	public void test06a_setRadians() {
		RemoteRotation t = new RemoteRotation(0, new Point2(1.5, 2.5));
		t.setRadians(Math.PI / 2);
		Matrix3 a = t.copyMatrix();
		
		double[] xvals = {0, -1, 4, 1, 0, 1, 0, 0, 1};
		Matrix3 exp = Tests.from(xvals);
		if (!Tests.areSimilar(a, exp)) {
			fail("expected:\n" + Tests.toString(exp) + "got:\n" + Tests.toString(a));
		}
	}
	
	@Test
	public void test06b_getRadians() {
		RemoteRotation t = new RemoteRotation(Math.PI / 2, new Point2(1.5, 2.5));
		t.setRadians(0.1);
		assertEquals(0.1, t.getRadians(), 1e-9);
	}

	@Test
	public void test07a_setCenter() {
		RemoteRotation t = new RemoteRotation(Math.PI / 2, new Point2());
		t.setCenter(new Point2(1.5, 2.5));
		Matrix3 a = t.copyMatrix();
		
		double[] xvals = {0, -1, 4, 1, 0, 1, 0, 0, 1};
		Matrix3 exp = Tests.from(xvals);
		if (!Tests.areSimilar(a, exp)) {
			fail("expected:\n" + Tests.toString(exp) + "got:\n" + Tests.toString(a));
		}
	}
	
	@Test
	public void test07b_setCenter() {
		RemoteRotation t = new RemoteRotation(Math.PI / 2, new Point2());
		Point2 c = new Point2(1.5, 2.5);
		t.setCenter(c);
		
		assertEquals(new Point2(1.5, 2.5), t.getCenter());
	}
	
	@Test
	public void test07c_setCenter() {
		RemoteRotation t = new RemoteRotation(Math.PI / 2, new Point2());
		Point2 c = new Point2(1.5, 2.5);
		t.setCenter(c);
		
		assertNotSame("setCenter should make a new copy for the center point",
				c, t.getCenter());
	}

	@Test
	public void test08_set() {
		RemoteRotation t = new RemoteRotation(Math.PI / 2, new Point2());
		Point2 c = new Point2(1.5, 2.5);
		t.set(Math.PI / 2, c);
		Matrix3 a = t.copyMatrix();
		
		double[] xvals = {0, -1, 4, 1, 0, 1, 0, 0, 1};
		Matrix3 exp = Tests.from(xvals);
		if (!Tests.areSimilar(a, exp)) {
			fail("expected:\n" + Tests.toString(exp) + "got:\n" + Tests.toString(a));
		}
		assertNotSame("setCenter should make a new copy for the center point",
				c, t.getCenter());
	}

}

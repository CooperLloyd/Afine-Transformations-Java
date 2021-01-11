package ca.queensu.cs.cisc124.asgmt.a4;

import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ca.queensu.cs.cisc124.notes.basics.geometry.Point2;

import org.junit.rules.Timeout;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RotationTest {

	@Rule
	public Timeout globalTimeout = Timeout.seconds(1);
	
	@Test
	public void test01_ctor() {
		Rotation t = new Rotation();
		Matrix3 a = t.copyMatrix();
		Matrix3 exp = Tests.identity();
		if (!Tests.areSimilar(a, exp)) {
			fail("expected:\n" + Tests.toString(exp) + "got:\n" + Tests.toString(a));
		}
	}

	@Test
	public void test02_ctor() {
		Rotation t = new Rotation(Math.PI / 2);
		Matrix3 a = t.copyMatrix();
		
		double[] xvals = {0, -1, 0, 1, 0, 0, 0, 0, 1};
		Matrix3 exp = Tests.from(xvals);
		if (!Tests.areSimilar(a, exp)) {
			fail("expected:\n" + Tests.toString(exp) + "got:\n" + Tests.toString(a));
		}
	}

	@Test
	public void test03_copyCtor() {
		Rotation t = new Rotation(Math.PI / 2);
		Rotation u = new Rotation(t);
		Matrix3 a = t.copyMatrix();
		Matrix3 b = u.copyMatrix();
		if (!Tests.areSimilar(a, b)) {
			fail("expected:\n" + Tests.toString(a) + "got:\n" + Tests.toString(b));
		}
	}

	@Test
	public void test04_getRadians() {
		Rotation t = new Rotation(Math.PI / 2);
		double exp = Math.PI / 2;
		assertEquals(exp, t.getRadians(), 1e-9);
	}

	@Test
	public void test05_setRadians() {
		Rotation t = new Rotation();
		t.setRadians(Math.PI / 3.0);
		
		Matrix3 a = t.copyMatrix();
		
		double[] xvals = {0.5, -0.86602540378, 0, 0.86602540378, 0.5, 0, 0, 0, 1}; 
		Matrix3 exp = Tests.from(xvals);
		
		if (!Tests.areSimilar(a, exp)) {
			fail("expected:\n" + Tests.toString(exp) + "got:\n" + Tests.toString(a));
		}
	}

	@Test
	public void test06_transform() {
		Rotation t = new Rotation(Math.PI / 3.0);
		double theta = Math.toRadians(145);
		Point2 p = new Point2(Math.cos(theta), Math.sin(theta));
		System.out.println(p.x());
		t.transform(p);
		System.out.println(p.x());
		
		theta = Math.toRadians(145 + 60);
		Point2 exp = new Point2(Math.cos(theta), Math.sin(theta));
		if (p.to(exp).mag() > 1e-9) {
			fail("expected: " + exp + ", got:\n" + p);
		}
	}
}

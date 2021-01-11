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
public class TranslationTest {

	@Rule
	public Timeout globalTimeout = Timeout.seconds(1);
	
	@Test
	public void test01_ctor() {
		Translation t = new Translation();
		Matrix3 a = t.copyMatrix();
		Matrix3 exp = Tests.identity();
		if (!Tests.areSimilar(a, exp)) {
			fail("expected:\n" + Tests.toString(exp) + "got:\n" + Tests.toString(a));
		}
	}

	@Test
	public void test02_ctor() {
		Translation t = new Translation(-100.5, 51.0);
		Matrix3 a = t.copyMatrix();
		Matrix3 exp = Tests.identity();
		exp.set(0, 2, -100.5);
		exp.set(1, 2, 51.0);
		if (!Tests.areSimilar(a, exp)) {
			fail("expected:\n" + Tests.toString(exp) + "got:\n" + Tests.toString(a));
		}
	}

	@Test
	public void test03a_getVector() {
		Translation t = new Translation(-100.5, 51.0);
		Vector2 exp = new Vector2(-100.5, 51.0);
		assertEquals(exp, t.getVector());
	}
	
	@Test
	public void test03b_getVector() {
		Translation t = new Translation(-100.5, 51.0);
		
		// try to mutate v
		Vector2 v = t.getVector();
		v.set(0, 0);
		
		Vector2 exp = new Vector2(-100.5, 51.0);
		assertEquals("getVector should return a new Vector2 object",
				exp, t.getVector());
	}

	@Test
	public void test04_setVector() {
		Translation t = new Translation();
		t.setVector(-100.5, 51.0);
		
		Matrix3 a = t.copyMatrix();
		Matrix3 exp = Tests.identity();
		exp.set(0, 2, -100.5);
		exp.set(1, 2, 51.0);
		if (!Tests.areSimilar(a, exp)) {
			fail("expected:\n" + Tests.toString(exp) + "got:\n" + Tests.toString(a));
		}
	}

	@Test
	public void test05_transform() {
		Translation t = new Translation(-100.5, 51.0);
		Point2 p = new Point2(100, 100);
		t.transform(p);
		
		Point2 exp = new Point2(-0.5, 151.0);
		assertEquals(exp, p);
	}
}

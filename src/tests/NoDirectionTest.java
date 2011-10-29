package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import misc.Direction;

import org.junit.Before;
import org.junit.Test;

import exceptions.RotationNotSupportedException;

import tiles.direction.NoDirection;

public class NoDirectionTest {

	private NoDirection d;

	@Before
	public void init() {
		d = new NoDirection();
	}

	@Test
	public void testCanRotate() {
		assertFalse(d.canRotate());
	}

	@Test(expected = RotationNotSupportedException.class)
	public void testDirectionSetter() {
		d.setDirection(Direction.EAST);
	}
	
	@Test
	public void testDirectionGetter() {
		assertTrue(d.getDirection() == null);
	}

	@Test(expected = RotationNotSupportedException.class)
	public void testRotate() {
		d.rotate();
	}

}
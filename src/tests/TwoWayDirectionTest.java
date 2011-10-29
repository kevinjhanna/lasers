package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import misc.Direction;

import org.junit.Before;
import org.junit.Test;

import exceptions.RotationNotSupportedException;

import tiles.direction.TwoWayDirection;

public class TwoWayDirectionTest {

	private TwoWayDirection d;
	
	@Before
	public void init() {
		d = new TwoWayDirection(true);
	}
	
	@Test
	public void testCanRotate() {
		assertTrue(d.canRotate());
	}
	
	@Test
	public void testDirectionAccessors() {
		d.setDirection(Direction.EAST);
		assertTrue(d.getDirection() == Direction.EAST);
	}
	
	@Test
	public void testRotate() {
		d.setDirection(Direction.EAST);
		d.rotate();
		assertTrue(d.getDirection() == Direction.SOUTH);
		d.rotate();
		assertTrue(d.getDirection() == Direction.EAST);
	}
	
	@Test
	public void testInmutableCanRotate() {
		d = new TwoWayDirection(false);
		assertFalse(d.canRotate());
	}
	
	@Test(expected=RotationNotSupportedException.class)
	public void testInmutableRotate() {
		d = new TwoWayDirection(false);
		d.setDirection(Direction.SOUTH);
		d.rotate();
	}
	
}

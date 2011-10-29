package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import misc.Direction;

import org.junit.Before;
import org.junit.Test;

import exceptions.RotationNotSupportedException;

import tiles.direction.FourWayDirection;

public class FourWayDirectionTest {

	private FourWayDirection d;
	
	@Before
	public void init() {
		d = new FourWayDirection(true);
	}
	
	@Test
	public void testCanRotate() {
		assertTrue(d.canRotate());
	}
	
	@Test
	public void testDirectionAccessors() {
		d.setDirection(Direction.NORTH);
		assertTrue(d.getDirection() == Direction.NORTH);
	}
	
	@Test
	public void testRotate() {
		d.setDirection(Direction.EAST);
		d.rotate();
		assertTrue(d.getDirection() == Direction.SOUTH);
		d.rotate();
		assertTrue(d.getDirection() == Direction.WEST);
	}
	
	@Test
	public void testInmutableCanRotate() {
		d = new FourWayDirection(false);
		assertFalse(d.canRotate());
	}
	
	@Test(expected=RotationNotSupportedException.class)
	public void testInmutableRotate() {
		d = new FourWayDirection(false);
		d.setDirection(Direction.SOUTH);
		d.rotate();
	}
	
}

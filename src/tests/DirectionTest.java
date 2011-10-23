package tests;

import junit.framework.TestCase;
import misc.Direction;

import org.junit.Test;

public class DirectionTest extends TestCase {
	
	@Test
	public void testRotate() {
		assertEquals(Direction.EAST, Direction.NORTH.rotate(1));
		assertEquals(Direction.SOUTH, Direction.NORTH.rotate(2));
		assertEquals(Direction.NORTH, Direction.NORTH.rotate(4));
	}

}

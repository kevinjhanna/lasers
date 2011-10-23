package tests;

import junit.framework.TestCase;
import misc.Direction;

import org.junit.Test;

public class DirectionTest extends TestCase {
	
	@Test
	public void testTurn() {
		assertEquals(Direction.EAST, Direction.NORTH.turn());
		assertEquals(Direction.SOUTH, Direction.NORTH.turn(2));
		assertEquals(Direction.NORTH, Direction.NORTH.turn(4));		
	}
	
	@Test
	public void testOpposite() {
		assertEquals(Direction.NORTH, Direction.SOUTH.getOpposite());
		assertEquals(Direction.EAST, Direction.WEST.getOpposite());
	}

}

package tests;

import static org.junit.Assert.assertEquals;
import misc.Direction;
import misc.Position;

import org.junit.Test;

public class PositionTest {

	@Test
	public void displaceTest() {
		Position base = new Position(1, 1);
		
		assertEquals(new Position(1, 2), base.displace(Direction.EAST));
		assertEquals(new Position(2, 1), base.displace(Direction.SOUTH));
	}

}

package misc;

/**
 * Enumeration of cartesian directions with methods to operate on them.
 */
public enum Direction {

	EAST, SOUTH, WEST, NORTH;

	/**
	 * Returns the direction corresponding to the integer parameter. It starts
	 * from 0 at EAST and moves clockwise.
	 * 
	 * @param n
	 * @return Direction
	 */
	public static Direction fromInteger(int n) {
		Direction[] values = Direction.values();
		return values[n];
	}

	/**
	 * Returns a direction that is 90 degrees times the parameter rotated
	 * clockwise from the original direction.
	 * 
	 * @param times
	 * @return Direction
	 */
	public Direction turn(int times) {
		Direction[] values = Direction.values();
		return values[(this.ordinal() + times) % values.length];
	}

	/**
	 * Returns a direction that is 90 degrees rotated clockwise from the
	 * original direction.
	 * 
	 * @return Direction
	 */
	public Direction turn() {
		return turn(1);
	}

	/**
	 * Returns the opposite direction.
	 * 
	 * @return Direction
	 */
	public Direction getOpposite() {
		return turn(2);
	}

	/**
	 * Compares two directions based on actual direction and not sense.
	 * 
	 * @param other
	 * @return Direction
	 */
	public boolean equalsIgnoreSense(Direction other) {
		return (this.ordinal() + other.ordinal()) % 2 == 0;
	}

}

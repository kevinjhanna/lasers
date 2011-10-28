package misc;

/**
 * Enumeration of cartesian directions with methods to operate on them
 */
public enum Direction {

	EAST, SOUTH, WEST, NORTH;

	public static Direction fromInteger(int n) {
		Direction[] values = Direction.values();
		return values[n];
	}

	public Direction turn(int times) {
		Direction[] values = Direction.values();
		return values[(this.ordinal() + times) % values.length];
	}

	public Direction turn() {
		return turn(1);
	}

	public Direction getOpposite() {
		return turn(2);
	}

	public boolean equalsIgnoreSense(Direction other) {
		return (this.ordinal() + other.ordinal()) % 2 == 0;
	}

}

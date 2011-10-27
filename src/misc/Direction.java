package misc;
public enum Direction {

	EAST, SOUTH, WEST, NORTH;
	
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

	public static Direction fromInteger(int n) {
		Direction[] values = Direction.values();
		return values[n];
	}
}

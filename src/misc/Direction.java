package misc;
public enum Direction {

	NORTH, EAST, SOUTH, WEST;
	
	public Direction rotate(int times) {
		Direction[] values = Direction.values();
		return values[(this.ordinal() + times) % values.length];
	}
}

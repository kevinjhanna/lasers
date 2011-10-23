package misc;
public enum Direction {

	NORTH, EAST, SOUTH, WEST;
	
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
}

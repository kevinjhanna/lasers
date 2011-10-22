package misc;
public enum Direction {

	NORTH(0), EAST(1), SOUTH(2), WEST(3);

	private final int dir;
	
	Direction(int dir) {
		this.dir = dir;
	}
	
	public Direction rotate(int times) {
		Direction[] values = Direction.values();
		return values[(this.ordinal() + times) % values.length];
	}
}

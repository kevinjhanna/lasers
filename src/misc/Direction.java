package misc;
public enum Direction {

	NORTH(-1, 0), EAST(0, 1), SOUTH(1, 0), WEST(0, -1);
	
	
	public final int row;
	public final int column;
	
	Direction(int row, int column){
		// TODO maybe change into a Position
		this.row = row;
		this.column = column;
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
}

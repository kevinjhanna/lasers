package tiles;

import misc.Direction;

public class SplitMirror extends Tile implements Rotatable {

	public static final String name = "SplitMirror";
	public Direction direction;

	public SplitMirror(Direction direction) {
		this.direction = direction;
	}

	@Override
	public Direction getDirection() {
		return direction;
	}

	@Override
	public void rotate() {
		direction = direction.turn();
	}
}

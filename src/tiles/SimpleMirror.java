package tiles;

import misc.Direction;

public class SimpleMirror extends Tile implements Rotatable {
	
	private Direction direction;

	public SimpleMirror(Direction direction) {
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

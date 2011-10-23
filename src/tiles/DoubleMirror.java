package tiles;

import misc.Direction;

public class DoubleMirror extends Tile implements Rotatable {

	private Direction direction;
	
	public DoubleMirror(Direction orientation) {
		this.direction = orientation;
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

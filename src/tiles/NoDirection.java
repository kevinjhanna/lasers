package tiles;

import exceptions.RotationNotSupportedException;
import misc.Direction;

public class NoDirection implements DirectionComponent {
	
	public boolean canRotate() {
		return false;
	}

	public Direction getDirection() {
		return Direction.EAST;
	}
	
	public void setDirection(Direction direction) {
		throw new RotationNotSupportedException();
	}
	
	public void rotate() {
		throw new RotationNotSupportedException();
	}

}

package tiles;

import exceptions.RotationNotSupportedException;
import misc.Direction;

public class NoRotation implements RotationComponent {
	
	@Override
	public boolean canRotate() {
		return false;
	}

	public Direction getDirection() {
		return Direction.NORTH;
	}
	
	public void setDirection(Direction direction) {
		throw new RotationNotSupportedException();
	}
	
	public void rotate() {
		throw new RotationNotSupportedException();
	}

}

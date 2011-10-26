package tiles;

import exceptions.RotationNotSupportedException;
import misc.Direction;

public class NoRotation implements RotationComponent {

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

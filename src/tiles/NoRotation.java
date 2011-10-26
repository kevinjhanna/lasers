package tiles;

import exceptions.RotationNotSupportedException;
import misc.Direction;

public class NoRotation implements RotationComponent {

	@Override
	public Direction getDirection() {
		return Direction.NORTH;
	}
	
	@Override
	public void setDirection(Direction direction) {
		throw new RotationNotSupportedException();
	}
	
	@Override
	public void rotate() {
		throw new RotationNotSupportedException();
	}

}

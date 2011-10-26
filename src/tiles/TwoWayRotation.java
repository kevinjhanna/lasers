package tiles;

import misc.Direction;
import exceptions.RotationNotSupportedException;

public class TwoWayRotation implements RotationComponent {

	public Direction direction;
	
	@Override
	public Direction getDirection() {
		return direction;
	}
	
	@Override
	public void setDirection(Direction direction) {
		if (direction != Direction.NORTH || direction != Direction.EAST) {
			throw new RotationNotSupportedException();
		}
	}
	
	@Override
	public void rotate() {
		if (direction == Direction.NORTH) {
			direction = Direction.EAST;
		} else {
			direction = Direction.NORTH;
		}
	}

}

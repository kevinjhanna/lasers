package tiles;

import misc.Direction;
import exceptions.RotationNotSupportedException;

public class TwoWayRotation implements RotationComponent {

	public Direction direction;
	
	public Direction getDirection() {
		return direction;
	}
	
	public void setDirection(Direction direction) {
		if (direction == null) {
			throw new IllegalArgumentException();
		}
		if (direction != Direction.NORTH && direction != Direction.EAST) {
			throw new RotationNotSupportedException();
		}
		this.direction = direction;
	}
	
	public void rotate() {
		if (direction == Direction.NORTH) {
			direction = Direction.EAST;
		} else {
			direction = Direction.NORTH;
		}
	}

}

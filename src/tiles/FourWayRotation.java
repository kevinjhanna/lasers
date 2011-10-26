package tiles;

import misc.Direction;

public class FourWayRotation implements RotationComponent {

	public Direction direction;
	
	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		if (direction == null) {
			throw new IllegalArgumentException();
		}
		this.direction = direction;
	}
	
	public void rotate() {
		direction = direction.turn();
	}

}

package tiles;

import misc.Direction;

public class FourWayRotation implements RotationComponent {

	public Direction direction;
	
	@Override
	public Direction getDirection() {
		return direction;
	}

	@Override
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	@Override
	public void rotate() {
		direction = direction.turn();
	}

}

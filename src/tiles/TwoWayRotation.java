package tiles;

import misc.Direction;
import exceptions.RotationNotSupportedException;

public class TwoWayRotation implements RotationComponent {

	private Direction direction;
	private boolean mutable;
	
	public TwoWayRotation(boolean mutable) {
		this.mutable = mutable;
	}
	
	@Override
	public boolean canRotate() {
		return mutable;
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	public void setDirection(Direction direction) {
		if (direction == null) {
			throw new IllegalArgumentException();
		}
		if (direction.ordinal() > 1) {
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

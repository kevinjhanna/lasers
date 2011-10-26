package tiles;

import misc.Direction;
import exceptions.RotationNotSupportedException;

public class FourWayRotation implements RotationComponent {

	public Direction direction;
	public boolean mutable;
	
	public FourWayRotation(boolean mutable) {
		this.mutable = mutable;
	}
	
	@Override
	public boolean canRotate() {
		return mutable;
	}
	
	@Override
	public Direction getDirection() {
		return direction;
	}

	@Override
	public void setDirection(Direction direction) {
		if (!mutable) {
			throw new RotationNotSupportedException();
		}
		if (direction == null) {
			throw new IllegalArgumentException();
		}
		this.direction = direction;
	}
	
	@Override
	public void rotate() {
		direction = direction.turn();
	}

}

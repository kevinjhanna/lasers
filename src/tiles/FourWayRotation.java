package tiles;

import misc.Direction;
import exceptions.RotationNotSupportedException;

public class FourWayRotation implements RotationComponent {

	public Direction direction;
	public boolean mutable;
	
	public FourWayRotation(boolean mutable) {
		this.mutable = mutable;
	}
	
	public boolean canRotate() {
		return mutable;
	}
	
	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		if (!mutable) {
			throw new RotationNotSupportedException();
		}
		if (direction == null) {
			throw new IllegalArgumentException();
		}
		this.direction = direction;
	}
	
	public void rotate() {
		direction = direction.turn();
	}

}

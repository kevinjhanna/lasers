package tiles;

import misc.Direction;
import exceptions.RotationNotSupportedException;

public class FourWayDirection implements DirectionComponent {

	private static final long serialVersionUID = 827751127088031392L;

	public Direction direction;
	public boolean mutable;
	
	public FourWayDirection(boolean mutable) {
		this.mutable = mutable;
	}
	
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
		this.direction = direction;
	}
	
	public void rotate() {
		if (!mutable) {
			throw new RotationNotSupportedException();
		}
		direction = direction.turn();
	}

}

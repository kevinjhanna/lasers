package tiles.direction;

import misc.Direction;
import exceptions.RotationNotSupportedException;

/**
 * Four-way (every direction) direction component.
 */
public class FourWayDirection implements DirectionComponent {

	private static final long serialVersionUID = -3350778156676049695L;

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

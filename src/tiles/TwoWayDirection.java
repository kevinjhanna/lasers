package tiles;

import misc.Direction;
import exceptions.RotationNotSupportedException;

public class TwoWayDirection implements DirectionComponent {

	private Direction direction;
	private boolean mutable;

	public TwoWayDirection(boolean mutable) {
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
		if (direction.ordinal() > 1) {
			throw new RotationNotSupportedException();
		}
		this.direction = direction;
	}

	public void rotate() {
		if (!mutable) {
			throw new RotationNotSupportedException();
		}
		direction = (direction == Direction.NORTH) ? Direction.EAST
				: Direction.NORTH;
	}

}

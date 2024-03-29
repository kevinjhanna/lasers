package tiles.direction;

import misc.Direction;
import exceptions.RotationNotSupportedException;

/**
 * Two-way (horizontal or vertical) direction component.
 */
public class TwoWayDirection implements DirectionComponent {

	private static final long serialVersionUID = 7558841317350855418L;

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
		direction = (direction == Direction.SOUTH) ? Direction.EAST
				: Direction.SOUTH;
	}

}

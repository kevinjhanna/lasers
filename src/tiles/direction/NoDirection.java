package tiles.direction;

import exceptions.RotationNotSupportedException;
import misc.Direction;

/**
 * Direction component for tiles that are not orientable.
 */
public class NoDirection implements DirectionComponent {


	public boolean canRotate() {
		return false;
	}

	public Direction getDirection() {
		return null;
	}

	public void setDirection(Direction direction) {
		throw new RotationNotSupportedException();
	}

	public void rotate() {
		throw new RotationNotSupportedException();
	}

}

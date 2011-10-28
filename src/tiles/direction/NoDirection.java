package tiles.direction;

import exceptions.RotationNotSupportedException;
import misc.Direction;

public class NoDirection implements DirectionComponent {

	private static final long serialVersionUID = -6917690184196042813L;

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

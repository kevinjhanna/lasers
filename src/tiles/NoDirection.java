package tiles;

import exceptions.RotationNotSupportedException;
import misc.Direction;

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

package tiles.direction;

import java.io.Serializable;

import misc.Direction;

public interface DirectionComponent extends Serializable {

	public boolean canRotate();
	
	public void rotate();
	
	public Direction getDirection();
	
	public void setDirection(Direction direction);
	
}

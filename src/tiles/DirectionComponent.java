package tiles;

import java.io.Serializable;

import misc.Direction;

interface DirectionComponent extends Serializable {

	public boolean canRotate();
	
	public void rotate();
	
	public Direction getDirection();
	
	public void setDirection(Direction direction);
	
}

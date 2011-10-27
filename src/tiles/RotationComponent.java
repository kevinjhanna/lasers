package tiles;

import java.io.Serializable;

import misc.Direction;

interface RotationComponent extends Serializable{

	public boolean canRotate();
	
	public void rotate();
	
	public Direction getDirection();
	
	public void setDirection(Direction direction);
	
}

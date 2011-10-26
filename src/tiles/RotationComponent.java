package tiles;

import misc.Direction;

interface RotationComponent {

	public boolean canRotate();
	
	public void rotate();
	
	public Direction getDirection();
	
	public void setDirection(Direction direction);
	
}

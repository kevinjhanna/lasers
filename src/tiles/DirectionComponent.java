package tiles;

import misc.Direction;

interface DirectionComponent {

	public boolean canRotate();
	
	public void rotate();
	
	public Direction getDirection();
	
	public void setDirection(Direction direction);
	
}

package tiles.direction;


import java.io.Serializable;

import misc.Direction;

/**
 * Component that handles tile direction.
 */
public interface DirectionComponent extends Serializable{

	/**
	 * Returns true if user can rotate the tile during the game.
	 * 
	 * @return boolean
	 */
	public boolean canRotate();

	/**
	 * Rotates the tile clockwise.
	 */
	public void rotate();

	/**
	 * Returns the tile direction.
	 * 
	 * @return Direction
	 */
	public Direction getDirection();

	/**
	 * Sets the tile direction.
	 * 
	 * @param direction
	 */
	public void setDirection(Direction direction);

}

package tiles;

import misc.Direction;
import exceptions.RotationNotSupportedException;
import game.TileDrawer;

/**
 * Abstract class that models a game tile
 */
public abstract class Tile implements Drawable {

	protected final RotationComponent rotation = new FourWayRotation();
	
	/* each react depends on the tile implementation*/
	public void react(Ray ray){
		ray.moveStraight();
	}

	public boolean isEmpty() {
		return false;
	}

	/**
	 * Returns whether the tile is fixed
	 * 
	 * @return boolean
	 */
	public boolean isFixed() {
		return false;
	}

	/**
	 * Returns the direction of the tile. For tiles that have no defined
	 * direction, return a default of NORTH
	 * 
	 * @see Direction
	 * @return Direction
	 */
	public Direction getDirection() {
		return rotation.getDirection();
	}

	/**
	 * Rotates the tile
	 * 
	 * @throws RotationNotSupportedException
	 *             If the tile does not support rotation
	 */	
	public void rotate() {
		rotation.rotate();
	}

	/**
	 * Returns a representation of the tile using the TileDrawer passed as
	 * parameter
	 * 
	 * @return T An object of the parametrization of the TileDrawer passed as
	 *         parameter
	 */
	public <T> T draw(TileDrawer<T> tileDrawer) {
		return tileDrawer.draw(this.getClass().getName());
	}
}

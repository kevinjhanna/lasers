package tiles;

import exceptions.RotationNotSupportedException;
import misc.Direction;
import game.TileDrawer;

/**
 * Abstract class that models a game tile
 */
public abstract class Tile implements Drawable {

	/**
	 * Returns whether the tile is empty
	 * 
	 * @return boolean
	 */
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
		return Direction.NORTH;
	}

	/**
	 * Rotates the tile
	 * 
	 * @throws RotationNotSupportedException
	 *             If the tile does not support rotation
	 */
	public void rotate() throws RotationNotSupportedException {
		throw new RotationNotSupportedException();
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

package tiles;

import exceptions.RotationNotSupportedException;
import game.Ray;
import game.TileDrawer;
import misc.Direction;

/**
 * Abstract class that models a game tile
 */
public abstract class Tile implements Drawable {

	protected RotationComponent rotation;
	
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
	
	public final RotationComponent getRotationComponent() {
		return rotation;
	}

	/**
	 * Returns the direction of the tile. For tiles that have no defined
	 * direction, return a default of NORTH
	 * 
	 * @see Direction
	 * @return Direction
	 */
	public final Direction getDirection() {
		return rotation.getDirection();
	}

	/**
	 * Rotates the tile
	 * 
	 * @throws RotationNotSupportedException
	 *             If the tile does not support rotation
	 */	
	public final void rotate() {
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

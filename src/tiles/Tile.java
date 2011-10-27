package tiles;

import java.awt.Color;

import exceptions.RotationNotSupportedException;
import game.Ray;
import game.TileDrawer;
import misc.Direction;

/**
 * Abstract class that models a game tile
 */
public abstract class Tile implements Drawable {

	private RotationComponent rotation;
	private Color[] rays = new Color[4];

	protected Tile() {
		rotation = getRotationComponent();
	}

	public void hit(Ray ray) {
		setRay(ray.getDirection(), ray.getColor());
		setRay(ray.getDirection().getOpposite(), ray.getColor());
		ray.move();
	}

	public void clearRays() {
		for (int i = 0; i < 4; i++) {
			rays[i] = null;
		}
	}

	protected final void setRay(Direction d, Color c) {
		rays[d.ordinal()] = c;
	}
	
	protected final Color getRay(Direction d) {
		return rays[d.ordinal()];
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
	public final Direction getDirection() {
		return rotation.getDirection();
	}

	protected final void setDirection(Direction direction) {
		rotation.setDirection(direction);
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

	protected abstract RotationComponent getRotationComponent();

}

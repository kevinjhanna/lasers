package tiles;

import exceptions.RotationNotSupportedException;
import game.Ray;
import game.TileDrawer;
import misc.Direction;

/**
 * Abstract class that models a game tile
 */
public abstract class Tile implements Drawable {

	private RotationComponent rotation;

	protected Tile() {
		rotation = getRotationComponent();
	}

	public void hit(Ray ray) {
	}

	public void clearRays() {
	}

	/**
	 * Returns true if the tile is empty. Empty tiles should override this
	 * method
	 * 
	 * @return boolean
	 */
	public boolean isEmpty() {
		return false;
	}

	/**
	 * Returns true if the tile is fixed in its position. Fixed tiles should
	 * override this method
	 * 
	 * @return boolean
	 */
	public boolean isFixed() {
		return false;
	}

	/**
	 * Returns true if the tile supports rotation
	 * 
	 * @return boolean
	 */
	public final boolean canRotate() {
		return rotation.canRotate();
	}

	/**
	 * Returns the current direction of the tile
	 * 
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
	 */
	public final void rotate() {
		rotation.rotate();
	}

	/**
	 * Returns a visual representation of the tile in its current state to use
	 * in the view
	 * 
	 * @param drawer
	 *            The TileDrawer that is going to render the tile representation
	 */
	public <T> T draw(TileDrawer<T> drawer) {
		return drawer.draw(this.getClass().getName());
	}

	protected abstract RotationComponent getRotationComponent();

}

package tiles;

import game.Ray;
import game.TileDrawer;
import misc.Direction;

/**
 * Abstract class that models a game tile
 */
public abstract class Tile implements Drawable {

	private DirectionComponent direction;

	protected Tile() {
		direction = getDirectionComponent();
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
		return direction.canRotate();
	}

	/**
	 * Returns the current direction of the tile
	 * 
	 * @return Direction
	 */
	public final Direction getDirection() {
		return direction.getDirection();
	}

	protected final void setDirection(Direction d) {
		direction.setDirection(d);
	}

	/**
	 * Rotates the tile
	 */
	public final void rotate() {
		direction.rotate();
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

	protected abstract DirectionComponent getDirectionComponent();

}

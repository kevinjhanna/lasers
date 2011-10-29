package tiles;

import game.Ray;

import java.awt.Color;
import java.io.Serializable;

import tiles.direction.DirectionComponent;
import tiles.propagation.PropagationComponent;

import misc.Direction;

/**
 * An abstract class that models a game tile.
 */
public abstract class Tile implements Drawable, Serializable {

	private static final long serialVersionUID = -3314285484900811741L;

	private DirectionComponent direction;
	private PropagationComponent propagation;

	protected Tile() {
		initializeComponents();
	}

	private void initializeComponents() {
		direction = getDirectionComponent();
		propagation = getPropagationComponent(this);
	}

	public Ray hit(Ray ray) {
		return propagation.process(ray);
	}

	public void clearRays() {
		propagation.clear();
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
	 * Returns the tile color or null if tile does has no specific color
	 * 
	 * @return Color
	 */
	public Color getColor() {
		return null;
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
		if (direction == null) {
			throw new IllegalArgumentException();
		}
		direction.setDirection(d);
	}

	/**
	 * Rotates the tile
	 */
	public final void rotate() {
		direction.rotate();
	}

	protected abstract DirectionComponent getDirectionComponent();

	protected abstract PropagationComponent getPropagationComponent(Tile tile);

	public Iterable<DrawableLayer> getUnderlay() {
		return propagation.getRays();
	}

	public Iterable<DrawableLayer> getOverlay() {
		return null;
	}

	public boolean hasRays() {
		return propagation.hasRays();
	}

	public boolean hasRay(Color color) {
		return propagation.hasRay(color);
	}
}

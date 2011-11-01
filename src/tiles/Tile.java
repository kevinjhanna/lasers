package tiles;

import game.Beam;
import game.Cell;
import game.Ray;

import java.awt.Color;
import java.io.Serializable;
import java.util.Stack;

import misc.Direction;
import tiles.direction.DirectionComponent;
import tiles.propagation.PropagationComponent;

/**
 * An abstract class that models a game tile.
 */
public abstract class Tile implements Cell, Serializable {

	private static final long serialVersionUID = -3314285484900811741L;

	private DirectionComponent direction;
	private PropagationComponent propagation;

	/**
	 * Abstract tile constructor. Subclasses should call this constructor in
	 * order to load their components.
	 */
	protected Tile() {
		initializeComponents();
	}

	/**
	 * Initializes the tile components
	 */
	private void initializeComponents() {
		direction = getDirectionComponent();
		propagation = getPropagationComponent(this);
	}

	/**
	 * Processes an incoming ray with the defined propagation component.
	 * 
	 * @param ray
	 * @param bifurcations
	 */
	public void hit(Ray ray, Stack<Ray> bifurcations) {
		propagation.process(ray, bifurcations);
	}

	/**
	 * Clears the tile rays.
	 */
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
	 * Rotates the tile.
	 */
	public final void rotate() {
		direction.rotate();
	}

	/**
	 * Returns the list of beams that run across this tile.
	 * 
	 * @return Iterable
	 */
	public Beam getBeam(Direction direction) {
		return propagation.getBeam(direction);
	}

	/**
	 * Returns true if there is a ray currently passing through the tile.
	 * 
	 * @return boolean
	 */
	public boolean hasBeams() {
		return propagation.hasBeams();
	}

	/**
	 * Returns true if a ray of the specified color is currently passing through
	 * the tile.
	 * 
	 * @param color
	 * @return boolean
	 */
	public boolean hasBeam(Color color) {
		return propagation.hasBeam(color);
	}

	/**
	 * Gets the direction component that the tile will use.
	 * 
	 * @return DirectionComponent
	 */
	protected abstract DirectionComponent getDirectionComponent();

	/**
	 * Gets the propagation component that the tile will use.
	 * 
	 * @param tile
	 * @return PropagationComponent
	 */
	protected abstract PropagationComponent getPropagationComponent(Tile tile);

}

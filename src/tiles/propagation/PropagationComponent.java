	package tiles.propagation;

import game.Beam;
import game.Ray;
import gui.ImageUtils;

import java.awt.Color;
import java.io.IOException;
import java.io.Serializable;
import java.util.Stack;

import misc.Direction;
import tiles.Tile;

/**
 * The base propagation component that defines public methods and encapsulates
 * access to tile state in subclasses.
 * 
 * @see Beam
 */
public abstract class PropagationComponent implements Serializable {

	private static final long serialVersionUID = -6724503922941506704L;

	private Tile tile;
	private transient Beam[] beams;

	/**
	 * Abstract constructor. Subclasses must call this in order to access tile
	 * properties.
	 * 
	 * @param tile
	 */
	protected PropagationComponent(Tile tile) {
		this.tile = tile;
		initialize();
	}

	/**
	 * Initializes the component.
	 * 
	 * @param ray
	 */
	private void initialize() {
		beams = new Beam[4];
	}

	/**
	 * Processes the incoming ray.
	 * 
	 * @param ray
	 * @param bifurcations 
	 */
	public abstract void process(Ray ray, Stack<Ray> bifurcations);

	/**
	 * Returns the tile direction.
	 */
	protected final Direction getDirection() {
		return tile.getDirection();
	}

	/**
	 * Returns the tile color.
	 * 
	 * @return Color
	 */
	protected final Color getColor() {
		return tile.getColor();
	}

	/**
	 * Sets the ray origin.
	 * 
	 * @param ray
	 */
	protected void setOrigin(Ray ray) {
		Direction origin = ray.getDirection().getOpposite();
		setBeam(origin, ray);
	}

	/**
	 * Clears all rays from the tile.
	 */
	public void clear() {
		for (int i = 0; i < 4; i++) {
			beams[i] = null;
		}
	}

	/**
	 * Sets a ray in the tile, if a ray has been already set in that direction,
	 * performs color mix and updates the incoming ray.
	 * 
	 * @param direction
	 * @param ray
	 */
	protected final void setBeam(Direction direction, Ray ray) {
		Ray clone = ray.clone();
		Beam existing = getBeam(direction);
		if (existing != null) {
			clone.setColor(ImageUtils.mix(ray.getColor(), existing.getColor()));
		}
		// Prevent infinite cycles
		if (existing != null && clone.getColor().equals(existing.getColor())) {
			ray.stop();
		} else {
			clone.setDirection(direction);
			beams[direction.ordinal()] = clone;
		}
	}

	/**
	 * Returns the ray at the specified direction
	 * 
	 * @param direction
	 * @return Beam
	 */
	public Beam getBeam(Direction direction) {
		return beams[direction.ordinal()];
	}

	/**
	 * Returns whether the tile has rays
	 * 
	 * @return boolean
	 */
	public boolean hasBeams() {
		for (Beam beam : beams) {
			if (beam != null) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns if the tile has a ray of the color specified.
	 * 
	 * @param color
	 * @return boolean
	 */
	public boolean hasBeam(Color color) {
		for (Beam beam : beams) {
			if (beam != null && beam.getColor().equals(color)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Initializes the component after a serialized read.
	 * 
	 * @return Object
	 * @throws IOException
	 */
	public Object readResolve() throws IOException {
		initialize();
		return this;
	}

}

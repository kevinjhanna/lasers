package game;

import java.awt.Color;

import misc.Direction;

/**
 * Interface that represents a drawable object.
 */
public interface Drawable {

	/**
	 * Returns the color of the drawable object or null if the object is not
	 * coloreable.
	 * 
	 * @return Color
	 */
	public abstract Color getColor();

	/**
	 * Returns the direction of the drawable object or null is the object has no
	 * specific orientation.
	 * 
	 * @return Direction
	 */
	public abstract Direction getDirection();

}
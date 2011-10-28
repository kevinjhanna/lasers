package tiles;

import java.awt.Color;

import misc.Direction;

/**
 * Interface that represents a layer that is part of a drawable object.
 * 
 * @see Drawable
 */
public interface DrawableLayer {

	/**
	 * Returns the layer color.
	 * 
	 * @return Color
	 */
	public Color getColor();

	/**
	 * Returns the layer direction.
	 * 
	 * @return Color
	 */
	public Direction getDirection();

}

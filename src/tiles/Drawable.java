package tiles;

import java.awt.Color;

import misc.Direction;

/**
 * Interface that represents a drawable object. Drawable objects can have many
 * underlay and overlay drawable layers.
 * 
 * @see DrawableLayer
 */
public interface Drawable extends DrawableLayer {

	public Color getColor();

	public Direction getDirection();

	public Iterable<DrawableLayer> getUnderlay();

	public Iterable<DrawableLayer> getOverlay();

}

package tiles;

import java.awt.Color;

import misc.Direction;

/**
 * Interface that represents a drawable object
 */
public interface DrawableLayer {

	public Color getColor();
	
	public Direction getDirection();
	
}

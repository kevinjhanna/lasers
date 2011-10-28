package tiles;

import java.awt.Color;

/**
 * Abtract class that represents a 
 * @author federicobond
 *
 */
public abstract class ColoredTile extends Tile {

	private Color color;
	
	protected ColoredTile(Color color) {
		super();
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}

}

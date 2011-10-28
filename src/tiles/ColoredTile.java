package tiles;

import java.awt.Color;

/**
 * Abtract class that represents a 
 * @author federicobond
 *
 */
public abstract class ColoredTile extends Tile {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4832706954888536656L;
	private Color color;
	
	protected ColoredTile(Color color) {
		super();
		this.color = color;
	}
	
	@Override
	public Color getColor() {
		return color;
	}

}

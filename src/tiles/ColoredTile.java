package tiles;

import java.awt.Color;

/**
 * An abstract class that adds color management to a bare <tt>Tile</tt>.
 * 
 * @see Tile
 */
public abstract class ColoredTile extends Tile {

	private static final long serialVersionUID = -4832706954888536656L;
	private final Color color;

	/**
	 * Initializes the tile color, which is an invariable during the game.
	 * 
	 * @param color
	 */
	protected ColoredTile(Color color) {
		super();
		this.color = color;
	}

	/**
	 * Returns the tile color
	 * 
	 * @return Color
	 */
	@Override
	public Color getColor() {
		return color;
	}

}

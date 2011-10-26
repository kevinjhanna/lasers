package tiles;

import game.TileDrawer;

import java.awt.Color;

/**
 * Abtract class that represents a 
 * @author federicobond
 *
 */
public abstract class ColoredTile extends Tile {

	private Color color;
	
	protected ColoredTile(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}
	
	public <T> T draw(TileDrawer<T> drawer) {
		return drawer.withColor(super.draw(drawer), color);
	}
}

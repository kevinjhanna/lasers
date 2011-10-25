package tiles;

import java.awt.Color;

import game.TileDrawer;

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
	
	protected Color getColor() {
		return color;
	}
	
	public <T> T draw(TileDrawer<T> drawer) {
		return drawer.withColor(super.draw(drawer), color);
	}
}

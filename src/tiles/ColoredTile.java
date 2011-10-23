package tiles;

import java.awt.Color;

import frontend.TileDrawer;

public abstract class ColoredTile extends Tile {

	protected Color color;
	
	public Color getColor() {
		return color;
	}
	
	public <T> T draw(TileDrawer<T> drawer) {
		return drawer.withColor(super.draw(drawer), color);
	}
}

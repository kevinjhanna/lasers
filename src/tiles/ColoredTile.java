package tiles;

import java.awt.Color;

import game.Ray;
import game.TileDrawer;

public abstract class ColoredTile extends Tile {
	
	protected Color color;
	
	public Color getColor() {
		return color;
	}
	
	public void react(Ray ray){
		
	}
	
	public <T> T draw(TileDrawer<T> drawer) {
		return drawer.withColor(super.draw(drawer), color);
	}
}

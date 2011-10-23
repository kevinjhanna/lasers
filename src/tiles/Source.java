package tiles;

import java.awt.Color;

import misc.Direction;
import game.TileDrawer;

public class Source extends ColoredTile {
	
	
private Direction direction;
	
	public Source(Color color, Direction direction) {
		this.color = color;
		this.direction = direction;
	}
	
	public boolean isFixed() {
		return true;
	}

	public <T> T draw(TileDrawer<T> drawer) {
		return drawer.withDirection(super.draw(drawer), direction);
	}
}

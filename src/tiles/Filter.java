package tiles;

import java.awt.Color;

import misc.Direction;
import frontend.TileDrawer;

public class Filter extends ColoredTile {
	
	public Direction direction;
	
	public Filter(Color color, Direction direction) {
		this.color = color;
		this.direction = direction;
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	public void rotate() {
		direction = direction.turn();
	}

	@Override
	public <T> T draw(TileDrawer<T> drawer) {
		return drawer.withDirection(super.draw(drawer), direction);
	}
}

package tiles;

import java.awt.Color;
import java.awt.Image;

import misc.Direction;
import frontend.ImageTileDrawer;

public class Source extends ColoredTile {
	
	private Direction direction;
	
	public Source(Color color, Direction direction) {
		this.color = color;
		this.direction = direction;
	}

	public Direction getDirection() {
		return direction;
	}
	
	public void rotate() {
		direction = direction.turn();
	}
	

	public Image draw(ImageTileDrawer drawer) {
		return drawer.withDirection(super.draw(drawer), direction);
	}
}

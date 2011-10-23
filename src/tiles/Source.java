package tiles;

import java.awt.Color;

import misc.Direction;

public class Source extends ColoredTile implements Rotatable {
	
	private Direction direction;
	
	public static final String name = "Source";

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

}

package tiles;

import java.awt.Color;
import misc.Direction;

public class Filter extends ColoredTile implements Rotatable {
	
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
	
}

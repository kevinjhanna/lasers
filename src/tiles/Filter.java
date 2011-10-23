package tiles;

import java.awt.Color;
import misc.Direction;

public class Filter extends ColoredTile implements Rotatable {
	
	public static final String name = "Filter";
	
	public Direction orientation;
	
	public Filter(Color color, Direction orientation) {
		this.color = color;
		this.orientation = orientation;
	}
	
	public Direction getOrientation() {
		return orientation;
	}
	
	public void rotate() {
		orientation = orientation.rotate(1);
	}
	
}

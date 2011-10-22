package tiles;

import java.awt.Color;

import misc.Direction;

public class Source extends ColoredTile implements Rotatable {
	
	private Direction orientation;
	
	public static final String name = "Source";

	public Source(Color color, Direction orientation) {
		this.color = color;
		this.orientation = orientation;
	}

	@Override
	public String getName() {
		return name;
	}

	public Direction getOrientation() {
		return orientation;
	}
	
	public boolean canRotate() {
		return true;
	}
	
	public void rotate() {
		orientation = orientation.rotate(1);
	}

}

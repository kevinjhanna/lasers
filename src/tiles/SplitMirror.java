package tiles;

import misc.Direction;

public class SplitMirror extends Tile implements Rotatable {

	public static final String name = "SplitMirror";
	public Direction orientation;

	public SplitMirror(Direction orientation) {
		this.orientation = orientation;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public Direction getOrientation() {
		return orientation;
	}

	@Override
	public void rotate() {
		orientation = orientation.rotate(1);
	}
}

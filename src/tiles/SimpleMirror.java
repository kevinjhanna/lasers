package tiles;

import misc.Direction;

public class SimpleMirror extends Tile implements Rotatable {
	
	public static final String name = "SimpleMirror";
	private Direction orientation;

	public SimpleMirror(Direction orientation) {
		this.orientation = orientation;
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

package tiles;

import misc.Direction;

public class DoubleMirror extends Tile implements Rotatable {

	public static final String name = "DoubleMirror";
	private Direction orientation;
	
	public DoubleMirror(Direction orientation) {
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

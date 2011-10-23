package tiles;

import misc.Direction;
import frontend.TileDrawer;

public abstract class Tile {

	public boolean isEmpty() {
		return false;
	}

	public Direction getDirection() {
		return Direction.NORTH;
	}
	
	public void rotate() throws RotationNotSupportedException {
		throw new RotationNotSupportedException();
	}
	
	public <T> T draw(TileDrawer<T> tileDrawer) {
		return tileDrawer.draw(this.getClass().getName());
	}
}

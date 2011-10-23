package tiles;

import misc.Direction;
import game.TileDrawer;

public abstract class Tile implements Drawable {

	public boolean isEmpty() {
		return false;
	}
	
	public boolean isFixed() {
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

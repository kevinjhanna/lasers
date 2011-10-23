package tiles;

import misc.Direction;
import frontend.TileDrawer;

public class DoubleMirror extends Tile {

	private Direction direction;
	
	public DoubleMirror(Direction orientation) {
		this.direction = orientation;
	}
	
	@Override
	public Direction getDirection() {
		return direction;
	}

	@Override
	public void rotate() {
		direction = direction.turn();
	}
	
	@Override
	public <T> T draw(TileDrawer<T> drawer) {
		return drawer.withDirection(super.draw(drawer), direction);
	}
}

package tiles;

import misc.Direction;
import frontend.TileDrawer;

public class SplitMirror extends Tile {

	public Direction direction;

	public SplitMirror(Direction direction) {
		this.direction = direction;
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

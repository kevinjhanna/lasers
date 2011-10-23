package tiles;

import misc.Direction;
import frontend.TileDrawer;

public class SimpleMirror extends Tile {
	
	private Direction direction;

	public SimpleMirror(Direction direction) {
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

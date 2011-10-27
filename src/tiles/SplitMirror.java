package tiles;

import game.TileDrawer;
import misc.Direction;

/**
 * Split Mirror. Supports rotation and movement
 */
public class SplitMirror extends Tile {
	
	public SplitMirror(Direction direction) {
		setDirection(direction);
	}
	
	@Override
	public <T> T draw(TileDrawer<T> drawer) {
		return drawer.withDirection(super.draw(drawer), getDirection());
	}

	@Override
	protected DirectionComponent getDirectionComponent() {
		return new TwoWayDirection(true);
	}
}

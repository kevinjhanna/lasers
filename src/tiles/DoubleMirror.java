package tiles;

import misc.Direction;
import game.TileDrawer;

/**
 * Double Mirror. Supports rotation and movement
 */
public class DoubleMirror extends Tile {

	public DoubleMirror(Direction direction) {
		setDirection(direction);
	}
	
	@Override
	public <T> T draw(TileDrawer<T> drawer) {
		return drawer.withDirection(super.draw(drawer), getDirection());
	}

	@Override
	protected RotationComponent getRotationComponent() {
		return new TwoWayRotation(true);
	}
}

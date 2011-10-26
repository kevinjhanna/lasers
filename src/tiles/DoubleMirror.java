package tiles;

import misc.Direction;
import game.TileDrawer;

/**
 * Double Mirror. Supports rotation and movement
 */
public class DoubleMirror extends Tile {

	public DoubleMirror(Direction direction) {
		setRotationComponent(new TwoWayRotation());
		setDirection(direction);
	}
	
	@Override
	public <T> T draw(TileDrawer<T> drawer) {
		return drawer.withDirection(super.draw(drawer), getDirection());
	}
}

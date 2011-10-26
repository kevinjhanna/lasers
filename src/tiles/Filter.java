package tiles;

import java.awt.Color;

import misc.Direction;
import game.TileDrawer;

/**
 * Filter. Supports rotation and movement
 * @author federicobond
 *
 */
public class Filter extends ColoredTile {
	
	protected final RotationComponent rotation = new TwoWayRotation();
	
	public Filter(Color color, Direction direction) {
		super(color);
	}

	@Override
	public <T> T draw(TileDrawer<T> drawer) {
		return drawer.withDirection(super.draw(drawer), getDirection());
	}
}

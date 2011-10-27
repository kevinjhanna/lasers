package tiles;

import game.TileDrawer;

import java.awt.Color;

import misc.Direction;

/**
 * Source. Does not support rotation or movement
 * @author federicobond
 *
 */
public class Source extends ColoredTile {	
	
	public Source(Color color, Direction direction) {
		super(color);
		setDirection(direction);
	}
	
	public boolean isFixed() {
		return true;
	}

	public <T> T draw(TileDrawer<T> drawer) {
		return drawer.withDirection(super.draw(drawer), getDirection());
	}

	@Override
	protected RotationComponent getRotationComponent() {
		return new FourWayRotation(true);
	}
}

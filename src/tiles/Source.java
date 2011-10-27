package tiles;

import game.TileDrawer;

import java.awt.Color;

import misc.Direction;

/**
 * Source. Does not support rotation or movement
 */
public class Source extends ColoredTile {

	/**
	 * Creates a new source tile
	 * 
	 * @param color
	 * @param direction
	 */
	public Source(Color color, Direction direction) {
		super(color);
		setDirection(direction);
	}

	/**
	 * Returns true because this tile is fixed in its position
	 */
	public boolean isFixed() {
		return true;
	}

	public <T> T draw(TileDrawer<T> drawer) {
		return drawer.withDirection(super.draw(drawer), getDirection());
	}

	@Override
	protected DirectionComponent getDirectionComponent() {
		return new FourWayDirection(false);
	}
}

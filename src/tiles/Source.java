package tiles;

import java.awt.Color;

import misc.Direction;

/**
 * Source. Does not support rotation or movement
 */
public class Source extends ColoredTile {

	private static final long serialVersionUID = -7755886326140761567L;

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

	@Override
	protected DirectionComponent getDirectionComponent() {
		return new FourWayDirection(false);
	}

	@Override
	protected PropagationComponent getPropagationComponent(Tile tile) {
		return new BlockPropagation(tile);
	}
}

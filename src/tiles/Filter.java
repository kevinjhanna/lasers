package tiles;

import java.awt.Color;

import misc.Direction;

/**
 * Filter. Supports rotation and movement
 */
public class Filter extends ColoredTile {

	public Filter(Color color, Direction direction) {
		super(color);
		setDirection(direction);
	}
	
	@Override
	protected DirectionComponent getDirectionComponent() {
		return new TwoWayDirection(true);
	}
	
	@Override
	protected PropagationComponent getPropagationComponent(Tile tile) {
		return new FilterPropagation(tile);
	}
	
}

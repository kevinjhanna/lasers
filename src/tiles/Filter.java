package tiles;

import java.awt.Color;

import tiles.direction.DirectionComponent;
import tiles.direction.TwoWayDirection;
import tiles.propagation.FilterPropagation;
import tiles.propagation.PropagationComponent;

import misc.Direction;

/**
 * A <tt>Filter</tt> tile that supports rotation and movement.
 */
public class Filter extends ColoredTile {

	private static final long serialVersionUID = -8077293024920731925L;

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

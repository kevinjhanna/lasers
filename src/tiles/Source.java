package tiles;

import java.awt.Color;

import tiles.direction.DirectionComponent;
import tiles.direction.FourWayDirection;
import tiles.propagation.BlockPropagation;
import tiles.propagation.PropagationComponent;

import misc.Direction;

/**
 * A <tt>MoveableSource</tt> tile that supports movement but not rotation.
 */
public class Source extends ColoredTile {

	private static final long serialVersionUID = -5430284811313280074L;

	public Source(Color color, Direction direction) {
		super(color);
		setDirection(direction);
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

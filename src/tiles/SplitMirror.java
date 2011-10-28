package tiles;

import tiles.direction.DirectionComponent;
import tiles.direction.TwoWayDirection;
import tiles.propagation.PropagationComponent;
import tiles.propagation.SplitMirrorPropagation;
import misc.Direction;

/**
 * Split Mirror. Supports rotation and movement
 */
public class SplitMirror extends Tile {

	private static final long serialVersionUID = -758475613262851807L;

	public SplitMirror(Direction direction) {
		super();
		setDirection(direction);
	}

	@Override
	protected DirectionComponent getDirectionComponent() {
		return new TwoWayDirection(true);
	}

	@Override
	protected PropagationComponent getPropagationComponent(Tile tile) {
		return new SplitMirrorPropagation(tile);
	}
}

package tiles;

import tiles.direction.DirectionComponent;
import tiles.direction.FourWayDirection;
import tiles.propagation.MirrorPropagation;
import tiles.propagation.PropagationComponent;
import misc.Direction;

/**
 * A <tt>SimpleMirror</tt> tile that supports rotation and movement.
 */
public class SimpleMirror extends Tile {

	private static final long serialVersionUID = -2552977902177287910L;

	public SimpleMirror(Direction direction) {
		setDirection(direction);
	}

	@Override
	protected DirectionComponent getDirectionComponent() {
		return new FourWayDirection(true);
	}

	@Override
	protected PropagationComponent getPropagationComponent(Tile tile) {
		return new MirrorPropagation(tile);
	}

}

package tiles;

import misc.Direction;

/**
 * Simple Mirror. Supports rotation and movement
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

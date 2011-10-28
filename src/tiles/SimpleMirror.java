package tiles;

import misc.Direction;

/**
 * Simple Mirror. Supports rotation and movement
 */
public class SimpleMirror extends Tile {
	
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

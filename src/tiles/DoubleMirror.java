package tiles;

import misc.Direction;

/**
 * Double Mirror. Supports rotation and movement
 */
public class DoubleMirror extends Tile {

	public DoubleMirror(Direction direction) {
		super();
		setDirection(direction);
	}

	@Override
	protected DirectionComponent getDirectionComponent() {
		return new TwoWayDirection(true);
	}
	
	@Override
	protected PropagationComponent getPropagationComponent(Tile tile) {
		return new DoubleMirrorPropagation(tile);
	}
	
}

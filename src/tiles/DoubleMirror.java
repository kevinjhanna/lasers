package tiles;

import tiles.direction.DirectionComponent;
import tiles.direction.TwoWayDirection;
import tiles.propagation.DoubleMirrorPropagation;
import tiles.propagation.PropagationComponent;
import misc.Direction;

/**
 * Double Mirror. Supports rotation and movement
 */
public class DoubleMirror extends Tile {

	private static final long serialVersionUID = 6426614138473635048L;

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

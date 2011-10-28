package tiles;

import tiles.direction.DirectionComponent;
import tiles.direction.NoDirection;
import tiles.propagation.IgnorePropagation;
import tiles.propagation.PropagationComponent;

/**
 * Empty tile
 */
public class EmptyTile extends Tile {

	private static final long serialVersionUID = -1389305795332202623L;

	@Override
	public boolean isEmpty() {
		return true;
	}

	@Override
	protected DirectionComponent getDirectionComponent() {
		return new NoDirection();
	}

	@Override
	protected PropagationComponent getPropagationComponent(Tile tile) {
		return new IgnorePropagation(tile);
	}
	
}

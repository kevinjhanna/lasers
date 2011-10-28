package tiles;

/**
 * Empty tile
 */
public class EmptyTile extends Tile {

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

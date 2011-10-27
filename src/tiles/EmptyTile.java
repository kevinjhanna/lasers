package tiles;


/**
 * Empty tile
 */
public class EmptyTile extends Tile {

	public boolean isEmpty() {
		return true;
	}

	@Override
	protected RotationComponent getRotationComponent() {
		return new NoRotation();
	}
	
}

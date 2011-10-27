package tiles;

import game.Ray;


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
	
	@Override
	public void hit(Ray ray) {
		ray.move();
	}
	
}

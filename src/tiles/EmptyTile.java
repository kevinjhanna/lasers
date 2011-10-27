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
	protected DirectionComponent getDirectionComponent() {
		return new NoDirection();
	}
	
	@Override
	public void hit(Ray ray) {
		ray.move();
	}
	
}

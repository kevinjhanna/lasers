package tiles;

import game.Ray;

/**
 * Empty tile
 */
public class EmptyTile extends Tile {

	protected RotationComponent rotation = new NoRotation();
	
	public boolean isEmpty() {
		return true;
	}
	
	public void react(Ray ray){
		ray.moveStraight();
		
	}
	
}

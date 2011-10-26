package tiles;

import game.Ray;

/**
 * Empty tile
 */
public class EmptyTile extends Tile {

	public boolean isEmpty() {
		return true;
	}
	
	public void react(Ray ray){
		ray.moveStraight();
		
	}
	
}

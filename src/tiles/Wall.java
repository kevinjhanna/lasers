package tiles;

import game.Ray;

/**
 * Wall tile. Does not support rotation or movement
 */
public class Wall extends Tile {
	
	public boolean isFixed() {
		return true;
	}
	
	public void react(Ray ray){
		ray.stopMovement();
	}
	
}

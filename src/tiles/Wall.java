package tiles;

import game.Ray;

public class Wall extends Tile {

	public boolean isFixed() {
		return true;
	}
	
	public void react(Ray ray){
		ray.stopMovement();
	}
	
}

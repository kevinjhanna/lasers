package tiles;

/**
 * Wall tile. Does not support rotation or movement
 */
public class Wall extends Tile {

	public boolean isFixed() {
		return true;
	}
	
}

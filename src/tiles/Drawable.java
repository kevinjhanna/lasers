package tiles;

import game.TileDrawer;

/**
 * Interface that represents a drawable tile
 */
public interface Drawable {

	public <T> T draw(TileDrawer<T> drawer);
	
}
